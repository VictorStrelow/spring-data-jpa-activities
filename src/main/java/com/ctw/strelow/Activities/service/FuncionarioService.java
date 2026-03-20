package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.FuncionarioRequestDTO;
import com.ctw.strelow.Activities.dto.response.FuncionarioResponseDTO;
import com.ctw.strelow.Activities.mapper.FuncionarioMapper;
import com.ctw.strelow.Activities.model.Departamento;
import com.ctw.strelow.Activities.model.Funcionario;
import com.ctw.strelow.Activities.repository.DepartamentoRepository;
import com.ctw.strelow.Activities.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final DepartamentoRepository departamentoRepository;
    private final FuncionarioMapper funcionarioMapper;

    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(funcionarioMapper::toResponseDto)
                .toList();
    }

    public Optional<FuncionarioResponseDTO> buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .map(funcionarioMapper::toResponseDto);
    }

    public List<FuncionarioResponseDTO> buscarPorDepartamento(Long idDepartamento) {
        return funcionarioRepository.findByDepartamentoId(idDepartamento)
                .stream()
                .map(funcionarioMapper::toResponseDto)
                .toList();
    }

    public List<FuncionarioResponseDTO> buscarPorNome(String nome) {
        return funcionarioRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(funcionarioMapper::toResponseDto)
                .toList();
    }

    public Optional<FuncionarioResponseDTO> buscarPorIdENome(Long id, String nome) {
        return funcionarioRepository.findByIdAndNomeContainingIgnoreCase(id, nome)
                .map(funcionarioMapper::toResponseDto);
    }

    public FuncionarioResponseDTO criar(FuncionarioRequestDTO  funcionarioRequestDTO) {
        Departamento departamento = departamentoRepository.findById(funcionarioRequestDTO.idDepartamento())
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado."));

        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioRequestDTO);
        funcionario.setDepartamento(departamento);

        return funcionarioMapper.toResponseDto(funcionarioRepository.save(funcionario));
    }

    public Optional<FuncionarioResponseDTO> atualizar(Long id, FuncionarioRequestDTO funcionarioRequestDTO) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            Departamento departamento = departamentoRepository.findById(funcionarioRequestDTO.idDepartamento())
                    .orElseThrow(() -> new RuntimeException("Departamento não encontrado."));

            funcionario.setNome(funcionarioRequestDTO.nome());
            funcionario.setCargo(funcionarioRequestDTO.cargo());
            funcionario.setDepartamento(departamento);

            return funcionarioMapper.toResponseDto(funcionarioRepository.save(funcionario));
        });
    }

    public boolean deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            return false;
        }

        funcionarioRepository.deleteById(id);
        return true;
    }

}