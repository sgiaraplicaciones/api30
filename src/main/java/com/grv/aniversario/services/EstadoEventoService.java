package com.grv.aniversario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.EstadoEventoModel;
import com.grv.aniversario.repositories.EstadoEventoRepository;

@Service
public class EstadoEventoService {
	
	@Autowired
	EstadoEventoRepository estadoEventoRepository;
	
	public Optional<EstadoEventoModel> getEstadoEventoById(Long idEstadoEvento) {
		return estadoEventoRepository.findById(idEstadoEvento);
	}

}
