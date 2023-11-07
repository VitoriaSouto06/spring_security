package med.vol.api.entidades.endereco;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.EnderecoDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	

	
	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String cidade;
	private String uf;
	


	public Endereco(EnderecoDto endereco) {
		this.logradouro = endereco.logradouro();
		this.bairro = endereco.bairro();
		this.cep = endereco.cep();
		this.numero = endereco.numero();
		this.cidade = endereco.cidade();
		this.uf = endereco.uf();
	}



	public void atualizarinformacoes(EnderecoDto endereco) {
		if(endereco.logradouro() !=null) {
		this.logradouro = endereco.logradouro();
		}
		if(endereco.bairro()!=null) {
		this.bairro = endereco.bairro();
		}
		if(endereco.cep()!=null) {
		this.cep = endereco.cep();
		}
		if(endereco.numero()!=null) {
		this.numero = endereco.numero();
		}
		if(endereco.cidade()!=null) {
		this.cidade = endereco.cidade();
		}
		if(endereco.uf()!=null) {
		this.uf = endereco.uf();}
	}
	
	
	
	
	
	
}
