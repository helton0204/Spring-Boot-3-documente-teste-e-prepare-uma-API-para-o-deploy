package med.voll.api.service.validacoesAgendaDeConsultas;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.dto.AgendamentoConsultaDto;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private PacienteRepository repository;

    public void validar(AgendamentoConsultaDto dados){
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("A consulçta não pode ser agendada com paciente inativo");
        }
    }
}
