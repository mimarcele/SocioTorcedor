package com.everis.desafio_campanha.controllers;

import com.everis.desafio_campanha.dto.TorcedorDto;
import com.everis.desafio_campanha.entities.Torcedor;
import com.everis.desafio_campanha.mappers.MapperTorcedorTorcedorDto;
import com.everis.desafio_campanha.services.CampanhaService;
import com.everis.desafio_campanha.services.TorcedorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/torcedor")
public class TorcedorController {

    @Autowired
    private TorcedorService torcedorService;

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private MapperTorcedorTorcedorDto mapperTorcedorTorcedorDto;

    @ApiOperation(value = "Criar novo sócio torcedor no banco de dados")
    @PostMapping("/criarsocio")
    public void criarSocioTorcedor(@RequestBody TorcedorDto torcedorDto){
        torcedorService.criarConta(torcedorDto);
    }

    @ApiOperation(value = "Consultar torcedor no banco de dados")
    @GetMapping("/consultar/{id}")
    public ResponseEntity<TorcedorDto> consultarTorcedor(@PathVariable Long id){
        TorcedorDto torcedorDto = torcedorService.consultarTorcedor(id);
        return ResponseEntity.ok (torcedorDto);
    }

    @ApiOperation(value = "Deletar torcedor do banco de dados")
    @DeleteMapping("/deletar/{id}")
    public void deletarTorcedor(@PathVariable Long id){
        torcedorService.excluirTorcedor(id);
    }

    @ApiOperation(value = "Listar torcedores cadastrados no banco de dados")
    @GetMapping("/listatorcedor")
    public ResponseEntity<List<TorcedorDto>> listarTorcedores(){
        List<Torcedor> torcedores = torcedorService.listarTorcedores();


        List<TorcedorDto> torcedorDtos = torcedores
                .stream ()
                .map (torcedor -> {
                    TorcedorDto torcedorDto = mapperTorcedorTorcedorDto.toDto (torcedor);


                   return torcedorDto;

                })
                .collect (Collectors.toList());

        return ResponseEntity.ok(torcedorDtos);
    }

    @ApiOperation(value = "Listar torcedor relacionado a campanha do time desejado")
    @GetMapping("/campanha/torcedores/{id}")
    public ResponseEntity<List<TorcedorDto>> listaTorcedorCampanha(Long idcampanha) {
        List<TorcedorDto> torcedorDtos = campanhaService.buscarTorcedoresRelacionadoACampanha (idcampanha);

        return ResponseEntity.ok (torcedorDtos);

    }

}






