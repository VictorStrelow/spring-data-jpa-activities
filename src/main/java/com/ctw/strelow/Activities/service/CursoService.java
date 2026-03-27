package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.CursoRequestDTO;
import com.ctw.strelow.Activities.dto.response.CursoResponseDTO;
import com.ctw.strelow.Activities.mapper.CursoMapper;
import com.ctw.strelow.Activities.model.Curso;
import com.ctw.strelow.Activities.model.Professor;
import com.ctw.strelow.Activities.repository.CursoRepository;
import com.ctw.strelow.Activities.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;
    private final CursoMapper cursoMapper;

    public List<CursoResponseDTO> listarTodos() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toResponseDto)
                .toList();
    }

    public Optional<CursoResponseDTO> buscarPorId(Long id) {
        return  cursoRepository.findById(id)
                .map(cursoMapper::toResponseDto);
    }

    public List<CursoResponseDTO> buscarPorProfessor(Long idProfessor) {
        return cursoRepository.findByProfessorId(idProfessor)
                .stream()
                .map(cursoMapper::toResponseDto)
                .toList();
    }

    public List<CursoResponseDTO> buscarPorNomeProfessor(String nome) {
        return cursoRepository.findByProfessorNomeContainingIgnoreCase(nome)
                .stream()
                .map(cursoMapper::toResponseDto)
                .toList();
    }

    public Optional<CursoResponseDTO> buscarPorIdETitulo(Long id, String titulo) {
        return  cursoRepository.findByIdAndTituloContainingIgnoreCase(id, titulo)
                .map(cursoMapper::toResponseDto);
    }

    public CursoResponseDTO criar(CursoRequestDTO cursoRequestDTO) {
        Professor professor = professorRepository.findById(cursoRequestDTO.idProfessor())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));

        Curso  curso = cursoMapper.toEntity(cursoRequestDTO);
        curso.setProfessor(professor);

        return cursoMapper.toResponseDto(cursoRepository.save(curso));
    }

    public Optional<CursoResponseDTO> atualizar(Long id, CursoRequestDTO cursoRequestDTO) {
        return cursoRepository.findById(id).map(curso -> {
            Professor professor = professorRepository.findById(cursoRequestDTO.idProfessor())
                    .orElseThrow(() -> new RuntimeException("Professor não encontrado."));

            curso.setTitulo(cursoRequestDTO.titulo());
            curso.setCargaHoraria(cursoRequestDTO.cargaHoraria());
            curso.setProfessor(professor);

            return cursoMapper.toResponseDto(cursoRepository.save(curso));
        });
    }

    public boolean deletar(Long id) {
        if (!cursoRepository.existsById(id)) {
            return false;
        }

        cursoRepository.deleteById(id);
        return true;
    }

}