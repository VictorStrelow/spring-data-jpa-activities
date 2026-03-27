package com.ctw.strelow.Activities.dto.request;

public record TarefaRequestDTO (

        String titulo,
        String status,
        Long idProjeto

) {}