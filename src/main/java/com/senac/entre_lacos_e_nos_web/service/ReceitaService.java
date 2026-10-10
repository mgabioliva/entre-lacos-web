package com.senac.entre_lacos_e_nos_web.service;

import com.senac.entre_lacos_e_nos_web.data.Receita;
import com.senac.entre_lacos_e_nos_web.data.ReceitaRepository;
import com.senac.entre_lacos_e_nos_web.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

  @Autowired
  ReceitaRepository receitaRepository;

  public Receita criarReceita(Receita rec) {
    rec.setId(null);
    receitaRepository.save(rec);
    return rec;
  }

  public Receita atualizarReceita(Integer recId, Receita receitaRequest) {
    Receita rec = getReceitaId(recId);
    rec.setNome(receitaRequest.getNome());
    rec.setCategoria(receitaRequest.getCategoria());
    rec.setDificuldade(receitaRequest.getDificuldade());
    rec.setMaterial(receitaRequest.getMaterial());
    rec.setConteudo(receitaRequest.getConteudo());
    receitaRepository.save(rec);
    return rec;
  }

  public Receita getReceitaId(Integer recId) {
    return receitaRepository.findById(recId).orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada " + recId));
  }

  public List<Receita> listarTodasReceitas() {
    return receitaRepository.findAll();
  }

  public void deletarReceita(Integer recId) {
    Receita rec = getReceitaId(recId);
    receitaRepository.delete(rec);
  }

  public List<Receita> getReceitaPorNomeOrCategoria(String nome, String categoria) {
    return receitaRepository.findByNomeContainingIgnoreCaseOrCategoriaContainingIgnoreCase(nome, categoria);
  }

  public List<Receita> getReceitaPorUsuarioNomeOrUsuarioUsername(String nomeUsuario, String usernameUsuario) {
    return receitaRepository.findByUsuario_NomeContainingIgnoreCaseOrUsuario_UsernameContainingIgnoreCase(nomeUsuario, usernameUsuario);
  }

}
