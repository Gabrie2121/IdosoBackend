package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.CursoPrestadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoPrestadorRepository extends JpaRepository<CursoPrestadorEntity, Long> {
}
