package com.grv.aniversario.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.OrganizacionModel;

@Repository
public interface OrganizacionRepository extends CrudRepository<OrganizacionModel, Long> {

}
