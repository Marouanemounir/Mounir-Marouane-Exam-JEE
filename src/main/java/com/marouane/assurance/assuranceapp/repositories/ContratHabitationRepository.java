package com.marouane.assurance.assuranceapp.repositories;

import com.marouane.assurance.assuranceapp.entities.ContratHabitation;
import com.marouane.assurance.assuranceapp.enums.TypeLogement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratHabitationRepository extends JpaRepository<ContratHabitation, Long> {
    List<ContratHabitation> findByTypeLogement(TypeLogement typeLogement);
}
