package com.grv.aniversario.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grv.aniversario.models.EstadoEventoModel;
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
	
//	@PostMapping(path="/save")
//	public EventoModel saveEvento(@RequestBody EventoModel evento) {
//		return eventoService.save(evento);
//	}
	
	@PostMapping(path="/save")
	public ResponseEntity<?> saveEvento(@RequestBody EventoModel evento) {
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	if(evento != null) {
    		EventoModel newEvent = null;
	    	try {
	    		newEvent = eventoService.save(evento);
	    		map.put("data", newEvent);
	    		map.put("status", "ok");
	    		map.put("message", "Evento guardado correctamente.");
	    		return new ResponseEntity<>(map, HttpStatus.OK);
	    	}catch(Exception e) {
	    		map.put("status", "error");
	    		map.put("message", "Ocurrio un error al intentar grabar el evento.");
	    		return new ResponseEntity<>(map, HttpStatus.NOT_MODIFIED);
	    	}
    	}else {
    		map.put("status", "error");
    		map.put("message", "Ocurrio un error al intentar grabar el evento. Verifique los datos enviados.");
    		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    	}
    	
    	
	}
	
	
	
	
	
	

}
