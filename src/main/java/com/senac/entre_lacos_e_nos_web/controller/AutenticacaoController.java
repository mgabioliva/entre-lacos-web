package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Usuario;
import com.senac.entre_lacos_e_nos_web.exception.AutenticacaoException;
import com.senac.entre_lacos_e_nos_web.service.AutenticacaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutenticacaoController {

  @Autowired
  AutenticacaoService autenticacaoService;

  @GetMapping("/login")
  public String login(@CookieValue(name = "tema", defaultValue = "escuro") String tema, Model model) {
    model.addAttribute("tema", tema);
    return "login";
  }

  @PostMapping("/login")
  public String autenticar(@RequestParam String username, @RequestParam String senha,
          HttpServletRequest request, @CookieValue(name = "tema", defaultValue = "escuro") String tema, Model model) {
    try {
      Usuario usuario = autenticacaoService.autenticar(username, senha);
      HttpSession sessao = request.getSession();
      sessao.setAttribute("usuarioId", usuario.getId());
      sessao.setAttribute("usuarioNome", usuario.getNome());
      return "redirect:/";
    } catch (AutenticacaoException e) {
      model.addAttribute("erro", e.getMessage());
      model.addAttribute("tema", tema);
      return "login";
    }
  }

  @GetMapping("/logoff")
  public String logoff(HttpServletRequest request) {
    HttpSession sessao = request.getSession(false);
    if (sessao != null) {
      sessao.invalidate();
    }
    return "redirect:/login";
  }
}