package med.vol.api.entidades.consulta;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.MedicoDto;
import med.vol.api.entidades.endereco.Endereco;
import med.vol.api.entidades.medico.Especialidade;
import med.vol.api.entidades.medico.Medico;
import med.vol.api.entidades.paciente.Paciente;

@Table(name="consultas")
@Entity(name="Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="medico_id")
	private Medico medico;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
	private LocalDateTime data;
	
	private Boolean ativo;
	

	public Consulta(Medico medico, Paciente paciente, LocalDateTime data) {
		this.ativo = true;
		this.medico = medico;
		this.paciente = paciente;
		this.data = data;
	}
	
	public void inativar() {
		this.ativo = false;
	}
}
