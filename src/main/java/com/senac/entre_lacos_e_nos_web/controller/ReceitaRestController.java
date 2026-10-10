package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Receita;
import com.senac.entre_lacos_e_nos_web.service.ReceitaService;
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
@RequestMapping("/receita")
public class ReceitaRestController {
  
  @Autowired
  ReceitaService receitaService;

  @GetMapping("/listar")
  public ResponseEntity<List> getAllReceitas() {
    List<Receita> receitas = receitaService.listarTodasReceitas();
    return new ResponseEntity<>(receitas, HttpStatus.OK);
  }

  @GetMapping("/pesquisar/{id}")
  public ResponseEntity<Receita> getReceitaById(@PathVariable Integer id) {
    Receita receita = receitaService.getReceitaId(id);
    return new ResponseEntity<>(receita, HttpStatus.OK);
  }

  @PostMapping("/adicionar")
  public ResponseEntity<Receita> addReceita(@Valid @RequestBody Receita rec) {
    var novaReceita = receitaService.criarReceita(rec);
    return new ResponseEntity<>(novaReceita, HttpStatus.CREATED);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Receita> atualizarProjeto(@PathVariable Integer id, @Valid @RequestBody Receita rec) {
    var receitaAtualizada = receitaService.atualizarReceita(id, rec);
    return new ResponseEntity<>(receitaAtualizada, HttpStatus.OK);
  }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity deletarReceita(@PathVariable Integer id) {
    receitaService.deletarReceita(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/pesquisar-nome-categoria")
  public ResponseEntity<List<Receita>> getPesquisarPorNomeOrCategoria(@RequestParam(required = false) String titulo, @RequestParam(required = false) String categoria) {
    List<Receita> receitas = receitaService.getReceitaPorNomeOrCategoria(titulo, categoria);
    return new ResponseEntity<>(receitas, HttpStatus.OK);
  }

  @GetMapping("/pesquisar-nome-username") 
  public ResponseEntity<List<Receita>> getPesquisarPorUsuarioNomeOrUsuarioUsername(@RequestParam(required = false) String nomeUsuario, @RequestParam(required = false) String usernameUsuario) {
    List<Receita> receitas = receitaService.getReceitaPorUsuarioNomeOrUsuarioUsername(nomeUsuario, usernameUsuario);
    return new ResponseEntity<>(receitas, HttpStatus.OK);
  }
}
