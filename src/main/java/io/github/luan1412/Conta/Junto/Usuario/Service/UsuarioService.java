package io.github.luan1412.Conta.Junto.Usuario.Service;

import io.github.luan1412.Conta.Junto.Usuario.Model.UsuarioModel;
import io.github.luan1412.Conta.Junto.Usuario.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
  

   
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioModel createUsuario(UsuarioModel usuario){
        String senhaOriginal = usuario.getSenha();

        String senhaCriptografada = this.passwordEncoder.encode(senhaOriginal);

        usuario.setSenha(senhaCriptografada);

        return this.usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> readUsuarios(){
        return this.usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> readUsuariosById(Long id){
        return this.usuarioRepository.findById(id);
    }

    public Optional<UsuarioModel> updateUsuario(Long id, UsuarioModel novosDadoUsuario){
        
        return this.usuarioRepository.findById(id).map(atualizaUsuario ->{

            atualizaUsuario.setNome(novosDadoUsuario.getNome());
            atualizaUsuario.setEmail(novosDadoUsuario.getEmail());
            atualizaUsuario.setSenha(novosDadoUsuario.getSenha());

            return this.usuarioRepository.save(atualizaUsuario);
        });
    }

    public boolean deleteUsuario(Long id){
        Optional<UsuarioModel> usuarioOptional = this.usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            this.usuarioRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
