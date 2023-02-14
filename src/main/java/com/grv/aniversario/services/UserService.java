package com.grv.aniversario.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grv.aniversario.models.UsuarioModel;
import com.grv.aniversario.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Optional<UsuarioModel> getUserByName(String name){
		return userRepository.findByName(name);
	}
	
	public Optional<UsuarioModel> getUserByMail(String mail){
		return userRepository.findByMail(mail);
	} 
}
