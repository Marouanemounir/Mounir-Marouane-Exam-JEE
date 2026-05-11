package com.marouane.assurance.assuranceapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
        Long id,
        @NotBlank String nom,
        @NotBlank @Email String email
) {}
