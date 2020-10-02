package com.everis.desafio_campanha.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CampanhaDto {

    @ApiModelProperty(value = "Código da campanha")
    private Long id;

    private String nomeCampanha;
    private String dataVigenciaInicial;
    private String dataVigenciaFinal;
    private TimeDoCoracaoDto timeDoCoracao;



}
