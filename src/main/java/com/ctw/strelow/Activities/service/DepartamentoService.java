package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.DepartamentoRequestDTO;
import com.ctw.strelow.Activities.dto.response.DepartamentoResponseDTO;
import com.ctw.strelow.Activities.mapper.DepartamentoMapper;
import com.ctw.strelow.Activities.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    public List<DepartamentoResponseDTO> listarTodos() {
        return departamentoRepository.findAll()
                .stream()
                .map(departamentoMapper::toResponseDto)
                .toList();
    }

    public Optional<DepartamentoResponseDTO> buscarPorId(Long id) {
        return departamentoRepository.findById(id)
                .map(departamentoMapper::toResponseDto);
    }

    public DepartamentoResponseDTO criar(DepartamentoRequestDTO departamentoRequestDTO) {
        return departamentoMapper.toResponseDto(
                departamentoRepository.save(departamentoMapper.toEntity(departamentoRequestDTO))
        );
    }

    public Optional<DepartamentoResponseDTO> atualizar(Long id, DepartamentoRequestDTO departamentoRequestDTO) {
        return departamentoRepository.findById(id).map(departamento -> {
            departamento.setNome(departamentoRequestDTO.nome());

            return departamentoMapper.toResponseDto(departamentoRepository.save(departamento));
        });
    }

    public boolean deletar(Long id) {
        if (!departamentoRepository.existsById(id)) {
            return false;
        }

        departamentoRepository.deleteById(id);
        return true;
    }

}