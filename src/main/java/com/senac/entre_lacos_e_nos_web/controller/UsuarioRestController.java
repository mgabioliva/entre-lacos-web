package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Usuario;
import com.senac.entre_lacos_e_nos_web.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

  @Autowired
  UsuarioService usuarioService;

  @GetMapping("/listar")
  public ResponseEntity<List> getAllUsuarios() {
    List<Usuario> usuarios = usuarioService.listarTodosUsuarios();
    return new ResponseEntity<>(usuarios, HttpStatus.OK);
  }

  @GetMapping("/pesquisar/{id}")
  public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
    Usuario usuario = usuarioService.getUsuarioId(id);
    return new ResponseEntity<>(usuario, HttpStatus.OK);
  }

  @PostMapping("/adicionar")
  public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario user) {
    var novoUsuario = usuarioService.criarUsuario(user);
    return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario user) {
    var usuarioAtualizado = usuarioService.atualizarUsuario(id, user);
    return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
  }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity deletarUsuario(@PathVariable Integer id) {
    usuarioService.deletarUsuario(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/pesquisar-nome-username")
  public ResponseEntity<List<Usuario>> getPesquisarPorNomeOrUsername(@RequestParam(required = false) String nome, @RequestParam(required = false) String username) {
    List<Usuario> usuarios = usuarioService.getUsuarioPorNomeOrUsername(nome, username);
    return new ResponseEntity<>(usuarios, HttpStatus.OK);
  }
}
