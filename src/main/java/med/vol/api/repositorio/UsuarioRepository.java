package med.vol.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import med.vol.api.entidades.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

	
	UserDetails findByLogin(String login);
}
