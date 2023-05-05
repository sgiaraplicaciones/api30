package com.grv.aniversario.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.DataActualizadaModel;
import com.grv.aniversario.repositories.DataActualizadaRepository;

@Service
public class DataActualizadaService {

	@Autowired
	DataActualizadaRepository dataActualizadaRepository;
	
	public DataActualizadaModel getByDni(Integer dni) {
		Optional<DataActualizadaModel> data = dataActualizadaRepository.findById(dni);
		if(data.isPresent()) {
			return data.get();
		}else {
			return null;
		}
	}
	
	public DataActualizadaModel saveData(DataActualizadaModel data) {
		return dataActualizadaRepository.save(data);
	}
}
