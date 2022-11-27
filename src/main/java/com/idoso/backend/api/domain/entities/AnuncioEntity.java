package com.idoso.backend.api.domain.entities;

import com.idoso.backend.api.domain.dto.request.Laudo;
import com.idoso.backend.api.domain.enuns.FrequenciaEnum;
import com.idoso.backend.api.domain.enuns.PeriodoEnum;
import com.idoso.backend.api.domain.enuns.SituacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_anuncios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class AnuncioEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    private IdosoEntity idoso;
    @ManyToOne
    private UsuarioEntity usuario;

    @Enumerated(STRING)
    private PeriodoEnum periodo;

    private String descricao;

    @Enumerated(STRING)
    private FrequenciaEnum frequencia;

    private String horaInicio;

    private String horaFim;

    private BigDecimal pagamentoBase;

    private Boolean moraJunto;

    private SituacaoEnum situacaoEnum;

    @OneToMany(mappedBy = "anuncio")
    private List<CandidaturaEntity> candidaturas;

    private LocalDate dtInicio;

    private LocalDate dtFim;
}
