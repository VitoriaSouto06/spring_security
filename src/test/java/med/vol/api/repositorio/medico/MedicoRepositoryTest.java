package med.vol.api.repositorio.medico;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import jakarta.persistence.EntityManager;
import med.vol.api.dto.DadosCadastroPaciente;
import med.vol.api.dto.EnderecoDto;
import med.vol.api.dto.MedicoDto;
import med.vol.api.dto.MedicoDto2;
import med.vol.api.entidades.consulta.Consulta;
import med.vol.api.entidades.medico.Especialidade;
import med.vol.api.entidades.medico.Medico;
import med.vol.api.entidades.paciente.Paciente;
import med.vol.api.repositorio.MedicoRepository;
import med.vol.api.dto.MedicoDto2;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
	
	@Autowired
	MedicoRepository mRepository;
	@Autowired
	TestEntityManager em;

	@Test
	@DisplayName("Teste que deve devolver null quando o único medico cadastrado não esta disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario1() {
		//GIVEN OU ARANGE
		var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
		
		//Cadastrando médico
		var medico = cadastrarMedico("Medico", "medico@email.com","123456", Especialidade.CARDIOLOGIA);
		
		//Cadastrando paciente
		var paciente = cadastrarPaciente("Vitoria", "vitoria@email.com","000.000.000-00");
		
		//Cadastrando consulta
		cadastrarConsulta(medico,paciente,proximaSegundaAs10);
		
	// WHEN OU ACT
	var medicoLivre = mRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10,proximaSegundaAs10);
	//THEN OU ASSERT 
	assertThat(medicoLivre).isNull();
			

		
	}
	
	
	@Test
	@DisplayName("Teste que deve devolver medico quando o único medico cadastrado esta disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario2() {
		
		
		
		var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
		
		//Cadastrando médico
		var medico = cadastrarMedico("Medico", "medico@gmail.com","123456", Especialidade.CARDIOLOGIA);
		

	
	var medicoLivre = mRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10,proximaSegundaAs10);
	 assertThat(medicoLivre).isEqualTo(medico);
			

		
	}
	
	
	private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
	    em.persist(new Consulta(medico, paciente, data));
	}
	
	private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
	    var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
	    em.persist(medico);
	    return medico;
	}

	
	private Paciente cadastrarPaciente(String nome, String email, String cpf) {
	    var paciente = new Paciente(dadosPaciente(nome, email, cpf));
	    em.persist(paciente);
	    return paciente;
	}

	
	
	private MedicoDto dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
	    return new MedicoDto(
	            nome,
	            email,
	            "61999999999",
	            crm,
	            especialidade,
	            dadosEndereco()
	    );
	}

	private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
	    return new DadosCadastroPaciente(
	            nome,
	            email,
	            "61999999999",
	            cpf,
	            dadosEndereco()
	    );
	}

	private EnderecoDto dadosEndereco() {
	    return new EnderecoDto(
	            "rua xpto",
	            "bairro",
	            "00000000",
	            "Brasilia",
	            "DF",
	            null,
	            null
	    );
	}

}
