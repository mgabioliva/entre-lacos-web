package com.senac.entre_lacos_e_nos_web.service;

import com.senac.entre_lacos_e_nos_web.data.Projeto;
import com.senac.entre_lacos_e_nos_web.data.ProjetoRepository;
import com.senac.entre_lacos_e_nos_web.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {

  @Autowired
  ProjetoRepository projetoRepository;

  public Projeto criarProjeto(Projeto proj) {
    proj.setId(null);
    projetoRepository.save(proj);
    return proj;
  }

  public Projeto atualizarProjeto(Integer projId, Projeto projetoRequest) {
    Projeto proj = getProjetoId(projId);
    proj.setNome(projetoRequest.getNome());
    proj.setCategoria(projetoRequest.getCategoria());
    proj.setDuracao(projetoRequest.getDuracao());
    proj.setMaterial(projetoRequest.getMaterial());
    proj.setStatus(projetoRequest.getStatus());
    proj.setDetalhes(projetoRequest.getDetalhes());
    projetoRepository.save(proj);
    return proj;
  }

  public Projeto getProjetoId(Integer projId) {
    return projetoRepository.findById(projId).orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado " + projId));
  }

  public List<Projeto> listarTodosProjetos() {
    return projetoRepository.findAll();
  }

  public void deletarProjeto(Integer projId) {
    Projeto proj = getProjetoId(projId);
    projetoRepository.delete(proj);
  }

  public List<Projeto> getProjetoPorNomeOrCategoria(String nome, String categoria) {
    return projetoRepository.findByNomeContainingIgnoreCaseOrCategoriaContainingIgnoreCase(nome, categoria);
  }

  public List<Projeto> getProjetoPorUsuarioNomeOrUsuarioUsername(String nomeUsuario, String usernameUsuario) {
    return projetoRepository.findByUsuario_NomeContainingIgnoreCaseOrUsuario_UsernameContainingIgnoreCase(nomeUsuario, usernameUsuario);
  }

  public List<Projeto> getProjetoPorReceitaNome(String nomeReceita) {
    return projetoRepository.findByReceita_NomeContainingIgnoreCase(nomeReceita);
  }
}
