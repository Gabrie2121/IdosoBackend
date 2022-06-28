package com.idoso.backend.api.domain.entities;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "laudo")
public final class LaudoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String path;

    public LaudoEntity() {}

    public LaudoEntity(Builder builder) {
        this.id = builder.id;
        this.path = builder.path;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaudoEntity laudoEntity = (LaudoEntity) o;
        return Objects.equals(id, laudoEntity.id) && Objects.equals(path, laudoEntity.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path);
    }

    @Override
    public String toString() {
        return "Laudo{id=" + id + ", path='" + path + '\'' + '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder {
        private Long id;
        private String path;

        private Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public LaudoEntity build() {
            return new LaudoEntity(this);
        }
    }
}
