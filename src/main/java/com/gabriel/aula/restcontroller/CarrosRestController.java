package com.gabriel.aula.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.aula.model.CarroModel;
import com.gabriel.aula.service.CarroService;

@RestController
public class CarrosRestController {
	
	@Autowired
	CarroService carroService;
	
	@GetMapping(value= {"/api"})
	public ResponseEntity<String> inicial(){
		return new ResponseEntity<String>("Método", HttpStatus.OK);
		
	}
	
	@PostMapping(value = { "/carro/salvar" })
	public ResponseEntity<String> salvar(@RequestBody/* @Valid*/ CarroModel carro) {
		carroService.salvar(carro);
		return new ResponseEntity<String>("Salvo com sucesso", HttpStatus.OK);

	}
	
	@GetMapping(value = {"/carro/listar"})
	public ResponseEntity<List<CarroModel>> listarCarros(){
		List<CarroModel> carro = carroService.buscarTodos();
		if(carro.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
			return new ResponseEntity<List<CarroModel>>(carro, HttpStatus.OK);
		
	}
	
	@GetMapping(value = {"/carro/buscar/{id}"})
	public ResponseEntity<String> buscarPorPlaca(@PathVariable String id){
		CarroModel carro = carroService.buscarPorPlaca(id);
		if(carro!=null) {
			
			return new ResponseEntity<String>(carro.getPlaca(),HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(value={"/carro/alterar/{id}"})
	public ResponseEntity<CarroModel>alterar(@PathVariable String id,/*@Valid*/ @RequestBody CarroModel carro){
		CarroModel carroQueSeraAlterado = carroService.buscarPorPlaca(id);
		if(carroQueSeraAlterado != null) {
			carroQueSeraAlterado.setPlaca(carro.getPlaca());
			carroService.salvar(carroQueSeraAlterado);
			return new ResponseEntity<CarroModel>(carroQueSeraAlterado,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<CarroModel>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = {"/carro/remover/{id}"})
	public ResponseEntity<String> removerPorPlaca(@PathVariable String id){
		CarroModel carro = carroService.buscarPorPlaca(id);
		if(carro!=null) {
			carroService.deletar(carro.getId());
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
}
