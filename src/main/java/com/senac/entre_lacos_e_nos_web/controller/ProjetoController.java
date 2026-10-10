package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Projeto;
import com.senac.entre_lacos_e_nos_web.service.ProjetoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProjetoController {

  @Autowired
  ProjetoService projetoService;

  @GetMapping("/")
  public String viewHomePage(@CookieValue(name = "tema", defaultValue = "escuro") String tema, HttpServletRequest request, Model model) {
    HttpSession sessao = request.getSession(false);
    Integer usuarioId = (sessao != null) ? (Integer) sessao.getAttribute("usuarioId") : null;
    String usuarioNome = (sessao != null) ? (String) sessao.getAttribute("usuarioNome") : null;

    if (usuarioNome == null) {
      return "redirect:/login";
    }
    model.addAttribute("usuarioId", usuarioId);
    model.addAttribute("usuarioNome", usuarioNome);
    model.addAttribute("tema", tema);
    return "tela-inicial";
  }

  @GetMapping("/deletarProjeto/{id}")
  public String deletarProjeto(@PathVariable(value = "id") Integer id) {
    projetoService.deletarProjeto(id);
    return "redirect:/";
  }

  @GetMapping("/criarProjetoForm")
  public String criarProjetoForm(@CookieValue(name = "tema", defaultValue = "escuro") String tema, HttpServletRequest request, Model model) {
    HttpSession sessao = request.getSession(false);
    Integer usuarioId = (sessao != null) ? (Integer) sessao.getAttribute("usuarioId") : null;
    model.addAttribute("usuarioId", usuarioId);
    model.addAttribute("tema", tema);
    return "projeto-cadastro"; 
  }

  @GetMapping("/atualizarProjetoForm/{id}")
  public String atualizarProjetoForm(@PathVariable Integer id, @CookieValue(name = "tema", defaultValue = "escuro") String tema, Model model) {
    Projeto proj = projetoService.getProjetoId(id);
    model.addAttribute("projeto", proj);
    model.addAttribute("tema", tema);
    return "projeto-atualizar";
  }

  @GetMapping("/projeto-receita/{id}")
  public String mostraRecita(@PathVariable Integer id, @CookieValue(name = "tema", defaultValue = "escuro") String tema, Model model) {
    Projeto proj = projetoService.getProjetoId(id);
    model.addAttribute("projeto", proj);
    model.addAttribute("tema", tema);
    return "projeto-receita"; // html da tela da receita desse projeto especifico
  }
}