package com.grv.aniversario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grv.aniversario.models.GrupoModel;
import com.grv.aniversario.services.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	GrupoService grupoService;

	@GetMapping
	public Iterable<GrupoModel> getGrupos() {
		return grupoService.getAll();
	}
}
