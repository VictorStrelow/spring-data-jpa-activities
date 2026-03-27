package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.TarefaRequestDTO;
import com.ctw.strelow.Activities.dto.response.TarefaResponseDTO;
import com.ctw.strelow.Activities.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public Tarefa toEntity(TarefaRequestDTO tarefaRequestDTO) {
        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(tarefaRequestDTO.titulo());
        tarefa.setStatus(tarefaRequestDTO.status());

        return tarefa;
    }

    public TarefaResponseDTO toResponseDto(Tarefa tarefa) {
        return  new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getStatus(),
                tarefa.getProjeto().getId(),
                tarefa.getProjeto().getNome()
        );
    }

}