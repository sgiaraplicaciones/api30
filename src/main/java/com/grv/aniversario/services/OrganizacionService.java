package com.grv.aniversario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.OrganizacionModel;
import com.grv.aniversario.repositories.OrganizacionRepository;

@Service
public class OrganizacionService {

	@Autowired
	OrganizacionRepository organizacionRepository;
	
	public OrganizacionModel save(OrganizacionModel org) {
		return organizacionRepository.save(org);
	}
}
