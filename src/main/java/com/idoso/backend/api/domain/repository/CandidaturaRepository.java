package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.CandidaturaEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CandidaturaRepository extends JpaRepository<CandidaturaEntity, Long> {

    @Query("SELECT c FROM CandidaturaEntity c WHERE c.anuncio.usuario = ?1")
    List<CandidaturaEntity> candidaturasByUser(UsuarioEntity usuario);

    @Query("SELECT c FROM CandidaturaEntity c WHERE c.anuncio = ?1")
    List<CandidaturaEntity> candidaturasByAnuncio(AnuncioEntity anuncio);

    @Modifying
    @Query("UPDATE CandidaturaEntity set status = :status WHERE id = :candidaturaId")
    void updateCandidatura(StatusCandidaturaEnum status, Long candidaturaId);

    @Query("SELECT c FROM CandidaturaEntity c WHERE c.prestador = ?1")
    List<CandidaturaEntity> candidaturasDoPrestador(UsuarioEntity prestador);

    @Query("SELECT c FROM CandidaturaEntity c WHERE c.anuncio.dtFim < :today AND c.prestador = :prestador")
    List<CandidaturaEntity> candidaturasVencidas(@Param("today") LocalDate today,@Param("prestador") UsuarioEntity prestador);


}
