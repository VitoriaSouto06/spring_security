package med.vol.api.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.vol.api.dto.DadosAgendamentoConsulta;
import med.vol.api.repositorio.MedicoRepository;
import med.vol.api.tratamentoerros.TratamentoDeErros;

@Component
public class ValidaMedicosInativos implements ValidadorAgendamentoDeConsulta{
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var medicoAtivo = medicoRepository.validaAtivo(dados.idMedico());
		if(medicoAtivo == null) {
			throw new TratamentoDeErros("O agendamento de consultas só é permitido para médicos ativos");
		}
	}

}
