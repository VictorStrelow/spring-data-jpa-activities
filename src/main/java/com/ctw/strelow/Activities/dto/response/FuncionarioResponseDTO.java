package com.ctw.strelow.Activities.dto.response;

public record FuncionarioResponseDTO (

        Long id,
        String nome,
        String cargo,
        Long idDepartamento

) {}