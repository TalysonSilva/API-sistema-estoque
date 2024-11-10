package com.app.sistema_de_controle_de_estoque.service;

import com.app.sistema_de_controle_de_estoque.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String geradorToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("sistema-de-controle-de-estoque")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataDeExpiracao())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            throw  new RuntimeException("Erro ao Criar Token", e);
        }
    }

    public String validarToken(String token) {

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("sistema-de-estoque")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return " ";
        }
    }


    private Date dataDeExpiracao() {
        return Date.from(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")));
    }
}
