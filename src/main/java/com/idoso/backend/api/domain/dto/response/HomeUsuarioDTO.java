package com.idoso.backend.api.domain.dto.response;

import com.idoso.backend.api.domain.dto.request.AnuncioPrestadorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeUsuarioDTO {

    private String foto;

    private Double avaliacao;

    private String cidade;

    private String biografia;

    private List<AnuncioPrestadorDTO> anunciosAberto;

    private String nome;


}
