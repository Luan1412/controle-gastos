package io.github.luan1412.Conta.Junto.Usuario.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import io.github.luan1412.Conta.Junto.Usuario.Model.UsuarioModel;
import io.github.luan1412.Conta.Junto.Usuario.Service.UsuarioService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public UsuarioModel creatUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.createUsuario(usuario);
    }

    @GetMapping
    public List<UsuarioModel> readUsuarios(){
        return this.usuarioService.readUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> readUsuarioById(@PathVariable Long id){
        Optional<UsuarioModel> usuarOptional = this.usuarioService.readUsuariosById(id);

        if (usuarOptional.isPresent()) {
            return ResponseEntity.ok(usuarOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> updateUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
        
        Optional<UsuarioModel> usuarioAtualizadoOptional = this.usuarioService.updateUsuario(id, usuario);

        if (usuarioAtualizadoOptional.isPresent()) {
            return ResponseEntity.ok(usuarioAtualizadoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteteUsuario(@PathVariable Long id){

        Boolean usuarioApagado = this.usuarioService.deleteUsuario(id);

        if (usuarioApagado) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
