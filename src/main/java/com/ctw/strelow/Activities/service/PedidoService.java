package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.ClienteRequestDTO;
import com.ctw.strelow.Activities.dto.request.PedidoRequestDTO;
import com.ctw.strelow.Activities.dto.response.PedidoResponseDTO;
import com.ctw.strelow.Activities.mapper.PedidoMapper;
import com.ctw.strelow.Activities.model.Cliente;
import com.ctw.strelow.Activities.model.Pedido;
import com.ctw.strelow.Activities.repository.ClienteRepository;
import com.ctw.strelow.Activities.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoMapper pedidoMapper;

    public List<PedidoResponseDTO> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(pedidoMapper::toResponseDto)
                .toList();
    }

    public Optional<PedidoResponseDTO> buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(pedidoMapper::toResponseDto);
    }

    public List<PedidoResponseDTO> buscarPorIdCliente(Long idCliente) {
        return pedidoRepository.findByClienteId(idCliente)
                .stream()
                .map(pedidoMapper::toResponseDto)
                .toList();
    }

    public List<PedidoResponseDTO> buscarPorNomeCliente(String nome) {
        return pedidoRepository.findByClienteNomeContainingIgnoreCase(nome)
                .stream()
                .map(pedidoMapper::toResponseDto)
                .toList();
    }

    public Optional<PedidoResponseDTO> buscarPorIdEDescricao(Long id, String descricao) {
        return pedidoRepository.findByIdAndDescricaoContainingIgnoreCase(id, descricao)
                .map(pedidoMapper::toResponseDto);
    }

    public List<PedidoResponseDTO> buscarPorClienteOrdenadoPorId(Long idCliente) {
        return pedidoRepository.findByClienteIdOrderById(idCliente)
                .stream()
                .map(pedidoMapper::toResponseDto)
                .toList();
    }

    public PedidoResponseDTO criar(PedidoRequestDTO pedidoRequestDTO) {
        Cliente cliente = clienteRepository.findById(pedidoRequestDTO.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Pedido pedido = pedidoMapper.toEntity(pedidoRequestDTO);
        pedido.setCliente(cliente);

        return pedidoMapper.toResponseDto(pedidoRepository.save(pedido));
    }

    public Optional<PedidoResponseDTO> atualizar(Long id, PedidoRequestDTO pedidoRequestDTO) {
        return pedidoRepository.findById(id).map(pedido -> {
            Cliente cliente = clienteRepository.findById(pedidoRequestDTO.idCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

            pedido.setDescricao(pedidoRequestDTO.descricao());
            pedido.setDataPedido(pedidoRequestDTO.dataPedido());
            pedido.setCliente(cliente);

            return pedidoMapper.toResponseDto(pedidoRepository.save(pedido));
        });
    }

    public boolean deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            return false;
        }

        pedidoRepository.deleteById(id);
        return true;
    }

}