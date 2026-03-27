package com.ctw.strelow.Activities.repository;

import com.ctw.strelow.Activities.model.Projeto;
import com.ctw.strelow.Activities.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {}