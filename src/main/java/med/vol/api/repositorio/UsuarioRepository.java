package med.vol.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import med.vol.api.entidades.usuario.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

	
	UserDetails findByLogin(String login);
}
