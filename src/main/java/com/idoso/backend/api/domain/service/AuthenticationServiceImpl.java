package com.idoso.backend.api.domain.service;

import com.idoso.backend.api.domain.repository.UsuarioRepository;

import com.idoso.backend.api.domain.service.contracts.AuthenticationService;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class AuthenticationServiceImpl implements UserDetailsService, AuthenticationService {

    private final UsuarioRepository repository;

    public AuthenticationServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        val usuarioOptional = repository.findByUsername(username);
        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado.. ");
        }
    }
}
