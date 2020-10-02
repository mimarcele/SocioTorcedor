package com.everis.desafio_campanha.mappers;

import com.everis.desafio_campanha.dto.TorcedorDto;
import com.everis.desafio_campanha.entities.Torcedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = { MapperTimeDoCoracaoTimeDoCoracaoDto.class })
public interface MapperTorcedorTorcedorDto {

    @Mapping(source = "dataDeNascimento", target = "dataDeNascimento", dateFormat = "dd/MM/yyyy")
    TorcedorDto toDto(Torcedor torcedor);


    @Mapping(source = "dataDeNascimento", target = "dataDeNascimento", dateFormat = "dd/MM/yyyy")
    Torcedor toEntity(TorcedorDto torcedorDto);
}
