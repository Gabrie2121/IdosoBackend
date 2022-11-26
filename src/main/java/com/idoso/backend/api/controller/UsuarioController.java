package com.idoso.backend.api.controller;

import com.idoso.backend.api.domain.dto.response.*;
import com.idoso.backend.api.domain.entities.CandidaturaEntity;
import com.idoso.backend.api.domain.entities.EnderecoEntity;
import com.idoso.backend.api.domain.entities.IdosoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.StatusCandidaturaEnum;
import com.idoso.backend.api.domain.repository.CandidaturaRepository;
import com.idoso.backend.api.domain.repository.UsuarioRepository;
import com.idoso.backend.api.domain.service.contracts.IdosoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/idoso")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    private final IdosoService idosoService;

    private final CandidaturaRepository candidaturaRepository;

    private final PasswordEncoder encoder;

    @GetMapping("/all")
    public List<UsuarioEntity> getAll() {
        return usuarioRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UsuarioEntity> delete(@PathVariable(value = "id") long id) {
        Optional<UsuarioEntity> cadastroPF = usuarioRepository.findById(id);
        if (cadastroPF.isPresent()) {
            usuarioRepository.delete(cadastroPF.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getById/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable("idUsuario") String idUsuario) {
        UsuarioEntity usuario = usuarioRepository.findById(Long.parseLong(idUsuario))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado id " + idUsuario));


        EnderecoEntity endereco = usuario.getEndereco();
        UsuarioResponseDTO usuarioByIdDto = UsuarioResponseDTO
                .builder()
                .nome(usuario.getNome())
                .sobrenome(usuario.getSobrenome())
                .email(usuario.getEmail())
                .senha(usuario.getPassword())
                .nDoc(usuario.getNDoc())
                .celular(usuario.getCelular())
                .genero(usuario.getGenero())
                .dataNasc(usuario.getDataNasc())
                .tipoPessoa(usuario.getTipoPessoa())
                .endereco(EnderecoDTO
                        .builder()
                        .id(endereco.getId())
                        .cep(endereco.getCep())
                        .logradouro(endereco.getLogradouro())
                        .cidade(endereco.getCidade())
                        .uf(endereco.getUf())
                        .complemento(endereco.getComplemento())
                        .principal(endereco.getPrincipal())
                        .build())
                .build();

        return ResponseEntity.ok(usuarioByIdDto);
    }

    @PatchMapping("/atualizaBiografia")
    public String atualizarBioagrafia(@RequestBody UsuarioEntity usuario) {
        Long id = usuario.getId();

        UsuarioEntity bdUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado id " + id));

        bdUsuario.setBiografia(usuario.getBiografia());

        usuarioRepository.save(bdUsuario);

        return "Biografia modificada com sucesso";

    }

    @GetMapping("/home/{idUsuario}")
    public ResponseEntity<HomeUsuarioDTO> getHome(@PathVariable String idUsuario) {
        HomeUsuarioDTO homeData = idosoService.getHome(Long.parseLong(idUsuario));
        return ResponseEntity.ok(homeData);
    }


    @GetMapping("/home/candidaturas/")
    public ResponseEntity<List<CandidaturaAnuncioDTO>> getCandidaturas() {

        List<CandidaturaEntity> candidaturas = candidaturaRepository.findAll();
        List<CandidaturaAnuncioDTO> listaDTO = new ArrayList<>();

        candidaturas.forEach(c -> {
            IdosoEntity idoso = c.getAnuncio().getIdoso();
            UsuarioEntity prestador = c.getPrestador();
            listaDTO.add(CandidaturaAnuncioDTO
                    .builder()
                            .id(c.getId())
                            .idUsuario(c.getAnuncio().getUsuario().getId())
                            .biografiaPrestador(prestador.getBiografia())
                            .formacao(prestador.getCurso().name())
                            .curso(prestador.getCurso())
                            .whatsapp(prestador.getCelular())
                            .avaliacao(prestador.getAvaliacao())
                            .nomePrestador(prestador.getNome())
                            .laudos(Arrays.asList("laudo 1", "laudo 2"))
                            .fotoPrestador("Foto prestador")
                            .formado(prestador.getFormado())
                            .foto("Foto parente")
                            .nomeIdoso(idoso.getNome() + " " + idoso.getSobrenome())
                    .build());
        });

        return ResponseEntity.ok(listaDTO);
    }


    @PatchMapping("/candidaturas/aceitar/{candidaturaId}")
    @Transactional
    public ResponseEntity<?> aceitarCandidatura(@PathVariable String candidaturaId) {
        CandidaturaEntity candidatura = candidaturaRepository.findById(Long.parseLong(candidaturaId)).get();
        candidaturaRepository.updateCandidatura(StatusCandidaturaEnum.ACEITA, candidatura.getId());

        List<CandidaturaEntity> candidaturasNaoAceitas = candidaturaRepository.candidaturasByAnuncio(candidatura.getAnuncio())
                .stream()
                .filter(c -> c.getId() != Long.parseLong(candidaturaId))
                .peek(c -> c.setStatus(StatusCandidaturaEnum.NAO_ACEITA))
                .collect(Collectors.toList());
        candidaturaRepository.saveAll(candidaturasNaoAceitas);

        IdosoEntity idoso = candidatura.getAnuncio().getIdoso();
        CandidaturaAceitaDTO retorno = CandidaturaAceitaDTO
                .builder()
                .candidaturaId(Long.parseLong(candidaturaId))
                .status(StatusCandidaturaEnum.ACEITA)
                .prestadorId(candidatura.getPrestador().getId())
                .nomeIdoso(idoso.getNome() + " " + idoso.getSobrenome())
                .build();

        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/getCandidatos/{anuncioId}")
    public ResponseEntity<?> getCandidatos(@PathVariable String anuncioId){
        List<CandidatoDTO> candidatos = idosoService.getListaCandidatos(Long.parseLong(anuncioId));
        return ResponseEntity.ok(candidatos);
    }

    @GetMapping("/getAceitas/{usuarioId}")
    public ResponseEntity<?> getAceitas(@PathVariable String usuarioId){
        List<AceitaDTO> aceitas = idosoService.getListaAceitas(Long.parseLong(usuarioId));

        return ResponseEntity.ok(aceitas);
    }

}

