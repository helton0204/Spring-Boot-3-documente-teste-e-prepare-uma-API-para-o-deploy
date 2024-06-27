package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.Especialidade;

public record CadastroMedicoDto(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        EnderecoDto endereco) {
}

/*
* DadosCadastroMedico é uma classe DTO (Data Transfer Object), também conhecida como modelo de transporte de dados, é um padrão
* de design comumente utilizado em desenvolvimento de software para transferir dados entre camadas ou componentes de um sistema.
* O objetivo principal de uma classe DTO é encapsular e transportar dados de forma eficiente e coesa, evitando a necessidade de
* expor diretamente os detalhes da implementação ou estrutura interna dos objetos envolvidos.

* As classes DTO geralmente são estruturadas como simples recipientes de dados, sem comportamentos complexos ou lógica de negócios.
* Elas contêm apenas atributos e métodos básicos de acesso (getters e setters) para os dados que estão sendo transferidos.

* As classes DTO são especialmente úteis em sistemas distribuídos ou em arquiteturas em camadas, onde as informações precisam
* ser transferidas entre diferentes partes do sistema. Por exemplo, em uma aplicação web, você pode ter uma classe DTO para
* representar os dados de um formulário preenchido pelo usuário, que será transferida do frontend (interface do usuário) para
* o backend (servidor) para processamento. Dessa forma, a classe DTO oferece uma estrutura comum e bem definida para a troca
* de informações, facilitando a comunicação e evitando o acoplamento desnecessário entre os componentes do sistema.
* */