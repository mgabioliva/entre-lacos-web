package com.senac.entre_lacos_e_nos_web.data;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
  
  boolean existsByUsername(String username);
  
  Optional<Usuario> findByUsername(String username);

  List<Usuario> findByNomeContainingIgnoreCaseOrUsernameContainingIgnoreCase(String nome, String username);

  List<Usuario> findByNomeStartingWithIgnoreCaseOrUsernameStartingWithIgnoreCase(String nome, String username);

  List<Usuario> findByNomeEndingWithIgnoreCaseOrUsernameEndingWithIgnoreCaseOr(String nome, String username);
  
}
