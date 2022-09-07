package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.TrabalhoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabalhoRepository extends JpaRepository<TrabalhoEntity, Long> {
    @Query("select t from TrabalhoEntity t where t.prestador = ?1")
    List<TrabalhoEntity> findByPrestador(UsuarioEntity prestador);
}
