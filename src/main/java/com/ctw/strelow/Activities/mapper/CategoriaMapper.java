package com.ctw.strelow.Activities.mapper;

import com.ctw.strelow.Activities.dto.request.CategoriaRequestDTO;
import com.ctw.strelow.Activities.dto.response.CategoriaResponseDTO;
import com.ctw.strelow.Activities.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaRequestDTO.nome());

        return categoria;
    }

    public CategoriaResponseDTO toResponseDto(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }

}