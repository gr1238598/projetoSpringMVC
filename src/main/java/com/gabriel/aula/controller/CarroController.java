package com.gabriel.aula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.aula.model.CarroModel;
import com.gabriel.aula.service.CarroService;

@Controller
public class CarroController {
	@Autowired
	private CarroService carroService;
	
	
	@GetMapping("/carros")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("ListaCarros");
		modelAndView.addObject("carros", carroService.buscarTodos());
		modelAndView.addObject(new CarroModel());
		return modelAndView;
	}

	@PostMapping("/carros")
	public String salvar(CarroModel carro) {
		carroService.salvar(carro);
		return "redirect:/carros";
	}

	@GetMapping("/carro/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("EditarCarros");
		modelAndView.addObject(carroService.procurar(id));
		return modelAndView;
	}

	@GetMapping("/carro/deletar/{id}")
	public String deletar(@PathVariable("id") Integer id) {
		carroService.deletar(id);
		return "redirect:/carros";
	}
}
