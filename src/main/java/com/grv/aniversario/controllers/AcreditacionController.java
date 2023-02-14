package com.grv.aniversario.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grv.aniversario.models.MiembroModel;
import com.grv.aniversario.services.AcreditacionService;
import com.grv.aniversario.services.MiembroService;

@RestController
@RequestMapping("/acreditacion")
public class AcreditacionController {

	@Autowired
	MiembroService miembroService;
	
	@Autowired
	AcreditacionService acreditacionService;
	
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
}
