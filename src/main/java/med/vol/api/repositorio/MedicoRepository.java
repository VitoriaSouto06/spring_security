package med.vol.api.repositorio;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import med.vol.api.entidades.medico.Especialidade;
import med.vol.api.entidades.medico.Medico;
import med.vol.api.entidades.paciente.Paciente;
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{
	Page<Medico> findAllByAtivoTrue(Pageable paginacao); 
	
	@Query("""
            select m from Medico m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
               	and c.data >= :hora
               	and c.ativo =true
            )
            order by rand()
            limit 1
            """)
	public Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data, LocalDateTime hora);

	@Query("SELECT m from Medico m where m.id = :id and m.ativo = true")
    public Medico validaAtivo(Long id);
	
	
	
	   @Query("""
	            select m from Medico m
	            where
	            m.id = :id
	            and
	            m.id not in(
	                select c.medico.id from Consulta c
	                where
	                c.data = :data
	                and c.ativo =true
	            )
	            order by rand()
	            limit 1
	            """)
	    Medico validaAgendaDiaEHora(Long id, LocalDateTime data);
	   
	   
	   @Query("""
		   		select m from Medico m
		   		where
		   		m.id = :id
		   		and
		   		m.id not in(
	                select c.medico.id from Consulta c
	                where
	                c.data >= :data
	                and c.ativo =true
	            )
	            order by rand()
	            limit 1
		   		""")
		   Medico valida1hAntes(Long id, LocalDateTime data);
	
}
