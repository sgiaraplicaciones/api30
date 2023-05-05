package com.grv.aniversario.controllers;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.grv.aniversario.DTO.AcreditacionGrupoDTO;
import com.grv.aniversario.DTO.AcreditarDTO;
import com.grv.aniversario.DTO.CounterDTO;
import com.grv.aniversario.DTO.DniDTO;
import com.grv.aniversario.DTO.LocalidadDTO;
import com.grv.aniversario.DTO.PersonaDTO;
import com.grv.aniversario.DTO.RequestAcreditacionExtranetDTO;
import com.grv.aniversario.DTO.RequestCountDTO;
import com.grv.aniversario.DTO.ResponseRequestDTO;
import com.grv.aniversario.models.AcreditacionGrupoModel;
import com.grv.aniversario.models.AcreditacionModel;
import com.grv.aniversario.models.BarrioModel;
import com.grv.aniversario.models.DataActualizadaModel;
import com.grv.aniversario.models.EventoModel;
import com.grv.aniversario.models.GrupoModel;
import com.grv.aniversario.models.MiembroModel;
import com.grv.aniversario.models.OrganizacionModel;
import com.grv.aniversario.services.AcreditacionGrupoService;
import com.grv.aniversario.services.AcreditacionService;
import com.grv.aniversario.services.BarrioService;
import com.grv.aniversario.services.DataActualizadaService;
import com.grv.aniversario.services.EventoService;
import com.grv.aniversario.services.GrupoService;
import com.grv.aniversario.services.MiembroService;
import com.grv.aniversario.services.OrganizacionService;

@RestController
@RequestMapping("/acreditacion")
public class AcreditacionController {

	@Autowired
	MiembroService miembroService;
	
	@Autowired
	AcreditacionService acreditacionService;
	
	@Autowired
	GrupoService grupoService;
	
	@Autowired
	AcreditacionGrupoService acreditacionGrupoService;
	
	@Autowired
	BarrioService barrioService;
	
	@Autowired
	EventoService eventoService;
	
	@Autowired
	OrganizacionService organizacionService;
	
	@Autowired
	DataActualizadaService dataActualizadaService;
	
	  @PostMapping
	    public MiembroModel acreditar(@RequestBody MiembroModel miembro){
	    	Optional<MiembroModel> optMiembro = miembroService.getMiembroByDni(miembro.getDni());
	    	if(optMiembro.isPresent()) {
	    		MiembroModel persona = optMiembro.get();
	    		boolean acreditado = acreditacionService.acreditarMiembro(persona);
	    		if(acreditado) {
	    			persona.setAcreditade(true);
	    			return persona;
	    		}
	    	}
	    	miembro.setAcreditade(false);
	    	return miembro;
	    }
	  @PostMapping("/grupos/nuevaPersona")
	  public ResponseEntity<?> acreditaGruposNuevaPersona(@RequestBody AcreditacionGrupoModel data) {
		  Map<String, Object> map = new LinkedHashMap<String, Object>();
		  AcreditacionGrupoModel acreditade = acreditacionGrupoService.getByDni(data.getDni()).get();
		  if(acreditade != null) {
			  acreditade.setNombre(data.getNombre());
			  acreditade.setApellido(data.getApellido());
			  acreditade.setCoordinadora(data.getCoordinadora());
			  acreditade.setRegion(data.getRegion());
			  acreditacionGrupoService.save(acreditade);
			  
			  map.put("status", "ok");
			  map.put("message", "Persona acreditada correctamente.");
			  map.put("data", acreditade);
			  return new ResponseEntity<>(map, HttpStatus.OK);
		  }
		  try {
			  acreditacionGrupoService.save(data);
			  map.put("status", "ok");
			  map.put("message", "Persona acreditada correctamente.");
			  map.put("data", data);
			  return new ResponseEntity<>(map, HttpStatus.OK);
		  }catch(Exception e) {
			  map.put("status", "error");
			  map.put("message", "Ocurrió un error al intentar grabar la persona");
			  map.put("data", data);
			  return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		  }
	  }
	  
