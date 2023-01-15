package com.grv.aniversario.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grv.aniversario.DTO.ImgResponseDTO;
import com.grv.aniversario.models.EventoModel;
import com.grv.aniversario.models.TicketModel;
import com.grv.aniversario.services.EventoService;
import com.grv.aniversario.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class ticketController {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	EventoService eventoService;

    @GetMapping(path="/{idTicket}")
    public Optional <TicketModel> getTicketById(@PathVariable("idTicket") Long idTicket){
    	return ticketService.findById(idTicket);
    }
    
    @PostMapping(path="/save")
    public TicketModel saveTicket(@RequestBody TicketModel ticket){
    	return ticketService.saveTicket(ticket);
    }
    
    @PostMapping(path="/event/save")
    public TicketModel saveEvent(@RequestBody TicketModel ticket){
    	TicketModel ticketExist = ticketService.getTicketById(ticket.getId()).get();
    	EventoModel event = ticket.getEvento();
    	ticketExist.setEvento(event);
    	return ticketService.saveTicket(ticketExist);
    }
    
    @PutMapping(path="/update")
    public TicketModel updateTicket(@RequestBody TicketModel ticket){
    	return ticketService.saveTicket(ticket);
    	
    }

}
