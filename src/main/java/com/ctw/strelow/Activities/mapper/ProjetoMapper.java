package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.ProjetoRequestDTO;
import com.ctw.strelow.Activities.dto.response.ProjetoResponseDTO;
import com.ctw.strelow.Activities.dto.response.TarefaResponseDTO;
import com.ctw.strelow.Activities.model.Projeto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjetoMapper {

    public Projeto toEntity(ProjetoRequestDTO projetoRequestDTO) {
        Projeto projeto = new Projeto();

        projeto.setNome(projetoRequestDTO.nome());
        projeto.setDescricao(projetoRequestDTO.descricao());

        return projeto;
    }

    public ProjetoResponseDTO toResponseDTO(Projeto projeto) {
        List<TarefaResponseDTO> tarefas = projeto.getTarefas() == null ? List.of() :
                projeto.getTarefas().stream()
                        .map(tarefa -> new TarefaResponseDTO(
                                tarefa.getId(),
                                tarefa.getTitulo(),
                                tarefa.getStatus(),
                                tarefa.getProjeto().getId(),
                                tarefa.getProjeto().getNome()
                        )).toList();

        return new ProjetoResponseDTO(
                projeto.getId(),
                projeto.getNome(),
                projeto.getDescricao(),
                tarefas
        );
    }

}