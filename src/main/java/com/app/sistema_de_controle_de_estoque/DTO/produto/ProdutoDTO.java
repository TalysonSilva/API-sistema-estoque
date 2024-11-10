package com.app.sistema_de_controle_de_estoque.DTO.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private String nome;
    private int quantidade;
    private Double preco;
    private String categoria;
    private String descricao;
}
