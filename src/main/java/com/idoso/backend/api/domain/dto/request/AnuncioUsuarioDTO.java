package com.idoso.backend.api.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.idoso.backend.api.domain.entities.IdosoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.FrequenciaEnum;
import com.idoso.backend.api.domain.enuns.PeriodoEnum;
import com.idoso.backend.api.domain.enuns.SituacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnuncioUsuarioDTO {

    private List<Laudo> laudos = new ArrayList<>();

    private IdosoEntity idoso;

    private UsuarioEntity usuario;

    private String foto;

    private PeriodoEnum periodo;

    private String descricao;

    private FrequenciaEnum frequencia;

    @JsonProperty(value = "dInicio")
    private Date dInicio;

    @JsonProperty(value = "dFim")
    private Date dFim;

    private String horaInicio;

    private String horaFim;

    private BigDecimal pagamentoBase;

    private Boolean moraJunto;

    private SituacaoEnum situacao;
}
