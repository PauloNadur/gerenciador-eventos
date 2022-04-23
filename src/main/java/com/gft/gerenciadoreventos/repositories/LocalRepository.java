package com.gft.gerenciadoreventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.gerenciadoreventos.entities.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
	
}
