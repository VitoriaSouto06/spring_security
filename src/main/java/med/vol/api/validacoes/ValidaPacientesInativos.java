package med.vol.api.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.vol.api.dto.DadosAgendamentoConsulta;
import med.vol.api.repositorio.PacienteRepository;
import med.vol.api.tratamentoerros.TratamentoDeErros;

@Component
public class ValidaPacientesInativos implements ValidadorAgendamentoDeConsulta{
	@Autowired
	private PacienteRepository  pacienteRepository;

	public void validar(DadosAgendamentoConsulta dados) {
		var pacienteAtivo = pacienteRepository.validaAtivo(dados.idPaciente());
		if(pacienteAtivo == null) {
			throw new TratamentoDeErros("O agendamento de consultas só é permitido para pacientes ativos");
		}
		
	}
}
