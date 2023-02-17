package com.grv.aniversario.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.EstadoEventoModel;

@Repository
public interface EstadoEventoRepository extends CrudRepository<EstadoEventoModel, Long> {

}
