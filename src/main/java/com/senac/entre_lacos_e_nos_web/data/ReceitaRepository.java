package com.senac.entre_lacos_e_nos_web.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
  
  List<Receita> findByNomeContainingIgnoreCaseOrCategoriaContainingIgnoreCase(String nome, String categoria);

  List<Receita> findByNomeStartingWithIgnoreCase(String nome);

  List<Receita> findByNomeEndingWithIgnoreCase(String nome);
  
  List<Receita> findByUsuario_NomeContainingIgnoreCaseOrUsuario_UsernameContainingIgnoreCase(String nomeUsuario, String usernameUsuario);

}
