package med.voll.api.service.validacoesAgendaDeConsultas;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.dto.AgendamentoConsultaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository repository;

    public void validar(AgendamentoConsultaDto dados){
        var medicoPossuiOutraConsultaNoMesmoHoraio = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaNoMesmoHoraio){
            throw new ValidacaoException("O médico selecionado já possui outra consulta agendada nesse horário");
        }
    }
}
