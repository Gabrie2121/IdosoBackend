package com.idoso.backend.api.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "trabalho")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class TrabalhoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Date dataInicio;

    private Date dataFim;

    @OneToOne
    private UsuarioEntity prestador;

    @OneToOne
    private UsuarioEntity idoso;

    @OneToOne
    private AnuncioEntity anuncio;

}
