package com.app.sistema_de_controle_de_estoque.controller;

import com.app.sistema_de_controle_de_estoque.DTO.usuario.RegistroDTO;
import com.app.sistema_de_controle_de_estoque.DTO.usuario.UsuarioDTO;
import com.app.sistema_de_controle_de_estoque.model.Usuario;
import com.app.sistema_de_controle_de_estoque.repository.UsuarioRepository;
import com.app.sistema_de_controle_de_estoque.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class ControllerAutenticacao {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity loginUsuario (@RequestBody @Valid UsuarioDTO usuarioDTO){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(usuarioDTO.getLogin(), usuarioDTO.getSenha());
        var aut = authenticationManager.authenticate(usuarioSenha);

        var token = tokenService.geradorToken((Usuario) aut.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/registrar")
    public ResponseEntity registrarUsuario (@RequestBody @Valid RegistroDTO dados){
        if(usuarioRepository.findByLogin(dados.getLogin()) != null){
            return ResponseEntity.badRequest().build();
        }
        String senhaCriptografardo = new BCryptPasswordEncoder().encode(dados.getSenha());
        Usuario novoUsuario = new Usuario(dados.getLogin(), senhaCriptografardo, dados.getFuncaoUsuario());
        this.usuarioRepository. save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
