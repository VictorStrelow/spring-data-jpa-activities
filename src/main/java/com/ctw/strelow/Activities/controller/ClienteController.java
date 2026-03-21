package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.ClienteRequestDTO;
import com.ctw.strelow.Activities.dto.response.ClienteResponseDTO;
import com.ctw.strelow.Activities.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDTO> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public List<ClienteResponseDTO> buscarPorNome(@RequestParam String nome) {
        return  clienteService.buscarPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.criar(clienteRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        return clienteService.atualizar(id, clienteRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return clienteService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}