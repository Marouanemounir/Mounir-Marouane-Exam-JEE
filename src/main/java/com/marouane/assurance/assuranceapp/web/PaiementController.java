package com.marouane.assurance.assuranceapp.web;

import com.marouane.assurance.assuranceapp.dtos.PaiementDTO;
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
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Paiements", description = "Gestion des paiements liés aux contrats")
@SecurityRequirement(name = "bearerAuth")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès"),
        @ApiResponse(responseCode = "404", description = "Ressource non trouvée"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
})
public class PaiementController {

    private final IAssuranceService service;

    @GetMapping
    @Operation(summary = "Récupérer tous les paiements")
    public ResponseEntity<List<PaiementDTO>> getAllPaiements() {
        return ResponseEntity.ok(service.getAllPaiements());
    }

    @PostMapping
    @Operation(summary = "Enregistrer un paiement")
    public ResponseEntity<PaiementDTO> createPaiement(@Valid @RequestBody PaiementDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePaiement(dto));
    }

    @GetMapping("/contrat/{contratId}")
    @Operation(summary = "Récupérer les paiements d'un contrat")
    public ResponseEntity<List<PaiementDTO>> getPaiementsByContrat(@PathVariable Long contratId) {
        return ResponseEntity.ok(service.getPaiementsByContrat(contratId));
    }
}
