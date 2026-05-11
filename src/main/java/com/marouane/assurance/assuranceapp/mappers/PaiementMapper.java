package com.marouane.assurance.assuranceapp.mappers;

import com.marouane.assurance.assuranceapp.dtos.PaiementDTO;
import com.marouane.assurance.assuranceapp.entities.Paiement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    @Mapping(source = "contrat.id", target = "contratId")
    PaiementDTO toDTO(Paiement paiement);

    @Mapping(target = "contrat", ignore = true)
    Paiement toEntity(PaiementDTO dto);

    List<PaiementDTO> toDTOList(List<Paiement> paiements);
}
