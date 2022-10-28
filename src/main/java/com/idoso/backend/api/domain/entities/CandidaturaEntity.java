package com.idoso.backend.api.domain.entities;

import com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum.ABERTA;
import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "tb_candidaturas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidaturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AnuncioEntity anuncio;

    @OneToOne
    private UsuarioEntity prestador;

    @Enumerated(STRING)
    private StatusCandidaturaEnum status = ABERTA;
}
