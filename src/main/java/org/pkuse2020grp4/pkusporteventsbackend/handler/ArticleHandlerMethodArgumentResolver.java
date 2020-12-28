package org.pkuse2020grp4.pkusporteventsbackend.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.google.common.collect.ImmutableList;
import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedArticle;
import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.exception.TagIdNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.exception.UserNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.interceptor.AuthenticationInterceptor;
import org.pkuse2020grp4.pkusporteventsbackend.repo.TagRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.pkuse2020grp4.pkusporteventsbackend.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.persistence.EntityNotFoundException;
import javax.transaction.SystemException;
import java.util.*;

import static org.pkuse2020grp4.pkusporteventsbackend.utils.WebUtils.checkUserIdInTokenValid;

public class ArticleHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final Logger logger = LoggerFactory.getLogger(ArticleHandlerMethodArgumentResolver.class);
    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //return methodParameter.getParameterType().equals(Article.class);
        return methodParameter.hasParameterAnnotation(CheckedArticle.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        int userId = checkUserIdInTokenValid(nativeWebRequest.getHeader("token"), jwtConfig, userRepository);

        String requestText = WebUtils.RequestToText(nativeWebRequest);

        JSONObject json = JSON.parseObject(requestText);

        Article article = new Article();

        article.setArticleId(json.getInteger("articleId"));
        article.setReleaseDate(new Date());
        article.setAuthorId(userId);
        article.setTitle(json.getString("title"));
        article.setMarkdownContent(json.getString("markdownContent"));
        article.setHtmlContent(json.getString("htmlContent"));
        article.setAbstractContent(json.getString("abstractContent"));
        if (!json.containsKey("tagIds")) {
            throw new NullPointerException("文章标签不能为 null");
        }
        JSONArray tagIds = json.getJSONArray("tagIds");
        ImmutableList.Builder<Tag> builder = new ImmutableList.Builder<Tag>();
        for (int i = 0; i < tagIds.size(); i++) {
            int tagId = tagIds.getIntValue(i);
            Tag tag = tagRepository.findTagByTagId(tagId);
            if (tag == null) throw new TagIdNotFoundException(tagId);
            builder.add(tag);
        }
        article.setTags(builder.build());

        Objects.requireNonNull(article.getTitle(), "文章标题不能为 null");
        Objects.requireNonNull(article.getMarkdownContent(), "文章markdown内容不能为 null");
        Objects.requireNonNull(article.getHtmlContent(), "文章html内容不能为 null");
        Objects.requireNonNull(article.getAbstractContent(), "文章摘要不能为 null");
        // Objects.requireNonNull(article.getTags(), "文章标签不能为 null");
        return article;
    }
}
