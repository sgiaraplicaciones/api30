package com.grv.aniversario.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.EstadoEventoModel;
import com.grv.aniversario.models.EventoModel;


@Repository
public interface EventoRepository extends CrudRepository<EventoModel, Long> {

	public abstract Iterable<EventoModel> findAllByEstado(EstadoEventoModel estado);
}
