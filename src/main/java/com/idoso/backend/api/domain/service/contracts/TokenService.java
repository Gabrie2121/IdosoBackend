package com.idoso.backend.api.domain.service.contracts;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.idoso.backend.api.domain.exception.InvalidTokenException;
import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateTokenJWT(Authentication authentication);

    boolean isTokenValido(String token, HttpServletResponse response) throws InvalidTokenException, IOException;

    String getIdUsuario(String token);
}
