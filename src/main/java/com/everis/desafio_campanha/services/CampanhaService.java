package com.everis.desafio_campanha.services;

import com.everis.desafio_campanha.dto.CampanhaDto;
import com.everis.desafio_campanha.dto.TorcedorDto;
import com.everis.desafio_campanha.entities.Campanha;
import com.everis.desafio_campanha.entities.TimeDoCoracao;
import com.everis.desafio_campanha.entities.Torcedor;
import com.everis.desafio_campanha.exceptions.CampanhaNotFoundException;
import com.everis.desafio_campanha.exceptions.TorcedorNotFoundException;
import com.everis.desafio_campanha.mappers.MapperCampanhaCampanhaDto;
import com.everis.desafio_campanha.mappers.MapperTorcedorTorcedorDto;
import com.everis.desafio_campanha.repositorys.CampanhaRepository;
import com.everis.desafio_campanha.repositorys.TorcedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private TorcedorRepository torcedorRepository;

    @Autowired
    private MapperCampanhaCampanhaDto mapperCampanhaCampanhaDto;

    @Autowired
    private MapperTorcedorTorcedorDto mapperTorcedorTorcedorDto;

    public CampanhaDto criarCampanha(CampanhaDto campanhaDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Campanha> listaCampanhasBD = campanhaRepository
                .findByDataVigenciaInicialGreaterThanEqualAndDataVigenciaFinalLessThanEqual(
                        LocalDate.parse(campanhaDto.getDataVigenciaInicial(), formatter),
                        LocalDate.parse(campanhaDto.getDataVigenciaFinal(), formatter)

                );

        Campanha campanha = mapperCampanhaCampanhaDto.toEntity(campanhaDto);

        calculaNovaDataVigencia(campanha, listaCampanhasBD);

        listaCampanhasBD.add(campanha);

        campanhaRepository.save(campanha);

        CampanhaDto campanhaDto1 = mapperCampanhaCampanhaDto.toDto(campanha);

        return campanhaDto1;
    }

    public Boolean temDataFinalIgual(LocalDate dataFinal, List<Campanha> listaCampanhasBD) {
        return listaCampanhasBD
                .stream()
                .anyMatch(campanha -> {
                    return campanha.getDataVigenciaFinal().equals(dataFinal);
                });
    }


    //Repercusividade
    public void calculaNovaDataVigencia(Campanha novaCampanha, List<Campanha> listaCampanhasBD) {
        listaCampanhasBD
                .forEach(campanha -> {
                    if (
                            temDataFinalIgual(campanha.getDataVigenciaFinal().plusDays(1), listaCampanhasBD) &&
                                    campanha.getDataVigenciaFinal().equals(novaCampanha.getDataVigenciaFinal())
                    ) {
                        campanha.setDataVigenciaFinal(campanha.getDataVigenciaFinal().plusDays(1));
                    }
                    campanha.setDataVigenciaFinal(campanha.getDataVigenciaFinal().plusDays(1));
                });
    }

    public List<Campanha> procurarDataVencida() {
        List<Campanha> listaCampanhas = campanhaRepository.findByDataVigenciaFinalGreaterThanEqual(LocalDate.now());

        return listaCampanhas;
    }


    public List<Campanha> mostrarCampanhas() {
        List<Campanha> campanhas = campanhaRepository.findAll();
        return campanhas;
    }

    public void excluirCampanha(Long id) {
        Campanha campanha = campanhaRepository.findById(id).get();

        if (campanha == null) {
            throw new CampanhaNotFoundException("Campanha não encontrada");
        }
        campanhaRepository.delete(campanha);
    }

    public void atualizarCampanha(CampanhaDto campanhaDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Campanha campanha = campanhaRepository.findById(campanhaDto.getId()).get();

        if (campanha == null) {
            throw new CampanhaNotFoundException("Campanha não encontrada");
        }

        campanha.setNomeCampanha(campanhaDto.getNomeCampanha());

        TimeDoCoracao timeDoCoracao = new TimeDoCoracao();

        timeDoCoracao.setId(campanhaDto.getTimeDoCoracao().getId());
        timeDoCoracao.setNome(campanhaDto.getTimeDoCoracao().getNome());

        campanha.setTimeDoCoracao(timeDoCoracao);

        campanha.setDataVigenciaInicial(LocalDate.parse(campanhaDto.getDataVigenciaInicial(), formatter));
        campanha.setDataVigenciaFinal(LocalDate.parse(campanhaDto.getDataVigenciaFinal(), formatter));

        campanhaRepository.save(campanha);
    }

    public CampanhaDto consultarCampanha(Long id) {

        Campanha campanha = campanhaRepository.findById(id).get();

        if (campanha == null) {
            throw new CampanhaNotFoundException("Campanha não encontrada");
        }
        CampanhaDto campanhaDto = mapperCampanhaCampanhaDto.toDto(campanha);
        return campanhaDto;
    }

    public CampanhaDto adicionarTorcedor(Long idTorcedor, Long idCampanha) {

        Campanha campanha = campanhaRepository.findById(idCampanha).get();

        if (campanha == null) {
            throw new CampanhaNotFoundException("Campanha não encontrada");
        }

        Torcedor torcedor = torcedorRepository.findById(idTorcedor).get();

        if (torcedor == null) {
            throw new TorcedorNotFoundException("Torcedor não encontrado");
        }
        campanha.getTorcedores().add(torcedor);
        campanhaRepository.save(campanha);
        CampanhaDto campanhaDto = mapperCampanhaCampanhaDto.toDto(campanha);

        return campanhaDto;
    }

    public List<TorcedorDto> buscarTorcedoresRelacionadoACampanha(Long idCampanha) {
        Campanha campanha = campanhaRepository.findById(idCampanha).get();

        List<TorcedorDto> torcedorDtos = campanha.getTorcedores()
                .stream()
                .map(torcedor -> {
                    TorcedorDto torcedorDto = mapperTorcedorTorcedorDto.toDto(torcedor);

                    return torcedorDto;
                })
                .collect(Collectors.toList());
        return torcedorDtos;

    }
}


