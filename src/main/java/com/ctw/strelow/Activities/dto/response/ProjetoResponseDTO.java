package com.ctw.strelow.Activities.dto.response;

import com.ctw.strelow.Activities.dto.request.ProjetoRequestDTO;

import java.util.List;

public record ProjetoResponseDTO (

        Long id,
        String nome,
        String descricao,
        List<TarefaResponseDTO> tarefas

) {}