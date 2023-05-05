package com.grv.aniversario.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.DataActualizadaModel;

@Repository
public interface DataActualizadaRepository extends CrudRepository<DataActualizadaModel, Integer> {

	public abstract Optional<DataActualizadaModel> findByDni(Integer dni);
}
