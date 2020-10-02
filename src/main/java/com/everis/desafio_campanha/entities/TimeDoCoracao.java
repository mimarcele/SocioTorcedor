package com.everis.desafio_campanha.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TimeDoCoracao  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}
