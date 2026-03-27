package com.ctw.strelow.Activities.repository;

import com.ctw.strelow.Activities.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaId(Long idCategoria);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    Optional<Produto> findByIdAndNomeContainingIgnoreCase(Long id, String nome);
    List<Produto> findByCategoriaNomeContainingIgnoreCase(String nomeCategoria);

}