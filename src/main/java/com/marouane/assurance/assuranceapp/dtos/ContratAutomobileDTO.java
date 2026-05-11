package com.marouane.assurance.assuranceapp.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratAutomobileDTO extends ContratBaseDTO {

    @NotBlank
    private String numeroImmatriculation;

    @NotBlank
    private String marque;

    @NotBlank
    private String modele;

    public ContratAutomobileDTO(Long id, java.time.LocalDate dateSouscription,
            com.marouane.assurance.assuranceapp.enums.StatutContrat statut,
            LocalDate dateValidation, Double montantCotisation,
            Integer dureeContrat, Double tauxCouverture, Long clientId,
            String numeroImmatriculation, String marque, String modele) {
        super(id, dateSouscription, statut, dateValidation,
              montantCotisation, dureeContrat, tauxCouverture, clientId);
        this.numeroImmatriculation = numeroImmatriculation;
        this.marque = marque;
        this.modele = modele;
    }
}
