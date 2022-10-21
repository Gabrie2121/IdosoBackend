package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.EnderecoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    @Query("SELECT e FROM EnderecoEntity e WHERE e.usuario = ?1")
    Optional<EnderecoEntity> findByUsuario(UsuarioEntity usuario);
}
