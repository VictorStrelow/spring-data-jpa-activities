package com.ctw.strelow.Activities.dto.request;

import java.time.LocalDate;

public record PedidoRequestDTO (

        String descricao,
        LocalDate dataPedido,
        Long idCliente

) {}