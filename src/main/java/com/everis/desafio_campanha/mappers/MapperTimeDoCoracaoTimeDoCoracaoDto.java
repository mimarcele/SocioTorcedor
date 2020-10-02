package com.everis.desafio_campanha.mappers;


import com.everis.desafio_campanha.dto.TimeDoCoracaoDto;
import com.everis.desafio_campanha.entities.TimeDoCoracao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperTimeDoCoracaoTimeDoCoracaoDto {

    TimeDoCoracaoDto toDto(TimeDoCoracao timeDoCoracao);
    TimeDoCoracao toEntity(TimeDoCoracaoDto timeDoCoracaoDto);
}
