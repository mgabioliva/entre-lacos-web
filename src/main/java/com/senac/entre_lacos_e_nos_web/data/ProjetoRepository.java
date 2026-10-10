package com.senac.entre_lacos_e_nos_web.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
  
  List<Projeto> findByNomeContainingIgnoreCaseOrCategoriaContainingIgnoreCase(String nome, String categoria);

  List<Projeto> findByNomeStartingWithIgnoreCase(String nome);

  List<Projeto> findByNomeEndingWithIgnoreCase(String nome);
  
  List<Projeto> findByUsuario_NomeContainingIgnoreCaseOrUsuario_UsernameContainingIgnoreCase(String nomeUsuario, String usernameUsuario);
  
  List<Projeto> findByReceita_NomeContainingIgnoreCase(String nomeReceita);

}
