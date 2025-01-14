package com.korit.moa.moa.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    // 이메일 토큰 만료 시간
    @Value("${mail.auth-code-expiration-millis}")
    private int jwtEmailExpirationMs;

    public int getExpiration() {
        return jwtExpirationMs;
    }

    public JwtProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") int jwtExpirationMs) {

        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        this.jwtExpirationMs = jwtExpirationMs;
    }

    public String generateJwtToken(String userId, String nickName, String profileImage) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("nickName", nickName)
                .claim("profileImage", profileImage)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateEmailValidToken(String userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtEmailExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateEmailValidTokenFindId(String userName, String phoneNumber) {
        return Jwts.builder()
                .claim("userName", userName)
                .claim("phoneNumber", phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtEmailExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String removeBearer(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid JWT token format");
        }
        return bearerToken.substring("Bearer ".length());
    }

    public String getUserIdFromJwt(String token) {
        Claims claims = getClaims(token);

        return claims.get("userId", String.class);
    }

    public String getNickNameFromJwt(String token) {
        Claims claims = getClaims(token);

        return claims.get("nickName", String.class);
    }

    public String getUserNameFromJwt(String token) {
        Claims claims = getClaims(token);

        return claims.get("userName", String.class);
    }

    public String getPhoneNumberFromJwt(String token) {
        Claims claims = getClaims(token);

        return claims.get("phoneNumber", String.class);
    }

    public String getProfileImageFromJwt(String token) {
        Claims claims = getClaims(token);
        return claims.get("profileImage", String.class);
    }

    public boolean isValidToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        return jwtParser.parseClaimsJws(token).getBody();
    }
}