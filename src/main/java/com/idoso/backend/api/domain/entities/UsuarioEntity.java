package com.idoso.backend.api.domain.entities;

import static java.util.Objects.isNull;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

import java.util.*;
import javax.persistence.*;
import com.idoso.backend.api.domain.enuns.FormaPagamentoEnum;
import com.idoso.backend.api.domain.enuns.TipoPessoaEnum;
import com.idoso.backend.api.domain.enuns.TipoUsuarioEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public final class UsuarioEntity implements UserDetails {

    private static final String EMPTY_CONTENT = "N/A";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = EAGER)
    private List<ProfileEntity> profileEntities = new ArrayList<>();

    @Column
    private String username;

    @Column
    private String password;

    @Column(length = 50)
    private String razaoSocial;

    @Column(length = 50)
    private String nomeFantasia;

    private String apelido;

    private Date dataNasc;

    private String nDoc;

    private String ie;

    private Character genero;

    private String celular;

    private String foneUm;

    private String foneDois;

    @OneToMany
    private List<EnderecoEntity> enderecos = new ArrayList<>();

    @Enumerated(STRING)
    private TipoPessoaEnum tipoPessoa;

    @Enumerated(STRING)
    private FormaPagamentoEnum formaPagamento;

    private Date dataCadastro;

    @PrePersist
    public void setDataCadastro() {
        if (isNull(dataCadastro))
            dataCadastro = new Date();

        if(isNull(razaoSocial))
            razaoSocial = EMPTY_CONTENT;

        if(isNull(nomeFantasia))
            nomeFantasia = EMPTY_CONTENT;

        if(isNull(ie))
            ie = EMPTY_CONTENT;
    }

    private String email;

    @Enumerated(STRING)
    private TipoUsuarioEnum tipoUsuario;

    @OneToOne(mappedBy = "prestador")
    private TrabalhoEntity trabalhoPrestador;

    @OneToOne(mappedBy = "idoso")
    private TrabalhoEntity trabalhoIdoso;

    @OneToMany
    private List<AnuncioEntity> anuncios = new ArrayList<>();

    public UsuarioEntity() {}

    public UsuarioEntity(Builder builder) {
        this.id = builder.id;
        this.profileEntities = builder.profileEntities;
        this.username = builder.username;
        this.password = builder.password;
        this.razaoSocial = builder.razaoSocial;
        this.nomeFantasia = builder.nomeFantasia;
        this.apelido = builder.apelido;
        this.dataNasc = builder.dataNasc;
        this.nDoc = builder.nDoc;
        this.ie = builder.ie;
        this.genero = builder.genero;
        this.celular = builder.celular;
        this.foneUm = builder.foneUm;
        this.foneDois = builder.foneDois;
        this.enderecos = builder.enderecos;
        this.tipoPessoa = builder.tipoPessoa;
        this.formaPagamento = builder.formaPagamento;
        this.dataCadastro = builder.dataCadastro;
        this.email = builder.email;
        this.anuncios = builder.anuncios;
    }

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

    public Long getId() {
        return id;
    }

    public List<ProfileEntity> getProfileEntities() {
        return profileEntities;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getApelido() {
        return apelido;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public String getnDoc() {
        return nDoc;
    }

    public String getIe() {
        return ie;
    }

    public Character getGenero() {
        return genero;
    }

    public String getCelular() {
        return celular;
    }

    public String getFoneUm() {
        return foneUm;
    }

    public String getFoneDois() {
        return foneDois;
    }

    public List<EnderecoEntity> getEnderecos() {
        return enderecos;
    }

    public TipoPessoaEnum getTipoPessoa() {
        return tipoPessoa;
    }

    public FormaPagamentoEnum getFormaPagamento() {
        return formaPagamento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public String getEmail() {
        return email;
    }

    public List<AnuncioEntity> getAnuncios() {
        return anuncios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(profileEntities, that.profileEntities) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(razaoSocial, that.razaoSocial) && Objects.equals(nomeFantasia, that.nomeFantasia) && Objects.equals(apelido, that.apelido) && Objects.equals(dataNasc, that.dataNasc) && Objects.equals(nDoc, that.nDoc) && Objects.equals(ie, that.ie) && Objects.equals(genero, that.genero) && Objects.equals(celular, that.celular) && Objects.equals(foneUm, that.foneUm) && Objects.equals(foneDois, that.foneDois) && Objects.equals(enderecos, that.enderecos) && tipoPessoa == that.tipoPessoa && formaPagamento == that.formaPagamento && Objects.equals(dataCadastro, that.dataCadastro) && Objects.equals(email, that.email) && Objects.equals(anuncios, that.anuncios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileEntities, username, password, razaoSocial, nomeFantasia, apelido, dataNasc, nDoc, ie, genero, celular, foneUm, foneDois, enderecos, tipoPessoa, formaPagamento, dataCadastro, email, anuncios);
    }

    @Override
    public String toString() {
        return "UsuarioEntity{id=" + id + ", username='" + username + '\'' + ", nomeFantasia='" + nomeFantasia + '\'' + '}';
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

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private List<ProfileEntity> profileEntities = new ArrayList<>();
        private String username;
        private String password;
        private String razaoSocial;
        private String nomeFantasia;
        private String apelido;
        private Date dataNasc;
        private String nDoc;
        private String ie;
        private Character genero;
        private String celular;
        private String foneUm;
        private String foneDois;
        private List<EnderecoEntity> enderecos = new ArrayList<>();
        private TipoPessoaEnum tipoPessoa;
        private FormaPagamentoEnum formaPagamento;
        private Date dataCadastro;
        private String email;
        private TipoUsuarioEnum tipoUsuario;
        private TrabalhoEntity trabalhoPrestador;
        private TrabalhoEntity trabalhoIdoso;
        private List<AnuncioEntity> anuncios = new ArrayList<>();

        private Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder profileEntities(List<ProfileEntity> profileEntities) {
            this.profileEntities = profileEntities;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder razaoSocial(String razaoSocial) {
            this.razaoSocial = razaoSocial;
            return this;
        }

        public Builder nomeFantasia(String nomeFantasia) {
            this.nomeFantasia = nomeFantasia;
            return this;
        }

        public Builder apelido(String apelido) {
            this.apelido = apelido;
            return this;
        }

        public Builder dataNasc(Date dataNasc) {
            this.dataNasc = dataNasc;
            return this;
        }

        public Builder nDoc(String nDoc) {
            this.nDoc = nDoc;
            return this;
        }

        public Builder ie(String ie) {
            this.ie = ie;
            return this;
        }

        public Builder genero(Character genero) {
            this.genero = genero;
            return this;
        }

        public Builder celular(String celular) {
            this.celular = celular;
            return this;
        }

        public Builder foneUm(String foneUm) {
            this.foneUm = foneUm;
            return this;
        }

        public Builder foneDois(String foneDois) {
            this.foneDois = foneDois;
            return this;
        }

        public Builder enderecos(List<EnderecoEntity> enderecos) {
            this.enderecos = enderecos;
            return this;
        }

        public Builder tipoPessoa(TipoPessoaEnum tipoPessoa) {
            this.tipoPessoa = tipoPessoa;
            return this;
        }

        public Builder formaPagamento(FormaPagamentoEnum formaPagamento) {
            this.formaPagamento = formaPagamento;
            return this;
        }

        public Builder dataCadastro(Date dataCadastro) {
            this.dataCadastro = dataCadastro;
            return this;
        }

        public Builder tipoUsuario(TipoUsuarioEnum tipoUsuario) {
            this.tipoUsuario = tipoUsuario;
            return this;
        }

        public Builder trabalhoPrestador(TrabalhoEntity trabalhoPrestador) {
            this.trabalhoPrestador = trabalhoPrestador;
            return this;
        }

        public Builder trabalhoIdoso(TrabalhoEntity trabalhoIdoso) {
            this.trabalhoIdoso = trabalhoIdoso;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder anuncios(List<AnuncioEntity> anuncios) {
            this.anuncios = anuncios;
            return this;
        }

        public UsuarioEntity build() {
            return new UsuarioEntity(this);
        }
    }
}
