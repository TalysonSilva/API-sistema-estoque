package com.app.sistema_de_controle_de_estoque.model;

public enum FuncaoUsuario {
    ADMIN("admin"),
    FUNCIONARIO("funcionario");

    private String funcao;

    FuncaoUsuario(String funcao) {
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }
}
