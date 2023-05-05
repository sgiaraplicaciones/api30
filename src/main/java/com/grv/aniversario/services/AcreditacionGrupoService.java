package com.grv.aniversario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.DTO.CounterDTO;
import com.grv.aniversario.models.AcreditacionGrupoModel;
import com.grv.aniversario.repositories.AcreditacionGrupoRepository;

@Service
public class AcreditacionGrupoService {
	@Autowired
	AcreditacionGrupoRepository acreditacionGrupoRepository;
	
	public AcreditacionGrupoModel save(AcreditacionGrupoModel data) {
		return acreditacionGrupoRepository.save(data);
	}
	
	public Iterable<AcreditacionGrupoModel> getAll(){
		return acreditacionGrupoRepository.findAll();
	}
	
	public Optional<AcreditacionGrupoModel> getByDni(String dni){
		return acreditacionGrupoRepository.findByDni(dni);
	}
	
//	public CounterDTO getTotales() {
//		return acreditacionGrupoRepository.getCount();
//	}
	
}
