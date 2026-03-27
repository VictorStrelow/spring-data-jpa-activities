package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.ProjetoRequestDTO;
import com.ctw.strelow.Activities.dto.response.ProjetoResponseDTO;
import com.ctw.strelow.Activities.mapper.ProjetoMapper;
import com.ctw.strelow.Activities.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final ProjetoMapper projetoMapper;

    public List<ProjetoResponseDTO> listarTodos() {
        return projetoRepository.findAll()
                .stream()
                .map(projetoMapper::toResponseDTO)
                .toList();
    }

    public Optional<ProjetoResponseDTO> buscarPorId(Long id) {
        return  projetoRepository.findById(id)
                .map(projetoMapper::toResponseDTO);
    }

    public ProjetoResponseDTO criar(ProjetoRequestDTO projetoRequestDTO) {
        return projetoMapper.toResponseDTO(
                projetoRepository.save(projetoMapper.toEntity(projetoRequestDTO))
        );
    }

    public Optional<ProjetoResponseDTO> atualizar(Long id, ProjetoRequestDTO projetoRequestDTO) {
        return projetoRepository.findById(id).map(projeto -> {
            projeto.setNome(projetoRequestDTO.nome());
            projeto.setDescricao(projetoRequestDTO.descricao());

            return projetoMapper.toResponseDTO(projetoRepository.save(projeto));
        });
    }

    public boolean deletar(Long id) {
        if (!projetoRepository.existsById(id)) {
            return false;
        }

        projetoRepository.deleteById(id);
        return true;
    }

}