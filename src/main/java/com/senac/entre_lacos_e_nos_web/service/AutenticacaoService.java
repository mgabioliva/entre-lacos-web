package com.senac.entre_lacos_e_nos_web.service;

import com.senac.entre_lacos_e_nos_web.data.Usuario;
import com.senac.entre_lacos_e_nos_web.data.UsuarioRepository;
import com.senac.entre_lacos_e_nos_web.exception.AutenticacaoException;
import com.senac.entre_lacos_e_nos_web.util.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

   
    public Usuario autenticar(String username, String senha) {
      
        if (username == null || username.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            throw new AutenticacaoException("username e senha são obrigatórios!");
        }

        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new AutenticacaoException("usuário ou senha incorretos!"));

        String senhaCriptografada = Criptografia.getMD5(senha);

        if (!usuario.getSenha().equals(senhaCriptografada)) {
            throw new AutenticacaoException("usuário ou senha incorretos!");
        }

        return usuario;
    }
}