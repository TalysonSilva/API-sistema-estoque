package com.app.sistema_de_controle_de_estoque.repository;

import com.app.sistema_de_controle_de_estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);

    boolean existsByNome(String nome);
}
