package com.idoso.backend.api.domain.entities;

import com.idoso.backend.api.domain.enuns.SituacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.EnumType.STRING;
@Data
@Entity
@Table(name = "tb_candidaturas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidaturaPrestadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AnuncioEntity anuncio;

    @Enumerated(STRING)
    private SituacaoEnum situacao;

    @OneToOne
    private UsuarioEntity prestador;

    private BigDecimal pagamentoBase;

    @OneToMany
    private List<CursoPrestadorEntity> cursos;




}
