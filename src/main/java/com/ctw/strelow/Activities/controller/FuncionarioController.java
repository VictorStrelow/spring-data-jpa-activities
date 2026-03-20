package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.FuncionarioRequestDTO;
import com.ctw.strelow.Activities.dto.response.FuncionarioResponseDTO;
import com.ctw.strelow.Activities.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/departamento/{idDepartamento}")
    public List<FuncionarioResponseDTO> buscarPorDepartamento(@PathVariable Long idDepartamento) {
        return funcionarioService.buscarPorDepartamento(idDepartamento);
    }

    @GetMapping("/busca")
    public List<FuncionarioResponseDTO> buscaPorNome(@RequestParam String nome) {
        return funcionarioService.buscarPorNome(nome);
    }

    @GetMapping("/filtro")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorIdENome(@RequestParam Long id, @RequestParam String nome) {
        return funcionarioService.buscarPorIdENome(id, nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criar(@RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.criar(funcionarioRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        return funcionarioService.atualizar(id, funcionarioRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deletar(@PathVariable Long id) {
        return funcionarioService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}