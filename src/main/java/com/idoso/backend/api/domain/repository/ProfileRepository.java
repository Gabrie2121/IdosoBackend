package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    Optional<ProfileEntity> findByNome(String name);
}
