package com.marouane.assurance.assuranceapp.dtos;

import java.util.List;

public record AuthResponseDTO(
        String token,
        String username,
        List<String> roles
) {}
