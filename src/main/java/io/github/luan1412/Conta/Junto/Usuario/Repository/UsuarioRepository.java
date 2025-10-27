package io.github.luan1412.Conta.Junto.Usuario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.luan1412.Conta.Junto.Usuario.Model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    UserDetails findByEmail(String email);
}
