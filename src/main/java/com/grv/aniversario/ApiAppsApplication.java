package com.grv.aniversario;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.grv.aniversario.property.FileStorageProperties;
import com.grv.aniversario.security.JWTAuthorizationFilter;


@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class ApiAppsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ApiAppsApplication.class, args);
	}
	
	private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };
	
	@Bean
    public ServletWebServerFactory servletContainer() {
        // Enable SSL Trafic
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

//         Add HTTP to HTTPS redirect
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }

    /*
    We need to redirect from HTTP to HTTPS. Without SSL, this application used
    port 8082. With SSL it will use port 8443. So, any request for 8082 needs to be
    redirected to HTTPS on 8443.
     */
    private Connector httpToHttpsRedirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(3001);
        return connector;
    }

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig  {
		
		
		
		@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.cors().and()
		        .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		        .csrf().disable()
	            .authorizeHttpRequests((authz) -> authz
	            		.antMatchers(HttpMethod.POST,"/auth/login").permitAll()
	            		.antMatchers(HttpMethod.POST, "/ticket/**").permitAll()
	            		.antMatchers(HttpMethod.POST, "/ticket/event/save").permitAll()
	            		.antMatchers(HttpMethod.POST, "/ticket/update/**").permitAll()
	            		.antMatchers(HttpMethod.GET, "/ticket/getAll/**").permitAll()
	            		.antMatchers(HttpMethod.GET, "/testConnection").permitAll()
	            		.antMatchers(HttpMethod.GET, SWAGGER_WHITELIST).permitAll()
	            		
	            		
	                .anyRequest().authenticated()
	            );
	        return http.build();
	    }
		
		
	}
	
	
}
