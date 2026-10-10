package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Receita;
import com.senac.entre_lacos_e_nos_web.service.ReceitaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReceitaController {

  @Autowired
  ReceitaService receitaService;

  @GetMapping("/deletarReceita/{id}")
  public String deletarReceita(@PathVariable(value = "id") Integer id) {
    receitaService.deletarReceita(id);
    return "redirect:/";
  }

  @GetMapping("/criarReceitaForm")
  public String criarReceitaForm(@CookieValue(name = "tema", defaultValue = "escuro") String tema, HttpServletRequest request, Model model) {
    HttpSession sessao = request.getSession(false);
    Integer usuarioId = (sessao != null) ? (Integer) sessao.getAttribute("usuarioId") : null;
    model.addAttribute("usuarioId", usuarioId);
    model.addAttribute("tema", tema);
    return "receita-cadastro";
  }

  @GetMapping("/atualizarReceitaForm/{id}")
  public String atualizarReceitaForm(@PathVariable Integer id, @CookieValue(name = "tema", defaultValue = "escuro") String tema, Model model) {
    Receita rec = receitaService.getReceitaId(id);
    model.addAttribute("receita", rec);
    model.addAttribute("tema", tema);
    return "receita-atualizar";
  }
}