	@PostMapping("/acreditar")
	  public ResponseEntity<?> acreditarMiembro(@RequestBody AcreditarDTO data) {
		  Map<String, Object> map = new LinkedHashMap<String, Object>();
		  String dni = data.getDni();
		  if(dni != null) {
			  AcreditacionModel acreditacion = new AcreditacionModel();
			  BarrioModel barrio = new BarrioModel();
			  EventoModel evento = new EventoModel();
			  OrganizacionModel org = new OrganizacionModel();
			  MiembroModel miembro = new MiembroModel();
			  
			  Optional<MiembroModel> miembroOpt = miembroService.getMiembroByDni(dni);
			  if(miembroOpt.isPresent()) {
				  Optional<AcreditacionModel> acreditOpt = acreditacionService.getByMiembro(miembroOpt.get());
				  if(acreditOpt.isPresent()) {
					  Long idEvento = acreditOpt.get().getEvento().getId();
					  if(idEvento.equals(data.getIdEvento())) {
						  	map.put("status", "error");
							map.put("message", "La persona ya fue acreditada en este evento.");
							map.put("data", data);
							return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
					  }
				  }
			  }
			  
			  if(data.getIdBarrio() != null) {
				 barrio = barrioService.getBarrioById(data.getIdBarrio()).get();
			  }
			  if(data.getIdEvento() != null) {
				  evento = eventoService.getEventoById(data.getIdEvento()).get();
			  }
			  try {
				  	ResponseRequestDTO response = getPersona(dni);
				  	if(response != null && response.getStatus().equalsIgnoreCase("ok")) {
						String nombre = response.getData().getPersona().getNombre();
						String apellido = response.getData().getPersona().getApellido();
						
						if(barrio != null) {				
							org.setBarrio(barrio);
							org.setComunidad(barrio.getComunidad());
							org.setCoordinadora(barrio.getCoordinadora());
							org.setRegion(barrio.getRegion());
							org = organizacionService.save(org);
						}

						miembro.setNombre(nombre);
						miembro.setApellido(apellido);
						miembro.setDni(dni);
						miembro.setOrganizacion(org);
						miembro = miembroService.saveMiembro(miembro);
						
						acreditacion.setMiembro(miembro);
						acreditacion.setFechaHoraIngreso(LocalDateTime.now());
						if(evento != null) {
							acreditacion.setEvento(evento);
						}
						acreditacion = acreditacionService.save(acreditacion);
						
						map.put("status", "ok");
						map.put("message", "La acreditación se realizó correctamente");
						map.put("data", acreditacion);
						
						return new ResponseEntity<>(map, HttpStatus.OK);
					}
			  }catch(Exception e) {
				  
				  if(barrio != null) {
					  org.setBarrio(barrio);
					  org.setComunidad(barrio.getComunidad());
					  org.setRegion(barrio.getRegion());
					  org.setCoordinadora(barrio.getCoordinadora());
					  org = organizacionService.save(org);
					  
					  miembro.setOrganizacion(org);
					  miembro.setDni(dni);
					  miembro = miembroService.saveMiembro(miembro);
					  
					  acreditacion.setMiembro(miembro);
				  }
				  acreditacion.setFechaHoraIngreso(LocalDateTime.now());
				  acreditacionService.save(acreditacion);
				  
				  map.put("status", "error");
				  map.put("message", "No se pudo encontrar la persona en extranet. Igualmente fue acreditada");
				  map.put("data", acreditacion);
				  return new ResponseEntity<>(map, HttpStatus.CREATED);
			  }
		  }
		  map.put("status", "error");
		  map.put("message", "Debe ingresar el dni");
		  return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	  }
	
	@PostMapping("/acreditar/nuevaPersona")
	  public ResponseEntity<?> acreditaNuevaPersona(@RequestBody AcreditacionModel data) {
		  Map<String, Object> map = new LinkedHashMap<String, Object>();
		  AcreditacionModel acreditacion = acreditacionService.getById(data.getIdAcreditacion()).get();
		  if(acreditacion != null) {
			  MiembroModel miembro = data.getMiembro();
			  miembroService.saveMiembro(miembro);
			  
			  acreditacion.setMiembro(miembro);
			  acreditacionService.save(acreditacion);
			  
			  map.put("status", "ok");
			  map.put("message", "Persona acreditada correctamente.");
			  map.put("data", acreditacion);
			  return new ResponseEntity<>(map, HttpStatus.OK);
		  }

		  map.put("status", "error");
		  map.put("message", "No se encontró el registro de acreditacion previo.");
		  map.put("data", data);
		  return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	  }
	  
	  
//	  @PostMapping("/grupos")
//	  public ResponseEntity<?> acreditaGrupos(@RequestBody AcreditacionGrupoDTO data) {
//		  Map<String, Object> map = new LinkedHashMap<String, Object>();
//		  String dni = data.getDni();
//		  Long idGrupo = data.getIdGrupo();
//		  Optional<GrupoModel> grupoOpt = null;
//		  GrupoModel grupo = null;
//			if(idGrupo != null) {
//				grupoOpt = grupoService.getGrupoById(idGrupo);
//				if(grupoOpt.isPresent()) {
//					grupo = grupoOpt.get();
//				}
//			}
//		  if(dni != null) {
//			  AcreditacionGrupoModel acreditado = new AcreditacionGrupoModel();
//			  try {
//				  	ResponseRequestDTO response = getPersona(dni);
//				  	if(response != null && response.getStatus().equalsIgnoreCase("ok")) {
//						String nombre = response.getData().getPersona().getNombre();
//						String apellido = response.getData().getPersona().getApellido();
//						String region = response.getData().getMiembro().getHan().getNombres().get(2);
//						String coordinadora = response.getData().getMiembro().getHan().getNombres().get(1);
//						
//						acreditado.setDni(dni);
//						acreditado.setGrupo(grupo);
//						acreditado.setFechaHoraIngreso(LocalDateTime.now());
//						acreditado.setNombre(nombre);
//						acreditado.setApellido(apellido);
//						acreditado.setRegion(region);
//						acreditado.setCoordinadora(coordinadora);
//						acreditacionGrupoService.save(acreditado);
//						
//						map.put("status", "ok");
//						map.put("message", "La acreditación se realizó correctamente");
//						map.put("data", acreditado);
//						
//						return new ResponseEntity<>(map, HttpStatus.OK);
//					}
//			  }catch(Exception e) {
//				  acreditado.setDni(dni);
//				  acreditado.setGrupo(grupo);
//				  acreditado.setFechaHoraIngreso(LocalDateTime.now());
//				  acreditacionGrupoService.save(acreditado);
//				  map.put("status", "error");
//				  map.put("message", "No se pudo encontrar la persona en extranet. Igualmente fue acreditada");
//				  map.put("data", data);
//				  return new ResponseEntity<>(map, HttpStatus.CREATED);
//			  }
//		  }
//		  map.put("status", "error");
//		  map.put("message", "Debe ingresar el dni");
//		  return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//	  }
	
