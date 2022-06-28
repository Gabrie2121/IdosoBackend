package com.idoso.backend.api.domain.entities;

import static java.util.Objects.isNull;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import com.idoso.backend.api.domain.enuns.FrequenciaEnum;
import com.idoso.backend.api.domain.enuns.PeriodoEnum;

@Entity
@Table(name = "anuncio")
public final class AnuncioEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private IdosoEntity idoso;

    @ManyToOne
    private UsuarioEntity usuario;

    @Enumerated(STRING)
    private PeriodoEnum periodo;

    @Enumerated(STRING)
    private FrequenciaEnum frequencia;

    private BigDecimal pagamentBase;


    private Boolean moraJunto;

    private Boolean ativo;

    @PrePersist
    public void setAtivo() {
        if (isNull(ativo)) {
            ativo = true;
        }
    }

    @OneToMany
    private List<LaudoEntity> laudos = new ArrayList<>();

    public AnuncioEntity() {
    }

    public AnuncioEntity(Builder builder) {
        this.id = builder.id;
        this.idoso = builder.idoso;
        this.usuario = builder.usuario;
        this.periodo = builder.periodo;
        this.frequencia = builder.frequencia;
        this.pagamentBase = builder.pagamentBase;
        this.moraJunto = builder.moraJunto;
        this.ativo = builder.ativo;
        this.laudos = builder.laudos;
    }

    public Long getId() {
        return id;
    }

    public IdosoEntity getIdoso() {
        return idoso;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public PeriodoEnum getPeriodo() {
        return periodo;
    }

    public FrequenciaEnum getFrequencia() {
        return frequencia;
    }

    public BigDecimal getPagamentBase() {
        return pagamentBase;
    }

    public Boolean getMoraJunto() {
        return moraJunto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public List<LaudoEntity> getLaudos() {
        return laudos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnuncioEntity that = (AnuncioEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idoso, that.idoso) &&
                Objects.equals(usuario, that.usuario) &&
                periodo == that.periodo &&
                frequencia == that.frequencia &&
                Objects.equals(pagamentBase, that.pagamentBase) &&
                Objects.equals(moraJunto, that.moraJunto) &&
                Objects.equals(ativo, that.ativo) &&
                Objects.equals(laudos, that.laudos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idoso, usuario, periodo, frequencia, pagamentBase, moraJunto, ativo, laudos);
    }

    @Override
    public String toString() {
        return "AnuncioEntity{id=" + id + '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private IdosoEntity idoso;
        private UsuarioEntity usuario;
        private PeriodoEnum periodo;
        private FrequenciaEnum frequencia;
        private BigDecimal pagamentBase;
        private Boolean moraJunto;
        private Boolean ativo;
        private List<LaudoEntity> laudos = new ArrayList<>();

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder idoso(IdosoEntity idoso) {
            this.idoso = idoso;
            return this;
        }

        public Builder usuario(UsuarioEntity usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder periodo(PeriodoEnum periodo) {
            this.periodo = periodo;
            return this;
        }

        public Builder frequencia(FrequenciaEnum frequencia) {
            this.frequencia = frequencia;
            return this;
        }

        public Builder pagamentBase(BigDecimal pagamentBase) {
            this.pagamentBase = pagamentBase;
            return this;
        }

        public Builder moraJunto(Boolean moraJunto) {
            this.moraJunto = moraJunto;
            return this;
        }

        public Builder ativo(Boolean ativo) {
            this.ativo = ativo;
            return this;
        }

        public Builder laudos(List<LaudoEntity> laudos) {
            this.laudos = laudos;
            return this;
        }

        public AnuncioEntity build() {
            return new AnuncioEntity(this);
        }
    }
}
