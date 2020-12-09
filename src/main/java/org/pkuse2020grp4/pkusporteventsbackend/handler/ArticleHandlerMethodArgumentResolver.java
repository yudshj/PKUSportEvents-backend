package org.pkuse2020grp4.pkusporteventsbackend.handler;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.google.common.collect.ImmutableList;
import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.exception.TagIdNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.repo.ArticleRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.TagRepository;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.persistence.EntityNotFoundException;
import java.util.*;

public class ArticleHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    TagRepository tagRepository;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Article.class);
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = nativeWebRequest.getHeader("token");
        Map<String, Claim> verify = JwtUtils.verify(token, jwtConfig);
        Article article = new Article();
        article.setReleaseDate(new Date());
        article.setAuthorId(verify.get("user_id").asInt());
        article.setTitle(Objects.requireNonNull(nativeWebRequest.getParameter("title"), "文章标题不能为 null"));
        article.setContent(Objects.requireNonNull(nativeWebRequest.getParameter("content"), "文章内容不能为 null"));
        try {
            List<Integer> tagIdList = JSON.parseArray(Objects.requireNonNull(nativeWebRequest.getParameter("tag_id_list"), "文章标签不能为 null"), Integer.class);
            ImmutableList.Builder<Tag> builder = new ImmutableList.Builder<Tag>();
            for (Integer tagId: tagIdList) {
                builder.add(tagRepository.getOne(tagId));
            }
            article.setTags(builder.build());
        }
        catch (EntityNotFoundException e) {
            throw new TagIdNotFoundException();
        }
        return article;
    }
}
