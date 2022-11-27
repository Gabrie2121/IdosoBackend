package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.dto.request.Laudo;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<AnuncioEntity, Long> {

    List<AnuncioEntity> findByUsuarioId(Long usuarioId);
    @Query("SELECT a FROM AnuncioEntity a WHERE a.dtFim < :today AND a.usuario = :parente")
    List<AnuncioEntity> anunciosVencidos(LocalDate today, UsuarioEntity parente);

    @Modifying
    @Query("UPDATE AnuncioEntity a SET a.nomePrestador = :nomePrestador WHERE a.id = :idAnuncio")
    void addNomePrestador(String nomePrestador, Long idAnuncio);

}
