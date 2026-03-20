package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.DepartamentoRequestDTO;
import com.ctw.strelow.Activities.dto.response.DepartamentoResponseDTO;
import com.ctw.strelow.Activities.dto.response.FuncionarioResponseDTO;
import com.ctw.strelow.Activities.model.Departamento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartamentoMapper {

    public Departamento toEntity(DepartamentoRequestDTO departamentoRequestDTO) {
        Departamento departamento = new Departamento();

        departamento.setNome(departamentoRequestDTO.nome());

        return departamento;
    }

    public DepartamentoResponseDTO toResponseDto(Departamento departamento) {
        List<FuncionarioResponseDTO> funcionarios = departamento.getFuncionarios() == null ? List.of():
                departamento.getFuncionarios().stream()
                        .map(funcionario -> new FuncionarioResponseDTO(
                                funcionario.getId(),
                                funcionario.getNome(),
                                funcionario.getCargo(),
                                funcionario.getDepartamento().getId()
                        )).toList();

        return new DepartamentoResponseDTO(
                departamento.getId(),
                departamento.getNome(),
                funcionarios
        );
    }

}