package com.grv.aniversario.controllers;

import java.util.Optional;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grv.aniversario.DTO.MailDTO;
import com.grv.aniversario.DTO.QRCodeDTO;
import com.grv.aniversario.models.EventoModel;
import com.grv.aniversario.models.TicketModel;
import com.grv.aniversario.services.EnvioMailService;
import com.grv.aniversario.services.EventoService;
import com.grv.aniversario.services.TicketService;

@RestController
@RequestMapping("/ticket")
public class ticketController {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	EventoService eventoService;
	
	@Autowired
	EnvioMailService mail;

    @GetMapping(path="/{idTicket}")
    public Optional <TicketModel> getTicketById(@PathVariable("idTicket") Long idTicket){
    	return ticketService.findById(idTicket);
    }
    
    @GetMapping(path="/getAll")
    public Iterable<TicketModel> getAllTickets(){
    	return ticketService.getAll();
    }
    
    @GetMapping(path="/getAll/evento/{idEvento}")
    public Iterable<TicketModel> getAllTicketsByEvento(@PathVariable("idEvento") Long idEvento){
    	Iterable<TicketModel> tickets = null;
    	if(idEvento != null) {
    		Optional<EventoModel> oEvent = eventoService.getEventoById(idEvento);
    		if(oEvent.isPresent()) {
    			EventoModel event = oEvent.get();
    			tickets = ticketService.getAllByEvento(event);
    		}
    	}
    	return tickets;
    }
    
    @PostMapping(path="/save")
    public TicketModel saveTicket(@RequestBody TicketModel ticket){
    	return ticketService.saveTicket(ticket);
    }
    
    @PostMapping(path="/event/save")
    public boolean saveEvent(@RequestBody TicketModel ticket){
    	TicketModel ticketExist = ticketService.getTicketById(ticket.getId()).get();
    	EventoModel event = ticket.getEvento();
    	boolean exist = ticketService.checkTicketExist(ticketExist.getDni(), event.getId());
    	if(!exist) {
    		ticketExist.setEvento(event);
    		ticketService.saveTicket(ticketExist);
    		return true;
    	}else {
    		return false;
    	}
    	
    }
    
    @PutMapping(path="/update")
    public TicketModel updateTicket(@RequestBody TicketModel ticket){
    	return ticketService.saveTicket(ticket);
    }
    
    @PostMapping(path="/mail")
    public String sendEmail(@RequestBody MailDTO data){
    	
    	String userName = "no_responder@sgiar.org.ar";
    	String pass = "Resp19";
    	
    	Properties props = new Properties();
		   props.put("mail.smtp.auth", "false");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "mr.fibercorp.com.ar");
		   props.put("mail.smtp.port", "465");
 	   
		  Session session = Session.getInstance(props);
    	return mail.sendEmail(session, data.getTo(), data.getSubject(), data.getContent());
    }
    
    @PostMapping(path="/event/acreditate")
    public boolean setAcreditation(@RequestBody QRCodeDTO qr){
    	Optional<TicketModel> ticketOpt = null;
    	if(qr.getDni() != null) {
    		ticketOpt = ticketService.getTicketByDniAndEvento(qr.getDni(), qr.getIdEvento());
    	}else if(qr.getMail() != null) {
    		ticketOpt = ticketService.getTicketByMailAndEvento(qr.getMail(), qr.getIdEvento());	
    	}
    	if(ticketOpt.isPresent()) {
			TicketModel ticket = ticketOpt.get();
			ticket.setVerificado(1);
			ticketService.saveTicket(ticket);
			return true;    			
		}
    	return false;
    }

}
