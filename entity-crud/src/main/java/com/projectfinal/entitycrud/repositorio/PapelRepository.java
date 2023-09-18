package com.projectfinal.entitycrud.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectfinal.entitycrud.modelo.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {

		Papel findByPapel(String papel);
}
