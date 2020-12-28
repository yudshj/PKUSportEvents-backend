package org.pkuse2020grp4.pkusporteventsbackend.interceptor;

import com.auth0.jwt.interfaces.Claim;
import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.dto.UserDTO;
import org.pkuse2020grp4.pkusporteventsbackend.perm.Perms;
import org.pkuse2020grp4.pkusporteventsbackend.service.UserService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ArticleInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(ArticleInterceptor.class);

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token = request.getHeader("token");

        Map<String, Claim> verify =  JwtUtils.verify(token, jwtConfig);

        UserDTO userDTO = userService.getUserDTOByUserId(verify.get("user_id").asInt());

        return userDTO.getPermission() <= Perms.PUBLISHER;
    }
}
