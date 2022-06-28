package com.idoso.backend.api.domain.repository;

import com.idoso.backend.api.domain.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
}
