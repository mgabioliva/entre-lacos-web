package com.senac.entre_lacos_e_nos_web.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new AutenticacaoInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns(
                    "/login",
                    "/logoff",
                    "/criarUsuarioForm",
                    "/salvarUsuario",
                    "/usuario/adicionar",
                    "/tema/**",
                    "/css/**",
                    "/js/**",
                    "/favicon.ico"
            );
  }
}
