package com.idoso.backend.api.controller;


import com.idoso.backend.api.controller.contracts.AuthenticationController;
import com.idoso.backend.api.domain.dto.request.Credentials;
import com.idoso.backend.api.domain.dto.response.ResponseError;
import com.idoso.backend.api.domain.dto.response.TokenDto;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.properties.JwtProperties;
import com.idoso.backend.api.domain.service.TokenServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin

public class AuthenticationControllerImpl implements AuthenticationController {
    private final AuthenticationManager authenticationManager;

    @Value("${idoso.jwt.appSecret}")
    private String jwtSecret;

    @Value("${idoso.jwt.tokenExpirationTime}")
    private int jwtExpiration;


    @Override
    public ResponseEntity<?> authenticate(@RequestBody Credentials credentials) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token =  generationToken(authentication);

        try {
            return ResponseEntity.ok(TokenDto
                    .newBuilder()
                    .tipo("Bearer")
                    .expiration(Integer.toString(jwtExpiration).concat(" (seconds)"))
                    .token(token)
                    .build());

        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
    public String generationToken(Authentication authentication) {
        UsuarioEntity userPrincipal = (UsuarioEntity) authentication.getPrincipal();
        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
}
