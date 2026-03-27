package com.ctw.strelow.Activities.repository;

import com.ctw.strelow.Activities.model.Projeto;
import com.ctw.strelow.Activities.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByProjetoId(Long idProjeto);
    Optional<Tarefa> findByIdAndTituloContainingIgnoreCase(Long id, String titulo);

}