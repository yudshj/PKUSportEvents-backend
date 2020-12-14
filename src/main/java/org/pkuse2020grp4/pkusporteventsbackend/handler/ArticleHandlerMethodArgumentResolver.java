package org.pkuse2020grp4.pkusporteventsbackend.handler;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.google.common.collect.ImmutableList;
import org.apache.coyote.Request;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class ArticleHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
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
        String token = nativeWebRequest.getHeader("token");
        Map<String, Claim> verify = JwtUtils.verify(token, jwtConfig);
        int userId = verify.get("user_id").asInt();
        System.out.println(userId);
        try {
            User currentUser = userRepository.findUserByUserId(verify.get("user_id").asInt());
            if (!currentUser.getUsername().equals(verify.get("username").asString())) {
                throw new SystemException("Token not valid!");
            }
        }
        catch (EntityNotFoundException e) {
            throw new UserNotFoundException(userId);
        }
        Article article=new Article();
        try {
            logger.info(String.format("User ID %d added article.", userId));
            Map<String, String[]> pmp = nativeWebRequest.getParameterMap();
            for (Map.Entry<String, String[]> entry :
                    pmp.entrySet()) {
                System.out.println(entry.getKey());
                String[] strs = entry.getValue();
                for (String s :
                        strs) {
                    System.out.println(s);
                }
            }
            System.out.println(methodParameter.toString());

            System.out.println(nativeWebRequest.toString());

            /*System.out.println(sb.toString());*/
            String tmp = JwtUtils.RequestToText(nativeWebRequest);
            System.out.println(tmp);

            article = JSON.parseObject(tmp, Article.class);
            System.out.println(article);
            article.setReleaseDate(new Date());
            article.setAuthorId(userId);
            Objects.requireNonNull(article.getTitle(), "文章标题不能为 null");
            Objects.requireNonNull(article.getContent(), "文章内容不能为 null");
            Objects.requireNonNull(article.getTags(), "文章标签不能为 null");
/*
        try {
            List<Integer> tagIdList = JSON.parseArray(Objects.requireNonNull(nativeWebRequest.getParameter("tag_id_list"), "文章标签不能为 null"), Integer.class);
            ImmutableList.Builder<Tag> builder = new ImmutableList.Builder<>();
            for (Integer tagId: tagIdList) {
                builder.add(tagRepository.getOne(tagId));
            }
            article.setTags(builder.build());
        }
        catch (EntityNotFoundException e) {
            throw new TagIdNotFoundException();
        }
*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return article;
    }
}
