package com.gft.gerenciadoreventos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.gerenciadoreventos.services.PopularService;

@Controller
public class PopularController {

	@Autowired
	private PopularService popularService;

	@GetMapping("/popular")
	public String popularBanco(RedirectAttributes att) {
		try {
			popularService.popularBanco();
			att.addFlashAttribute("mensagemSucesso", "Banco de dados populado com sucesso!");
			return "redirect:/home";
		} catch (Exception e) {
			att.addFlashAttribute("mensagemErro",
					"Não foi possível popular o banco de dados, pois já foi populado anteriormente, por a");
			return "redirect:/home";
		}
	}
}
