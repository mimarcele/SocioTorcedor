package com.everis.desafio_campanha.services;

import com.everis.desafio_campanha.dto.CampanhaDto;
import com.everis.desafio_campanha.dto.TimeDoCoracaoDto;
import com.everis.desafio_campanha.mappers.MapperTimeDoCoracaoTimeDoCoracaoDto;
import com.everis.desafio_campanha.repositorys.TimeDoCoracaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TimeDoCoracaoServiceTest {

    @InjectMocks
    @Autowired
    private TimeDoCoracaoService timeDoCoracaoService;

    @Mock
    private TimeDoCoracaoRepository timeDoCoracaoRepository;

    private MapperTimeDoCoracaoTimeDoCoracaoDto mapperTimeDoCoracaoTimeDoCoracaoDto;

    @Test
    void criarTimeDoCoracao() {
        //Cenario
        TimeDoCoracaoDto timeDoCoracaoDto = new TimeDoCoracaoDto();
        timeDoCoracaoDto.setId(0L);
        timeDoCoracaoDto.setNome("TimeFlamengoTest");

        //Acao
        TimeDoCoracaoDto response = timeDoCoracaoService.criarTimeDoCoracao(timeDoCoracaoDto);

        //Validacao
        assertEquals(response, timeDoCoracaoDto);
    }

    @Test
    void pesquisarTimesDisponveis() {
        //Cenario
        TimeDoCoracaoDto timeDoCoracaoDto = new TimeDoCoracaoDto();
        timeDoCoracaoDto.setId(0L);
        timeDoCoracaoDto.setNome("TimeFlamengoTest");

        //Acao

        //Validacao

    }

    @Test
    void listarTimesCadastrados() {
        //Cenario

        //Acao

        //Validacao
    }

    @Test
    void excluirTimeDoCoracao() {
    }
}