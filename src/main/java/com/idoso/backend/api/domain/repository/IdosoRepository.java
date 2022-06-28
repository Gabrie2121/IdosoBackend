package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.IdosoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdosoRepository extends JpaRepository<IdosoEntity, Long> {
}
