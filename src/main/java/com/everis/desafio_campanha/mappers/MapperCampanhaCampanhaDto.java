package com.everis.desafio_campanha.mappers;

import com.everis.desafio_campanha.entities.Campanha;
import org.mapstruct.Mapper;

import com.everis.desafio_campanha.dto.CampanhaDto;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {MapperTimeDoCoracaoTimeDoCoracaoDto.class, MapperTorcedorTorcedorDto.class})
public interface MapperCampanhaCampanhaDto {

    @Mapping(source = "dataVigenciaInicial", target = "dataVigenciaInicial", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "dataVigenciaFinal", target = "dataVigenciaFinal", dateFormat = "dd/MM/yyyy")
    CampanhaDto toDto(Campanha campanha);

    @Mapping(source = "dataVigenciaInicial", target = "dataVigenciaInicial", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "dataVigenciaFinal", target = "dataVigenciaFinal", dateFormat = "dd/MM/yyyy")
    Campanha toEntity(CampanhaDto campanhaDto);
}


