package com.idoso.backend.api.domain.repository;

import java.util.Optional;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.username = ?1")
    Optional<UsuarioEntity> buscarPorUserName(String userName);


}
