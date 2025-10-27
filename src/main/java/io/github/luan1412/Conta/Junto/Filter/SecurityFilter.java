package io.github.luan1412.Conta.Junto.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // <-- Importar
import org.springframework.security.core.context.SecurityContextHolder; // <-- Importar
import org.springframework.security.core.userdetails.UserDetails; // <-- Importar
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.luan1412.Conta.Junto.Autenticacao.Service.TokenService;
import io.github.luan1412.Conta.Junto.Usuario.Repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.var;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository){
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            
            var subject = tokenService.getSubject(tokenJWT); 
            // 4. Busca o usuário no banco pelo email
            UserDetails usuario = usuarioRepository.findByEmail(subject);

       
            if (usuario != null) {
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        
        filterChain.doFilter(request, response);
    }

    
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            
            return authorizationHeader.replace("Bearer ", "");
        }
        return null; 
    }

}
