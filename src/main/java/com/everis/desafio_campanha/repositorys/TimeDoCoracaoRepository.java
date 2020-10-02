package com.everis.desafio_campanha.repositorys;

import com.everis.desafio_campanha.entities.TimeDoCoracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TimeDoCoracaoRepository extends JpaRepository<TimeDoCoracao, Long> {

     TimeDoCoracao findByNomeEquals(String nome);
}