	@PostMapping("/grupos")
	  public ResponseEntity<?> acreditaGrupos(@RequestBody AcreditacionGrupoDTO data) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Integer dni = data.getDni();
		if(dni != null) {
			Long idGrupo = null;
			Long idLocalidad = null;
			String nombreLocalidad = data.getLocalidad() != null ? data.getLocalidad().getNombre() : "";
			DataActualizadaModel dataActualizada = dataActualizadaService.getByDni(dni);
			if(dataActualizada != null) {
				//ACREDITO CON LA DATA QUE YA TENGO
				idGrupo = dataActualizada.getIdGrupo();
				idLocalidad = dataActualizada.getIdLocalidad();
			}else {
				// ACREDITO CON LA DATA QUE ME LLEGA
				idGrupo = data.getIdGrupo();
				idLocalidad = data.getLocalidad() != null ? data.getLocalidad().getIdLocalidad() : null;
				if(idGrupo == null || idLocalidad == null) {
					map.put("status", "error");
					map.put("cod", 1);
					map.put("message", "requiere actualizar grupo y localidad");
					return new ResponseEntity<>(map, HttpStatus.OK);
				}
				//GUARDO DATA
				DataActualizadaModel newData = new DataActualizadaModel();
				newData.setDni(dni);
				newData.setIdGrupo(idGrupo);
				newData.setIdLocalidad(idLocalidad);
				newData.setFichaMyo(data.getFichaMyo());
				newData = dataActualizadaService.saveData(newData);			
			}
			
			//REGISTRO PARA CONTEO POR GRUPOS
			AcreditacionGrupoModel log = new AcreditacionGrupoModel();
			log.setDni(dni.toString());
			log.setBarrio(nombreLocalidad);
			GrupoModel grupo = grupoService.getGrupoById(idGrupo);
			log.setGrupo(grupo);
			log.setFechaHoraIngreso(LocalDateTime.now());
			acreditacionGrupoService.save(log);
			
			//ACREDITO EN EXTRANET
			try {
				RequestAcreditacionExtranetDTO req = new RequestAcreditacionExtranetDTO();
				req.setId(dni);
				req.setId_grupo(idGrupo);
				req.setId_localidad(idLocalidad);
				ResponseRequestDTO res = this.acreditarPersonaExtranet(req);
				
				map.put("status", res.getStatus());
				map.put("message", "Persona acreditada correctamente.");
				return new ResponseEntity<>(map, HttpStatus.OK);
			}catch(Exception e) {
				map.put("status", "error");
				map.put("message", e.getMessage());
				return new ResponseEntity<>(map, HttpStatus.OK);
			}
		}
		map.put("status", "error");
		map.put("message", "Falta DNI");
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
	  
	  @GetMapping("/grupos")
	  public Iterable<AcreditacionGrupoModel> getAllAcreditados(){
		  return acreditacionGrupoService.getAll();
	  }
	  
//	  @GetMapping("/grupos/count")
//	  public CounterDTO getTotales(@RequestBody RequestCountDTO req) {
//		  return acreditacionGrupoService.getTotales();
//	  }
	  
		private ResponseRequestDTO getPersona(String dni) {
			String url = "https://www.sgiar.org.ar/api/v1/acredita/persona?token=tokenPrivadoSecret&d=" + dni;
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate.getForObject(url, ResponseRequestDTO.class);
		}
		
		private ResponseRequestDTO acreditarPersonaExtranet(RequestAcreditacionExtranetDTO req) {
			String uri = "https://www.sgiar.org.ar/api/v1-test/acredita/acredita";
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate.postForObject(uri, req, ResponseRequestDTO.class);
		}
		
}
