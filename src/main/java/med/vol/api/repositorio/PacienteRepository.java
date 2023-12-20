package med.vol.api.repositorio;

import java.time.LocalDateTime;
import java.time.Month;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.vol.api.entidades.paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
    
    @Query("SELECT p from Paciente p where p.id = :id and p.ativo = true")
    Paciente validaAtivo(Long id);
    

    
    @Query("""
            select p from Paciente p
            where
            p.id = :id
            and
            p.id not in(
                select c.paciente.id from Consulta c
                where
                  EXTRACT(day FROM c.data)=:dia
                and
                EXTRACT(month FROM c.data)=:mes
                and c.ativo = true
            )
            order by rand()
            limit 1
            """)
	Paciente validaAgendaDia(Long id, int dia, int mes);
    
  
}
