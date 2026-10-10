package com.senac.entre_lacos_e_nos_web.service;

import com.senac.entre_lacos_e_nos_web.data.Usuario;
import com.senac.entre_lacos_e_nos_web.data.UsuarioRepository;
import com.senac.entre_lacos_e_nos_web.exception.ResourceNotFoundException;
import com.senac.entre_lacos_e_nos_web.util.Criptografia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  @Autowired
  UsuarioRepository usuarioRepository;

  public Usuario criarUsuario(Usuario user) {
    user.setId(null);
    usuarioRepository.save(user);
    return user;
  }

  public Usuario atualizarUsuario(Integer userId, Usuario usuarioRequest) {
    Usuario user = getUsuarioId(userId);
    user.setNome(usuarioRequest.getNome());
    user.setUsername(usuarioRequest.getUsername());
    user.setEmail(usuarioRequest.getEmail());
    String senhaCriptografada = Criptografia.getMD5(user.getSenha());
    user.setSenha(senhaCriptografada);
    user.setBiografia(usuarioRequest.getBiografia());
    user.setTipo(usuarioRequest.getTipo());
    usuarioRepository.save(user);
    return user;
  }

  public Usuario getUsuarioId(Integer userId) {
    return usuarioRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado " + userId));
  }

  public List<Usuario> listarTodosUsuarios() {
    return usuarioRepository.findAll();
  }

  public void deletarUsuario(Integer userId) {
    Usuario user = getUsuarioId(userId);
    usuarioRepository.delete(user);
  }

  public List<Usuario> getUsuarioPorNomeOrUsername(String nome, String username) {
    return usuarioRepository.findByNomeContainingIgnoreCaseOrUsernameContainingIgnoreCase(nome, username);
  }
}
