package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.FuncionarioRequestDTO;
import com.ctw.strelow.Activities.dto.response.FuncionarioResponseDTO;
import com.ctw.strelow.Activities.model.Funcionario;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {

    public Funcionario toEntity(FuncionarioRequestDTO funcionarioRequestDTO) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(funcionarioRequestDTO.nome());
        funcionario.setCargo(funcionarioRequestDTO.cargo());

        return funcionario;
    }

    public FuncionarioResponseDTO toResponseDto(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo(),
                funcionario.getDepartamento().getId()
        );
    }

}