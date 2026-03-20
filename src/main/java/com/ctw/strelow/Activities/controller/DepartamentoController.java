package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.DepartamentoRequestDTO;
import com.ctw.strelow.Activities.dto.response.DepartamentoResponseDTO;
import com.ctw.strelow.Activities.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @GetMapping
    public List<DepartamentoResponseDTO> listarTodos() {
        return departamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return departamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<DepartamentoResponseDTO> criar(@RequestBody DepartamentoRequestDTO departamentoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.criar(departamentoRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> atualizar(@PathVariable Long id, @RequestBody DepartamentoRequestDTO departamentoRequestDTO) {
        return departamentoService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deletar(@PathVariable Long id) {
        return departamentoService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}