package com.gabriel.aula.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.aula.model.CarroModel;
import com.gabriel.aula.model.InfracaoModel;
import com.gabriel.aula.repository.InfracaoRepository;

@Service
public class InfracaoService {
	
	@Autowired
	InfracaoRepository infracaoRepository;
	
	public List<InfracaoModel>buscarTodos(){
		return infracaoRepository.findAll();
		
	}
	public void salvar(InfracaoModel infracaoModel) {
		infracaoRepository.save(infracaoModel);
	}
	public InfracaoModel procurar(Integer id) {
		return infracaoRepository.getById(id);
	}
	public void deletar(Integer id) {
		infracaoRepository.deleteById(id);
	}
	public InfracaoModel BuscarPorDescricao(String descricao) {
		return infracaoRepository.findByDescricao(descricao);
	}
	
	
	

}
