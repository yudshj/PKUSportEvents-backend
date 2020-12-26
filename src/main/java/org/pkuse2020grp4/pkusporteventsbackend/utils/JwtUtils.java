package org.pkuse2020grp4.pkusporteventsbackend.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.coyote.Request;
import org.pkuse2020grp4.pkusporteventsbackend.configuation.JwtConfig;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
public class JwtUtils {
    public static String sign(User user, JwtConfig jwtConfig){
        String token = null;
        try {
            Date expire = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
            token = JWT.create()
                    .withClaim("username", user.getUsername())
                    .withClaim("user_id", user.getUserId())
                    // .withClaim("user_tags", user.getInterestTags())
                    // 这里注释掉，是否传List会拖慢速度？
                    .withExpiresAt(expire)
                    .sign(Algorithm.HMAC256(jwtConfig.getSecret()));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    public static Map<String, Claim> verify(String token, JwtConfig jwtConfig){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtConfig.getSecret())).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    public static int getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("user_id").asInt();
        } catch (Exception e) {
            return -1;
        }
    }

}