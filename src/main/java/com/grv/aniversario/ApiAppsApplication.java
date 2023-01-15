package com.grv.aniversario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.grv.aniversario.security.JWTAuthorizationFilter;


@SpringBootApplication
public class ApiAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAppsApplication.class, args);
	}
	
	private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig  {
		
		
		
		@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
		        .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		        .csrf().disable()
	            .authorizeHttpRequests((authz) -> authz
	            		.antMatchers(HttpMethod.POST,"/auth/**").permitAll()
	            		.antMatchers(HttpMethod.POST, "/**").permitAll()
	            		.antMatchers(HttpMethod.GET, "/**").permitAll()
	            		.antMatchers(HttpMethod.GET, SWAGGER_WHITELIST).permitAll()
	            		
	            		
	                .anyRequest().authenticated()
	            );
	            /*.httpBasic(withDefaults());*/
	        return http.build();
	    }
		
		
	}
	
	
}
