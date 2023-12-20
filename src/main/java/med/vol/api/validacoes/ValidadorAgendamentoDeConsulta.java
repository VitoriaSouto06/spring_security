package med.vol.api.validacoes;

import org.springframework.stereotype.Component;

import med.vol.api.dto.DadosAgendamentoConsulta;

@Component
public interface ValidadorAgendamentoDeConsulta {

	void validar(DadosAgendamentoConsulta dados);
}
