package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.TarefaRequestDTO;
import com.ctw.strelow.Activities.dto.response.TarefaResponseDTO;
import com.ctw.strelow.Activities.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;
    private final TaskExecutionProperties taskExecutionProperties;

    @GetMapping
    public List<TarefaResponseDTO> listarTodos() {
        return  tarefaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/projeto/{idProjeto}")
    public List<TarefaResponseDTO> buscarPorIdProjeto(@PathVariable Long idProjeto) {
        return tarefaService.buscarPorProjetoId(idProjeto);
    }

    @GetMapping("/filtro")
    public ResponseEntity<TarefaResponseDTO> buscarPorIdETitulo(@RequestParam Long id, @RequestParam String titulo) {
        return tarefaService.buscarPorIdETitulo(id, titulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criar(@RequestBody TarefaRequestDTO tarefaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.criar(tarefaRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizar(@PathVariable Long id, @RequestBody TarefaRequestDTO tarefaRequestDTO) {
        return tarefaService.atualizar(id, tarefaRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return tarefaService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}