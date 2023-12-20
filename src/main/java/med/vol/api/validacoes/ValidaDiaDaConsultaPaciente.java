package med.vol.api.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.vol.api.dto.DadosAgendamentoConsulta;
import med.vol.api.repositorio.PacienteRepository;
import med.vol.api.tratamentoerros.TratamentoDeErros;

@Component
public class ValidaDiaDaConsultaPaciente implements ValidadorAgendamentoDeConsulta{
	@Autowired
	private PacienteRepository pacienteRepositoy;
	
	public void validar(DadosAgendamentoConsulta dados) {
	var	pacienteSemConulta = pacienteRepositoy.validaAgendaDia(dados.idPaciente(),dados.data().getDayOfMonth(), dados.data().getMonthValue());
	if(pacienteSemConulta ==null) {
		throw new TratamentoDeErros("Este paciente já tem agendamento neste dia");
	}
	}
}
