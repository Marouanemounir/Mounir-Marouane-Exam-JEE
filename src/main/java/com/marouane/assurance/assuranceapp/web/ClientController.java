package com.marouane.assurance.assuranceapp.web;

import com.marouane.assurance.assuranceapp.dtos.ClientDTO;
import com.marouane.assurance.assuranceapp.entities.Contrat;
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
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Clients", description = "Gestion des clients")
@SecurityRequirement(name = "bearerAuth")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Succès"),
        @ApiResponse(responseCode = "404", description = "Ressource non trouvée"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
})
public class ClientController {

    private final IAssuranceService service;

    @GetMapping
    @Operation(summary = "Récupérer tous les clients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un client par son ID")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getClientById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau client")
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveClient(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un client")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id,
                                                   @Valid @RequestBody ClientDTO dto) {
        return ResponseEntity.ok(service.updateClient(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        service.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/contrats")
    @Operation(summary = "Récupérer les contrats d'un client")
    public ResponseEntity<List<Contrat>> getContratsByClient(@PathVariable Long id) {
        return ResponseEntity.ok(service.getContratsByClient(id));
    }
}
