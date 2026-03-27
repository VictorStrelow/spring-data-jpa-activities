package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.ProjetoRequestDTO;
import com.ctw.strelow.Activities.dto.response.ProjetoResponseDTO;
import com.ctw.strelow.Activities.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @GetMapping
    public List<ProjetoResponseDTO> listarTodos() {
        return projetoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> buscarPorId(@PathVariable Long id) {
        return projetoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> criar(@RequestBody ProjetoRequestDTO projetoRequestDTO) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(projetoService.criar(projetoRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProjetoRequestDTO projetoRequestDTO) {
        return projetoService.atualizar(id, projetoRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return projetoService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}