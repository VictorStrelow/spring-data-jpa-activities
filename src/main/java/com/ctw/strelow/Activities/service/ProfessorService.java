package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.ProfessorRequestDTO;
import com.ctw.strelow.Activities.dto.response.ProfessorResponseDTO;
import com.ctw.strelow.Activities.mapper.ProfessorMapper;
import com.ctw.strelow.Activities.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public List<ProfessorResponseDTO> listarTodos() {
        return professorRepository.findAll()
                .stream()
                .map(professorMapper::toResponseDto)
                .toList();
    }

    public Optional<ProfessorResponseDTO> buscarPorId(Long id) {
        return professorRepository.findById(id)
                .map(professorMapper::toResponseDto);
    }

    public ProfessorResponseDTO criar(ProfessorRequestDTO professorRequestDTO) {
        return professorMapper.toResponseDto(
                professorRepository.save(professorMapper.toEntity(professorRequestDTO))
        );
    }

    public Optional<ProfessorResponseDTO> atualizar(Long id, ProfessorRequestDTO professorRequestDTO) {
        return professorRepository.findById(id).map(professor -> {
            professor.setNome(professorRequestDTO.nome());
            professor.setEspecialidade(professorRequestDTO.especialidade());

            return professorMapper.toResponseDto(professorRepository.save(professor));
        });
    }

    public boolean deletar(Long id) {
        if (!professorRepository.existsById(id)) {
            return false;
        }

        professorRepository.deleteById(id);
        return true;
    }

}