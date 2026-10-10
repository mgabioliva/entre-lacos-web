package com.senac.entre_lacos_e_nos_web.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "Receita")
@JsonPropertyOrder({"id_receita", "nome", "categoria", "dificuldade", "material", "conteudo", "usuario"})
public class Receita {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id_receita;

  @NotBlank(message = "nome obrigatório")
  private String nome;

  @NotBlank(message = "categoria obrigatória")
  private String categoria;

  @NotBlank(message = "dificuldade obrigatória")
  private String dificuldade;

  @NotBlank(message = "material obrigatório")
  private String material;

  @NotBlank(message = "conteúdo obrigatório")
  private String conteudo;

  @ManyToOne
  @JsonIgnoreProperties("receita")
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;

  @OneToMany(mappedBy = "recita", cascade = CascadeType.ALL)
  private List<Projeto> projeto;

}
