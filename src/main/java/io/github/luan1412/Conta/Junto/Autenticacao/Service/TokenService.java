package io.github.luan1412.Conta.Junto.Autenticacao.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import io.github.luan1412.Conta.Junto.Usuario.Model.UsuarioModel;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    private String secret = "minha-chave-secreta-muito-longa-e-segura";//chave provisória enqunato aprendo.

    public String generateToken(UsuarioModel usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                .withIssuer("Conta Junto API")
                .withSubject(usuario.getEmail())
                .withExpiresAt(genExpirationDate())
                .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar Token JWT", exception);
        }
    } 
        private Instant genExpirationDate(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));//fuso horario de sp
        }  
}
