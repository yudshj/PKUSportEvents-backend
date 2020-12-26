package org.pkuse2020grp4.pkusporteventsbackend.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedArticle;
import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedUserId;
import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.exception.TagIdNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.repo.TagRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.pkuse2020grp4.pkusporteventsbackend.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Date;
import java.util.Objects;

import static org.pkuse2020grp4.pkusporteventsbackend.utils.WebUtils.checkUserIdInTokenValid;

public class UserIdHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final Logger logger = LoggerFactory.getLogger(UserIdHandlerMethodArgumentResolver.class);
    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //return methodParameter.getParameterType().equals(Article.class);
        return methodParameter.hasParameterAnnotation(CheckedUserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        int userId = checkUserIdInTokenValid(nativeWebRequest.getHeader("token"), jwtConfig, userRepository);
        logger.info(String.format("UserId: %d got user info.", userId));
        return userId;
    }
}
