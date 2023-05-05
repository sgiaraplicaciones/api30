package com.grv.aniversario.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.UsuarioModel;

@Repository
public interface UserRepository extends CrudRepository<UsuarioModel, Long> {

	public abstract Optional<UsuarioModel> findByName(String name);
	public abstract Optional<UsuarioModel> findByMail(String mail);

}
