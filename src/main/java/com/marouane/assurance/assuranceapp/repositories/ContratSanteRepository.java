package com.marouane.assurance.assuranceapp.repositories;

import com.marouane.assurance.assuranceapp.entities.ContratSante;
import com.marouane.assurance.assuranceapp.enums.NiveauCouverture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratSanteRepository extends JpaRepository<ContratSante, Long> {
    List<ContratSante> findByNiveauCouverture(NiveauCouverture niveauCouverture);
}
