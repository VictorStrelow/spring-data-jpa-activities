package com.ctw.strelow.Activities.dto.response;

import java.util.List;

public record DepartamentoResponseDTO (

        Long id,
        String nome,
        List<FuncionarioResponseDTO> funcionarios

) {}