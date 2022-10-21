package com.idoso.backend.api.domain.dto.request;

import com.idoso.backend.api.domain.entities.IdosoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.FrequenciaEnum;
import com.idoso.backend.api.domain.enuns.PeriodoEnum;
import com.idoso.backend.api.domain.enuns.SituacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnuncioDTO {

    private List<Laudo> laudos = new ArrayList<>();
    private IdosoEntity idoso;
    private UsuarioEntity usuario;

    private String foto;

    private PeriodoEnum periodo;

    private String descricao;

    private FrequenciaEnum frequencia;

    private String horaInicio;

    private String horaFim;

    private BigDecimal pagamentoBase;

    private Boolean moraJunto;

    private SituacaoEnum situacao;
}
