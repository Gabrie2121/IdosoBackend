package com.idoso.backend.api.domain.entities;

import static java.util.Objects.isNull;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import com.idoso.backend.api.domain.enuns.FrequenciaEnum;
import com.idoso.backend.api.domain.enuns.PeriodoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "anuncio")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class AnuncioEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private IdosoEntity idoso;

    @ManyToOne
    private UsuarioEntity usuario;

    @Enumerated(STRING)
    private PeriodoEnum periodo;

    @Enumerated(STRING)
    private FrequenciaEnum frequencia;

    private BigDecimal pagamentBase;


    private Boolean moraJunto;

    private Boolean ativo;

    @OneToOne(mappedBy = "anuncio")
    private TrabalhoEntity trabalho;

    @PrePersist
    public void setAtivo() {
        if (isNull(ativo)) ativo = true;
    }

    @OneToMany
    private List<LaudoEntity> laudos = new ArrayList<>();
}
