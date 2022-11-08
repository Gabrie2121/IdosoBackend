package com.idoso.backend.api.controller;


import com.idoso.backend.api.controller.contracts.AuthenticationController;
import com.idoso.backend.api.domain.dto.request.Credentials;
import com.idoso.backend.api.domain.dto.response.TokenDto;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin

public class AuthenticationControllerImpl implements AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final TokenService authenticationService;

    @Value("${idoso.jwt.tokenExpirationTime}")
    private Long expiration;


    @PostMapping
    public ResponseEntity<?> authenticate(
            @RequestBody Credentials credentials
    ) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());

        try {
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String[] tokenData = authenticationService.generateTokenJWT(authenticate);

            return ResponseEntity.ok(new TokenDto(tokenData[1], "Bearer", tokenData[0], Long.toString(expiration), tokenData[2]));
        } catch (AuthenticationException e) {
            log.info("Ocorreu um erro interno:");
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
