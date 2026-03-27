package com.ctw.strelow.Activities.dto.request;

public record ProdutoRequestDTO (

        String nome,
        Double preco,
        Long idCategoria

) {}