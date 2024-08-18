package com.app.sistema_de_controle_de_estoque.controller;

import com.app.sistema_de_controle_de_estoque.model.Produto;
import com.app.sistema_de_controle_de_estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ControllerProduto {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionar(@RequestBody Produto novoProduto){
        String produto =  produtoService.cadastroNovoProduto(novoProduto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping("/cadastrados")
    public ResponseEntity<List<Produto>> todosProdutos(){
        List<Produto> produtos = produtoService.todosProdutos();

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

}
