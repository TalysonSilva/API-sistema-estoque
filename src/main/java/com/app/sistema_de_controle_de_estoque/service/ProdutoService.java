package com.app.sistema_de_controle_de_estoque.service;

import com.app.sistema_de_controle_de_estoque.model.Produto;
import com.app.sistema_de_controle_de_estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> todosProdutos() {
        //
        return null;
    }

    public String cadastroNovoProduto(Produto novoProduto) {
        produtoRepository.save(novoProduto);

        return "Novo Produto cadastrado com Sucesso";
    }
}
