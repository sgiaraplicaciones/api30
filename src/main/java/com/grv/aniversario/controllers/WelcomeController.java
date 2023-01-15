package com.grv.aniversario.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {

	@GetMapping
	public String welcome() {
		return "<h3> API SGIAR </h3> <p>Conexión Establecida correctamente<p>";
	}
	
	@GetMapping(path="/testConnection")
    public boolean testController() {
    	return true;
    }
    
    @GetMapping(path="/testConnection/auth")
    public boolean testControllerAuth() {
    	return true;
    }
    
}
