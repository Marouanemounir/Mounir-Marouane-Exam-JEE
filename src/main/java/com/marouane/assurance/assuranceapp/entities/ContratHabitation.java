package com.marouane.assurance.assuranceapp.entities;


import com.marouane.assurance.assuranceapp.enums.TypeLogement;
import jakarta.persistence.*;
import lombok.*;

@Entity @Data @NoArgsConstructor
@DiscriminatorValue("HABITATION")
public class ContratHabitation extends Contrat {
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;
    private String adresse;
    private Double superficie;
}
