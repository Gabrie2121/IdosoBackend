package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.entities.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
@Slf4j
public class TokenService {
    @Value("{idoso.jwt.appSecret}")
    private String secret;

    @Value("${idoso.jwt.tokenExpirationTime}")
    private Long expiration;

    public String[] generateTokenJWT(Authentication authentication) {
        UsuarioEntity user = (UsuarioEntity) authentication.getPrincipal();
        Date today = new Date();
        Date expirationTime = Date.from(Instant.now().plusSeconds(expiration));

        String token = Jwts.builder()
                .setIssuer("My API")
                .setSubject(user.getUsername())
                .setClaims(new HashMap<>())
                .setIssuedAt(today)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();


        return new String[]{token, Long.toString(user.getId())};
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.info("Erro ao tentar fazer o parse do token:");
            log.info(e.getMessage());
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getId());
    }

}
