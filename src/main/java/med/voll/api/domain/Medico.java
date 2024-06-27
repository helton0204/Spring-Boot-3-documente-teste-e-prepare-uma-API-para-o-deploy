package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AtualizacaoMedicoDto;
import med.voll.api.dto.CadastroMedicoDto;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id //Essa anotação é usada para indicar que o campo id é a chave primária da entidade. A chave primária é um campo único que identifica exclusivamente cada registro na tabela do banco de dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Essa anotação é usada em conjunto com @Id para especificar a estratégia de geração de valores para a chave primária. Nesse caso, está sendo utilizada a estratégia de "IDENTITY", o que significa que o valor será gerado automaticamente pelo banco de dados.
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING) //Essa anotação é usada para especificar o tipo de mapeamento de um campo enumerado (enum) para o banco de dados. Nesse caso, o campo especialidade está sendo mapeado como uma string, onde o valor do enum será armazenado como uma string no banco de dados.
    private Especialidade especialidade;
    @Embedded //Essa anotação é usada para indicar que o campo endereco é um objeto embutido (embedded) na entidade Medico. Isso significa que os campos do objeto Endereco serão mapeados como colunas na tabela do banco de dados associada à entidade Medico. O objeto Endereco é tratado como parte da entidade Medico e não como uma entidade separada.
    private Endereco endereco;
    private Boolean ativo;

    public Medico(CadastroMedicoDto dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(AtualizacaoMedicoDto dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

/*
* A classe Medico é uma entidade JPA (Java Persistence API)
*
* Uma classe JPA (Java Persistence API) é uma classe Java que representa uma entidade persistente em um banco de dados relacional.
* A JPA é uma especificação do Java que define uma interface comum para mapeamento objeto-relacional e persistência de dados em
* bancos de dados relacionais.
*
* Ao utilizar a JPA, você pode criar classes que representam tabelas no banco de dados e mapear os atributos dessas classes para as
* colunas correspondentes nas tabelas. Essas classes, conhecidas como entidades, geralmente são anotadas com as anotações da JPA
* para indicar o mapeamento correto.
*
* A JPA oferece uma série de recursos e funcionalidades para facilitar a persistência de dados, como a criação automática de tabelas,
* consultas de banco de dados usando uma linguagem de consulta chamada JPQL (Java Persistence Query Language), relacionamentos entre
* entidades (como associações de um para um, um para muitos e muitos para muitos), gerenciamento de transações, entre outros.
*
* Além disso, a JPA permite que você escreva código de maneira independente do banco de dados específico que está sendo utilizado.
* Você pode escolher um provedor de persistência compatível com a JPA, como o Hibernate, EclipseLink ou OpenJPA, que implementam a
* especificação da JPA e fornecem a funcionalidade real de persistência.
* */
