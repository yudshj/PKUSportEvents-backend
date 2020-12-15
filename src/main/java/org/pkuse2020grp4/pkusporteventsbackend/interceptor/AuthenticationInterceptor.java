package org.pkuse2020grp4.pkusporteventsbackend.interceptor;
import com.auth0.jwt.interfaces.Claim;
import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.exception.NoTokenException;
import org.pkuse2020grp4.pkusporteventsbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import java.util.Map;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Autowired
    JwtConfig jwtConfig;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        if (token == null){
            throw new NoTokenException("没有在Header中发现`token`.");
        }

        // logger.info("认证 {}", token);
        Map<String, Claim> verify = JwtUtils.verify(token, jwtConfig);
        return true;
    }
}