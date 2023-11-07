package med.vol.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.vol.api.dto.DadosDetalhamentoMedico;
import med.vol.api.dto.MedicoAlterarDto;
import med.vol.api.dto.MedicoDto;
import med.vol.api.dto.MedicoDto2;
import med.vol.api.entidades.medico.Medico;
import med.vol.api.servicos.MedicoService;

@RestController
@RequestMapping("medicos")

public class MedicoController {
	@Autowired
	private MedicoService medicoService;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid MedicoDto medico, UriComponentsBuilder uriBuilder) {
		var medico2 = new Medico(medico);
		medicoService.salvar(medico2);
	
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico2.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico2));
		
	}
	
	@GetMapping
	public ResponseEntity<Page<MedicoDto2>> listar(@PageableDefault(size=10, sort={"nome"})Pageable pageable){
		var page = medicoService.listar(pageable);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity altualizar(@RequestBody @Valid MedicoAlterarDto medico){
		var medico2 = medicoService.procurarPeloId(medico.id());
		medico2.atualizarInfos(medico);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico2));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Medico> excluir(@PathVariable Long id){
		Medico medico2 = medicoService.procurarPeloId(id);
		medico2.atualizarAtivo();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var medico = medicoService.procurarPeloId(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
		
	}
}
