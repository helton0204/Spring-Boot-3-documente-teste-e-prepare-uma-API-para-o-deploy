package med.voll.api.service.validacoesAgendaDeConsultas;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.dto.AgendamentoConsultaDto;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{
    public void validar(AgendamentoConsultaDto dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertudaDAClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if(domingo || antesDaAbertudaDAClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Não é possível agendar consulta fora do horário de atendimento da clínica");
        }
    }
}
