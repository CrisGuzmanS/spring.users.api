package com.tasks.tasks.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author Mahesh
 */
@Component
public class JWTTokenComponent {
    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    /**
     * Create a new token.
     *
     * @param id
     * @param subject
     * @return
     */
    public String create(Long id, String subject) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts
                .builder()
                .id(String.valueOf(id))
                .issuedAt(now)
                .subject(subject)
                .issuer(issuer)
                .signWith(getSigningKey());

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.expiration(exp);
        }

        return builder.compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String value(String jwt) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Method to validate and read the JWT
     *
     * @param jwt
     * @return
     */
    public String key(String jwt) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getId();
    }
}