package com.marouane.assurance.assuranceapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequestDTO(
        @NotBlank String username,
        @NotBlank @Size(min = 4) String password
) {}
