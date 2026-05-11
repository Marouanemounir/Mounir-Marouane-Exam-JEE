package com.marouane.assurance.assuranceapp;

import com.marouane.assurance.assuranceapp.entities.*;
import com.marouane.assurance.assuranceapp.enums.*;
import com.marouane.assurance.assuranceapp.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final ClientRepository clientRepository;
    private final ContratAutomobileRepository contratAutomobileRepository;
    private final ContratHabitationRepository contratHabitationRepository;
    private final ContratSanteRepository contratSanteRepository;
    private final PaiementRepository paiementRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // --- 1. UTILISATEURS ---
        if (!appUserRepository.existsByUsername("admin")) {
            AppUser admin = new AppUser(null, "admin", passwordEncoder.encode("1234"), List.of("ROLE_ADMIN"));
            AppUser employe1 = new AppUser(null, "employe1", passwordEncoder.encode("1234"), List.of("ROLE_EMPLOYE"));
            AppUser client1 = new AppUser(null, "client1", passwordEncoder.encode("1234"), List.of("ROLE_CLIENT"));
            appUserRepository.saveAll(List.of(admin, employe1, client1));
        }

        // --- 2. CLIENTS ---
        if (!clientRepository.existsByEmail("ahmed.bennani@mail.com")) {
            Client ahmed = new Client(null, "Ahmed Bennani", "ahmed.bennani@mail.com", List.of());
            Client sara = new Client(null, "Sara Alaoui", "sara.alaoui@mail.com", List.of());
            Client karim = new Client(null, "Karim Idrissi", "karim.idrissi@mail.com", List.of());
            clientRepository.saveAll(List.of(ahmed, sara, karim));
        }

        // Récupération des clients pour les contrats
        Client ahmed = clientRepository.findByEmail("ahmed.bennani@mail.com").orElseThrow();
        Client sara = clientRepository.findByEmail("sara.alaoui@mail.com").orElseThrow();
        Client karim = clientRepository.findByEmail("karim.idrissi@mail.com").orElseThrow();

        // --- 3. CONTRAT AUTOMOBILE pour Ahmed ---
        if (contratAutomobileRepository.findByNumeroImmatriculation("12345-A-6").isEmpty()) {
            ContratAutomobile contratAuto = new ContratAutomobile();
            contratAuto.setMarque("Toyota");
            contratAuto.setModele("Yaris");
            contratAuto.setNumeroImmatriculation("12345-A-6");
            contratAuto.setStatut(StatutContrat.VALIDE);
            contratAuto.setDateValidation(LocalDate.now());
            contratAuto.setDateSouscription(LocalDate.now().minusDays(30));
            contratAuto.setMontantCotisation(1500.0);
            contratAuto.setDureeContrat(12);
            contratAuto.setTauxCouverture(80.0);
            contratAuto.setClient(ahmed);
            contratAutomobileRepository.save(contratAuto);

            // --- 6. PAIEMENTS : 3 mensualités de 125.0 pour le contrat auto ---
            Paiement p1 = new Paiement(null, LocalDate.now().minusDays(60), 125.0, TypePaiement.MENSUALITE, contratAuto);
            Paiement p2 = new Paiement(null, LocalDate.now().minusDays(30), 125.0, TypePaiement.MENSUALITE, contratAuto);
            Paiement p3 = new Paiement(null, LocalDate.now(),              125.0, TypePaiement.MENSUALITE, contratAuto);
            paiementRepository.saveAll(List.of(p1, p2, p3));
        }

        // --- 4. CONTRAT HABITATION pour Sara ---
        if (contratHabitationRepository.findByTypeLogement(TypeLogement.APPARTEMENT).isEmpty()) {
            ContratHabitation contratHab = new ContratHabitation();
            contratHab.setTypeLogement(TypeLogement.APPARTEMENT);
            contratHab.setAdresse("12 Rue Hassan II, Casablanca");
            contratHab.setSuperficie(95.0);
            contratHab.setStatut(StatutContrat.EN_COURS);
            contratHab.setDateSouscription(LocalDate.now().minusDays(15));
            contratHab.setMontantCotisation(2500.0);
            contratHab.setDureeContrat(12);
            contratHab.setTauxCouverture(70.0);
            contratHab.setClient(sara);
            contratHabitationRepository.save(contratHab);
        }

        // --- 5. CONTRAT SANTÉ pour Karim ---
        if (contratSanteRepository.findByNiveauCouverture(NiveauCouverture.PREMIUM).isEmpty()) {
            ContratSante contratSante = new ContratSante();
            contratSante.setNiveauCouverture(NiveauCouverture.PREMIUM);
            contratSante.setNombrePersonnesCouvertes(4);
            contratSante.setStatut(StatutContrat.VALIDE);
            contratSante.setDateValidation(LocalDate.now().minusDays(5));
            contratSante.setDateSouscription(LocalDate.now().minusDays(60));
            contratSante.setMontantCotisation(3500.0);
            contratSante.setDureeContrat(24);
            contratSante.setTauxCouverture(90.0);
            contratSante.setClient(karim);
            contratSanteRepository.save(contratSante);
        }

        System.out.println("✅ Base de données initialisée avec succès !");
    }
}
