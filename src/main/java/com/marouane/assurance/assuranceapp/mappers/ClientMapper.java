package com.marouane.assurance.assuranceapp.mappers;

import com.marouane.assurance.assuranceapp.dtos.ClientDTO;
import com.marouane.assurance.assuranceapp.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);

    @Mapping(target = "contrats", ignore = true)
    Client toEntity(ClientDTO dto);

    List<ClientDTO> toDTOList(List<Client> clients);
}
