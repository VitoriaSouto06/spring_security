package med.vol.api.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import med.vol.api.entidades.consulta.MotivoCancelamento;
import med.vol.api.entidades.medico.Especialidade;

public record DadosCancelamentoConsuta(@NotNull Long id, MotivoCancelamento motivoCancelamento) {

}
