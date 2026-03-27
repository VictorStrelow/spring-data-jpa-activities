package com.ctw.strelow.Activities.dto.response;

public record CursoResponseDTO (

        Long id,
        String titulo,
        Integer cargaHoraria,
        Long idProfessor,
        String nomeProfessor

) {}