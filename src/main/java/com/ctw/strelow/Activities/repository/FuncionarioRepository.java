package com.ctw.strelow.Activities.repository;

import com.ctw.strelow.Activities.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByDepartamentoId(Long idDepartamento);
    List<Funcionario> findByNomeContainingIgnoreCase(String nome);
    Optional<Funcionario> findByIdAndNomeContainingIgnoreCase(Long id,String nome);

}