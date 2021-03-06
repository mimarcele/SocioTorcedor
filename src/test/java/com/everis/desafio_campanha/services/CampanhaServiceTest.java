package com.everis.desafio_campanha.services;

import com.everis.desafio_campanha.mappers.MapperCampanhaCampanhaDto;
import com.everis.desafio_campanha.repositorys.CampanhaRepository;
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
public class CampanhaServiceTest {

    @InjectMocks
    @Autowired
    private CampanhaService campanhaService;

    @Mock
    private CampanhaRepository campanhaRepository;

    private MapperCampanhaCampanhaDto mapperCampanhaCampanhaDto;

    @Test
    void criarCampanha() {
    }

    @Test
    void temDataFinalIgual() {
    }

    @Test
    void calculaNovaDataVigencia() {
    }

    @Test
    void procurarDataVencida() {
    }

    @Test
    void mostrarCampanhas() {
    }

    @Test
    void excluirCampanha() {
    }

    @Test
    void atualizarCampanha() {
    }

    @Test
    void consultarCampanha() {
    }

    @Test
    void adicionarTorcedor() {
    }

    @Test
    void buscarTorcedoresRelacionadoACampanha() {
    }
}