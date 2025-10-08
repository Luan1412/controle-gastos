package io.github.luan1412.Conta.Junto.Usuario.Service;

import io.github.luan1412.Conta.Junto.Usuario.Model.UsuarioModel;
import io.github.luan1412.Conta.Junto.Usuario.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository UsuarioRepository;

    public UsuarioModel createUsuario(UsuarioModel usuario){
        return this.UsuarioRepository.save(usuario);
    }

    public List<UsuarioModel> readUsuarios(){
        return this.UsuarioRepository.findAll();
    }

    public Optional<UsuarioModel> readUsuariosById(Long id){
        return this.UsuarioRepository.findById(id);
    }

    public Optional<UsuarioModel> updateUsuario(Long id, UsuarioModel novosDadoUsuario){
        
        return this.UsuarioRepository.findById(id).map(atualizaUsuario ->{

            atualizaUsuario.setNome(novosDadoUsuario.getNome());
            atualizaUsuario.setEmail(novosDadoUsuario.getEmail());
            atualizaUsuario.setSenha(novosDadoUsuario.getSenha());

            return this.UsuarioRepository.save(atualizaUsuario);
        });
    }

    public boolean deleteUsuario(Long id){
        Optional<UsuarioModel> usuarioOptional = this.UsuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            this.UsuarioRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
