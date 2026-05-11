package com.marouane.assurance.assuranceapp.entities;


import com.marouane.assurance.assuranceapp.enums.TypePaiement;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypePaiement type;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    @JsonIgnore
    private Contrat contrat;
}
