package com.grv.aniversario.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.MiembroModel;

@Repository
public interface MiembroRepository extends CrudRepository<MiembroModel, Long> {

	public abstract Optional<MiembroModel> findByDni(String dni);
}
