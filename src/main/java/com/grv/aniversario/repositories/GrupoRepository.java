package com.grv.aniversario.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.GrupoModel;

@Repository
public interface GrupoRepository extends CrudRepository<GrupoModel, Long> {

}
