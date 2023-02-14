package com.grv.aniversario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grv.aniversario.models.EventoModel;
import com.grv.aniversario.services.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {
	
	@Autowired
	EventoService eventoService;
	
	@GetMapping
	public Iterable<EventoModel> getAllEventos() {
		return eventoService.getAll();
	}
	
	@PostMapping(path="/save")
	public EventoModel saveEvento(@RequestBody EventoModel evento) {
		return eventoService.save(evento);
	}
	
	
	
	
	
	

}
