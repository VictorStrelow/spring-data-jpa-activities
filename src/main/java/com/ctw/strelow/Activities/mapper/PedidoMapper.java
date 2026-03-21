package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.PedidoRequestDTO;
import com.ctw.strelow.Activities.dto.response.PedidoResponseDTO;
import com.ctw.strelow.Activities.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public Pedido toEntity(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido();

        pedido.setDescricao(pedidoRequestDTO.descricao());
        pedido.setDataPedido(pedidoRequestDTO.dataPedido());

        return pedido;
    }

    public PedidoResponseDTO toResponseDto(Pedido pedido) {
        return  new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDescricao(),
                pedido.getDataPedido(),
                pedido.getCliente().getId(),
                pedido.getCliente().getNome()
        );
    }

}