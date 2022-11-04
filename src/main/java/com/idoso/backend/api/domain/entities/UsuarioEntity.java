package com.idoso.backend.api.domain.entities;

import static java.util.Objects.isNull;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.idoso.backend.api.domain.dto.request.Certificado;
import com.idoso.backend.api.domain.enuns.CursoEnum;
import com.idoso.backend.api.domain.enuns.FormaPagamentoEnum;
import com.idoso.backend.api.domain.enuns.TipoPessoaEnum;
import com.idoso.backend.api.domain.enuns.TipoUsuarioEnum;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
@Table(name = "tb_usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UsuarioEntity implements UserDetails {
    private static final String EMPTY_CONTENT = "N/A";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = EAGER)
    private List<ProfileEntity> profileEntities = new ArrayList<>();
    private String username;
    private String password;

    private String nome;

    private String sobrenome;

    private Double avaliacao;
    @Column(length = 50)
    private String razaoSocial;
    @Column(length = 50)
    private String nomeFantasia;

    private String apelido;

    private Date dataNasc;

    @OneToMany(mappedBy = "usuario")
    List<AnuncioEntity> anuncios;

    @JsonProperty("nDoc")
    private String nDoc;
    private String ie;

    private Character genero;

    private String celular;

    private String biografia;

    @OneToOne
    private EnderecoEntity endereco;

    @Enumerated(STRING)
    @JsonProperty("tipoPessoa")
    private TipoPessoaEnum tipoPessoa;

    @Enumerated(STRING)
    @JsonProperty("formaPagamento")
    private FormaPagamentoEnum formaPagamento;

    private String foto;

    private Date dataCadastro;

    private Boolean formado;

    @Enumerated(STRING)
    private CursoEnum curso;

    private Double valoHora;

    @PrePersist
    public void setDataCadastro() {
        if (isNull(dataCadastro)) dataCadastro = new Date();

        if(isNull(razaoSocial)) razaoSocial = EMPTY_CONTENT;

        if(isNull(nomeFantasia)) nomeFantasia = EMPTY_CONTENT;

        if(isNull(ie)) ie = EMPTY_CONTENT;

        if(isNull(valoHora)) valoHora = 0.00;
    }

    private String email;

    @Transient
    private List<String> certificados;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profileEntities;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
