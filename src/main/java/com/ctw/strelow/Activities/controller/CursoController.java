package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.CursoRequestDTO;
import com.ctw.strelow.Activities.dto.response.CursoResponseDTO;
import com.ctw.strelow.Activities.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public List<CursoResponseDTO> listarTodos() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> buscarPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/professor/{idProfessor}")
    public List<CursoResponseDTO> buscarPorProfessor(@PathVariable Long idProfessor) {
        return cursoService.buscarPorProfessor(idProfessor);
    }

    @GetMapping("/busca/professor")
    public List<CursoResponseDTO> buscarPorNomeProfessor(@RequestParam String nome) {
        return cursoService.buscarPorNomeProfessor(nome);
    }

    @GetMapping("/filtro")
    public ResponseEntity<CursoResponseDTO> buscarPorIdETitulo(
            @RequestParam Long id,
            @RequestParam String titulo) {
        return cursoService.buscarPorIdETitulo(id, titulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> criar(@RequestBody CursoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> atualizar(@PathVariable Long id, @RequestBody CursoRequestDTO dto) {
        return cursoService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return cursoService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}