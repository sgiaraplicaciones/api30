
package com.grv.aniversario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.EventoModel;
import com.grv.aniversario.models.TicketModel;
import com.grv.aniversario.repositories.TicketRepository;


@Service
public class TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	
//	public ArrayList<TicketModel> findByEvento(Long idEvento){
//		return ticketRepository.findByEvento(idEvento);	
//	}
	public Iterable<TicketModel> getAll(){
		return ticketRepository.findAll();
	}
	
	public Iterable<TicketModel> getAllByEvento(EventoModel evento){
		return ticketRepository.findAllByEvento(evento);
	}
	
	public Optional<TicketModel> findById(Long idTicket){
		return ticketRepository.findById(idTicket);
	}
	
	public TicketModel saveTicket(TicketModel ticket) {
		// ACA AGREGAR VALIDACION POR CADA PROPIEDAD, SI ES DIFERENTE SE GRABA SINO NO
		return ticketRepository.save(ticket);
	}
	
	public Optional<TicketModel> getTicketById(Long idTicket) {
		return ticketRepository.findById(idTicket);
	}
	
	public boolean checkTicketExist(String dni, Long idEvento) {
		Optional<TicketModel> exist = ticketRepository.findByDniAndEvento(dni, idEvento);
		return exist.isPresent();
	}
	
	public Optional<TicketModel> getTicketByDniAndEvento(String dni, Long idEvento){
		return ticketRepository.findByDniAndEvento(dni, idEvento);
	}
	
	public Optional<TicketModel> getTicketByMailAndEvento(String mail, Long idEvento){
		return ticketRepository.findByMailAndEvento(mail, idEvento);
	}
    
}
