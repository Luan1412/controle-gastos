package io.github.luan1412.Conta.Junto.Autenticacao.Controller;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.luan1412.Conta.Junto.Autenticacao.Service.TokenService;
import io.github.luan1412.Conta.Junto.Usuario.Model.UsuarioModel;
import io.github.luan1412.Conta.Junto.dto.DadosAutenticacao;
import io.github.luan1412.Conta.Junto.dto.DadosTokenJWT;
import lombok.var;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    AutenticacaoController(TokenService tokenService, AuthenticationManager authenticationManager){
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;   
    }

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody DadosAutenticacao dados){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        var usuario = (UsuarioModel) authentication.getPrincipal();

        String tokenJWT = tokenService.generateToken(usuario);


        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        

    }

}
