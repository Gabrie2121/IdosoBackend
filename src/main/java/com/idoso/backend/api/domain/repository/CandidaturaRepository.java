package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.CandidaturaPrestadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidaturaRepository extends JpaRepository<CandidaturaPrestadorEntity, Long> {
}
