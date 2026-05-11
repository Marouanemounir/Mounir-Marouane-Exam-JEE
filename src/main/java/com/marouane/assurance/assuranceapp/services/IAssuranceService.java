package com.marouane.assurance.assuranceapp.services;

import com.marouane.assurance.assuranceapp.dtos.*;
import com.marouane.assurance.assuranceapp.entities.Contrat;

import java.util.List;

public interface IAssuranceService {

    // ── Clients ──────────────────────────────────────────────────────────────
    ClientDTO saveClient(ClientDTO dto);
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long id);
    ClientDTO updateClient(Long id, ClientDTO dto);
    void deleteClient(Long id);
    List<Contrat> getContratsByClient(Long clientId);

    // ── Contrats Automobile ──────────────────────────────────────────────────
    ContratAutomobileDTO saveContratAuto(ContratAutomobileDTO dto);
    List<ContratAutomobileDTO> getAllContratsAuto();
    ContratAutomobileDTO getContratAutoById(Long id);

    // ── Contrats Habitation ──────────────────────────────────────────────────
    ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto);
    List<ContratHabitationDTO> getAllContratsHabitation();
    ContratHabitationDTO getContratHabitationById(Long id);

    // ── Contrats Santé ───────────────────────────────────────────────────────
    ContratSanteDTO saveContratSante(ContratSanteDTO dto);
    List<ContratSanteDTO> getAllContratsSante();
    ContratSanteDTO getContratSanteById(Long id);

    // ── Statut ───────────────────────────────────────────────────────────────
    ContratAutomobileDTO validerContrat(Long contratId);
    void resilierContrat(Long contratId);

    // ── Paiements ────────────────────────────────────────────────────────────
    PaiementDTO savePaiement(PaiementDTO dto);
    List<PaiementDTO> getPaiementsByContrat(Long contratId);
    List<PaiementDTO> getAllPaiements();
}
