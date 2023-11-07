package med.vol.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.entidades.medico.Especialidade;

public record MedicoDto(
		@NotBlank
		String nome,
		
		@NotBlank
		@Email
		String email,
		
		@NotBlank
		String telefone,
		
		@NotBlank
		@Pattern(regexp="\\d{4,6}")
		String crm,
		
		Especialidade especialidade,
		
		@NotNull
		@Valid
		EnderecoDto endereco) {
	
	

}
