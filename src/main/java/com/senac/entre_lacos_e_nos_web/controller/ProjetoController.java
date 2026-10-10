package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Projeto;
import com.senac.entre_lacos_e_nos_web.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjetoController {

  @Autowired
  ProjetoService projetoService;

  @GetMapping("/")
  public String viewHomePage(@CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
    model.addAttribute("listarProjetos", projetoService.listarTodosProjetos());
    model.addAttribute("tema", tema);
    return "projeto-lista"; // html da tela inicial
  }

  @GetMapping("/deletarProjeto/{id}")
  public String deletarProjeto(@PathVariable(value = "id") Integer id) {
    projetoService.deletarProjeto(id);
    return "redirect:/";
  }

  @GetMapping("/criarProjetoForm")
  public String criarProjetoForm(@CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
    Projeto proj = new Projeto();
    model.addAttribute("projeto", proj);
    model.addAttribute("tema", tema);
    return "projeto-cadastro"; // html da tela de cadastro
  }

  @PostMapping("/salvarProjeto")
  public String salvarProjeto(@Valid @ModelAttribute("projeto") Projeto proj, BindingResult result) {
    if (result.hasErrors()) {
      return "projeto-cadastro"; // html da tela de cadastro
    }
    if (proj.getId() == null) {
      projetoService.criarProjeto(proj);
    } else {
      projetoService.atualizarProjeto(proj.getId(), proj);
    }
    return "redirect:/";
  }

  @GetMapping("/atualizarProjetoForm/{id}")
  public String atualizarProjetoForm(@PathVariable Integer id, @CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
    Projeto proj = projetoService.getProjetoId(id);
    model.addAttribute("projeto", proj);
    model.addAttribute("tema", tema);
    return "projeto-atualizar";
  }

  @GetMapping("/projeto-receita/{id}")
  public String mostraRecita(@PathVariable Integer id, @CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
    Projeto proj = projetoService.getProjetoId(id);
    model.addAttribute("projeto", proj);
    model.addAttribute("tema", tema);
    return "projeto-receita"; // html da tela da receita desse projeto especifico
  }
}
