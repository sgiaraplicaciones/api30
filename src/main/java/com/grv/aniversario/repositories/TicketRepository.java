
package com.grv.aniversario.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grv.aniversario.models.EventoModel;
import com.grv.aniversario.models.TicketModel;


@Repository
public interface TicketRepository extends CrudRepository<TicketModel, Long> {
	
	@Query(value = "SELECT o from TicketModel o WHERE o.dni = ?1 and o.evento.id = ?2")
	public abstract Optional<TicketModel> findByDniAndEvento(String dni, Long idEvento);
	
	@Query(value = "SELECT o from TicketModel o WHERE o.mail = ?1 and o.evento.id = ?2")
	public abstract Optional<TicketModel> findByMailAndEvento(String mail, Long idEvento);
	
	public abstract Iterable<TicketModel> findAllByEvento(EventoModel evento);
	
	public abstract Iterable<TicketModel> findAllByVerificado(int verificado);
	
	@Query(value = "SELECT o from TicketModel o WHERE o.verificado = 1 and o.evento.id = ?1")
	public abstract Iterable<TicketModel> findAllVerificadosByEvento(Long idEvento);

}
