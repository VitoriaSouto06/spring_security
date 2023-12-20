package med.vol.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import med.vol.api.entidades.consulta.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Long>{

}
