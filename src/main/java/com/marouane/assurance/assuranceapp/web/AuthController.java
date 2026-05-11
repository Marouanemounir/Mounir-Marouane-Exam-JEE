package com.marouane.assurance.assuranceapp.web;

import com.marouane.assurance.assuranceapp.dtos.AuthRequestDTO;
import com.marouane.assurance.assuranceapp.dtos.AuthResponseDTO;
import com.marouane.assurance.assuranceapp.dtos.RegisterRequestDTO;
import com.marouane.assurance.assuranceapp.entities.AppUser;
import com.marouane.assurance.assuranceapp.repositories.AppUserRepository;
import com.marouane.assurance.assuranceapp.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Authentification", description = "Endpoints publics de connexion et d'inscription")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès"),
        @ApiResponse(responseCode = "404", description = "Ressource non trouvée"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
})
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @Operation(summary = "Connexion et obtention du token JWT")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
        AppUser user = userRepo.findByUsername(dto.username()).orElseThrow();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
        return ResponseEntity.ok(new AuthResponseDTO(token, user.getUsername(), user.getRoles()));
    }

    @PostMapping("/register")
    @Operation(summary = "Inscription d'un nouvel utilisateur")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        if (userRepo.existsByUsername(dto.username())) {
            throw new RuntimeException("Username déjà pris");
        }
        List<String> roles = (dto.roles() != null && !dto.roles().isEmpty())
                ? dto.roles()
                : List.of("ROLE_CLIENT");
        AppUser user = new AppUser(null, dto.username(),
                passwordEncoder.encode(dto.password()), roles);
        userRepo.save(user);
        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponseDTO(token, user.getUsername(), user.getRoles()));
    }
}
