package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.ClienteRequestDTO;
import com.ctw.strelow.Activities.dto.response.ClienteResponseDTO;
import com.ctw.strelow.Activities.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();

        cliente.setNome(clienteRequestDTO.nome());
        cliente.setEmail(clienteRequestDTO.email());

        return cliente;
    }

    public ClienteResponseDTO toResponseDto(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail()
        );
    }

}