package com.idoso.backend.api.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.idoso.backend.api.domain.enuns.GrauParentescoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "idoso")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class IdosoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nome;

    private String sobrenome;

    private Date dataNasc;

    private String idade;

    @JsonProperty("cpf")
    private String nDoc;

    private Character genero;

    private Boolean pcd;

    private Boolean disturbio;

    @OneToOne
    private UsuarioEntity usuario;

    @OneToMany
    private List<DoencaEntity> doencas = new ArrayList<>();

    @Enumerated(STRING)
    @JsonProperty("grauParentesco")
    private GrauParentescoEnum grauParentesco;

//    @OneToOne(mappedBy = "idoso")
//    private AnuncioEntity anuncio;

    @OneToOne(mappedBy = "idoso")
    private EnderecoEntity endereco;
}
