package com.everis.desafio_campanha.controllers;


import com.everis.desafio_campanha.dto.TimeDoCoracaoDto;
import com.everis.desafio_campanha.services.TimeDoCoracaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/timedocoracao")
public class TimeDoCoracaoController {


    @Autowired
    private TimeDoCoracaoService timeDoCoracaoService;

    @ApiOperation(value = "Criar novo time no banco de dados")
    @PostMapping("/save")
    public void criarTimeDoCoracao(@RequestBody TimeDoCoracaoDto timeDoCoracaoDto){
        timeDoCoracaoService.criarTimeDoCoracao(timeDoCoracaoDto);
    }

    @ApiOperation(value = "Consultar times no banco de dados")
    @GetMapping("/{nome}")
    public ResponseEntity<TimeDoCoracaoDto> consultarTimeDoCoracao(@PathVariable String nome){
        TimeDoCoracaoDto timeDoCoracaoDto = timeDoCoracaoService.pesquisarTimesDisponveis(nome);
        return ResponseEntity.ok(timeDoCoracaoDto);
    }

    @ApiOperation(value = "Listar times cadastrados no banco de dados")
    @GetMapping()
    public ResponseEntity<List<TimeDoCoracaoDto>> listarTimesCadastrados(){
        List<TimeDoCoracaoDto> timeDoCoracaoDtos = timeDoCoracaoService.listarTimesCadastrados();
        return ResponseEntity.ok(timeDoCoracaoDtos);
    }

    @ApiOperation(value = "Deletar time do banco de dados")
    @DeleteMapping("/deletar/{nome}")
    public void deletarTime(@PathVariable String nome){
        timeDoCoracaoService.excluirTimeDoCoracao(nome);
    }

}
