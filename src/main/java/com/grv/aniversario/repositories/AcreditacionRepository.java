package com.grv.aniversario.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.AcreditacionModel;
import com.grv.aniversario.models.MiembroModel;

@Repository
public interface AcreditacionRepository extends CrudRepository<AcreditacionModel, Long> {

	public abstract Optional<AcreditacionModel> findByMiembro(MiembroModel miembro);
}
