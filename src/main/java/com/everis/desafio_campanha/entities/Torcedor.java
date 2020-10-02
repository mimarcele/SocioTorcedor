package com.everis.desafio_campanha.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Torcedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nomeCompleto;
    private LocalDate dataDeNascimento;
    private String email;
    private String senha;
    private String confirmarSenha;

    @ManyToOne
    @JoinColumn(name = "nome")
    private TimeDoCoracao timeDoCoracao;


}
