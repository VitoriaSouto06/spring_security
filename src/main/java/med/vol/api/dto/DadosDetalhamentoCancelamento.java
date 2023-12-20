package med.vol.api.dto;

import med.vol.api.entidades.consulta.Consulta;

public record DadosDetalhamentoCancelamento(String fraseFinal) {
	public DadosDetalhamentoCancelamento() {
		this("Consulta cancelada!");
	}
}
