package med.vol.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.vol.api.repositorio.UsuarioRepository;

//Anotamos como component pois é um filtro genérico
@Component
//Herdamos do OncePerRequstFilter para usar o metodo doFilterInternal
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//Aqui recuperamos o token que vai vir no cabeçalho
		var tokenJWT =  recuperarToken(request);
	
		//Aqui estamos passando o token que recuperamos na linha anterior e pegando o subject dele com
		//o metodo get subject que criamos na classe token service
		if(tokenJWT!=null) {
		var subject = tokenService.getSubject(tokenJWT);
		
		//Se o token esta valido temos que dizer ao spring que ela esta logada, entao temos que usar o find
		//by login para encontrar o usuario
		var usuario = usuarioRepository.findByLogin(subject);
		
		//Nesta linha estamos criando um dto que identifica o usuario
		var authentication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
		
		//Nesta linha estamos forcando a autenticacao com o usuario que identificamos na linha acima 
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		}
		
		
		filterChain.doFilter(request, response);
	}
	

	//Esse metodo serve para recuperar o token que vai vir no header das requisições
	private String recuperarToken(HttpServletRequest request) {
		//Aqui pegamos o header
		var authorizationHeader = request.getHeader("Authorization");
		
		
		//Se o header for vazio significa que nao temos cabeçalho valido vindo na requisição
		if (authorizationHeader !=null) {
			//Se não for nulo nos devolvemos o header
			return authorizationHeader.replace("Bearer ", "");
	
		}
		
		return null;
		
	}

	
	

}
