package com.marouane.assurance.assuranceapp.dtos;

import com.marouane.assurance.assuranceapp.enums.TypePaiement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record PaiementDTO(
        Long id,
        @NotNull LocalDate date,
        @Positive Double montant,
        @NotNull TypePaiement type,
        @NotNull Long contratId
) {}
