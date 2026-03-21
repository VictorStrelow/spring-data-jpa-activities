package com.ctw.strelow.Activities.controller;

import com.ctw.strelow.Activities.dto.request.PedidoRequestDTO;
import com.ctw.strelow.Activities.dto.response.PedidoResponseDTO;
import com.ctw.strelow.Activities.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public List<PedidoResponseDTO> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{idCliente}")
    public List<PedidoResponseDTO> buscarPorIdCliente(@PathVariable Long idCliente) {
        return pedidoService.buscarPorIdCliente(idCliente);
    }

    @GetMapping("/cliente/{idCliente}/ordenado")
    public List<PedidoResponseDTO> buscarPorClienteOrdenadoPorId(@PathVariable Long idCliente) {
        return pedidoService.buscarPorClienteOrdenadoPorId(idCliente);
    }

    @GetMapping("/busca/cliente")
    public List<PedidoResponseDTO> buscarPorNomeCliente(@RequestParam String nome) {
        return pedidoService.buscarPorNomeCliente(nome);
    }

    @GetMapping("/filtro")
    public ResponseEntity<PedidoResponseDTO> buscarPorIdEDescricao(@RequestParam Long id, @RequestParam String descricao) {
        return pedidoService.buscarPorIdEDescricao(id, descricao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criar(pedidoRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id, @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        return pedidoService.atualizar(id, pedidoRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return pedidoService.deletar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}