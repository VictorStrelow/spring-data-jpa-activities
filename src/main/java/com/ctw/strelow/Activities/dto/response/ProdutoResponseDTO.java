package com.ctw.strelow.Activities.dto.response;

public record ProdutoResponseDTO (

        Long id,
        String nome,
        Double preco,
        String nomeCategoria

) {}