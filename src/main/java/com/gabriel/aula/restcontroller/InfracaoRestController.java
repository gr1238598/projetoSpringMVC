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

import com.gabriel.aula.model.InfracaoModel;
import com.gabriel.aula.model.MultaModel;
import com.gabriel.aula.service.InfracaoService;


@RestController
public class InfracaoRestController {
	@Autowired
	InfracaoService infracaoService;
	
	@GetMapping(value= {"/msgatividade"})
	public ResponseEntity<String> inicial(){
		return new ResponseEntity<String>("Método", HttpStatus.OK);
		
	}
	
	@PostMapping(value = { "/infracao/salvar" })
	public ResponseEntity<String> salvar(@RequestBody/* @Valid*/ InfracaoModel infracao) {
		infracaoService.salvar(infracao);
		return new ResponseEntity<String>("Salvo com sucesso", HttpStatus.OK);

	}
	
	@GetMapping(value = {"/infracoes"})
	public ResponseEntity<List<InfracaoModel>> listarInfracoes(){
		
		List<InfracaoModel> listaInfracao = infracaoService.buscarTodos();
		
		try {
		if(listaInfracao.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<InfracaoModel>>(listaInfracao, HttpStatus.OK);
		}catch(Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = {"/infracoes/buscar/{id}"})
	public ResponseEntity<InfracaoModel> buscarPorDescricao(@PathVariable String id){
		InfracaoModel infracao = infracaoService.BuscarPorDescricao(id);
		if(infracao!=null) {
			//infracao.procurar(id);
			return new ResponseEntity<InfracaoModel>(infracao,HttpStatus.OK);
		}else {
			return new ResponseEntity<InfracaoModel>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = {"/infracoes/buscarId/{id}"})
	public ResponseEntity<InfracaoModel> buscarPorI(@PathVariable Integer id){
		InfracaoModel infracao = infracaoService.procurar(id);
		if(infracao!=null) {
			//infracao.procurar(id);
			return new ResponseEntity<InfracaoModel>(infracao,HttpStatus.OK);
		}else {
			return new ResponseEntity<InfracaoModel>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(value={"/infracoes/alterar/{id}"})
	public ResponseEntity<InfracaoModel>alterar(@PathVariable String id, @RequestBody InfracaoModel infracao){
		InfracaoModel infracaoQueSeraAlterado = infracaoService.BuscarPorDescricao(id);
		if(infracaoQueSeraAlterado != null) {
			infracaoQueSeraAlterado.setDescricao(infracao.getDescricao());
			infracaoService.salvar(infracaoQueSeraAlterado);
			return new ResponseEntity<InfracaoModel>(infracaoQueSeraAlterado,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<InfracaoModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = {"/infracoes/remover/{id}"})
	public ResponseEntity<String> removerPorDescricao(@PathVariable String id){
		InfracaoModel infracao = infracaoService.BuscarPorDescricao(id);
		if(infracao!=null) {
			infracaoService.deletar(infracao.getId());
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
		
		
	}
	@DeleteMapping(value = {"/infracoes/removerId/{id}"})
	public ResponseEntity<String> removerPorId(@PathVariable Integer id){
		InfracaoModel infracao = infracaoService.procurar(id);
		if(infracao!=null) {
			infracaoService.deletar(infracao.getId());
			return new ResponseEntity<String>(HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	
	
	
}
