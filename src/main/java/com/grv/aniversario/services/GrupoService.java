package com.grv.aniversario.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.GrupoModel;
import com.grv.aniversario.repositories.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	GrupoRepository grupoRepository;
	
	public GrupoModel getGrupoById(Long idGrupo){
		Optional<GrupoModel> grupo = grupoRepository.findById(idGrupo);
		if(grupo.isPresent()) {
			return grupo.get();
		}else {
			throw new NoSuchElementException("No existe el grupo con ID " + idGrupo);
		}
	}
	
	public Iterable<GrupoModel> getAll(){
		return grupoRepository.findAll();
	}
	
}
