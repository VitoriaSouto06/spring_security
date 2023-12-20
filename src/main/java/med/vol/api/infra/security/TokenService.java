package med.vol.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.vol.api.entidades.usuario.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String gerarToken(Usuario usuario) {
		try {
			//Algotimo para gerar o token
		Algorithm algoritimo = Algorithm.HMAC256(secret);
		return JWT.create()
				//Aqui colocamos o nome da api para indetificar
				.withIssuer("API voll.med")
				//Aqui recuperamos o login do usuario para gerar o token dele
				.withSubject(usuario.getLogin())
				//Aqui é o tempo que esse token tem até expirar
				.withExpiresAt(dataExpiracao())
				.sign(algoritimo);
		}catch(JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token jwt ",exception);
		}
		
		
		}
	
	
	//Metodo para saber quem é o usuario que esta vindo na requisição
	public String getSubject(String tokenJWT) {
		try {
			//Algotimo para pegar o usuario que é dono deste token
		Algorithm algoritimo = Algorithm.HMAC256(secret);
		return JWT.require(algoritimo)
				.withIssuer("API voll.med")
				.build()
				//Verificamos o token
				.verify(tokenJWT)
				//Pegamos o usuario dono deste token
				.getSubject();
		
		}catch(JWTCreationException exception) {
			throw new RuntimeException("Token JWT iválido ou expirado ",exception);
		}
		
		
	}
	
	
	
	private Instant dataExpiracao() {
		//Instant para definir o tempo que o teoken vai expirar
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
