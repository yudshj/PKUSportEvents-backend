package org.pkuse2020grp4.pkusporteventsbackend.configuation;

import org.pkuse2020grp4.pkusporteventsbackend.handler.ApplyFormHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.handler.ArticleHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.handler.TagIdListHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.handler.UserIdHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/register");
    }

    @Bean
    public AuthenticationInterceptor createAuthenticationInterceptor(){
        return new AuthenticationInterceptor();
    }

    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.addAllowedOrigin("http://127.0.0.1:8080");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }

    @Bean
    ArticleHandlerMethodArgumentResolver createArticleHandlerMethodArgumentResolver(){
        return new ArticleHandlerMethodArgumentResolver();
    }

    @Bean
    TagIdListHandlerMethodArgumentResolver createTagIdListHandlerMethodArgumentResolver(){
        return new TagIdListHandlerMethodArgumentResolver();
    }

    @Bean
    UserIdHandlerMethodArgumentResolver createUserIdListHandlerMethodArgumentResolver(){
        return new UserIdHandlerMethodArgumentResolver();
    }

    @Bean
    ApplyFormHandlerMethodArgumentResolver createApplyFormListHandlerMethodArgumentResolver(){
        return new ApplyFormHandlerMethodArgumentResolver();
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(createArticleHandlerMethodArgumentResolver());
        argumentResolvers.add(createTagIdListHandlerMethodArgumentResolver());
        argumentResolvers.add(createUserIdListHandlerMethodArgumentResolver());
        argumentResolvers.add(createApplyFormListHandlerMethodArgumentResolver());
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedOrigins("http://127.0.0.1:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }


}