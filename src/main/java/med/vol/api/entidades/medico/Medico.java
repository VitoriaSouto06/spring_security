package med.vol.api.entidades.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.EnderecoDto;
import med.vol.api.dto.MedicoAlterarDto;
import med.vol.api.dto.MedicoDto;
import med.vol.api.entidades.endereco.*;

@Table(name="medicos")
@Entity(name="Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	private Boolean ativo;
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;

	public Medico(MedicoDto medico) {
		this.ativo = true;
		this.nome = medico.nome();
		this.email = medico.email();
		this.crm = medico.crm();
		this.telefone = medico.telefone();
		this.endereco = new Endereco(medico.endereco());
		this.especialidade = medico.especialidade();
	}
	
	
	public void atualizarInfos(MedicoAlterarDto medico) {
		if(medico.nome() !=null) {
		this.nome = medico.nome();
		}
		if(medico.telefone() != null) {
		this.telefone = medico.telefone();
		}
		if(medico.endereco()!=null) {
		this.endereco.atualizarinformacoes(medico.endereco());
		}
	}
	
	
	public void atualizarAtivo() {
		this.ativo = false;
	}
	
	
}
