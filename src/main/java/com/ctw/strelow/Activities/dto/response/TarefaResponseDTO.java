package com.ctw.strelow.Activities.dto.response;

public record TarefaResponseDTO (

        Long id,
        String titulo,
        String status,
        Long idProjeto,
        String nomeProjeto

) {}