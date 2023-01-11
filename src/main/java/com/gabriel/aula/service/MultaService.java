package com.gabriel.aula.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.aula.model.CarroModel;
import com.gabriel.aula.model.InfracaoModel;
import com.gabriel.aula.model.MultaModel;
import com.gabriel.aula.repository.CarroRepository;
import com.gabriel.aula.repository.MultaRepository;

@Service
public class MultaService {
	@Autowired
	private MultaRepository multas;
	@Autowired
	private CarroRepository carros;

	public List<MultaModel> buscarTodos() {
		return multas.findAll();
	}

	public void salvar(MultaModel multa) {
		CarroModel carro = carros.findByPlaca(multa.getCarro().getPlaca());
		multa.setCarro(carro);
		multas.save(multa);
	}
	public MultaModel buscarPorCidade(String cidade) {
		return multas.findByCidade(cidade);
	}
	
	public MultaModel procurar(Integer id) {
		return multas.getById(id);
	}
	public void deletar(Integer id) {
		multas.deleteById(id);
	}
}
