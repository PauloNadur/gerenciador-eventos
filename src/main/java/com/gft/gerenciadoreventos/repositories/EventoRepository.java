package com.gft.gerenciadoreventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.gerenciadoreventos.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
	
}
