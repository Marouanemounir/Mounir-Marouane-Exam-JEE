package com.marouane.assurance.assuranceapp.repositories;

import com.marouane.assurance.assuranceapp.entities.Paiement;
import com.marouane.assurance.assuranceapp.enums.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByContratId(Long contratId);
    List<Paiement> findByType(TypePaiement type);

    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.contrat.id = :contratId")
    Double sumMontantByContratId(@Param("contratId") Long contratId);
}
