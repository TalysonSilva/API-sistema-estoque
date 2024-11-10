package com.app.sistema_de_controle_de_estoque.DTO.usuario;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UsuarioDTO {
    private String login;
    private String senha;
}
