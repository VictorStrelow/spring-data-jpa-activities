package com.ctw.strelow.Activities.dto.response;

import java.time.LocalDate;

public record PedidoResponseDTO (

        Long id,
        String descricao,
        LocalDate dataPedido,
        Long idCliente,
        String nomeCliente

) {}