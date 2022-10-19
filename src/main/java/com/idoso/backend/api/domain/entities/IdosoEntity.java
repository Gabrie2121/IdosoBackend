package com.idoso.backend.api.domain.entities;

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

    @Column(length = 50)
    private String apelido;

    private Date dataNasc;

    private String nDoc;

    private Character genero;

    private Boolean pcd;

    @ManyToOne
    private UsuarioEntity usuario;

    @OneToMany
    private List<DoencaEntity> doencas = new ArrayList<>();

    private String detalheDoenca;

    @Enumerated(STRING)
    private GrauParentescoEnum grauParentesco;
}
