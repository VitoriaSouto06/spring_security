package med.vol.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Classe para configurações do spring security
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private SecurityFilter securityFilter;
	
	//Desabilitando o formulário padrão
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http.csrf(csrf -> csrf.disable())
	            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(req -> {
	                req.requestMatchers(HttpMethod.POST, "/login").permitAll();
	                req.anyRequest().authenticated();
	            })
	            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}
	
	@Bean
	//Ensinando ao spring como injetar o authentication manager que usamos na controller para autenticar um token que seria o usuario e senha
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}
	
	@Bean
	//Codifica a senha que vem do payload do postman 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
