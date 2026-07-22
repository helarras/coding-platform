package com.helarras.codingplatform.auth.internal;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private final String secretKey;


    public JwtService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public String generateToken(String email) {

        Map<String, Object> claims = new HashMap<>();

        var currentDate = new Date(System.currentTimeMillis());
        var exp = new Date(currentDate.getTime() + TimeUnit.MINUTES.toMillis(15));

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(email)
                .issuedAt(currentDate)
                .expiration(exp)
                .and()
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
