package com.tour.kuma.global.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class JwtTokenProvider {
    private static final String SECRET_KEY = "mySecretKey";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public String createToken(Long id,String email, String socialType) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(Long.toString(id))
                .claim("email",email)
                .claim("socialType", socialType)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // 토큰에서 id 추출
    public Long extractUserId(String token) {
        Claims claims = parseToken(token);
        return Long.parseLong(claims.getSubject());
    }

    // 토큰에서 email 추출
    public String extractEmail(String token) {
        Claims claims = parseToken(token);
        return (String) claims.get("email");
    }

    // 토큰에서 socialType 추출
    public String extractSocialType(String token) {
        Claims claims = parseToken(token);
        return (String) claims.get("socialType");
    }

}
