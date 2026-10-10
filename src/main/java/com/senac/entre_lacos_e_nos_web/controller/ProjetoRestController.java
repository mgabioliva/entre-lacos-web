package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Projeto;
import com.senac.entre_lacos_e_nos_web.service.ProjetoService;
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
@RequestMapping("/projeto")
public class ProjetoRestController {

  @Autowired
  ProjetoService projetoService;

  @GetMapping("/listar")
  public ResponseEntity<List> getAllProjetos() {
    List<Projeto> projetos = projetoService.listarTodosProjetos();
    return new ResponseEntity<>(projetos, HttpStatus.OK);
  }

  @GetMapping("/pesquisar/{id}")
  public ResponseEntity<Projeto> getProjetoById(@PathVariable Integer id) {
    Projeto projeto = projetoService.getProjetoId(id);
    return new ResponseEntity<>(projeto, HttpStatus.OK);
  }

  @PostMapping("/adicionar")
  public ResponseEntity<Projeto> addProjeto(@Valid @RequestBody Projeto proj) {
    var novoProjeto = projetoService.criarProjeto(proj);
    return new ResponseEntity<>(novoProjeto, HttpStatus.CREATED);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Integer id, @Valid @RequestBody Projeto proj) {
    var projetoAtualizado = projetoService.atualizarProjeto(id, proj);
    return new ResponseEntity<>(projetoAtualizado, HttpStatus.OK);
  }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity deletarProjeto(@PathVariable Integer id) {
    projetoService.deletarProjeto(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/pesquisar-nome-categoria")
  public ResponseEntity<List<Projeto>> getPesquisarPorNomeOrCategoria(@RequestParam(required = false) String titulo, @RequestParam(required = false) String categoria) {
    List<Projeto> projetos = projetoService.getProjetoPorNomeOrCategoria(titulo, categoria);
    return new ResponseEntity<>(projetos, HttpStatus.OK);
  }

  @GetMapping("/pesquisar-nome-username")
  public ResponseEntity<List<Projeto>> getPesquisarPorUsuarioNomeOrUsuarioUsername(@RequestParam(required = false) String nomeUsuario, @RequestParam(required = false) String usernameUsuario) {
    List<Projeto> projetos = projetoService.getProjetoPorUsuarioNomeOrUsuarioUsername(nomeUsuario, usernameUsuario);
    return new ResponseEntity<>(projetos, HttpStatus.OK);
  }

  @GetMapping("/pesquisar-nome")
  public ResponseEntity<List<Projeto>> getPesquisarPorReceitaNome(@RequestParam String nomeReceita) {
    List<Projeto> projetos = projetoService.getProjetoPorReceitaNome(nomeReceita);
    return new ResponseEntity<>(projetos, HttpStatus.OK);
  }
}
