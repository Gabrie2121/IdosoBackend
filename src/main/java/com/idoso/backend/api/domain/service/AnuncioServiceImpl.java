package com.idoso.backend.api.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.idoso.backend.api.domain.dto.request.AnuncioUsuarioDTO;
import com.idoso.backend.api.domain.dto.request.Laudo;
import com.idoso.backend.api.domain.dto.response.AnuncioCriadoDTO;
import com.idoso.backend.api.domain.entities.*;
import com.idoso.backend.api.domain.repository.*;
import com.idoso.backend.api.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.idoso.backend.api.domain.exception.ObjectNotFoundException;
import com.idoso.backend.api.domain.service.contracts.AnuncioService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AnuncioServiceImpl implements AnuncioService {

    private final AnuncioRepository anuncioRepository;

    private final IdosoRepository idosoRepository;

    private final EnderecoRepository enderecoRepository;


    private final UsuarioRepository usuarioRepository;

    private final FileService fileService;

    @Value("${idoso.anuncioFolder}")
    private String anunciosFolder;


    @Override
    public AnuncioEntity findById(Long id) throws ObjectNotFoundException {
        Optional<AnuncioEntity> obj = anuncioRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        throw new ObjectNotFoundException("Anúncio não encontrado");
    }

    public List<AnuncioCriadoDTO> findAll() {
        List<AnuncioEntity> all = anuncioRepository.findAll();

        List<AnuncioCriadoDTO> anuncios = new ArrayList<>();

        all.forEach(anuncio -> {
            AnuncioCriadoDTO anuncioCriado = new AnuncioCriadoDTO();
            anuncioCriado.setId(anuncio.getId());
            anuncioCriado.setNomeIdoso(anuncio.getIdoso().getNome());
            anuncios.add(anuncioCriado);
        });

        return anuncios;
    }

    @Transactional
    public AnuncioEntity criarAnuncio(AnuncioUsuarioDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuario().getId()).get();
        IdosoEntity idoso = dto.getIdoso();
        idoso.setUsuario(usuario);
        dto.setUsuario(usuario);
        IdosoEntity idosoSalvo = idosoRepository.save(idoso);
        dto.setIdoso(idosoSalvo);

        EnderecoEntity  endereco = enderecoRepository.save(dto.getIdoso().getEndereco());

        idoso.setEndereco(endereco);

        String temp = dto.getFoto();
        dto.setFoto("");

        List<Laudo> laudosTemp = dto.getLaudos();

        AnuncioEntity anuncioASerSalvo = AnuncioEntity
                .builder()
                .descricao(dto.getDescricao())
                .usuario(dto.getUsuario())
                .idoso(dto.getIdoso())
                .periodo(dto.getPeriodo())
                .frequencia(dto.getFrequencia())
                .pagamentoBase(dto.getPagamentoBase())
                .horaInicio(dto.getHoraInicio())
                .horaFim(dto.getHoraFim())
                .moraJunto(dto.getMoraJunto())
                .descricao(dto.getDescricao())
                .build();

        //Salva o anuncio
        AnuncioEntity anuncioSalvo = anuncioRepository.save(anuncioASerSalvo);

        String osName = System.getProperty("os.name");
        String separator = osName.contains("Windows") ? "\\" : "/";

        //Atualiza o anuncio no banco com o path da foto
        String fotoPath = anunciosFolder + separator + "FIA"+ String.format("%05d" , anuncioSalvo.getId())+".jpg";

        //Salva a foto do idoso no diretório de anuncios
        fileService.converteBytesParaArquivo(fotoPath, temp);

        int count = 1;
        Iterator<Laudo> iterator = laudosTemp.iterator();

        while(iterator.hasNext()){
            Laudo laudo = iterator.next();
            String novoPath = anunciosFolder + separator + "L"+ String.format("%05d" , count) + "A"+String.format("%05d", anuncioSalvo.getId())+".jpg";
            fileService.converteBytesParaArquivo(novoPath, laudo.getData());
        }

        return anuncioSalvo;
    }
}
