package org.pkuse2020grp4.pkusporteventsbackend.configuation;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.pkuse2020grp4.pkusporteventsbackend.handler.ApplyFormHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.handler.ArticleHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.handler.TagIdListHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.handler.UserIdHandlerMethodArgumentResolver;
import org.pkuse2020grp4.pkusporteventsbackend.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        converters.add(converter);

        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //5.将convert添加到converters当中.
        converters.add(fastJsonHttpMessageConverter);

    }
}