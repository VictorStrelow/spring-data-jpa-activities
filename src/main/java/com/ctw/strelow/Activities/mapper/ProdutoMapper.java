package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.ProdutoRequestDTO;
import com.ctw.strelow.Activities.dto.response.ProdutoResponseDTO;
import com.ctw.strelow.Activities.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();

        produto.setNome(produtoRequestDTO.nome());
        produto.setPreco(produtoRequestDTO.preco());

        return  produto;
    }

    public ProdutoResponseDTO toResponseDto(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria().getNome()
        );
    }

}