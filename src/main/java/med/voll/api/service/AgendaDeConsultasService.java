package med.voll.api.service;

import med.voll.api.domain.Consulta;
import med.voll.api.domain.Medico;
import med.voll.api.dto.AgendamentoConsultaDto;
import med.voll.api.dto.DetalhamentoConsultaDto;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.validacoesAgendaDeConsultas.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultasService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores; //aqui o Spring vai procurar todas as classes que implementam a interface ValidadorAgendamentoDeConsulta e vai fazer a injeção de dependências através dessa lista List<ValidadorAgendamentoDeConsulta>

    public DetalhamentoConsultaDto agendarConsulta(AgendamentoConsultaDto dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);

        return new DetalhamentoConsultaDto(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDto dados){
        if(dados.idMedico() != null){
            Medico medicoEscolhido = medicoRepository.getReferenceById(dados.idMedico());
            return medicoEscolhido;
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido");
        }
        Medico medicoAleatorio = medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data()); //aqui o retorno pode ser null caso não exista médico na data selecionada
        return medicoAleatorio;
    }
}
