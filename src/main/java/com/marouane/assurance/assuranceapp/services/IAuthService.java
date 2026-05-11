package com.marouane.assurance.assuranceapp.services;

import com.marouane.assurance.assuranceapp.dtos.AuthRequestDTO;
import com.marouane.assurance.assuranceapp.dtos.AuthResponseDTO;
import com.marouane.assurance.assuranceapp.dtos.RegisterRequestDTO;

public interface IAuthService {
    AuthResponseDTO login(AuthRequestDTO dto);
    AuthResponseDTO register(RegisterRequestDTO dto);
}
