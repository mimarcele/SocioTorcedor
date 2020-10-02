package com.everis.desafio_campanha.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "torcedor_campanhas", joinColumns = @JoinColumn(name = "campanha_id"),
    inverseJoinColumns = @JoinColumn(name = "torcedor_id"))
    private List<Torcedor> torcedores = new ArrayList<>();

    private String nomeCampanha;
    private LocalDate dataVigenciaInicial;
    private LocalDate dataVigenciaFinal;

    @OneToOne
    private TimeDoCoracao timeDoCoracao;
}
