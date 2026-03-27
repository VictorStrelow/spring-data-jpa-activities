package com.ctw.strelow.Activities.repository;

import com.ctw.strelow.Activities.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByProfessorId(Long idProfessor);
    List<Curso> findByProfessorNomeContainingIgnoreCase(String nome);
    Optional<Curso> findByIdAndTituloContainingIgnoreCase(Long id, String titulo);

}