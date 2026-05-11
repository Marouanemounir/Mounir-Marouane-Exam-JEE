package com.marouane.assurance.assuranceapp.dtos;

import com.marouane.assurance.assuranceapp.enums.StatutContrat;
import com.marouane.assurance.assuranceapp.enums.TypeLogement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratHabitationDTO extends ContratBaseDTO {

    @NotNull
    private TypeLogement typeLogement;

    @NotBlank
    private String adresse;

    @Positive
    private Double superficie;

    public ContratHabitationDTO(Long id, LocalDate dateSouscription,
            StatutContrat statut, LocalDate dateValidation,
            Double montantCotisation, Integer dureeContrat,
            Double tauxCouverture, Long clientId,
            TypeLogement typeLogement, String adresse, Double superficie) {
        super(id, dateSouscription, statut, dateValidation,
              montantCotisation, dureeContrat, tauxCouverture, clientId);
        this.typeLogement = typeLogement;
        this.adresse = adresse;
        this.superficie = superficie;
    }
}
