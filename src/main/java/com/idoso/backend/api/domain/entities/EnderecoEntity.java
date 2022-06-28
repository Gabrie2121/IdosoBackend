package com.idoso.backend.api.domain.entities;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Objects;
import javax.persistence.*;
import com.idoso.backend.api.domain.enuns.TipoEnderecoEnum;

@Entity
@Table(name = "endereco")
public final class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String cep;

    private String logradouro;

    private String bairro;

    private String cidade;

    @Column(length = 2)
    private String uf;

    private String complemento;

    @Enumerated(STRING)
    private TipoEnderecoEnum tipoEndereco;

    public EnderecoEntity() {
    }

    public EnderecoEntity(Builder builder) {
        this.id = builder.id;
        this.cep = builder.cep;
        this.logradouro = builder.logradouro;
        this.bairro = builder.bairro;
        this.cidade = builder.cidade;
        this.uf = builder.uf;
        this.complemento = builder.complemento;
        this.tipoEndereco = builder.tipoEndereco;
    }

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public TipoEnderecoEnum getTipoEndereco() {
        return tipoEndereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnderecoEntity enderecoEntity = (EnderecoEntity) o;
        return Objects.equals(id, enderecoEntity.id) &&
                Objects.equals(cep, enderecoEntity.cep) &&
                Objects.equals(logradouro, enderecoEntity.logradouro) &&
                Objects.equals(bairro, enderecoEntity.bairro) &&
                Objects.equals(cidade, enderecoEntity.cidade) &&
                Objects.equals(uf, enderecoEntity.uf) &&
                Objects.equals(complemento, enderecoEntity.complemento) &&
                tipoEndereco == enderecoEntity.tipoEndereco;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cep, logradouro, bairro, cidade, uf, complemento, tipoEndereco);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", complemento='" + complemento + '\'' +
                ", tipoEndereco=" + tipoEndereco +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String cep;
        private String logradouro;
        private String bairro;
        private String cidade;
        private String uf;
        private String complemento;
        private TipoEnderecoEnum tipoEndereco;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public Builder logradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public Builder bairro(String bairro) {
            this.bairro = bairro;
            return this;
        }

        public Builder cidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public Builder uf(String uf) {
            this.uf = uf;
            return this;
        }

        public Builder complemento(String complemento) {
            this.complemento = complemento;
            return this;
        }

        public Builder tipoEndereco(TipoEnderecoEnum tipoEndereco) {
            this.tipoEndereco = tipoEndereco;
            return this;
        }

        public EnderecoEntity build() {
            return new EnderecoEntity(this);
        }
    }
}
