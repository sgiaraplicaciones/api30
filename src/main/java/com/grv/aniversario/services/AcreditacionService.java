package com.grv.aniversario.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.AcreditacionModel;
import com.grv.aniversario.models.MiembroModel;
import com.grv.aniversario.repositories.AcreditacionRepository;

@Service
public class AcreditacionService {
	@Autowired
	AcreditacionRepository acreditacionRepository;

	public boolean acreditarMiembro(MiembroModel miembro) {
		AcreditacionModel acreditacion = new AcreditacionModel();
		acreditacion.setMiembro(miembro);
		acreditacion.setFechaHoraIngreso(LocalDateTime.now());
		acreditacionRepository.save(acreditacion);
		return true;
	}
	
	public AcreditacionModel save(AcreditacionModel acreditacion) {
		return acreditacionRepository.save(acreditacion);
	}
	
	public Optional<AcreditacionModel> getById(Long idAcreditacion) {
		return acreditacionRepository.findById(idAcreditacion);
	}
	
	public Optional<AcreditacionModel> getByMiembro(MiembroModel miembro){
		return acreditacionRepository.findByMiembro(miembro);
	}
}
