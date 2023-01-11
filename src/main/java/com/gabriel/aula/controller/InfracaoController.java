package com.gabriel.aula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gabriel.aula.model.InfracaoModel;
import com.gabriel.aula.service.InfracaoService;

@Controller
public class InfracaoController {

	@Autowired
	private InfracaoService infracaoService;
	
	@GetMapping("/infracao")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listaInfracoes");
		modelAndView.addObject("infracao",infracaoService.buscarTodos());
		modelAndView.addObject(new InfracaoModel());
		
		return modelAndView;
	}
	
	@PostMapping("/infracao")
	public String salvar(InfracaoModel infracaoModel, RedirectAttributes attributes) {
		infracaoService.salvar(infracaoModel);
		attributes.addFlashAttribute("tipo", "success");
		attributes.addFlashAttribute("mensagem", "Salvo!");
		return "redirect:/infracao";
	}
	@GetMapping("/infracao2/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("EditarInfracao");
		modelAndView.addObject(infracaoService.procurar(id));
		return modelAndView;
		
		
	}
	@GetMapping("/infracao/deletar/{id}")
	public String deletar(@PathVariable("id") Integer id, RedirectAttributes attributes) {
		
		try {
			infracaoService.deletar(id);
			attributes.addFlashAttribute("tipo", "info");
			attributes.addFlashAttribute("mensagem", "Ocorreu um problema, verifique com o administrador do sistema");
		}catch(Exception e){
			attributes.addFlashAttribute("tipo", "danger");
			attributes.addFlashAttribute("mensagem", e.getMessage());
		}
		
		
		return "redirect:/infracao";
		
	}
	@GetMapping("/")
	public String principal() {
		return "redirect:/infracao";
	}


}
