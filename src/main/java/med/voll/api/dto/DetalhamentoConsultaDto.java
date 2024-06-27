package med.voll.api.dto;

import med.voll.api.domain.Consulta;

import java.time.LocalDateTime;

public record DetalhamentoConsultaDto(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DetalhamentoConsultaDto(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
