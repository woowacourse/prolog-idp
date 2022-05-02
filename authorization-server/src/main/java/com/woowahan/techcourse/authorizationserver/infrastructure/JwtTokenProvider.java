package com.woowahan.techcourse.authorizationserver.infrastructure;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.woowahan.techcourse.authorizationserver.domain.Account;
import com.woowahan.techcourse.authorizationserver.domain.Registration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    public static final String TOKEN_ISSUER = "https://account.techcourse.co.kr/auth";

    @Value("${provider.id-token.secret}")
    private String idTokenSecret;
    @Value("${provider.id-token.expire-length}")
    private long idTokenExpireLength;
    @Value("${provider.access-token.expire-length}")
    private long accessTokenExpireLength;

    public String createIdToken(Account account) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + idTokenExpireLength);
        Map<String, Object> userInfoClaims = new HashMap<>();
        userInfoClaims.put("sub", account.getId().toString());
        userInfoClaims.put("name", account.getName());
        userInfoClaims.put("nickname", account.getNickname());
        userInfoClaims.put("picture", account.getPicture());
        userInfoClaims.put("email", account.getEmail());
        userInfoClaims.put("phone-number", account.getPhoneNumber());

        return Jwts.builder()
                .setClaims(userInfoClaims)
                .setIssuer(TOKEN_ISSUER)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, idTokenSecret)
                .compact();
    }

    public String createAccessToken(Registration registration, Account account) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenExpireLength);

        return Jwts.builder()
                .setSubject(account.getId().toString())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, registration.getClientSecret())
                .compact();
    }

    public boolean validateToken(String idTokenSecret, String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(idTokenSecret).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractSubject(String token) {
        return Jwts.parser().setSigningKey(idTokenSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
