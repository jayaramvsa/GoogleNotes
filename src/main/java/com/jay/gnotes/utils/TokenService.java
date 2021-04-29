package com.jay.gnotes.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;

@Component
public class TokenService {

    @Value("${secret.token}")
    public String TOKEN_SECRET;

    public String createToken(Long id) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            JwtBuilder jwtBuilder = Jwts.builder().setId(String.valueOf(id)).signWith(signatureAlgorithm, DatatypeConverter.parseString(TOKEN_SECRET));
            return jwtBuilder.compact();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Long decodeToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseString(token)).parseClaimsJws(token).getBody();
            return Long.parseLong(claims.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (long) 0;
    }
}
