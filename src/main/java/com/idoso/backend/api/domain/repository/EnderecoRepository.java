package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.EnderecoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    @Query("SELECT e FROM EnderecoEntity e WHERE e.usuario = ?1")
    Optional<EnderecoEntity> findByUsuario(UsuarioEntity usuario);

    @Modifying  
    @Query("UPDATE EnderecoEntity e set e.cep = ?1, e.logradouro = ?2, e.cidade = ?3," + 
        " e.uf = ?4, e.apelido = ?5, e.principal = ?6, e.complemento = ?7 where e.id = ?8")
    void atualizaEndereco(String cep, String logradouro, String cidade, String uf, String apelido,Boolean principal, String complemento, Long id);
}
