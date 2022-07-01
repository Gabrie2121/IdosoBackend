package com.idoso.backend.api.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idoso.backend.api.domain.dto.response.ResponseError;
import com.idoso.backend.api.domain.exception.InvalidTokenException;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.TokenServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private static final String AUTH_URI = "/auth";
    private final TokenServiceImpl tokenServiceImpl;
    private final UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenServiceImpl tokenServiceImpl, UsuarioRepository usuarioRepository) {
        this.tokenServiceImpl = tokenServiceImpl;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            validateToken(request, response);
            filterChain.doFilter(request, response);

        } catch (InvalidTokenException e) {
            handleIsTokenInvalid(response, e.getMessage());
            return;
        }
    }

    private void validateToken(HttpServletRequest request, HttpServletResponse response)
            throws IOException, InvalidTokenException {

        val calledUri = request.getRequestURI();
        boolean isNotAuthenticationCall = !calledUri.equals(AUTH_URI);

        if (calledUri.startsWith("/h2-console/")) return;

        if (isNotAuthenticationCall) {
            val token = getTokenFromHeader(request);
            boolean isValid = tokenServiceImpl.validateToken(token, response);

            if (isValid) authenticate(token);
        }
    }

    private String getTokenFromHeader(HttpServletRequest req) {
        val token = req.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            log.info("Token is null");
            return null;
        }
        return token.substring(7, token.length());
    }

    private void handleIsTokenInvalid(HttpServletResponse response, String message) throws IOException {
        log.warn("This token is not valid. Reason: {}", message);

        ResponseError responseError = ResponseError.newBuilder().errorCode("400").message(message).build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseError));
    }

    private void authenticate(String token) {
        val idUsuario = tokenServiceImpl.getIdUsuario(token);
        val usuario = usuarioRepository.findByUsername(idUsuario).get();

        val authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
