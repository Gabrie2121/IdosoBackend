package com.idoso.backend;

import static com.idoso.backend.api.domain.enuns.FormaPagamentoEnum.DINHEIRO;
import static com.idoso.backend.api.domain.enuns.FrequenciaEnum.TODOS_OS_DIAS;
import static com.idoso.backend.api.domain.enuns.GrauParentescoEnum.MÃE;
import static com.idoso.backend.api.domain.enuns.PeriodoEnum.NOITE;
import static com.idoso.backend.api.domain.enuns.TipoEnderecoEnum.RESIDENCIAL;
import static com.idoso.backend.api.domain.enuns.TipoPessoaEnum.FISICA;
import static java.util.Calendar.APRIL;
import static java.util.Calendar.FEBRUARY;
import static java.util.Collections.singletonList;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import com.idoso.backend.api.domain.entities.*;
import com.idoso.backend.api.domain.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Massa {


    private final UsuarioRepository usuarioRepository;

    private final ProfileRepository profileRepository;

    private final DoencaRepository doencaRepository;

    private final LaudoRepository laudoRepository;

    private final IdosoRepository idosoRepository;

    private final AnuncioRepository anuncioRepository;

    private final EnderecoRepository enderecoRepository;

    public Massa(
            UsuarioRepository usuarioRepository,
            ProfileRepository profileRepository,
            DoencaRepository doencaRepository,
            LaudoRepository laudoRepository,
            IdosoRepository idosoRepository,
            AnuncioRepository anuncioRepository,
            EnderecoRepository enderecoRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.profileRepository = profileRepository;
        this.doencaRepository = doencaRepository;
        this.laudoRepository = laudoRepository;
        this.idosoRepository = idosoRepository;
        this.anuncioRepository = anuncioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<DoencaEntity> doencas = createDoencas();
        List<LaudoEntity> laudos = createLaudos();
        ProfileEntity profile = createProfile();

        EnderecoEntity endereco = createEndereco();
        UsuarioEntity usuario = createUsuario(profile, endereco);
        IdosoEntity idoso = createIdoso(doencas);
        AnuncioEntity anuncio = createAnuncio(idoso, usuario, laudos);
    }

    private EnderecoEntity createEndereco() {
        return enderecoRepository
            .save(EnderecoEntity
                .newBuilder()
                .cep("05230451")
                .logradouro("Rua da cadela molhada, 77")
                .bairro("Favela do fuzil")
                .cidade("São Paulo")
                .uf("SP")
                .complemento("Em frente ao boteco do mané varela")
                .tipoEndereco(RESIDENCIAL)
                .build());
    }

    private UsuarioEntity createUsuario(ProfileEntity profile, EnderecoEntity endereco) {
        List<ProfileEntity> profileEntities = singletonList(profile);
        String telefones = "1199192-3321";

        UsuarioEntity usuario = UsuarioEntity
                .newBuilder()
                .profileEntities(profileEntities)
                .username("d.alves@teste.com")
                .password(new BCryptPasswordEncoder().encode("abcdef"))
                .apelido("Nil")
                .dataNasc(new GregorianCalendar(1987, APRIL, 4).getTime())
                .nDoc("010203040506")
                .genero('M')
                .celular(telefones)
                .foneUm(telefones)
                .foneDois(telefones)
                .enderecos(singletonList(endereco))
                .tipoPessoa(FISICA)
                .formaPagamento(DINHEIRO)
                .email("d.alves@teste.com")
                .build();

        return usuarioRepository.save(usuario);
    }

    private ProfileEntity createProfile() {
        ProfileEntity admin = new ProfileEntity();
        admin.setNome("ROLE_ADMIN");
        return profileRepository.save(admin);
    }

    private List<DoencaEntity> createDoencas() {
        return doencaRepository
                .saveAll(
                    Arrays.asList(
                        DoencaEntity
                                .newBuilder()
                                .description("Esclerose múltipla")
                                .build(),
                        DoencaEntity
                                .newBuilder()
                                .description("Alzheimer")
                                .build()
                    )
                );
    }

    private List<LaudoEntity> createLaudos() {
        return laudoRepository
            .saveAll(singletonList(LaudoEntity.newBuilder().path("C:/laudos/laudo.pdf").build()));
    }

    private IdosoEntity createIdoso(List<DoencaEntity> doencas) {
       return idosoRepository
            .save(
                IdosoEntity
                    .newBuilder()
                    .nome("Francisca Antonieta")
                    .apelido("Chiquinha")
                    .dataNasc(new GregorianCalendar(1918, FEBRUARY, 11).getTime())
                    .nDoc("00001")
                    .genero('F')
                    .endereco("Rua da cadela molhada, 77")
                    .pcd(false)
                    .doencas(doencas)
                    .detalheDoenca("Esquecimento das coisas")
                    .grauParentesco(MÃE)
                    .build()
            );
    }

    private AnuncioEntity createAnuncio(IdosoEntity idoso, UsuarioEntity usuario, List<LaudoEntity> laudos) {
        return anuncioRepository.save(
                AnuncioEntity
                    .newBuilder()
                    .idoso(idoso)
                    .usuario(usuario)
                    .periodo(NOITE)
                    .frequencia(TODOS_OS_DIAS)
                    .pagamentBase(new BigDecimal("250.00"))
                    .moraJunto(true)
                    .ativo(true)
                    .laudos(laudos)
                    .build()
        );
    }

}
