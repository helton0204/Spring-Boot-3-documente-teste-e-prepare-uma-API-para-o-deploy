package med.voll.api.service.validacoesAgendaDeConsultas;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.dto.AgendamentoConsultaDto;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private MedicoRepository repository;

    public void validar(AgendamentoConsultaDto dados){
        if(dados.idMedico() == null){ //escolha opcional do médico
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo){
            throw new ValidacaoException("A consulta não pode ser agendada com um médico excluído");
        }
    }
}
