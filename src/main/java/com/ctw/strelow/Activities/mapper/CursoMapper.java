package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.CursoRequestDTO;
import com.ctw.strelow.Activities.dto.response.CursoResponseDTO;
import com.ctw.strelow.Activities.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public Curso toEntity(CursoRequestDTO cursoRequestDTO) {
        Curso curso = new Curso();

        curso.setTitulo(cursoRequestDTO.titulo());
        curso.setCargaHoraria(cursoRequestDTO.cargaHoraria());

        return  curso;
    }

    public CursoResponseDTO toResponseDto(Curso curso) {
        return new CursoResponseDTO(
                curso.getId(),
                curso.getTitulo(),
                curso.getCargaHoraria(),
                curso.getProfessor().getId(),
                curso.getProfessor().getNome()
        );
    }

}