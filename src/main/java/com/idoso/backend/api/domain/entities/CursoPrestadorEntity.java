package com.idoso.backend.api.domain.entities;

import com.idoso.backend.api.domain.enuns.SituacaoCursoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_cursos")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoPrestadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private SituacaoCursoEnum situacao;

    @Transient
    private String certificado;


}
