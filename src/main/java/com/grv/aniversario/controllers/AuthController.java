package com.grv.aniversario.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grv.aniversario.models.UserAuthModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthController {
	
	@PostMapping("auth/loginWhitPassword")
	public UserAuthModel login(@RequestParam("key") String key, @RequestBody UserAuthModel user) {
		String username = user.getUser();
		String pass = user.getPass();
		String keyHarcode = "-ColoniaSuiza_salud_2022-GRV_APIKeyForApps";
		String userHarcode = "AppsGRV";
		String passHarcode = ".ColoniaSuiza.2022.";
		if(keyHarcode.equalsIgnoreCase(key)) {
			if(username.equalsIgnoreCase(userHarcode) && pass.contentEquals(passHarcode)) {
				String token = getJWTToken(username);
				user.setUser(username);
				user.setToken(token);
				user.setPass("hidden");
				return user;
			}
		}

		return null;
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
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
