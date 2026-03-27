package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.ProjetoRequestDTO;
import com.ctw.strelow.Activities.dto.request.TarefaRequestDTO;
import com.ctw.strelow.Activities.dto.response.TarefaResponseDTO;
import com.ctw.strelow.Activities.mapper.TarefaMapper;
import com.ctw.strelow.Activities.model.Projeto;
import com.ctw.strelow.Activities.model.Tarefa;
import com.ctw.strelow.Activities.repository.ProjetoRepository;
import com.ctw.strelow.Activities.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;
    private final TarefaMapper tarefaMapper;

    public List<TarefaResponseDTO> listarTodos() {
        return tarefaRepository.findAll()
                .stream()
                .map(tarefaMapper::toResponseDto)
                .toList();
    }

    public Optional<TarefaResponseDTO> buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .map(tarefaMapper::toResponseDto);
    }

    public List<TarefaResponseDTO> buscarPorProjetoId(Long idProjeto) {
        return tarefaRepository.findByProjetoId(idProjeto)
                .stream()
                .map(tarefaMapper::toResponseDto)
                .toList();
    }

    public Optional<TarefaResponseDTO> buscarPorIdETitulo(Long id, String titulo) {
        return tarefaRepository.findByIdAndTituloContainingIgnoreCase(id, titulo)
                .map(tarefaMapper::toResponseDto);
    }

    public TarefaResponseDTO criar(TarefaRequestDTO tarefaRequestDTO) {
        Projeto projeto = projetoRepository.findById(tarefaRequestDTO.idProjeto())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado."));

        Tarefa tarefa = tarefaMapper.toEntity(tarefaRequestDTO);
        tarefa.setProjeto(projeto);

        return  tarefaMapper.toResponseDto(tarefaRepository.save(tarefa));
    }

    public Optional<TarefaResponseDTO> atualizar(Long id, TarefaRequestDTO tarefaRequestDTO) {
        return tarefaRepository.findById(id).map(tarefa -> {
            Projeto projeto = projetoRepository.findById(tarefaRequestDTO.idProjeto())
                    .orElseThrow(() -> new RuntimeException("Projeto não encontrado."));

            tarefa.setTitulo(tarefaRequestDTO.titulo());
            tarefa.setStatus(tarefaRequestDTO.status());
            tarefa.setProjeto(projeto);

            return tarefaMapper.toResponseDto(tarefaRepository.save(tarefa));
        });
    }

    public boolean deletar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            return false;
        }

        tarefaRepository.deleteById(id);
        return true;
    }

}