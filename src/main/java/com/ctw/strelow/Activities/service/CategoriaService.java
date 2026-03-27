package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.CategoriaRequestDTO;
import com.ctw.strelow.Activities.dto.response.CategoriaResponseDTO;
import com.ctw.strelow.Activities.mapper.CategoriaMapper;
import com.ctw.strelow.Activities.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaResponseDTO> listarTodos() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toResponseDto)
                .toList();
    }

    public Optional<CategoriaResponseDTO> buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toResponseDto);
    }

    public CategoriaResponseDTO criar(CategoriaRequestDTO categoriaRequestDTO) {
        return categoriaMapper.toResponseDto(
                categoriaRepository.save(categoriaMapper.toEntity(categoriaRequestDTO))
        );
    }

    public Optional<CategoriaResponseDTO> atualizar(Long id, CategoriaRequestDTO categoriaRequestDTO) {
        return categoriaRepository.findById(id).map(cat -> {
            cat.setNome(categoriaRequestDTO.nome());

            return categoriaMapper.toResponseDto(categoriaRepository.save(cat));
        });
    }

    public boolean deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            return false;
        }

        categoriaRepository.deleteById(id);
        return true;
    }

}