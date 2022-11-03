package com.idoso.backend.api.controller.contracts;

import com.idoso.backend.api.domain.dto.request.Credentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationController {

    @PostMapping
    ResponseEntity<?> authenticate(@RequestBody Credentials credentials);
}
