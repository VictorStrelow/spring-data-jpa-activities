package com.ctw.strelow.Activities.dto.response;

import java.util.List;

public record ProfessorResponseDTO (

        Long id,
        String nome,
        String especialidade,
        List<CursoResponseDTO> cursos

) {}