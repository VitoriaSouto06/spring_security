package med.vol.api.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.vol.api.dto.DadosAgendamentoConsulta;
import med.vol.api.repositorio.MedicoRepository;
import med.vol.api.tratamentoerros.TratamentoDeErros;

@Component
public class ValidaDiaEHorarioMedico implements ValidadorAgendamentoDeConsulta{
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void validar(DadosAgendamentoConsulta dados){
		var medicoSemAgendamentoDiaHorario = medicoRepository.validaAgendaDiaEHora(dados.idMedico(), dados.data());
		if(medicoSemAgendamentoDiaHorario == null) {
			throw new TratamentoDeErros("Este medico já tem um agendamento neste dia e horário");
		}
		
		var validaUmaHoraAtras = medicoRepository.valida1hAntes(dados.idMedico(), dados.data().minusMinutes(59));
		if(validaUmaHoraAtras == null) {
			throw new TratamentoDeErros("Este médico já tem uma consulta neste horário");
		}
	}
}
