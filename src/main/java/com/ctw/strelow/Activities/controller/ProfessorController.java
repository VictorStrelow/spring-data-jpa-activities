package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.ProfessorRequestDTO;
import com.ctw.strelow.Activities.dto.response.ProfessorResponseDTO;
import com.ctw.strelow.Activities.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public List<ProfessorResponseDTO> listarTodos() {
        return professorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable Long id) {
        return professorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> criar(@RequestBody ProfessorRequestDTO professorRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.criar(professorRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProfessorRequestDTO professorRequestDTO) {
        return professorService.atualizar(id, professorRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return professorService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}