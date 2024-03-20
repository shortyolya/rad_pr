package com.baltinfo.radius.radapi.security;

import com.baltinfo.radius.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

import static java.util.Objects.requireNonNull;

/**
 * @author Igor Lapenok
 * @since 13.12.2021
 */
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    private final String secretKey;
    private final Integer expireTimeInSeconds;

    public TokenService(String secretKey, Integer expireTimeInSeconds) {
        this.secretKey = requireNonNull(secretKey, "Token secret key cannot be null");
        this.expireTimeInSeconds = requireNonNull(expireTimeInSeconds, "Token expire time cannot be null.");
    }

    public Result<String, String> getOpenPartToken() {
        try {
            return generateToken(new Token());
        } catch (Exception e) {
            logger.warn("Can't get user token", e);
            return Result.error("Не удалось получить токен пользователя");
        }
    }

    public Result<String, String> generateToken(Token subject) {
        try {
            ZonedDateTime expiresAt = ZonedDateTime.now().plusSeconds(expireTimeInSeconds);
            ObjectMapper objectMapper = new ObjectMapper();
            String token = objectMapper.writeValueAsString(subject);
            return Result.ok(generate(token, Date.from(expiresAt.toInstant())));
        } catch (Exception e) {
            logger.warn("Can't generate token", e);
            return Result.error("Generate token error: " + e.getMessage());
        }
    }

    public Result<String, String> generateToken(String subjectJson) {
        try {
            ZonedDateTime expiresAt = ZonedDateTime.now().plusSeconds(expireTimeInSeconds);
            return Result.ok(generate(subjectJson, Date.from(expiresAt.toInstant())));
        } catch (Exception e) {
            logger.warn("Can't generate token", e);
            return Result.error("Generate token error: " + e.getMessage());
        }
    }

    private String generate(String subject, Date expirationTime) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(secretKey.getBytes(), signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(subject)
                .setExpiration(expirationTime)
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
}
