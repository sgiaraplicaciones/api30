package com.grv.aniversario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.grv.aniversario.DTO.ResponseRequestDTO;
import com.grv.aniversario.models.MiembroModel;
import com.grv.aniversario.services.MiembroService;

@RestController
@RequestMapping("/miembros")
public class MiembroController {
	
	@Autowired
	MiembroService miembroService;
	
	@GetMapping(path="/extranet/check/{dni}")
    public String getIfIsMember(@PathVariable("dni") String dni){
		String url = "https://www.sgiar.org.ar/api/v1/public/_p?d=" + dni;
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, String.class);
    }
	
	@GetMapping(path="/extranet/data/{dni}")
    public ResponseRequestDTO getDataMember(@PathVariable("dni") String dni){
		String url = "https://www.sgiar.org.ar/api/v1/acredita/persona?token=tokenPrivadoSecret&d=" + dni;
		RestTemplate restTemplate = new RestTemplate();
    	return restTemplate.getForObject(url, ResponseRequestDTO.class);
    }
	
	@PostMapping(path="/save")
	public MiembroModel guardarMiembro(@RequestBody MiembroModel miembro) {
		return miembroService.saveMiembro(miembro);
	}
		
	
}
