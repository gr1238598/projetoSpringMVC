package com.gabriel.aula.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.aula.model.CarroModel;
import com.gabriel.aula.repository.CarroRepository;

@Service
public class CarroService {
	@Autowired
	private CarroRepository carros;

	public List<CarroModel> buscarTodos() {
		return carros.findAll();
	}

	public void salvar(CarroModel carro) {
		carros.save(carro);
	}

	public CarroModel procurar(Integer id) {
		return carros.getById(id);
	}

	public void deletar(Integer id) {
		carros.deleteById(id);
	}
	
	public CarroModel buscarPorPlaca(String placa) {
		return carros.findByPlaca(placa);
	}
}