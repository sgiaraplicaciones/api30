package com.grv.aniversario.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.BarrioModel;

@Repository
public interface BarrioRepository extends CrudRepository <BarrioModel, Long> {

}
