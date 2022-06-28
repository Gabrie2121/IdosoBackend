package com.idoso.backend.api.domain.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "doenca")
public final class DoencaEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String description;

    public DoencaEntity() {}

    public DoencaEntity(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoencaEntity doencaEntity = (DoencaEntity) o;
        return Objects.equals(id, doencaEntity.id) && Objects.equals(description, doencaEntity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Doenca{id=" + id + ", description='" + description + '\'' + '}';
    }


    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String description;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public DoencaEntity build() {
            return new DoencaEntity(this);
        }
    }
}
