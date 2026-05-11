package com.marouane.assurance.assuranceapp.services.impl;

import com.marouane.assurance.assuranceapp.dtos.*;
import com.marouane.assurance.assuranceapp.entities.*;
import com.marouane.assurance.assuranceapp.enums.StatutContrat;
import com.marouane.assurance.assuranceapp.mappers.*;
import com.marouane.assurance.assuranceapp.repositories.*;
import com.marouane.assurance.assuranceapp.services.IAssuranceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AssuranceServiceImpl implements IAssuranceService {

    private final ClientRepository clientRepository;
    private final ContratRepository contratRepository;
    private final ContratAutomobileRepository contratAutoRepository;
    private final ContratHabitationRepository contratHabitationRepository;
    private final ContratSanteRepository contratSanteRepository;
    private final PaiementRepository paiementRepository;

    private final ClientMapper clientMapper;
    private final ContratMapper contratMapper;
    private final PaiementMapper paiementMapper;

    // ── Clients ──────────────────────────────────────────────────────────────

    @Override
    public ClientDTO saveClient(ClientDTO dto) {
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        return clientMapper.toDTOList(clientRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client non trouvé avec l'id : " + id));
        return clientMapper.toDTO(client);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client non trouvé avec l'id : " + id));
        client.setNom(dto.nom());
        client.setEmail(dto.email());
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client non trouvé avec l'id : " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrat> getContratsByClient(Long clientId) {
        return contratRepository.findByClientId(clientId);
    }

    // ── Contrats Automobile ──────────────────────────────────────────────────

    @Override
    public ContratAutomobileDTO saveContratAuto(ContratAutomobileDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client non trouvé avec l'id : " + dto.getClientId()));
        ContratAutomobile contrat = contratMapper.toEntity(dto);
        contrat.setClient(client);
        return contratMapper.toDTO(contratAutoRepository.save(contrat));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratAutomobileDTO> getAllContratsAuto() {
        return contratMapper.toDTOListAuto(contratAutoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ContratAutomobileDTO getContratAutoById(Long id) {
        ContratAutomobile contrat = contratAutoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat automobile non trouvé avec l'id : " + id));
        return contratMapper.toDTO(contrat);
    }

    // ── Contrats Habitation ──────────────────────────────────────────────────

    @Override
    public ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client non trouvé avec l'id : " + dto.getClientId()));
        ContratHabitation contrat = contratMapper.toEntity(dto);
        contrat.setClient(client);
        return contratMapper.toDTO(contratHabitationRepository.save(contrat));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratHabitationDTO> getAllContratsHabitation() {
        return contratMapper.toDTOListHabitation(contratHabitationRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ContratHabitationDTO getContratHabitationById(Long id) {
        ContratHabitation contrat = contratHabitationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat habitation non trouvé avec l'id : " + id));
        return contratMapper.toDTO(contrat);
    }

    // ── Contrats Santé ───────────────────────────────────────────────────────

    @Override
    public ContratSanteDTO saveContratSante(ContratSanteDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client non trouvé avec l'id : " + dto.getClientId()));
        ContratSante contrat = contratMapper.toEntity(dto);
        contrat.setClient(client);
        return contratMapper.toDTO(contratSanteRepository.save(contrat));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratSanteDTO> getAllContratsSante() {
        return contratMapper.toDTOListSante(contratSanteRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ContratSanteDTO getContratSanteById(Long id) {
        ContratSante contrat = contratSanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrat santé non trouvé avec l'id : " + id));
        return contratMapper.toDTO(contrat);
    }

    // ── Statut ───────────────────────────────────────────────────────────────

    @Override
    public ContratAutomobileDTO validerContrat(Long contratId) {
        ContratAutomobile contrat = contratAutoRepository.findById(contratId)
                .orElseThrow(() -> new EntityNotFoundException("Contrat automobile non trouvé avec l'id : " + contratId));
        contrat.setStatut(StatutContrat.VALIDE);
        contrat.setDateValidation(LocalDate.now());
        return contratMapper.toDTO(contratAutoRepository.save(contrat));
    }

    @Override
    public void resilierContrat(Long contratId) {
        Contrat contrat = contratRepository.findById(contratId)
                .orElseThrow(() -> new EntityNotFoundException("Contrat non trouvé avec l'id : " + contratId));
        contrat.setStatut(StatutContrat.RESILIE);
        contratRepository.save(contrat);
    }

    // ── Paiements ────────────────────────────────────────────────────────────

    @Override
    public PaiementDTO savePaiement(PaiementDTO dto) {
        Contrat contrat = contratRepository.findById(dto.contratId())
                .orElseThrow(() -> new EntityNotFoundException("Contrat non trouvé avec l'id : " + dto.contratId()));
        Paiement paiement = paiementMapper.toEntity(dto);
        paiement.setContrat(contrat);
        return paiementMapper.toDTO(paiementRepository.save(paiement));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> getPaiementsByContrat(Long contratId) {
        return paiementMapper.toDTOList(paiementRepository.findByContratId(contratId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementDTO> getAllPaiements() {
        return paiementMapper.toDTOList(paiementRepository.findAll());
    }
}
