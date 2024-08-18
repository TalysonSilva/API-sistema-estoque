package com.app.sistema_de_controle_de_estoque.repository;

import com.app.sistema_de_controle_de_estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
