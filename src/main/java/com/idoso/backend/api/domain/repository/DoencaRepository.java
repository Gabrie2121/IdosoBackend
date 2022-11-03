package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.DoencaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoencaRepository extends JpaRepository<DoencaEntity, Long> {
}
