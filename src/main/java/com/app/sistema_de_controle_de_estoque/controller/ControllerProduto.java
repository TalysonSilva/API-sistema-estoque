package com.app.sistema_de_controle_de_estoque.controller;

import com.app.sistema_de_controle_de_estoque.model.Produto;
import com.app.sistema_de_controle_de_estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ControllerProduto {

    @Autowired
    private ProdutoService produtoService;


    //Adicionar novo produto
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestBody Produto novoProduto){
        String produto =  produtoService.cadastroNovoProduto(novoProduto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    //Todos produtos cadastrado
    @GetMapping("/cadastrados")
    public ResponseEntity<List<Produto>> todosProdutos(){
        List<Produto> produtos = produtoService.todosProdutos();

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    //Remover Produto com base no id
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> removerProduto(@PathVariable Long id){

        String msg = produtoService.removerProdutos(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    //Atualizar Produto
    @PutMapping("/atualizar/{nome}")
    public ResponseEntity<String> atualizarProduto(@PathVariable String nome, @RequestBody Produto atualizado) {

        Optional<Produto> produtoOpt = produtoService.buscaProdutoPorNome(nome);

        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            String msg = produtoService.atualizarProduto(produto.getId(), atualizado);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
