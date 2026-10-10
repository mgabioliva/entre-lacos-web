package com.senac.entre_lacos_e_nos_web.controller;

import com.senac.entre_lacos_e_nos_web.data.Receita;
import com.senac.entre_lacos_e_nos_web.service.ReceitaService;
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
public class ReceitaController {
  
  @Autowired
  ReceitaService receitaService;
  
  @GetMapping("/receitas")
  public String viewHomePage(@CookieValue(name = "tema", defaultValue = "claro") String tema,Model model) {
    model.addAttribute("listarReceitas", receitaService.listarTodasReceitas());
    model.addAttribute("tema", tema);
    return "projeto-lista"; // html da tela inicial
  }

  @GetMapping("/deletarReceita/{id}")
  public String deletarReceita(@PathVariable(value = "id") Integer id) {
    receitaService.deletarReceita(id);
    return "redirect:/";
  }

  @GetMapping("/criarReceitaForm")
  public String criarReceitaForm (@CookieValue(name = "tema", defaultValue = "claro") String tema,Model model) {
    Receita rec = new Receita();
    model.addAttribute("receita", rec);
    model.addAttribute("tema", tema);
    return "receita-cadastro"; // html da tela de cadastro
  }

  @PostMapping("/salvarReceita")
  public String salvarReceita(@Valid @ModelAttribute("receita") Receita rec, BindingResult result) {
    if (result.hasErrors()) {
      return "receita-cadastro"; // html da tela de cadastro
    }
    if (rec.getId() == null) {
      receitaService.criarReceita(rec);
    } else {
      receitaService.atualizarReceita(rec.getId(), rec);
    }
    return "redirect:/";
  }

  @GetMapping("/atualizarReceitaForm/{id}")
  public String atualizarReceitaForm(@PathVariable Integer id,@CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
    Receita rec = receitaService.getReceitaId(id);
    model.addAttribute("receita", rec);
    model.addAttribute("tema", tema);
    return "receita-atualizar";
  }
}
