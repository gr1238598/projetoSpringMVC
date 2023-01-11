package com.gabriel.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.aula.model.MultaModel;

public interface MultaRepository extends JpaRepository<MultaModel, Integer>{
	public MultaModel findByCidade(String cidade);
}
