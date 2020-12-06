package org.pkuse2020grp4.pkusporteventsbackend.configuation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;
}
