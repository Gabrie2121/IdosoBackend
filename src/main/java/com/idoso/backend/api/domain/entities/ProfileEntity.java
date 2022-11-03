package com.idoso.backend.api.domain.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_profiles")
public class ProfileEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    @Column
    String nome;

    @Override
    public String getAuthority() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
