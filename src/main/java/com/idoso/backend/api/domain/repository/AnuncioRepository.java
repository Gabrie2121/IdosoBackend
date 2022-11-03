package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.dto.request.Laudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<AnuncioEntity, Long> {

    List<AnuncioEntity> findByUsuarioId(Long usuarioId);

}
