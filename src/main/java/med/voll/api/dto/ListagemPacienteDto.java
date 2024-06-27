package med.voll.api.dto;

import med.voll.api.domain.Paciente;

public record ListagemPacienteDto(Long id, String nome, String email, String cpf) {

    public ListagemPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
