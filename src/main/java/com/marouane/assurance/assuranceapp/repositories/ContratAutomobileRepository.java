package com.marouane.assurance.assuranceapp.repositories;

import com.marouane.assurance.assuranceapp.entities.ContratAutomobile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContratAutomobileRepository extends JpaRepository<ContratAutomobile, Long> {
    Optional<ContratAutomobile> findByNumeroImmatriculation(String numeroImmatriculation);
}
