package com.idoso.backend.api.handlers;

import com.idoso.backend.api.domain.dto.response.ResponseError;
import com.idoso.backend.api.domain.exception.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleBadCredentialsException(AuthenticationException exception) {
        return ResponseError
                .newBuilder()
                .errorCode("400")
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleUserNotFoundException(UsernameNotFoundException exception) {
        return ResponseError
                .newBuilder()
                .errorCode("400")
                .message(exception.getMessage().concat(" User not found"))
                .build();
    }
}
