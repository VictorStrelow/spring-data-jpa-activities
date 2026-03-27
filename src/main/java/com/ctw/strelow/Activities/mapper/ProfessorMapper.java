package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.ProfessorRequestDTO;
import com.ctw.strelow.Activities.dto.response.CursoResponseDTO;
import com.ctw.strelow.Activities.dto.response.ProfessorResponseDTO;
import com.ctw.strelow.Activities.model.Professor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfessorMapper {

    public Professor toEntity(ProfessorRequestDTO professorRequestDTO) {
        Professor professor = new Professor();

        professor.setNome(professorRequestDTO.nome());
        professor.setEspecialidade(professorRequestDTO.especialidade());

        return professor;
    }

    public ProfessorResponseDTO toResponseDto(Professor professor) {
        List<CursoResponseDTO> cursos = professor.getCursos() == null ? List.of() :
                professor.getCursos().stream()
                        .map(curso -> new CursoResponseDTO(
                                curso.getId(),
                                curso.getTitulo(),
                                curso.getCargaHoraria(),
                                curso.getProfessor().getId(),
                                curso.getProfessor().getNome()
                        )).toList();

        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getNome(),
                professor.getEspecialidade(),
                cursos
        );
    }

}