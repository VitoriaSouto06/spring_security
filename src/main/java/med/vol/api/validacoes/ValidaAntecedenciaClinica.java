package med.vol.api.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.vol.api.dto.DadosAgendamentoConsulta;
import med.vol.api.tratamentoerros.TratamentoDeErros;
@Component
public class ValidaAntecedenciaClinica implements ValidadorAgendamentoDeConsulta{
	
	public void validar(DadosAgendamentoConsulta dados) {
		var horarioAtual = LocalDateTime.now();
		var horarioAntes = dados.data().minusMinutes(30);
		var diferencaMinutos = Duration.between(horarioAtual, horarioAntes).toMinutes();
		if(diferencaMinutos <30) {
			throw new TratamentoDeErros("As consultas devem ser agendadas pelo menos 30 minuts de antecedência");
		}
		
	}

}
