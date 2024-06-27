package med.voll.api.dto;

import med.voll.api.domain.Especialidade;
import med.voll.api.domain.Medico;

public record ListagemMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public ListagemMedicoDto(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
