package org.pkuse2020grp4.pkusporteventsbackend.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedApplyForm;
import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedUserId;
import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.dto.ApplyFormDTO;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.exception.TagIdNotFoundException;
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

public class ApplyFormHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final Logger logger = LoggerFactory.getLogger(ApplyFormHandlerMethodArgumentResolver.class);
    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //return methodParameter.getParameterType().equals(Article.class);
        return methodParameter.hasParameterAnnotation(CheckedApplyForm.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        int userId = checkUserIdInTokenValid(nativeWebRequest.getHeader("token"), jwtConfig, userRepository);

        String requestText = WebUtils.RequestToText(nativeWebRequest);

        JSONObject json = JSON.parseObject(requestText);
        int perm = json.getInteger("permission");
        if (perm < 1 || perm > 10) {
            throw new Exception("Permission not valid (1 to 10)");
        }

        ApplyFormDTO applyFormDTO = new ApplyFormDTO();

        applyFormDTO.setApplyDate(new Date());
        applyFormDTO.setPermission(perm);
        applyFormDTO.setUserId(userId);
        return applyFormDTO;
    }
}
