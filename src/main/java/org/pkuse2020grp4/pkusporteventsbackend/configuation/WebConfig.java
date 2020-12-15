package org.pkuse2020grp4.pkusporteventsbackend.configuation;

import org.pkuse2020grp4.pkusporteventsbackend.handler.ArticleHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.handler.TagIdListHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(createAuthenticationInterceptor())
                .addPathPatterns("/api/article/**");
    }

    @Bean
    public AuthenticationInterceptor createAuthenticationInterceptor(){
        return new AuthenticationInterceptor();
    }

    @Bean
    ArticleHandlerMethodArgumentResolver createArticleHandlerMethodArgumentResolver(){
        return new ArticleHandlerMethodArgumentResolver();
    }

    @Bean
    TagIdListHandlerMethodArgumentResolver createTagIdListHandlerMethodArgumentResolver(){
        return new TagIdListHandlerMethodArgumentResolver();
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(createArticleHandlerMethodArgumentResolver());
        argumentResolvers.add(createTagIdListHandlerMethodArgumentResolver());
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600);
    }


}