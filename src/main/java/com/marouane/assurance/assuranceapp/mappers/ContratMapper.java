package com.marouane.assurance.assuranceapp.mappers;

import com.marouane.assurance.assuranceapp.dtos.ContratAutomobileDTO;
import com.marouane.assurance.assuranceapp.dtos.ContratHabitationDTO;
import com.marouane.assurance.assuranceapp.dtos.ContratSanteDTO;
import com.marouane.assurance.assuranceapp.entities.ContratAutomobile;
import com.marouane.assurance.assuranceapp.entities.ContratHabitation;
import com.marouane.assurance.assuranceapp.entities.ContratSante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratMapper {

    // ── Automobile ──────────────────────────────────────────────────────────

    @Mapping(source = "client.id", target = "clientId")
    ContratAutomobileDTO toDTO(ContratAutomobile contrat);

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    ContratAutomobile toEntity(ContratAutomobileDTO dto);

    List<ContratAutomobileDTO> toDTOListAuto(List<ContratAutomobile> contrats);

    // ── Habitation ──────────────────────────────────────────────────────────

    @Mapping(source = "client.id", target = "clientId")
    ContratHabitationDTO toDTO(ContratHabitation contrat);

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    ContratHabitation toEntity(ContratHabitationDTO dto);

    List<ContratHabitationDTO> toDTOListHabitation(List<ContratHabitation> contrats);

    // ── Santé ───────────────────────────────────────────────────────────────

    @Mapping(source = "client.id", target = "clientId")
    ContratSanteDTO toDTO(ContratSante contrat);

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "paiements", ignore = true)
    ContratSante toEntity(ContratSanteDTO dto);

    List<ContratSanteDTO> toDTOListSante(List<ContratSante> contrats);
}
