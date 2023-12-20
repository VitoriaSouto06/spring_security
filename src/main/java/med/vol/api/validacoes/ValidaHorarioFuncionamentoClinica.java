package med.vol.api.validacoes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.vol.api.dto.DadosAgendamentoConsulta;
import med.vol.api.tratamentoerros.TratamentoDeErros;

@Component
public class ValidaHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{
	
	public void validar(DadosAgendamentoConsulta dados){
		var diaConsulta = dados.data().getDayOfWeek();
		var domingo = dados.data().getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var antesDaAberturaClinica = dados.data().getHour() <7;
		var depoisDoFechamentoClina = dados.data().getHour()>18;
		
		if(domingo || antesDaAberturaClinica || depoisDoFechamentoClina) {
			throw new TratamentoDeErros("Não podemos agendar uma consulta fora do dia ou horário de atendimento da Clinica");
		}
	}
	
}
