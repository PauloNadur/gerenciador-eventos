package com.gft.gerenciadoreventos.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.gerenciadoreventos.entities.Evento;
import com.gft.gerenciadoreventos.entities.Local;
import com.gft.gerenciadoreventos.entities.enums.GeneroMusical;

@Service
public class PopularService {

	@Autowired
	private EventoService eventoService;

	@Autowired
	private LocalService localService;

	private boolean bancoPopulado;

	public void popularBanco() throws Exception {

		if (bancoPopulado == true) {
			throw new Exception();

		} else {

			List<Local> listaLocais = new ArrayList<>();
			listaLocais.add(new Local("Popinjays", "The Murray, Hong Kong"));
			listaLocais.add(new Local("Baba Nest", "Sri Panwa Resort, Phuket, Tailândia"));
			listaLocais.add(new Local("Moon Bar", "Banyan Tree, Bangkok, Tailândia"));
			listaLocais.add(new Local("La Dolce Vitae", "Majestic Hotel & Spa, Barcelona, Espanha"));
			localService.salvarTodos(listaLocais);

			List<Evento> listaEventos = new ArrayList<>();
			listaEventos.add(new Evento("Show do Mc Kevin e Don Juan", 1000, new Date(), new BigDecimal("100"),
					localService.obterLocal(1l), GeneroMusical.FUNK));
			listaEventos.add(new Evento("Show do Red Hot Chilli Peppers", 2000, new Date(), new BigDecimal("200"),
					localService.obterLocal(2l), GeneroMusical.ROCK));
			listaEventos.add(new Evento("DJ Alan Walker & Alok", 3000, new Date(), new BigDecimal("300"),
					localService.obterLocal(3l), GeneroMusical.ELETRÔNICA));
			listaEventos.add(new Evento("Show Mc Teto, Tuê e Wiu", 4000, new Date(), new BigDecimal("400"),
					localService.obterLocal(4l), GeneroMusical.TRAP));
			eventoService.salvarTodos(listaEventos);

			bancoPopulado = true;
		}
	}
}
