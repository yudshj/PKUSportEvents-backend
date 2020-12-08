package org.pkuse2020grp4.pkusporteventsbackend.handler;

import com.auth0.jwt.interfaces.Claim;
import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

public class ArticleHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    JwtConfig jwtConfig;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Article.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = nativeWebRequest.getHeader("token");
        Map<String, Claim> verify = JwtUtils.verify(token, jwtConfig);
        return new Article();
    }
}
