package com.senac.entre_lacos_e_nos_web.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "Projeto")
@JsonPropertyOrder({"id_projeto", "nome", "categoria", "duracao", "material", "status", "detalhes", "usuario", "receita"})
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id_projeto;

  @NotBlank(message = "nome obrigatório")
  private String nome;

  @NotBlank(message = "categoria obrigatória")
  private String categoria;

  @NotBlank(message = "duração obrigatória")
  @Pattern(regexp = "^[0-9]+:[0-5][0-9]$", message = "A duração deve estar no formato HH:mm (ex: 02:30, 28:15 ou 100:45)")
  private String duracao;

  @NotBlank(message = "material obrigatório")
  private String material;

  @NotBlank(message = "status obrigatório")
  private String status;

  @NotBlank(message = "detalhes obrigatório")
  private String detalhes;

  @ManyToOne
  @JsonIgnoreProperties("projeto")
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;

  @ManyToOne
  @JsonIgnoreProperties("projeto")
  @JoinColumn(name = "id_receita")
  private Receita receita;

}
