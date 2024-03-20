package com.baltinfo.radius.application.configuration;

import com.baltinfo.radius.radapi.security.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfiguration {

    @Value("${rad.api.token.secret.key}")
    private String tokenSecretKey;
    @Value("${rad.api.token.expire.time}")
    private Integer tokenExpireTime;

    @Bean
    TokenService tokenService() {
        return new TokenService(tokenSecretKey, tokenExpireTime);
    }
}
