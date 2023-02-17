package com.grv.aniversario.controllers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.grv.aniversario.DTO.MailDTO;
import com.grv.aniversario.DTO.QRCodeDTO;
import com.grv.aniversario.DTO.ResponseRequestDTO;
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
	

//    @GetMapping(path="/{idTicket}")
//    public Optional <TicketModel> getTicketById(@PathVariable("idTicket") Long idTicket){
//    	return ticketService.findById(idTicket);
//    }
//    
    @GetMapping(path="/{idTicket}")
    public ResponseEntity<?> getTicketById(@PathVariable("idTicket") Long idTicket){
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	Optional<TicketModel> ticketOpt = ticketService.findById(idTicket);
    	if(ticketOpt.isPresent()) {
    		map.put("status", 200);
    		map.put("data", ticketOpt.get());
    		return new ResponseEntity<>(map, HttpStatus.OK);
    	}else {
    		map.clear();
    		map.put("message", "Ticket no encontrado");
    		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    	}
    	
    }
    
    @GetMapping(path="/getAll")
    public Iterable<TicketModel> getAllTickets(){
    	return ticketService.getAll();
    }
    
    @GetMapping(path="/acreditados/getAll")
    public Iterable<TicketModel> getAllTicketsPresent(){
    	return ticketService.getAllAcreditados(1);
    }
    
    @GetMapping(path="/acreditados/getAllByEvento/{idEvento}")
    public Iterable<TicketModel> getAllAcreditadosByEvento(@PathVariable("idEvento") Long idEvento){
    	return ticketService.getAllAcreditadosByEvento(idEvento);
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
    	try {
    		ResponseRequestDTO response = getPersona(ticket.getDni());
    		if(response != null && response.getStatus().equalsIgnoreCase("ok")) {
    			String nombre = response.getData().getPersona().getNombre();
    			String apellido = response.getData().getPersona().getApellido();
    			String region = response.getData().getMiembro().getHan().getNombres().get(2);
    			String partidoComunidad = response.getData().getMiembro().getHan().getNombres().get(3);
    			String localidadBarrio = response.getData().getMiembro().getHan().getNombres().get(4);
    			String han = response.getData().getMiembro().getHan().getNombres().get(response.getData().getMiembro().getHan().getNombres().size() - 1);
    			
    			ticket.setEsMiembro(1);
    			ticket.setNombre(nombre);
    			ticket.setApellido(apellido);
    			ticket.setRegion(region);
    			ticket.setPartidoComunidad(partidoComunidad);
    			ticket.setLocalidadBarrio(localidadBarrio);
    			ticket.setHan(han);
    			
    		}else {
    			ticket.setEsMiembro(0);
    		}
    	}catch (Exception ex) {
    		System.out.println("Error en servicio saveTiket. Error: " + ex.getMessage());
    	}
    	
    	ticket.setFechaGenerado(LocalDateTime.now());
    	return ticketService.saveTicket(ticket);
    }
    
    @PostMapping(path="/update/datos/")
    public TicketModel updateDatosTicket(@RequestBody TicketModel t){
    	Optional<TicketModel> opt = ticketService.getTicketByDniAndEvento(t.getDni(), t.getEvento().getId());
    	TicketModel ticket = new TicketModel();
    	if(opt.isPresent()) {
    		ticket = opt.get();
    		
    		ResponseRequestDTO response = getPersona(t.getDni());
			if(response != null && response.getStatus().equalsIgnoreCase("ok")) {
				String nombre = response.getData().getPersona().getNombre();
				String apellido = response.getData().getPersona().getApellido();
				String region = response.getData().getMiembro().getHan().getNombres().get(2);
				String partidoComunidad = response.getData().getMiembro().getHan().getNombres().get(3);
				String localidadBarrio = response.getData().getMiembro().getHan().getNombres().get(4);
				String han = response.getData().getMiembro().getHan().getNombres().get(response.getData().getMiembro().getHan().getNombres().size() - 1);
				
				ticket.setEsMiembro(1);
				ticket.setNombre(nombre);
				ticket.setApellido(apellido);
				ticket.setRegion(region);
				ticket.setPartidoComunidad(partidoComunidad);
				ticket.setLocalidadBarrio(localidadBarrio);
				ticket.setHan(han);
				
			}else {
				ticket.setEsMiembro(0);
			}
    	}else {
    		return ticket;
    	}
    	
    	
    	return ticketService.saveTicket(ticket);
    }
    
