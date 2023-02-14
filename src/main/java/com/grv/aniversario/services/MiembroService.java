package com.grv.aniversario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.MiembroModel;
import com.grv.aniversario.repositories.MiembroRepository;

@Service
public class MiembroService {
	@Autowired
	MiembroRepository miembroRepository;

	public Optional<MiembroModel> getMiembroById(Long idMiembro) {
		return miembroRepository.findById(idMiembro);
	}
	
	public Optional<MiembroModel> getMiembroByDni(String dni) {
		return miembroRepository.findByDni(dni);
	}
	
	public MiembroModel saveMiembro(MiembroModel miembro) {
		return miembroRepository.save(miembro);
	}
}
