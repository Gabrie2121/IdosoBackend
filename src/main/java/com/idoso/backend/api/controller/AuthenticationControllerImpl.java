package com.idoso.backend.api.controller;


import com.idoso.backend.api.controller.contracts.AuthenticationController;
import com.idoso.backend.api.domain.dto.request.Credentials;
import com.idoso.backend.api.domain.dto.response.ResponseError;
import com.idoso.backend.api.domain.dto.response.TokenDto;
import com.idoso.backend.api.domain.properties.JwtProperties;
import com.idoso.backend.api.domain.service.TokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin

public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenServiceImpl authenticationService;

    private final JwtProperties properties;

    public AuthenticationControllerImpl(
            AuthenticationManager authenticationManager,
            TokenServiceImpl authenticationService,
            JwtProperties jwtProperties
    ) {

        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.properties = jwtProperties;
    }

    @Override
    public ResponseEntity<?> authenticate(@RequestBody Credentials credentials) {

        val usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());

        try {
            val authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            val token = authenticationService.generateTokenJWT(authenticate);
            return ResponseEntity.ok(TokenDto
                    .newBuilder()
                    .tipo("Bearer")
                    .expiration(properties.getTokenExpirationTime().concat(" (seconds)"))
                    .token(token)
                    .build());

        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
