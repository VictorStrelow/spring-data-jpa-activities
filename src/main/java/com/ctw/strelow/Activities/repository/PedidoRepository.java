package com.ctw.strelow.Activities.repository;

import com.ctw.strelow.Activities.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    List<Pedido> findByClienteId(Long idCliente);
    List<Pedido> findByClienteNomeContainingIgnoreCase(String nome);
    Optional<Pedido> findByIdAndDescricaoContainingIgnoreCase(Long id, String descricao);
    List<Pedido> findByClienteIdOrderById(Long idCliente);

}