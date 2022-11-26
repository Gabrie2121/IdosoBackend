package com.idoso.backend;

import com.idoso.backend.api.domain.entities.ProfileEntity;
import com.idoso.backend.api.domain.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Massa {

    private final ProfileRepository profileRepository;

    public Massa(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // createProfile("ROLE_PRESTADOR");
        // createProfile("ROLE_IDOSO");
    }
    private ProfileEntity createProfile(String role) {
        ProfileEntity admin = new ProfileEntity();
        admin.setNome(role);
        return profileRepository.save(admin);
    }
}
