package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.ClienteRequestDTO;
import com.ctw.strelow.Activities.dto.response.ClienteResponseDTO;
import com.ctw.strelow.Activities.mapper.ClienteMapper;
import com.ctw.strelow.Activities.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponseDto)
                .toList();
    }

    public Optional<ClienteResponseDTO> buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toResponseDto);
    }

    public List<ClienteResponseDTO> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(clienteMapper::toResponseDto)
                .toList();
    }

    public ClienteResponseDTO criar(ClienteRequestDTO  clienteRequestDTO) {
        return clienteMapper.toResponseDto(
                clienteRepository.save(clienteMapper.toEntity(clienteRequestDTO)));
    }

    public Optional<ClienteResponseDTO> atualizar(Long id, ClienteRequestDTO clienteRequestDTO) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(clienteRequestDTO.nome());
            cliente.setEmail(clienteRequestDTO.email());

            return clienteMapper.toResponseDto(clienteRepository.save(cliente));
        });
    }

    public boolean deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            return  false;
        }

        clienteRepository.deleteById(id);
        return true;
    }

}