package med.voll.api.dto;

import med.voll.api.domain.Endereco;
import med.voll.api.domain.Paciente;

public record DetalhamentoPacienteDto(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DetalhamentoPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}