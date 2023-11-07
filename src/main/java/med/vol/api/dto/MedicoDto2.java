package med.vol.api.dto;

import med.vol.api.entidades.medico.Especialidade;
import med.vol.api.entidades.medico.Medico;

public record MedicoDto2(Long id,String nome, String email,String crm, Especialidade especialidade) {

	public MedicoDto2(Medico medico) {
		this(medico.getId(),medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
	}
	
}
