package com.senac.entre_lacos_e_nos_web.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuario")
@JsonPropertyOrder({"id", "nome", "username", "email", "senha", "biografia", "tipo"})
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "nome obrigatório")
  private String nome;

  @NotBlank(message = "username obrigatório")
  @Column(unique = true)
  private String username;

  @NotBlank(message = "email obrigatório")
  @Email(message = "E-mail inválido")
  private String email;

  @NotBlank(message = "senha obrigatória")
  @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
  private String senha;

  @NotBlank(message = "biografia obrigatória")
  private String biografia;

  @NotBlank(message = "tipo de usuário obrigatório")
  private String tipo;
  
  @JsonIgnore
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
  private List<Projeto> projeto;
  
  @JsonIgnore
  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
  private List<Receita> receita;

}
