
package com.grv.aniversario.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.EventoModel;
import com.grv.aniversario.models.TicketModel;


@Repository
public interface TicketRepository extends CrudRepository<TicketModel, Long> {
//	@Query(value = "SELECT o from ImagenModel o WHERE o.estado.idTipoEstadoImagen not in (3) and o.compartida = 1 and o.denuncia.idDenuncia = ?1")
//	public abstract ArrayList<ImagenModel> findByDenuncia(Long idDenuncia);
	
	@Query(value = "SELECT o from TicketModel o WHERE o.dni = ?1 and o.evento.id = ?2")
	public abstract Optional<TicketModel> findByDniAndEvento(String dni, Long idEvento);
	
	@Query(value = "SELECT o from TicketModel o WHERE o.mail = ?1 and o.evento.id = ?2")
	public abstract Optional<TicketModel> findByMailAndEvento(String mail, Long idEvento);
	
	public abstract Iterable<TicketModel> findAllByEvento(EventoModel evento);
	
}
