
package com.grv.aniversario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.TicketModel;
import com.grv.aniversario.repositories.TicketRepository;


@Service
public class TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	
//	public ArrayList<TicketModel> findByEvento(Long idEvento){
//		return ticketRepository.findByEvento(idEvento);	
//	}
	
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
    
}
