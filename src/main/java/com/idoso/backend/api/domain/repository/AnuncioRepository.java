package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.AnuncioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<AnuncioEntity, Long> {
}
