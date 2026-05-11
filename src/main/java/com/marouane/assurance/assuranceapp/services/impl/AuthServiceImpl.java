package com.marouane.assurance.assuranceapp.services.impl;

import com.marouane.assurance.assuranceapp.dtos.AuthRequestDTO;
import com.marouane.assurance.assuranceapp.dtos.AuthResponseDTO;
import com.marouane.assurance.assuranceapp.dtos.RegisterRequestDTO;
import com.marouane.assurance.assuranceapp.entities.AppUser;
import com.marouane.assurance.assuranceapp.repositories.AppUserRepository;
import com.marouane.assurance.assuranceapp.services.IAuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {
        AppUser user = appUserRepository.findByUsername(dto.username())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé : " + dto.username()));
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Mot de passe incorrect");
        }
        // La génération du token JWT sera complétée en partie 4
        return new AuthResponseDTO("TOKEN_PLACEHOLDER", user.getUsername(), user.getRoles());
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (appUserRepository.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("Nom d'utilisateur déjà utilisé : " + dto.username());
        }
        List<String> roles = (dto.roles() != null && !dto.roles().isEmpty())
                ? dto.roles()
                : List.of("ROLE_CLIENT");
        AppUser user = new AppUser(null, dto.username(),
                passwordEncoder.encode(dto.password()), roles);
        appUserRepository.save(user);
        // La génération du token JWT sera complétée en partie 4
        return new AuthResponseDTO("TOKEN_PLACEHOLDER", user.getUsername(), user.getRoles());
    }
}
