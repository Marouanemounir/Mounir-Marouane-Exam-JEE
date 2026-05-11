package com.marouane.assurance.assuranceapp.repositories;

import com.marouane.assurance.assuranceapp.entities.Contrat;
import com.marouane.assurance.assuranceapp.enums.StatutContrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    List<Contrat> findByClientId(Long clientId);
    List<Contrat> findByStatut(StatutContrat statut);
    long countByClientId(Long clientId);
}
