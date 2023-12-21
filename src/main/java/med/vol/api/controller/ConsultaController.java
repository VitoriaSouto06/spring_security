package med.vol.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.dto.DadosAgendamentoConsulta;
import med.vol.api.dto.DadosCancelamentoConsuta;
import med.vol.api.dto.DadosDetalhamentoConsulta;
import med.vol.api.entidades.consulta.Consulta;
import med.vol.api.entidades.medico.Medico;
import med.vol.api.servicos.ConsultaService;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
	
	@Autowired
	private ConsultaService consultaService;
	
	
	@PostMapping
	public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
		var dto = consultaService.agendar(dados);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping
	@Transactional
	public void excluir(@RequestBody @Valid DadosCancelamentoConsuta dados){
		consultaService.cancelar(dados);
	
	}
	
	
}
