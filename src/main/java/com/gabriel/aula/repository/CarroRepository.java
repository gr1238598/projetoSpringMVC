package com.gabriel.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.aula.model.CarroModel;

public interface CarroRepository extends JpaRepository<CarroModel, Integer>{
	public CarroModel findByPlaca(String placa);

}
