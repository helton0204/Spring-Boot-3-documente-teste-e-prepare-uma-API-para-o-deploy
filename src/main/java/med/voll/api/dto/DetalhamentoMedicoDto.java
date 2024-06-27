package med.voll.api.dto;

import med.voll.api.domain.Endereco;
import med.voll.api.domain.Especialidade;
import med.voll.api.domain.Medico;

public record DetalhamentoMedicoDto(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    public DetalhamentoMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco()); //construtor da classe dto DadosDetalhamentoMedico

    }
}