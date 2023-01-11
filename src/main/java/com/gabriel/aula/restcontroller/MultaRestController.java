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

import com.gabriel.aula.dto.MultaDto;
import com.gabriel.aula.model.CarroModel;
import com.gabriel.aula.model.InfracaoModel;
import com.gabriel.aula.model.MultaModel;
import com.gabriel.aula.service.CarroService;
import com.gabriel.aula.service.InfracaoService;
import com.gabriel.aula.service.MultaService;

@RestController
public class MultaRestController {
	@Autowired
	MultaService multaService;
	
	@Autowired
	CarroService carroService;
	
	@Autowired
	InfracaoService infracaoService;
	
	
	@GetMapping(value = {"/multa"})
	public ResponseEntity<List<MultaDto>> listarMultas(){
		
		List<MultaModel> listaMultas = multaService.buscarTodos();
		
		try {
		if(listaMultas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<MultaDto>>(MultaDto.converter(listaMultas), HttpStatus.OK);
		}catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(value = { "/multa/salvar" })
	public ResponseEntity<String> salvar(@RequestBody/* @Valid*/ MultaModel multa) {
		
		multaService.salvar(multa);
		return new ResponseEntity<String>("Salvo com sucesso", HttpStatus.OK);

	}
	
	
	@GetMapping(value = {"/multa2/buscar/{id}"})
	public ResponseEntity<MultaDto> buscarPorCidade(@PathVariable String id){
		MultaModel multa = multaService.buscarPorCidade(id);
		if(multa!=null) {
			//infracao.procurar(id);
			return new ResponseEntity<MultaDto>(MultaDto.converter2(multa),HttpStatus.OK);
		}else {
			return new ResponseEntity<MultaDto>(HttpStatus.NOT_FOUND);
		}
		
	}
	@GetMapping(value = {"/multa/buscarId/{id}"})
	public ResponseEntity<MultaDto> buscarPorI(@PathVariable Integer id){
		
		MultaModel multa = multaService.procurar(id);
		if(multa!=null) {
			//infracao.procurar(id);
			return new ResponseEntity<MultaDto>(MultaDto.converter2(multa),HttpStatus.OK);
		}else {
			return new ResponseEntity<MultaDto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = {"/multa/remover/{id}"})
	public ResponseEntity<String> removerPorCidade(@PathVariable String id){
		MultaModel multa = multaService.buscarPorCidade(id);
		if(multa!=null) {
			multaService.deletar(multa.getId());
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@DeleteMapping(value = {"/multa/removerId/{id}"})
	public ResponseEntity<String> removerPorId(@PathVariable Integer id){
		MultaModel multa = multaService.procurar(id);
		if(multa!=null) {
			multaService.deletar(multa.getId());
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PutMapping(value={"/multa/alterar/{id}"})
	public ResponseEntity<MultaDto>alterar(@PathVariable Integer id,/*@Valid*/ @RequestBody MultaModel multa){
		MultaModel multaQueSeraAlterado = multaService.procurar(id);
		
		
		//CarroModel carro = carroService.procurar(multaQueSeraAlterado.getCarro().getId());
		//InfracaoModel infracao = infracaoService.procurar(multaQueSeraAlterado.getInfracao().getId());
		
		if(multaQueSeraAlterado != null) {
			
			multaQueSeraAlterado.setAno(multa.getAno());
			multaQueSeraAlterado.setCidade(multa.getCidade());
			
			multaQueSeraAlterado.setInfracao(new InfracaoModel(multa.getInfracao().getDescricao()));
			multaQueSeraAlterado.setCarro(new CarroModel(multa.getCarro().getPlaca()));
			multaService.salvar(multaQueSeraAlterado);
			return new ResponseEntity<MultaDto>(MultaDto.converter2(multaQueSeraAlterado),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<MultaDto>(HttpStatus.NOT_FOUND);
		}
	}
}

