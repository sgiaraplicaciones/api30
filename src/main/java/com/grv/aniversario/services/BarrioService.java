package com.grv.aniversario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.BarrioModel;
import com.grv.aniversario.repositories.BarrioRepository;

@Service
public class BarrioService {

	@Autowired
	BarrioRepository barrioRepository;
	
	public Optional<BarrioModel> getBarrioById(Long idBarrio){
		return barrioRepository.findById(idBarrio);
	}
}
