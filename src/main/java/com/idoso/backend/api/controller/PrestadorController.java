package com.idoso.backend.api.controller;

import com.idoso.backend.api.domain.dto.response.AnuncioCriadoDTO;
import com.idoso.backend.api.domain.dto.response.CandidaturaDTO;
import com.idoso.backend.api.domain.dto.response.HomePrestadorDTO;
import com.idoso.backend.api.domain.entities.CandidaturaEntity;
import com.idoso.backend.api.domain.repository.AnuncioRepository;
import com.idoso.backend.api.domain.repository.CandidaturaRepository;
import com.idoso.backend.api.domain.service.contracts.UsuarioService;
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

    private final CandidaturaRepository candidaturaRepository;

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

    @GetMapping("/candidaturaIds")
    public ResponseEntity<List<Long>> getAllCandidaturaIds() {
       List<Long> ids = new ArrayList<>();

        List<CandidaturaEntity> all = candidaturaRepository.findAll();
        all.forEach(c -> ids.add(c.getId()) );


        return ResponseEntity.ok(ids);
    }

    @GetMapping("/home/{idUsuario}")
    public ResponseEntity<HomePrestadorDTO> getHomePrestador(@PathVariable String idUsuario) {
        HomePrestadorDTO homeData = usuarioService.getHomePrestador(Long.parseLong(idUsuario));
        return ResponseEntity.ok(homeData);
    }
}
