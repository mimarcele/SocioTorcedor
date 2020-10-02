package com.everis.desafio_campanha.controllers;

import com.everis.desafio_campanha.dto.CampanhaDto;
import com.everis.desafio_campanha.dto.TorcedorDto;
import com.everis.desafio_campanha.entities.Campanha;
import com.everis.desafio_campanha.mappers.MapperCampanhaCampanhaDto;
import com.everis.desafio_campanha.repositorys.CampanhaRepository;
import com.everis.desafio_campanha.services.CampanhaService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private MapperCampanhaCampanhaDto mapperCampanhaCampanhaDto;

    @ApiOperation(value = "Listar Todas as Campanhas do Banco de dados")
    @GetMapping("/campanha/list")
    public ResponseEntity<List<CampanhaDto>> listaCampanhas() {
        List<Campanha> campanhas = campanhaService.procurarDataVencida();

        List<CampanhaDto> campanhaDtos = campanhas
                .stream()
                .filter(c-> c.getTimeDoCoracao() != null)
                .map (campanha -> {
                    CampanhaDto campanhaDto = mapperCampanhaCampanhaDto.toDto(campanha);
                log.info("campanha: {}", campanha);
                    return campanhaDto;
                })
                .collect (Collectors.toList ( ));

        return ResponseEntity.ok (campanhaDtos);
    }

    @ApiOperation(value = "Criar nova campanha no banco de dados")
    @PostMapping("/campanha/save")
    public ResponseEntity<CampanhaDto> criarCampanha(@RequestBody CampanhaDto campanhaDto) {
        CampanhaDto campanhaDto1 = campanhaService.criarCampanha (campanhaDto);
        return ResponseEntity.ok (campanhaDto1);
    }

    @ApiOperation(value = "Deletar uma campanha do Banco de dados")
    @DeleteMapping("/campanha/deletar/{id}")
    public void deletarCampanha(@PathVariable Long id) {
        campanhaService.excluirCampanha (id);
    }


    @ApiOperation(value = "Atualizar campanha no banco de dados")
    @PutMapping("/campanha/atualizar/{id}")
    public void atualizarCampanha(@RequestBody CampanhaDto campanhaDto) {
        campanhaService.atualizarCampanha (campanhaDto);

    }
    @ApiOperation(value = "Consultar campanha no banco de dados")
    @GetMapping("/campanha/consultar/{id}")
    public ResponseEntity<CampanhaDto> consultarCampanha(@PathVariable Long id) {
        CampanhaDto campanhaDto = campanhaService.consultarCampanha (id);
        return ResponseEntity.ok (campanhaDto);
    }

    @ApiOperation(value = "Adicionar o torcedor a campanha desejada")
    @PostMapping("/campanha/{idcampanha}/add/torcedor")
    public ResponseEntity<CampanhaDto> adicionarTorcedor(@PathVariable Long idcampanha, Long idtorcedor) {
        CampanhaDto campanhaDto = campanhaService.adicionarTorcedor(idtorcedor, idcampanha);

        return ResponseEntity.ok (campanhaDto);
    }

    @ApiOperation(value = "Listar torcedores relacionados a campanha")
    @GetMapping("/campanha/torcedores/listar")
    public ResponseEntity<List<TorcedorDto>> listaTorcedorCampanha(Long idcampanha) {
        List<TorcedorDto> torcedorDtos = campanhaService.buscarTorcedoresRelacionadoACampanha(idcampanha);
        return ResponseEntity.ok (torcedorDtos);

    }
}