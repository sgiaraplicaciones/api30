package com.grv.aniversario.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.DTO.CounterDTO;
import com.grv.aniversario.models.AcreditacionGrupoModel;


@Repository
public interface AcreditacionGrupoRepository extends CrudRepository<AcreditacionGrupoModel, Long> {

	public abstract Optional<AcreditacionGrupoModel> findByDni(String dni);
	
//	@Query(value = "SELECT COUNT(o.idGrupo) as total, o.idGrupo as grupo FROM acreditacionGrupoModel o WHERE o.fechaHoraIngreso BETWEEN ?1 AND ?2 GROUP BY o.idGrupo")
//	public abstract CounterDTO getCount(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
}
