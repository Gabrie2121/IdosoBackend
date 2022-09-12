package com.idoso.backend.api.domain.dto.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.idoso.backend.api.domain.entities.AnuncioEntity;
import com.idoso.backend.api.domain.entities.IdosoEntity;
import com.idoso.backend.api.domain.entities.LaudoEntity;
import com.idoso.backend.api.domain.entities.UsuarioEntity;
import com.idoso.backend.api.domain.enuns.FrequenciaEnum;
import com.idoso.backend.api.domain.enuns.PeriodoEnum;

public class AnuncioDTO {

    private Long id;
    private IdosoEntity idoso;
    private UsuarioEntity usuario;
    private PeriodoEnum periodo;
    private FrequenciaEnum frequencia;
    private BigDecimal pagamentBase;
    private Boolean moraJunto;
    private Boolean ativo;
    private List<LaudoEntity> laudos = new ArrayList<>();

    public AnuncioDTO() {
        super();
    }

    public AnuncioDTO(AnuncioEntity obj) {
        super();
        this.id = obj.getId();
        this.idoso = obj.getIdoso();
        this.usuario = obj.getUsuario();
        this.periodo = obj.getPeriodo();
        this.frequencia = obj.getFrequencia();
        this.pagamentBase = obj.getPagamentBase();
        this.moraJunto = obj.getMoraJunto();
        this.ativo = obj.getAtivo();
        this.laudos = obj.getLaudos();
    }

    public AnuncioDTO(Builder builder) {
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

        public AnuncioDTO build() {
            return new AnuncioDTO(this);
        }
    }

}
