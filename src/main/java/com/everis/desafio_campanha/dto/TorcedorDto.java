package com.everis.desafio_campanha.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class TorcedorDto {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String dataDeNascimento;
    private String senha;
    private String confirmarSenha;
    private TimeDoCoracaoDto timeDoCoracao;
}
