package com.marouane.assurance.assuranceapp.dtos;

import com.marouane.assurance.assuranceapp.enums.NiveauCouverture;
import com.marouane.assurance.assuranceapp.enums.StatutContrat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratSanteDTO extends ContratBaseDTO {

    @NotNull
    private NiveauCouverture niveauCouverture;

    @Min(1)
    private Integer nombrePersonnesCouvertes;

    public ContratSanteDTO(Long id, LocalDate dateSouscription,
            StatutContrat statut, LocalDate dateValidation,
            Double montantCotisation, Integer dureeContrat,
            Double tauxCouverture, Long clientId,
            NiveauCouverture niveauCouverture, Integer nombrePersonnesCouvertes) {
        super(id, dateSouscription, statut, dateValidation,
              montantCotisation, dureeContrat, tauxCouverture, clientId);
        this.niveauCouverture = niveauCouverture;
        this.nombrePersonnesCouvertes = nombrePersonnesCouvertes;
    }
}
