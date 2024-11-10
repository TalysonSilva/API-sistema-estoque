package com.app.sistema_de_controle_de_estoque.service;

import com.app.sistema_de_controle_de_estoque.DTO.produto.ProdutoDTO;
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

    public List<Produto> todosProdutos() {
        return  produtoRepository.findAll();
    }

    public String cadastroNovoProduto(ProdutoDTO novoProduto) {

        if (!isProdutoValido(novoProduto)) {
           return "Dados do produto inválidos";
        }

        if (produtoRepository.existsByNome(novoProduto.getNome())){
            return "Produto já existe";
        }

        Produto produto = mapProduto(novoProduto);
        produtoRepository.save(produto);

        return "Novo Produto cadastrado com Sucesso";

    }

    @Transactional
    public String removerProdutos(Long id) {

        if (!produtoRepository.existsById(id)) {
           throw new EntityNotFoundException("Produto não existe");
        }

        produtoRepository.deleteById(id);
        return "Deletado com sucesso";

    }

    public Optional<Produto> buscaProdutoPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public String atualizarProduto(Long id, ProdutoDTO atualizado) {

        //Verificar se o produto existe

        Optional<Produto> produtoExisteOpt = produtoRepository.findById(id);
        if (produtoExisteOpt.isPresent()) {

            Produto produtoExite = produtoExisteOpt.get();

            //converte o ProdutoDTO para produto
            if (atualizado.getQuantidade() > 0){
                produtoExite.setQuantidade(atualizado.getQuantidade());
            }

            if (atualizado.getPreco() != null) {
                produtoExite.setPreco(atualizado.getPreco());
            }
            if (atualizado.getNome() != null){
                produtoExite.setNome(atualizado.getNome());
            }
            if (atualizado.getDescricao() != null){
                produtoExite.setDescricao(atualizado.getDescricao());
            }

            if (atualizado.getCategoria() != null){
                produtoExite.setCategoria(atualizado.getCategoria());
            }

            produtoRepository.save(produtoExite);
            return "Produto atualizado com sucesso";

        }

        return "Produto não encontrado";
    }


    //Converte ProdutoDTO para  Produto
    private Produto mapProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(produtoDTO.getCategoria());
        return produto;
    }

    //Verificar se o produto é valido
    private boolean isProdutoValido(ProdutoDTO produtoDTO){
        return produtoDTO != null
                && produtoDTO.getNome() != null && !produtoDTO.getNome().isBlank()
                && produtoDTO.getPreco() > 0
                && produtoDTO.getQuantidade() > 0
                && produtoDTO.getCategoria() != null && !produtoDTO.getCategoria().isBlank()
                && produtoDTO.getDescricao() != null && !produtoDTO.getDescricao().isBlank();
    }
}
