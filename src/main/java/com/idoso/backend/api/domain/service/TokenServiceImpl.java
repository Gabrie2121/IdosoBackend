package com.idoso.backend.api.domain.service;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.exception.InvalidTokenException;
import com.idoso.backend.api.domain.properties.JwtProperties;
import com.idoso.backend.api.domain.service.contracts.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public final class TokenServiceImpl implements TokenService {

    private final JwtProperties jwtProperties;

    public TokenServiceImpl(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public String generateTokenJWT(Authentication authentication) {
        val user = (UsuarioEntity) authentication.getPrincipal();
        val today = new Date();
        val expirationTime = Date.from(Instant.now().plusSeconds(Long.parseLong(jwtProperties.getTokenExpirationTime())));

        return Jwts.builder()
                .setIssuer("Idoso API")
                .setSubject(user.getUsername())
                .setIssuedAt(today)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getAppSecret())
                .compact();
    }

    @Override
    public boolean validateToken(String token, HttpServletResponse response) throws IOException, InvalidTokenException {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getAppSecret()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage());
        }
    }

    @Override
    public String getIdUsuario(String token) {
        val claims = Jwts.parser().setSigningKey(jwtProperties.getAppSecret()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
