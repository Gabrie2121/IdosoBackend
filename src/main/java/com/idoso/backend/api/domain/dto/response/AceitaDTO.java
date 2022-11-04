package com.idoso.backend.api.domain.dto.response;

import com.idoso.backend.api.domain.enuns.CursoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AceitaDTO {

    private String fotoPrestador;

    private Double avaliacao;

    private String cidade;

    private String biografia;

    private List<String> certificados;

    private String nome;

    private Boolean formado;

    private CursoEnum curso;

    private Double valorHora;

}
