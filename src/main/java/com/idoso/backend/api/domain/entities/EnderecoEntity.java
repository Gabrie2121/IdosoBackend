package com.idoso.backend.api.domain.entities;

import com.idoso.backend.api.domain.enuns.TipoEnderecoEnum;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "endereco")
public final class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String cep;

    private String logradouro;

    private String bairro;

    private String cidade;

    @Column(length = 2)
    private String uf;

    private String complemento;

    @Enumerated(STRING)
    private TipoEnderecoEnum tipoEndereco;
}
