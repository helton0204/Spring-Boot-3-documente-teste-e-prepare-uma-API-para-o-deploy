package med.voll.api.repository;

import med.voll.api.domain.Especialidade;
import med.voll.api.domain.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

//A interface repository faz o acesso ao banco de dados integrando a entidade jpa com sua respectiva tabela no banco de dados
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m.ativo from Medico m where m.id = :idMedico
            """)
    Boolean findAtivoById(Long idMedico);

    @Query("""
            select m from Medico m where m.ativo = true and m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c where c.data = :data
            )
            order by rand() 
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

}

/*
* A interface JpaRepository é uma interface genérica fornecida pelo Spring Data JPA que possui métodos pré-definidos para
* realizar operações de CRUD (Create, Read, Update, Delete) em um repositório de entidades.
*
* No caso específico dessa interface MedicoRepository, ela é usada para acessar e manipular dados relacionados à entidade
* Medico. O tipo Medico é especificado como o primeiro parâmetro genérico da interface JpaRepository, indicando que os métodos
* fornecidos por essa interface serão aplicados à entidade Medico. O segundo parâmetro genérico Long especifica o tipo do
* identificador único da entidade Medico.
*
* Essa interface MedicoRepository herda todos os métodos da interface JpaRepository, como save, findById, findAll, delete,
* entre outros. Esses métodos permitem realizar operações comuns de persistência de dados, como salvar um médico, buscar
* um médico por ID, buscar todos os médicos, excluir um médico, etc.
* */