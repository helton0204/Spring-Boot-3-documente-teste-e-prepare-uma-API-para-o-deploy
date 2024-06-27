package med.voll.api.dto;

import med.voll.api.domain.Consulta;
import med.voll.api.domain.Especialidade;

import java.time.LocalDateTime;

public record ListagemConsultaDto(Long id, String nomeMedico, Especialidade especialidadeMedico, String nomePaciente, LocalDateTime data) {
    public ListagemConsultaDto(Consulta consulta){
        this(consulta.getId(), consulta.getMedico().getNome(), consulta.getMedico().getEspecialidade(), consulta.getPaciente().getNome(), consulta.getData());
    }
}

