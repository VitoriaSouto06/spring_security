package med.vol.api.servicos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import med.vol.api.dto.MedicoAlterarDto;
import med.vol.api.dto.MedicoDto;
import med.vol.api.dto.MedicoDto2;
import med.vol.api.entidades.medico.Medico;
import med.vol.api.repositorio.MedicoRepository;
@Service
public class MedicoService {
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void salvar(Medico medico) {
		medicoRepository.save(medico);
	}
	
	public Page<MedicoDto2> listar(@PageableDefault(size=10, sort={"nome"})Pageable pageable){
		return medicoRepository.findAllByAtivoTrue(pageable).map(MedicoDto2::new);
	}
	
	public Medico procurarPeloId(Long long1) {
		return medicoRepository.getReferenceById(long1);
	}
	
	public void excluir(Long id) {
		medicoRepository.deleteById(id);
	}
	
	public Medico validaAtivo(Long id) {
		return medicoRepository.validaAtivo(id);
	}
	
	public Medico validaAgendaDiaEHora(Long id, LocalDateTime data) {
		return medicoRepository.validaAgendaDiaEHora(id, data);
	}
}
