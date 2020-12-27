package org.pkuse2020grp4.pkusporteventsbackend.filter;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.pkuse2020grp4.pkusporteventsbackend.dto.UserDTO;
import org.pkuse2020grp4.pkusporteventsbackend.exception.NoTokenException;
import org.pkuse2020grp4.pkusporteventsbackend.perm.perm;
import org.pkuse2020grp4.pkusporteventsbackend.service.UserService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    private UserService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            httpServletResponse.setHeader("Access-Control-Allow-Methods", httpServletRequest.getMethod());
            httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestAPI = getPathWithinApplication(request);

        String token = httpServletRequest.getHeader("token");
        if(token == null){
            throw new NoTokenException("没有在Header中发现`token`.");
        }

        UserDTO user = userService.getUserDTOByUserId(JwtUtils.getUserId(token));
        List<String> list = perm.permAPI.get(user.getPermission());
        Set<String> permAPIs = new HashSet<>(list);
        for(String api: permAPIs){
            if(requestAPI.startsWith(api))
                return true;
        }
        throw new AuthenticationException("Permission denied!");
    }

}
