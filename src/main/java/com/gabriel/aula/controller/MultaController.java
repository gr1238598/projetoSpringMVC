package com.gabriel.aula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.aula.model.MultaModel;
import com.gabriel.aula.service.InfracaoService;
import com.gabriel.aula.service.MultaService;

@Controller
public class MultaController {
	@Autowired
	private MultaService multaService;
	@Autowired
	private InfracaoService infracaoService;

	@GetMapping("/multas")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("ListaMultas");
		modelAndView.addObject("multa", multaService.buscarTodos());
		modelAndView.addObject(new MultaModel());
		modelAndView.addObject("infracoes", infracaoService.buscarTodos());/**/
		
		return modelAndView;
	}

	@PostMapping("/multas")
	public String salvar(MultaModel multa) {
		multaService.salvar(multa);
		return"redirect:/multas";
	
	}
}
