package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.CategoriaRequestDTO;
import com.ctw.strelow.Activities.dto.response.CategoriaResponseDTO;
import com.ctw.strelow.Activities.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponseDTO> listarTodos() {
        return categoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criar(categoriaRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        return categoriaService.atualizar(id, categoriaRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return categoriaService.deletar(id)
                ?  ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}