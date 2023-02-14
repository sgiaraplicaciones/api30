package com.grv.aniversario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.EstadoEventoModel;
import com.grv.aniversario.models.EventoModel;
import com.grv.aniversario.repositories.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	EventoRepository eventoRepository;

	public Optional<EventoModel> getEventoById(Long idEvento) {
		return eventoRepository.findById(idEvento);
	}
	
	public Iterable<EventoModel> getAll(){
		EstadoEventoModel estado = new EstadoEventoModel();
		estado.setIdEstadoEvento(1L);
		return eventoRepository.findAllByEstado(estado);
	}
	
	public EventoModel save(EventoModel evento) {
		return eventoRepository.save(evento);
	}
}
