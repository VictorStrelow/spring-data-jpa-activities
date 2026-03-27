package com.ctw.strelow.Activities.service;

import com.ctw.strelow.Activities.dto.request.ProdutoRequestDTO;
import com.ctw.strelow.Activities.dto.response.ProdutoResponseDTO;
import com.ctw.strelow.Activities.mapper.ProdutoMapper;
import com.ctw.strelow.Activities.model.Categoria;
import com.ctw.strelow.Activities.model.Produto;
import com.ctw.strelow.Activities.repository.CategoriaRepository;
import com.ctw.strelow.Activities.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoMapper produtoMapper;

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toResponseDto)
                .toList();
    }

    public Optional<ProdutoResponseDTO> buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .map(produtoMapper::toResponseDto);
    }

    public List<ProdutoResponseDTO> buscarPorCategoria(Long idCategoria) {
        return produtoRepository.findByCategoriaId(idCategoria)
                .stream()
                .map(produtoMapper::toResponseDto)
                .toList();
    }

    public List<ProdutoResponseDTO> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(produtoMapper::toResponseDto)
                .toList();
    }

    public Optional<ProdutoResponseDTO> buscarPorIdENome(Long id, String nome) {
        return produtoRepository.findByIdAndNomeContainingIgnoreCase(id, nome)
                .map(produtoMapper::toResponseDto);
    }

    public List<ProdutoResponseDTO> buscarPorNomeCategoria(String nomeCategoria) {
        return produtoRepository.findByCategoriaNomeContainingIgnoreCase(nomeCategoria)
                .stream()
                .map(produtoMapper::toResponseDto)
                .toList();
    }

    public ProdutoResponseDTO criar(ProdutoRequestDTO  produtoRequestDTO) {
        Categoria  categoria = categoriaRepository.findById(produtoRequestDTO.idCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        Produto produto = produtoMapper.toEntity(produtoRequestDTO);
        produto.setCategoria(categoria);

        return  produtoMapper.toResponseDto(produtoRepository.save(produto));
    }

    public Optional<ProdutoResponseDTO> atualizar(Long id, ProdutoRequestDTO produtoRequestDTO) {
        return produtoRepository.findById(id).map(produto -> {
            Categoria categoria = categoriaRepository.findById(produtoRequestDTO.idCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

            produto.setNome(produtoRequestDTO.nome());
            produto.setPreco(produtoRequestDTO.preco());
            produto.setCategoria(categoria);

            return  produtoMapper.toResponseDto(produtoRepository.save(produto));
        });
    }

    public boolean deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            return false;
        }

        produtoRepository.deleteById(id);
        return true;
    }

}