//    @PostMapping(path="/event/save")
//    public boolean saveEvent(@RequestBody TicketModel ticket){
//    	TicketModel ticketExist = ticketService.getTicketById(ticket.getId()).get();
//    	EventoModel event = ticket.getEvento();
//    	boolean exist = ticketService.checkTicketExist(ticketExist.getDni(), event.getId());
//    	if(!exist) {
//    		ticketExist.setEvento(event);
//    		ticketService.saveTicket(ticketExist);
//    		return true;
//    	}else {
//    		return false;
//    	}
//    }
    
    @PostMapping(path="/event/save")
    public ResponseEntity<?> saveEvent(@RequestBody TicketModel ticket){
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	Optional<TicketModel> optT = ticketService.getTicketById(ticket.getId());
    	if(optT.isPresent()) {
    		TicketModel ticketExist = optT.get();
    		EventoModel event = ticket.getEvento();
    		boolean exist = ticketService.checkTicketExist(ticketExist.getDni(), event.getId());
    		if(!exist) {
    			ticketExist.setEvento(event);
        		ticketService.saveTicket(ticketExist);
        		map.put("status", "ok");
        		map.put("message", "Evento guardado correctamente");
        		return new ResponseEntity<>(map, HttpStatus.OK);
        		
    		}else {
    			map.put("status", "error");
        		map.put("message", "Ya existe un ticket para la persona y evento seleccionados");
        		return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    		}
    	}else {
    		map.put("status", "error");
    		map.put("message", "Ticket inexistente");
    		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    	}
    	
    }
    
//    @PutMapping(path="/update")
//    public TicketModel updateTicket(@RequestBody TicketModel ticket){
//    	return ticketService.saveTicket(ticket);
//    }
    
    @PostMapping(path="/mail")
    public String sendEmail(@RequestBody MailDTO data){
    	
    	Properties props = new Properties();
		   props.put("mail.smtp.auth", "false");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "mr.fibercorp.com.ar");
		   props.put("mail.smtp.port", "465");
 	   
		  Session session = Session.getInstance(props);
    	return mail.sendEmail(session, data.getTo(), data.getSubject(), data.getContent());
    }
    
//    @PostMapping(path="/event/acreditate")
//    public TicketModel setAcreditation(@RequestBody QRCodeDTO qr){
//    	Optional<TicketModel> ticketOpt = null;
//    	if(qr.getDni() != null) {
//    		ticketOpt = ticketService.getTicketByDniAndEvento(qr.getDni(), qr.getIdEvento());
//    	}else if(qr.getMail() != null) {
//    		ticketOpt = ticketService.getTicketByMailAndEvento(qr.getMail(), qr.getIdEvento());	
//    	}
//    	if(ticketOpt.isPresent()) {
//			TicketModel ticket = ticketOpt.get();
//			if(ticket.getVerificado() == 1) {
//				return null;
//			}
//			ticket.setVerificado(1);
//			ticket.setFechaAcreditado(LocalDateTime.now());
//			ticketService.saveTicket(ticket);
//			return ticket;    			
//		}
//    	return ticketOpt.get();
//    }
    
    @PostMapping(path="/event/acreditate")
    public ResponseEntity<?> acreditate(@RequestBody QRCodeDTO qr){
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	if(qr.getDni() != null) {
    		Optional<TicketModel> ticketOpt = ticketService.getTicketByDniAndEvento(qr.getDni(), qr.getIdEvento());
    		if(ticketOpt.isPresent()) {
    			TicketModel ticket = ticketOpt.get();
    			if(ticket.getVerificado() == 1) {
    				map.put("status", "error");
    				map.put("message", "La persona con dni " + qr.getDni() + " ya ha sido acreditada.");
    				return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
    			}
    			ticket.setVerificado(1);
    			ticket.setFechaAcreditado(LocalDateTime.now());
    			ticketService.saveTicket(ticket);
    			map.put("status", "ok");
				map.put("message", "La persona con dni " + qr.getDni() + " ha sido acreditada correctamente.");
				map.put("data", ticket);
				return new ResponseEntity<>(map, HttpStatus.OK);
    		}
    		map.put("status", "error");
			map.put("message", "No existe entrada para este evento con el dni: " + qr.getDni());
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    	}
    	map.put("status", "error");
		map.put("message", "Se requiere DNI");
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    
    private ResponseRequestDTO getPersona(String dni) {
    	String url = "https://www.sgiar.org.ar/api/v1/acredita/persona?token=tokenPrivadoSecret&d=" + dni;
		RestTemplate restTemplate = new RestTemplate();
    	return restTemplate.getForObject(url, ResponseRequestDTO.class);
    }

}
