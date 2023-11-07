package med.vol.api.dto;

import jakarta.validation.constraints.NotNull;

public record MedicoAlterarDto(
		@NotNull
		Long id, 
		
		
		String nome, 
		
		
		String telefone, 
		
		
		EnderecoDto endereco) {

}
