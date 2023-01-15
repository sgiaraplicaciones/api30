package com.grv.aniversario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.EventoModel;
import com.grv.aniversario.repositories.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	EventoRepository eventoRepository;

	public Optional<EventoModel> getEventoById(Long idEvento) {
		return eventoRepository.findById(idEvento);
	}
}
