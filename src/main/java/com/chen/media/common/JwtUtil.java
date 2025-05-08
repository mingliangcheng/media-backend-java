package com.chen.media.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // 密钥（用于签名和验证）
    private static final SecretKey secretKey = Keys.hmacShaKeyFor("sajdsadiasdias".getBytes());

    // 过期时间（单位：毫秒，例如 1 小时）
    private static final long EXPIRATION_TIME = 3600000;

    /**
     * 生成 JWT Token
     */
    public String generateToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject("LOGIN_USER")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000 * 24 * 365L))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims parseToken(String token) {

        if (token == null) {
//            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (ExpiredJwtException e) {
//            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
//            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

    /**
     * 校验 Token 是否有效
     */
    public boolean validateToken(String token) {
        try {
            parseToken(token); // 如果解析成功，则 Token 有效
            return true;
        } catch (Exception e) {
            return false; // 如果解析失败，则 Token 无效
        }
    }


}
