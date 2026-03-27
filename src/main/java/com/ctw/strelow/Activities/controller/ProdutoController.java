package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.ProdutoRequestDTO;
import com.ctw.strelow.Activities.dto.response.ProdutoResponseDTO;
import com.ctw.strelow.Activities.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public List<ProdutoResponseDTO> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<ProdutoResponseDTO> buscarPorCategoria(@PathVariable Long idCategoria) {
        return produtoService.buscarPorCategoria(idCategoria);
    }

    @GetMapping("/busca")
    public List<ProdutoResponseDTO> buscarPorNome(@RequestParam String nome) {
        return produtoService.buscarPorNome(nome);
    }

    @GetMapping("/filtro")
    public ResponseEntity<ProdutoResponseDTO> buscarPorIdENome(
            @RequestParam Long id,
            @RequestParam String nome) {
        return produtoService.buscarPorIdENome(id, nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca/categoria")
    public List<ProdutoResponseDTO> buscarPorNomeCategoria(@RequestParam String nome) {
        return produtoService.buscarPorNomeCategoria(nome);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criar(produtoRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        return produtoService.atualizar(id, produtoRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return produtoService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}