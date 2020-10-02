package com.everis.desafio_campanha.controllers;

import com.everis.desafio_campanha.repositorys.TorcedorRepository;
import com.everis.desafio_campanha.services.TorcedorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TorcedorControllerTest {

    @InjectMocks
    @Autowired
    private TorcedorService torcedorService;

    @Mock
    private TorcedorRepository torcedorRepository;

    @Test
    void criarSocioTorcedor() {
    }

    @Test
    void consultarTorcedor() {
    }

    @Test
    void deletarTorcedor() {
    }

    @Test
    void listarTorcedores() {
    }

    @Test
    void listaTorcedorCampanha() {
    }
}