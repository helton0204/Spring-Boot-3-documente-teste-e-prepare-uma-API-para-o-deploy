package med.voll.api.service.validacoesAgendaDeConsultas;

import med.voll.api.dto.AgendamentoConsultaDto;

public interface ValidadorAgendamentoDeConsulta {

    void validar(AgendamentoConsultaDto dados);
}
