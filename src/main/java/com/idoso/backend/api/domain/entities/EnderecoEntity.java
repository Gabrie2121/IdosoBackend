package com.idoso.backend.api.domain.entities;

import com.idoso.backend.api.domain.enuns.TipoEnderecoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_enderecos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public final class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String cep;

    private String logradouro;

    private String cidade;

    @Column(length = 2)
    private String uf;

    private String complemento;

    private String apelido;

    private Boolean principal;

    @OneToOne
    private UsuarioEntity usuario;

    @OneToOne
    private IdosoEntity idoso;

}
