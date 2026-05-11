package com.marouane.assurance.assuranceapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RegisterRequestDTO(
        @NotBlank String username,
        @NotBlank @Size(min = 4) String password,
        List<String> roles
) {}
