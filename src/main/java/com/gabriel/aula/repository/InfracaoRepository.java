package com.gabriel.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.aula.model.InfracaoModel;

public interface InfracaoRepository extends JpaRepository<InfracaoModel, Integer> {
	public InfracaoModel findByDescricao(String descricao);
	
	
	
}
