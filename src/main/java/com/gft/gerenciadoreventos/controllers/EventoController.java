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

import com.gft.gerenciadoreventos.entities.Evento;
import com.gft.gerenciadoreventos.entities.enums.GeneroMusical;
import com.gft.gerenciadoreventos.services.EventoService;
import com.gft.gerenciadoreventos.services.LocalService;

@Controller
@RequestMapping("evento")
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private LocalService localService;

	@RequestMapping(path = "novo")
	public ModelAndView novoEvento(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("evento/formEvento.html");
		Evento evento;
		if (id == null) {
			evento = new Evento();
		} else {
			try {
				evento = eventoService.obterEvento(id);
			} catch (Exception e) {
				evento = new Evento();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		mv.addObject("evento", evento);
		mv.addObject("listaGenerosMusicais", GeneroMusical.values());
		mv.addObject("listaLocais", localService.listarLocal());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarEvento(@Valid Evento evento, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("evento/formEvento.html");
		boolean novo = true;
		if (evento.getId() != null) {
			novo = false;
		}
		if (bindingResult.hasErrors()) {
			mv.addObject("evento", evento);
			mv.addObject("listaGenerosMusicais", GeneroMusical.values());
			mv.addObject("listaLocais", localService.listarLocal());
			mv.addObject("mensagem", "Por favor preencher todos os campos corretamente.");
			return mv;
		}
		Evento eventoSalvo = eventoService.salvarEvento(evento);
		if (novo) {
			mv.addObject("evento", new Evento());
		} else {
			mv.addObject("evento", eventoSalvo);
		}
		mv.addObject("mensagem", "Evento salvo com sucesso!");
		mv.addObject("listaGenerosMusicais", GeneroMusical.values());
		mv.addObject("listaLocais", localService.listarLocal());
		return mv;
	}

	@RequestMapping("/editar")
	public ModelAndView editarEvento(@RequestParam Long id) {
		ModelAndView mv = new ModelAndView("evento/formEvento.html");
		Evento evento;
		try {
			evento = eventoService.obterEvento(id);
		} catch (Exception e) {
			evento = new Evento();
			mv.addObject("mensagem", e.getMessage());
		}
		mv.addObject("evento", evento);
		mv.addObject("listaGenerosMusicais", GeneroMusical.values());
		mv.addObject("listaLocais", localService.listarLocal());
		return mv;
	}

	@RequestMapping
	public ModelAndView listarEvento() {
		ModelAndView mv = new ModelAndView("evento/listaEvento.html");
		mv.addObject("lista", eventoService.listarEvento());
		return mv;
	}

	@RequestMapping("/excluir")
	public ModelAndView excluirEvento(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/evento");
		try {
			eventoService.excluirEvento(id);
			redirectAttributes.addFlashAttribute("mensagem", "Evento excluído com sucesso.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir evento!" + e.getMessage());
		}
		return mv;
	}
}
