package med.vol.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



import jakarta.validation.Valid;
import med.vol.api.dto.DtoAutenticacao;
import med.vol.api.entidades.usuario.Usuario;
import med.vol.api.infra.security.DadosTokenJWT;
import med.vol.api.infra.security.TokenService;

@Controller
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager autenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DtoAutenticacao autenticacao) {
		//Aqui geramos um token baseado em usuario e senha
		var autenticationToken = new UsernamePasswordAuthenticationToken(autenticacao.login(),autenticacao.senha());
		
		//Autenticamos ele com o auhtentication Manager que foi injetado nessa classe, mas para ele ser injetado tivemos
		//que fazer uma configuração na clase security configurations para pegar o autentication manager
		var authentication =  autenticationManager.authenticate(autenticationToken);
		
		
		//Aqui estamos retornando o token baseado no nosso metodos de gerar token, e passando o usuario que foi autenticado
				//usando o autentication manager
		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}

}
