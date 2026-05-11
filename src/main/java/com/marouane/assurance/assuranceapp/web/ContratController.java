package com.marouane.assurance.assuranceapp.web;

import com.marouane.assurance.assuranceapp.dtos.ContratAutomobileDTO;
import com.marouane.assurance.assuranceapp.dtos.ContratHabitationDTO;
import com.marouane.assurance.assuranceapp.dtos.ContratSanteDTO;
import com.marouane.assurance.assuranceapp.services.IAssuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Contrats", description = "Gestion des contrats d'assurance (automobile, habitation, santé)")
@SecurityRequirement(name = "bearerAuth")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès"),
        @ApiResponse(responseCode = "404", description = "Ressource non trouvée"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
})
public class ContratController {

    private final IAssuranceService service;

    // ── Automobile ──────────────────────────────────────────────────────────

    @GetMapping("/auto")
    @Operation(summary = "Récupérer tous les contrats automobile")
    public ResponseEntity<List<ContratAutomobileDTO>> getAllAuto() {
        return ResponseEntity.ok(service.getAllContratsAuto());
    }

    @GetMapping("/auto/{id}")
    @Operation(summary = "Récupérer un contrat automobile par son ID")
    public ResponseEntity<ContratAutomobileDTO> getAutoById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getContratAutoById(id));
    }

    @PostMapping("/auto")
    @Operation(summary = "Créer un contrat automobile")
    public ResponseEntity<ContratAutomobileDTO> createAuto(@Valid @RequestBody ContratAutomobileDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveContratAuto(dto));
    }

    // ── Habitation ──────────────────────────────────────────────────────────

    @GetMapping("/habitation")
    @Operation(summary = "Récupérer tous les contrats habitation")
    public ResponseEntity<List<ContratHabitationDTO>> getAllHabitation() {
        return ResponseEntity.ok(service.getAllContratsHabitation());
    }

    @GetMapping("/habitation/{id}")
    @Operation(summary = "Récupérer un contrat habitation par son ID")
    public ResponseEntity<ContratHabitationDTO> getHabitationById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getContratHabitationById(id));
    }

    @PostMapping("/habitation")
    @Operation(summary = "Créer un contrat habitation")
    public ResponseEntity<ContratHabitationDTO> createHabitation(@Valid @RequestBody ContratHabitationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveContratHabitation(dto));
    }

    // ── Santé ───────────────────────────────────────────────────────────────

    @GetMapping("/sante")
    @Operation(summary = "Récupérer tous les contrats santé")
    public ResponseEntity<List<ContratSanteDTO>> getAllSante() {
        return ResponseEntity.ok(service.getAllContratsSante());
    }

    @GetMapping("/sante/{id}")
    @Operation(summary = "Récupérer un contrat santé par son ID")
    public ResponseEntity<ContratSanteDTO> getSanteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getContratSanteById(id));
    }

    @PostMapping("/sante")
    @Operation(summary = "Créer un contrat santé")
    public ResponseEntity<ContratSanteDTO> createSante(@Valid @RequestBody ContratSanteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveContratSante(dto));
    }

    // ── Actions sur statut ──────────────────────────────────────────────────

    @PutMapping("/{id}/valider")
    @Operation(summary = "Valider un contrat automobile")
    public ResponseEntity<ContratAutomobileDTO> validerContrat(@PathVariable Long id) {
        return ResponseEntity.ok(service.validerContrat(id));
    }

    @PutMapping("/{id}/resilier")
    @Operation(summary = "Résilier un contrat")
    public ResponseEntity<Void> resilierContrat(@PathVariable Long id) {
        service.resilierContrat(id);
        return ResponseEntity.noContent().build();
    }
}
