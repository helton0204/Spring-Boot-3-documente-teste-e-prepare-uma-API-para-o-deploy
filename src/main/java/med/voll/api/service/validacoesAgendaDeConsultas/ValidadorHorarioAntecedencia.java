package med.voll.api.service.validacoesAgendaDeConsultas;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.dto.AgendamentoConsultaDto;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{

    public void validar(AgendamentoConsultaDto dados){
        var dataConsulta = dados.data();
        var dataAtual = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataAtual, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("A Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
