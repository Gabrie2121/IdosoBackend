package com.idoso.backend.api.domain.dto.response;

import com.idoso.backend.api.domain.entities.EnderecoEntity;
import com.idoso.backend.api.domain.enuns.TipoPessoaEnum;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UsuarioResponseDTO {

    private String email;

    private String senha;

    private String nome;

    private String sobrenome;

    private String nDoc;

    private String celular;

    private Character genero;

    private Date dataNasc;

    private TipoPessoaEnum tipoPessoa;

    private EnderecoDTO endereco;
}
