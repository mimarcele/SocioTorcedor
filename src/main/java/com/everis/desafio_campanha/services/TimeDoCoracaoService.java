package com.everis.desafio_campanha.services;

import com.everis.desafio_campanha.dto.TimeDoCoracaoDto;
import com.everis.desafio_campanha.entities.TimeDoCoracao;
import com.everis.desafio_campanha.exceptions.TimeNaoEncontradoException;
import com.everis.desafio_campanha.mappers.MapperTimeDoCoracaoTimeDoCoracaoDto;
import com.everis.desafio_campanha.repositorys.TimeDoCoracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeDoCoracaoService {

    @Autowired
    private TimeDoCoracaoRepository timeDoCoracaoRepository;

    @Autowired
    private MapperTimeDoCoracaoTimeDoCoracaoDto mapperTimeDoCoracaoTimeDoCoracaoDto;

    public TimeDoCoracaoDto criarTimeDoCoracao(TimeDoCoracaoDto timeDoCoracaoDto) {
        TimeDoCoracao timeDoCoracao = mapperTimeDoCoracaoTimeDoCoracaoDto.toEntity(timeDoCoracaoDto);

        timeDoCoracaoRepository.save (timeDoCoracao);

        return timeDoCoracaoDto;
    }


    public TimeDoCoracaoDto pesquisarTimesDisponveis(String nomeTimeDoCoracao) {
        TimeDoCoracao timeDoCoracaoBD = timeDoCoracaoRepository.findByNomeEquals(nomeTimeDoCoracao);

        if (timeDoCoracaoBD == null) {
            throw new TimeNaoEncontradoException ("Time não encontrado");
        }
        TimeDoCoracaoDto timeDoCoracaoDto1 = mapperTimeDoCoracaoTimeDoCoracaoDto.toDto(timeDoCoracaoBD);

        return timeDoCoracaoDto1;
    }

    public List<TimeDoCoracaoDto> listarTimesCadastrados() {
        List<TimeDoCoracao> timeDoCoracoes = timeDoCoracaoRepository.findAll();

        if (timeDoCoracoes == null) {
            throw new TimeNaoEncontradoException ("Time não encontrado");
        }

        List<TimeDoCoracaoDto> timeDoCoracaoDtos = timeDoCoracoes
                .stream ( )
                .map (timeDoCoracao -> {
                    TimeDoCoracaoDto timeDoCoracaoDto = mapperTimeDoCoracaoTimeDoCoracaoDto.toDto(timeDoCoracao);
//                    timeDoCoracaoDto.setId (timeDoCoracao.getId ( ));
//                    timeDoCoracaoDto.setTimeDoCoracao (timeDoCoracao.getNomeTimeDoCoracao ());
                    return timeDoCoracaoDto;
                })
                .collect (Collectors.toList());

            return timeDoCoracaoDtos;
    }
    public void excluirTimeDoCoracao(String nome){
        TimeDoCoracao timeDoCoracao = timeDoCoracaoRepository.findByNomeEquals(nome);

        if(timeDoCoracao == null){
            throw new TimeNaoEncontradoException ("Time não encontrado");
        }
        timeDoCoracaoRepository.delete(timeDoCoracao);
    }

}





