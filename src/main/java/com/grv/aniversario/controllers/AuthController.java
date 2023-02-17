package com.grv.aniversario.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.grv.aniversario.DTO.FirebaseResponseDTO;
import com.grv.aniversario.DTO.UserDTO;
import com.grv.aniversario.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthController {
	
	@Autowired
	UserService userService;
	private HttpHeaders headers;
	
	
	@PostMapping("auth/login")
	public UserDTO login(@RequestBody UserDTO userDTO) {
		
		boolean loginFirebase = loginInFirebase(userDTO.getUser(), userDTO.getPass());
		if(loginFirebase) {
			String token = getJWTToken(userDTO.getUser());
			userDTO.setToken(token);
			userDTO.setPass(null);
		}
		return userDTO;
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 18000000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	private boolean loginInFirebase(String email, String password) {
    	String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDzuyH9BHl93tGo0K0M-O4X58-BXBI5Jw4";		
    	RestTemplate restTemplate = new RestTemplate();
    	JsonObject json = new JsonObject();
    	json.addProperty("email", email);
    	json.addProperty("password", password);
    	json.addProperty("returnSecureToken", true);
    	headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    	HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
    	FirebaseResponseDTO response = null;
    	try {
    		response = restTemplate.postForObject(url, request,  FirebaseResponseDTO.class);
    	}catch (HttpClientErrorException err) {
    		System.out.println(err.getMessage());
    		return false;
    	}
    	
    	if(!response.getIdToken().isBlank()) {
    		return true;
    	}
    	return false;
    }
}
