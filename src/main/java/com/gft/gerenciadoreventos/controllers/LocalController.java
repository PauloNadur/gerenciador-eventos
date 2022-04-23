package com.gft.gerenciadoreventos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.gerenciadoreventos.entities.Local;
import com.gft.gerenciadoreventos.services.EventoService;
import com.gft.gerenciadoreventos.services.LocalService;

@Controller
@RequestMapping("local")
public class LocalController {

	@Autowired
	private LocalService localService;

	@Autowired
	private EventoService eventoService;

	@RequestMapping(path = "editar")
	public ModelAndView editarLocal(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("local/formLocal.html");
		Local local;

		if (id == null) {
			local = new Local();
		} else {
			try {
				local = localService.obterLocal(id);
			} catch (Exception e) {
				local = new Local();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("local", local);
		mv.addObject("listaEventos", eventoService.listarEvento());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarLocal(@Valid Local local, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("local/formLocal.html");
		
		boolean novo = true;
		if (local.getId() != null) {
			novo = false;
		}
		if (bindingResult.hasErrors()) {
			mv.addObject("local", local);
			return mv;
		}
		localService.salvarLocal(local);
		if (novo) {
			mv.addObject("local", new Local());
		} else {
			mv.addObject("local", local);
		}
		mv.addObject("mensagem", "Local salvo com sucesso!");
		mv.addObject("listaEventos", eventoService.listarEvento());
		return mv;
	}

	@RequestMapping
	public ModelAndView listarLocal(String nome, String endereco) {
		ModelAndView mv = new ModelAndView("local/listaLocal.html");
		mv.addObject("lista", localService.listarLocal());
		return mv;
	}

	@RequestMapping("/excluir")
	public ModelAndView excluirLocal(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/local");
		try {
			localService.excluirLocal(id);
			redirectAttributes.addFlashAttribute("mensagem", "Local excluído com sucesso.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir local!" + e.getMessage());
		}
		return mv;
	}
}
