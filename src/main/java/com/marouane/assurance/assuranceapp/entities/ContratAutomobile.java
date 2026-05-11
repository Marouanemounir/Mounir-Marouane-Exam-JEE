package com.marouane.assurance.assuranceapp.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity @Data @NoArgsConstructor
@DiscriminatorValue("AUTO")
public class ContratAutomobile extends Contrat {
    private String numeroImmatriculation;
    private String marque;
    private String modele;
}
