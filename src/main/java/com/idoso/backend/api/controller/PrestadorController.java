package com.idoso.backend.api.controller;

import com.idoso.backend.api.domain.dto.response.*;
import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.service.contracts.UsuarioService;
import com.idoso.backend.api.service.PrestadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prestador")
@RequiredArgsConstructor
@CrossOrigin
public class PrestadorController {

    private final UsuarioService usuarioService;

    private final AnuncioRepository anuncioRepository;

    private final PrestadorService prestadorService;


    @PostMapping("/candidatar/{anuncioId}")
    public ResponseEntity<?> candidatura(@RequestBody CandidaturaDTO dto, @PathVariable String anuncioId) {
        return ResponseEntity.ok(usuarioService.novaCandidatura(dto, Long.parseLong(anuncioId)));
    }

    @GetMapping("/anunciosCriados")
    public ResponseEntity<List<AnuncioCriadoDTO>> getAllAnunciosForHome() {
        List<AnuncioCriadoDTO> retorno = new ArrayList<>();
        anuncioRepository.findAll().forEach(a -> {

            String nome = a.getIdoso().getNome();

            AnuncioCriadoDTO dto =
                AnuncioCriadoDTO
                    .builder()
                    .id(a.getId())
                    .nomeIdoso(nome.concat(" ").concat(a.getIdoso().getSobrenome()))
                    .fotoIdoso("FOTO IDOSO")
                    .valor(a.getPagamentoBase().doubleValue())
                    .periodoEnum(a.getPeriodo())
                    .avaliacao(a.getUsuario().getAvaliacao())
                    .isPcd(a.getIdoso().getPcd())
                    .build();

            retorno.add(dto);
        });

         return ResponseEntity.ok(retorno);
    }

    @GetMapping("/anuncioIds")
    public ResponseEntity<List<IdsDTO>> getAllAnuncioIds() {
       List<IdsDTO> ids = new ArrayList<>();

        List<AnuncioEntity> all = anuncioRepository.findAll();

        for(int i = 0; i< all.size(); i++) {
            long posicao = i + 1;

            IdsDTO dto = IdsDTO
                .builder()
                .posicao(posicao)
                .id(all.get(i).getId())
                .build();

            ids.add(dto);
        }


        return ResponseEntity.ok(ids);
    }

    @GetMapping("/home/{idUsuario}")
    public ResponseEntity<HomePrestadorDTO> getHomePrestador(@PathVariable String idUsuario) {
        HomePrestadorDTO homeData = usuarioService.getHomePrestador(Long.parseLong(idUsuario));
        return ResponseEntity.ok(homeData);
    }

    @GetMapping("/getAceitas/{idUsuario}")
    public ResponseEntity<?> getAceitas(@PathVariable String idUsuario){
        List<AceitaPrestadorDTO> aceitas = prestadorService.getListaAceitas(Long.parseLong(idUsuario));

        return ResponseEntity.ok(aceitas);
    }

    @GetMapping("/historico/{idUsuario}")
    public ResponseEntity<List<HistoricoTrabalhosDTO>> getHistoricoTrabalhos(@PathVariable String idUsuario) {
        System.out.println(idUsuario);
        return ResponseEntity.ok(prestadorService.getHistoricoContratos(idUsuario));
    }
}
