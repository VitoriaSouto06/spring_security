package med.vol.api.servicos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.vol.api.dto.DadosAgendamentoConsulta;
import med.vol.api.dto.DadosCancelamentoConsuta;
import med.vol.api.dto.DadosDetalhamentoCancelamento;
import med.vol.api.dto.DadosDetalhamentoConsulta;
import med.vol.api.entidades.consulta.Consulta;
import med.vol.api.entidades.medico.Medico;
import med.vol.api.infra.exception.ValidacaoException;
import med.vol.api.repositorio.ConsultaRepository;
import med.vol.api.repositorio.MedicoRepository;
import med.vol.api.repositorio.PacienteRepository;
import med.vol.api.tratamentoerros.TratamentoDeErros;
import med.vol.api.validacoes.ValidaHorarioFuncionamentoClinica;
import med.vol.api.validacoes.*;


@Service
public class ConsultaService {
	@Autowired
	private ConsultaRepository consultaRepository;
	@Autowired
	private MedicoRepository medicoRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidadorAgendamentoDeConsulta> validadores;
	
	
	public Optional<Consulta> procurarPeloId(Long id) {
		return consultaRepository.findById(id);
	}
	
	public void cancelar(DadosCancelamentoConsuta dados) {
		if(!consultaRepository.existsById(dados.id())) {
			throw new ValidacaoException("Id da consulta não existe, tente novamente");
		}
		
		
		Optional<Consulta> consulta = consultaRepository.findById(dados.id());
		var consulta2 = consulta.get();
		System.out.println(consulta2.getData().toLocalDate());
		System.out.println(LocalDate.now());
		if(consulta2.getData().toLocalDate().equals(LocalDate.now())) {
			throw new ValidacaoException("A consulta só pode ser cancelada com 24h de antecedência");
			}
		else{
			consulta2.inativar();
			
		}
		
		
	}


	public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do paciente não existe");
		}
		
		if(dados.idMedico() !=null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do médico não existe");
		}
		
		validadores.forEach(v -> v.validar(dados));
		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
	
		var medico = escolherMedico(dados);
		if(medico == null) {
			throw new TratamentoDeErros("Não temos médicos disponivel neste dia e horário");
		}
		var consulta = new Consulta(medico,paciente,dados.data());
		consultaRepository.save(consulta);
		return new DadosDetalhamentoConsulta(consulta);
		

	}
	
	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		if(dados.idMedico()!=null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		if(dados.especialidade() == null){
			throw new TratamentoDeErros("Especialidade é obrigatória quando um médico não for escolhido");
		}
		
		
		return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data(),dados.data().minusMinutes(59));
		
	}

	
	
	
	
	
}
