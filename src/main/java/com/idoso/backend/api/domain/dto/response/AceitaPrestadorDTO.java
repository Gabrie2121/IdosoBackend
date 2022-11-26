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
public class AceitaPrestadorDTO {

    private String fotoIdoso;

    private Double avaliacao;

    private String cidade;

    private String biografia;

    private String nome;

    private Double valorHora;
}
