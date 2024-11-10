package com.app.sistema_de_controle_de_estoque.DTO.usuario;

import com.app.sistema_de_controle_de_estoque.model.FuncaoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {
    private String login;
    private String senha;
    private FuncaoUsuario funcaoUsuario;
}
