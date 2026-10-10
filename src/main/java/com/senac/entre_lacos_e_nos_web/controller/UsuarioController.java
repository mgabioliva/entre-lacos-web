package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Usuario;
import com.senac.entre_lacos_e_nos_web.service.UsuarioService;
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
public class UsuarioController {
  
  @Autowired
  UsuarioService usuarioService;
  
  @GetMapping("/usuarios")
  public String viewHomePage(@CookieValue(name = "tema", defaultValue = "claro") String tema,Model model) {
    model.addAttribute("listarUsuarios", usuarioService.listarTodosUsuarios());
    model.addAttribute("tema", tema);
    return "projeto-lista"; // html da tela inicial
  }

  @GetMapping("/deletarUsuario/{id}")
  public String deletarReceita(@PathVariable(value = "id") Integer id) {
    usuarioService.deletarUsuario(id);
    return "redirect:/";
  }

  @GetMapping("/criarUsuarioForm")
  public String criarReceitaForm (@CookieValue(name = "tema", defaultValue = "claro") String tema,Model model) {
    Usuario user = new Usuario();
    model.addAttribute("usuario", user);
    model.addAttribute("tema", tema);
    return "usuario-cadastro"; // html da tela de cadastro
  }

  @PostMapping("/salvarUsuario")
  public String salvarUsuario(@Valid @ModelAttribute("usuario") Usuario user, BindingResult result) {
    if (result.hasErrors()) {
      return "usuario-cadastro"; // html da tela de cadastro
    }
    if (user.getId() == null) {
      usuarioService.criarUsuario(user);
    } else {
      usuarioService.atualizarUsuario(user.getId(), user);
    }
    return "redirect:/";
  }

  @GetMapping("/atualizarUsuarioForm/{id}")
  public String atualizarUsuarioForm(@PathVariable Integer id,@CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
    Usuario user = usuarioService.getUsuarioId(id);
    model.addAttribute("usuario", user);
    model.addAttribute("tema", tema);
    return "usuario-atualizar";
  }
}
