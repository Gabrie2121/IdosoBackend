package com.idoso.backend.api.domain.repository;

import java.util.Optional;

import com.idoso.backend.api.domain.entities.EnderecoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.TipoPessoaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.username = ?1")
    Optional<UsuarioEntity> buscarPorUserName(String userName);

    @Query("SELECT u FROM UsuarioEntity u where u.tipoPessoa = ?1")
    List<UsuarioEntity> buscarPorTipoPessoa(TipoPessoaEnum tipo);

    @Modifying
    @Query("UPDATE UsuarioEntity u set u.nome = ?1, u.sobrenome = ?2, u.nDoc = ?3, u.celular = ?4, u.dataNasc = ?5, u.genero = ?6 where u.id = ?7")
    void updateUsuario(String nome, String sobrenome, String nDoc, String celular, Date dataNasc, Character genero,Long idUsuario);

}
