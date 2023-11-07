package med.vol.api.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import med.vol.api.entidades.medico.Medico;
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
	public Page<Medico> findAllByAtivoTrue(Pageable pageable);

	
}
