package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.EnderecoDto;

public record AtualizacaoMedicoDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
