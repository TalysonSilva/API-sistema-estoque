package com.app.sistema_de_controle_de_estoque.service;

import com.app.sistema_de_controle_de_estoque.model.DTO.ProdutoDTO;
import com.app.sistema_de_controle_de_estoque.model.Produto;
import com.app.sistema_de_controle_de_estoque.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    private Produto produto;

    public List<Produto> todosProdutos() {
        return  produtoRepository.findAll();
    }

    public String cadastroNovoProduto(ProdutoDTO novoProduto) {

        if (novoProduto == null || novoProduto.getNome() == null
                || novoProduto.getPreco() <= 0 || novoProduto.getQuantidade() <= 0) {
           return "Dados do produto inválidos";
        }

        if (produtoRepository.existsByNome(novoProduto.getNome())){
            return "Produto já existe";
        }

        produto.setNome(novoProduto.getNome());
        produto.setPreco(novoProduto.getPreco());
        produto.setQuantidade(novoProduto.getQuantidade());

        produtoRepository.save(produto);
        return "Novo Produto cadastrado com Sucesso";

    }

    @Transactional
    public String removerProdutos(Long id) {

        if (!produtoRepository.existsById(id)) {
           throw new EntityNotFoundException("Produto não exite");
        }

        produtoRepository.deleteById(id);
        return "Deletado com sucesso";

    }

    public Optional<Produto> buscaProdutoPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public String atualizarProduto(Long id, ProdutoDTO atualizado) {

        return produtoRepository.findById(id).map(produto -> {

            produto.setNome(atualizado.getNome());
            produto.setPreco(atualizado.getPreco());
            produto.setQuantidade(atualizado.getQuantidade());
            produtoRepository.save(produto);

            return "Produto atualizado com sucesso";

        }).orElseGet(() -> {

            produto.setQuantidade(atualizado.getQuantidade());
            produto.setNome(atualizado.getNome());
            produto.setPreco(atualizado.getPreco());

            produtoRepository.save(produto);
            return "Produto não existia, foi adicionado à base de dados";

        });
    }
}
