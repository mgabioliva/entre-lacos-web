package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Usuario;
import com.senac.entre_lacos_e_nos_web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsuarioController {
  
  @Autowired
  UsuarioService usuarioService;
  
  @GetMapping("/deletarUsuario/{id}")
  public String deletarReceita(@PathVariable(value = "id") Integer id) {
    usuarioService.deletarUsuario(id);
    return "redirect:/";
  }

  @GetMapping("/criarUsuarioForm")
  public String criarReceitaForm (@CookieValue(name = "tema", defaultValue = "escuro") String tema,Model model) {
    Usuario user = new Usuario();
    model.addAttribute("usuario", user);
    model.addAttribute("tema", tema);
    return "usuario-cadastro"; // html da tela de cadastro
  }

  @GetMapping("/atualizarUsuarioForm/{id}")
  public String atualizarUsuarioForm(@PathVariable Integer id,@CookieValue(name = "tema", defaultValue = "escuro") String tema, Model model) {
    Usuario user = usuarioService.getUsuarioId(id);
    model.addAttribute("usuario", user);
    model.addAttribute("tema", tema);
    return "usuario-atualizar";
  }
}