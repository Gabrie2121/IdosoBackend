package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.CandidaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepository extends JpaRepository<CandidaturaEntity, Long> {
}
