package org.pkuse2020grp4.pkusporteventsbackend.utils;

import com.auth0.jwt.interfaces.Claim;
import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.exception.UserNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import java.io.BufferedReader;
import java.util.Map;

public class WebUtils {
    public static String RequestToText(NativeWebRequest nativeWebRequest) throws Exception
    {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // 把reqeust的body读取到StringBuilder
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int rd;
        while((rd = reader.read(buf)) != -1){
            sb.append(buf, 0, rd);
        }
        return sb.toString();
    }


    public static int checkUserIdInTokenValid(String token, JwtConfig jwtConfig, UserRepository userRepository) throws SystemException, UserNotFoundException {
        Map<String, Claim> verify = JwtUtils.verify(token, jwtConfig);
        int userId = verify.get("user_id").asInt();
        try {
            User currentUser = userRepository.findUserByUserId(verify.get("user_id").asInt());
            if (currentUser.getUserId() != verify.get("user_id").asInt()) {
                throw new SystemException("Token not valid!");
            }
        }
        catch (EntityNotFoundException e) {
            throw new UserNotFoundException(userId);
        }
        return userId;
    }
}
