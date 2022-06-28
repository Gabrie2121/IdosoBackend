package com.idoso.backend.api.domain.entities;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.*;
import javax.persistence.*;
import com.idoso.backend.api.domain.enuns.GrauParentescoEnum;

@Entity
@Table(name = "idoso")
public final class IdosoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nome;

    @Column(length = 50)
    private String apelido;

    private Date dataNasc;

    private String nDoc;

    private Character genero;

    private String endereco;

    private Boolean pcd;

    @OneToMany
    private List<DoencaEntity> doencas = new ArrayList<>();

    private String detalheDoenca;

    @Enumerated(STRING)
    private GrauParentescoEnum grauParentesco;

    public IdosoEntity() {}

    public IdosoEntity(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.apelido = builder.apelido;
        this.dataNasc = builder.dataNasc;
        this.nDoc = builder.nDoc;
        this.genero = builder.genero;
        this.endereco = builder.endereco;
        this.pcd = builder.pcd;
        this.doencas = builder.doencas;
        this.detalheDoenca = builder.detalheDoenca;
        this.grauParentesco = builder.grauParentesco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
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

    public Character getGenero() {
        return genero;
    }

    public String getEndereco() {
        return endereco;
    }

    public Boolean getPcd() {
        return pcd;
    }

    public List<DoencaEntity> getDoencas() {
        return doencas;
    }

    public String getDetalheDoenca() {
        return detalheDoenca;
    }

    public GrauParentescoEnum getGrauParentesco() {
        return grauParentesco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdosoEntity that = (IdosoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(apelido, that.apelido) && Objects.equals(dataNasc, that.dataNasc) && Objects.equals(nDoc, that.nDoc) && Objects.equals(genero, that.genero) && Objects.equals(endereco, that.endereco) && Objects.equals(pcd, that.pcd) && Objects.equals(doencas, that.doencas) && Objects.equals(detalheDoenca, that.detalheDoenca) && grauParentesco == that.grauParentesco;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, apelido, dataNasc, nDoc, genero, endereco, pcd, doencas, detalheDoenca, grauParentesco);
    }

    @Override
    public String toString() {
        return "IdosoEntity{id=" + id + ", nome='" + nome + '\'' + '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String nome;
        private String apelido;
        private Date dataNasc;
        private String nDoc;
        private Character genero;
        private String endereco;
        private Boolean pcd;
        private List<DoencaEntity> doencas = new ArrayList<>();
        private String detalheDoenca;
        private GrauParentescoEnum grauParentesco;

        private Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
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

        public Builder genero(Character genero) {
            this.genero = genero;
            return this;
        }

        public Builder endereco(String endereco) {
            this.endereco = endereco;
            return this;
        }

        public Builder pcd(Boolean pcd) {
            this.pcd = pcd;
            return this;
        }

        public Builder doencas(List<DoencaEntity> doencas) {
            this.doencas = doencas;
            return this;
        }

        public Builder detalheDoenca(String detalheDoenca) {
            this.detalheDoenca = detalheDoenca;
            return this;
        }

        public Builder grauParentesco(GrauParentescoEnum grauParentesco) {
            this.grauParentesco = grauParentesco;
            return this;
        }

        public IdosoEntity build() {
            return new IdosoEntity(this);
        }
    }
}
