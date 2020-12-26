package org.pkuse2020grp4.pkusporteventsbackend.filter;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.pkuse2020grp4.pkusporteventsbackend.dto.UserDTO;
import org.pkuse2020grp4.pkusporteventsbackend.exception.NoTokenException;
import org.pkuse2020grp4.pkusporteventsbackend.perm.perm;
import org.pkuse2020grp4.pkusporteventsbackend.service.UserService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
