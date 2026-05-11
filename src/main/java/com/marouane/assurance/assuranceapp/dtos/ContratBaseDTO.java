package com.marouane.assurance.assuranceapp.dtos;

import com.marouane.assurance.assuranceapp.enums.StatutContrat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ContratBaseDTO {
    private Long id;
    private LocalDate dateSouscription;
    private StatutContrat statut;
    private LocalDate dateValidation;

    @NotNull @Positive
    private Double montantCotisation;

    @NotNull @Min(1)
    private Integer dureeContrat;

    private Double tauxCouverture;

    @NotNull
    private Long clientId;
}
