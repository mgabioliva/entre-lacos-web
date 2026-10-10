package com.senac.entre_lacos_e_nos_web.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tema")
public class TemaController {

  @GetMapping("/{tema}")
  public String mudarTema(@PathVariable String tema, HttpServletResponse response) {
    Cookie cookieTema = new Cookie("tema", tema);
    cookieTema.setPath("/");
    cookieTema.setMaxAge(60 * 60 * 24 * 365); 
    response.addCookie(cookieTema);
    return "redirect:/";
  }
}
