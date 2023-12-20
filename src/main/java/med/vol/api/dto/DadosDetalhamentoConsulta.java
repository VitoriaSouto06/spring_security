package med.vol.api.dto;

import java.time.LocalDateTime;

import med.vol.api.entidades.consulta.Consulta;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

	public DadosDetalhamentoConsulta(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(),consulta.getPaciente().getId(), consulta.getData());
	}

}
