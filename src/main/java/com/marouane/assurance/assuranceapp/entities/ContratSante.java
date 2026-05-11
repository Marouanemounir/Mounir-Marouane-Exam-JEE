package com.marouane.assurance.assuranceapp.entities;


import com.marouane.assurance.assuranceapp.enums.NiveauCouverture;
import jakarta.persistence.*;
import lombok.*;

@Entity @Data @NoArgsConstructor
@DiscriminatorValue("SANTE")
public class ContratSante extends Contrat {
    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;
    private Integer nombrePersonnesCouvertes;
}