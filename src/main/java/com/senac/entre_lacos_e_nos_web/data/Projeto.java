package com.senac.entre_lacos_e_nos_web.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalTime;
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
  // validação do formato
  private LocalTime duracao;
  
  @NotBlank(message = "material obrigatório")
  private String material;
  
  @NotBlank(message = "status obrigatório")
  private String status;
  
  @NotBlank(message = "detalhes obrigatório")
  private String detalhes;
  
  @ManyToOne
  //@JoinColumn(name="id_usuario") ver se é compatível com essa esturtura de código
  private Usuario usuario;
  
  @ManyToOne
  //@JoinColumn(name="id_receita") ver se é compatível com essa esturtura de código
  private Receita receita;
  
}
