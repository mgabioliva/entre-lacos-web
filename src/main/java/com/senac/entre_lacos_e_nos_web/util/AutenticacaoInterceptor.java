package com.senac.entre_lacos_e_nos_web.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.web.servlet.HandlerInterceptor;

public class AutenticacaoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession sessao = request.getSession(false);
        boolean autenticado = sessao != null && sessao.getAttribute("usuarioId") != null;

        if (autenticado) {
            return true;
        }

        String uri = request.getRequestURI();
        boolean ehApi = uri.startsWith("/receita") || uri.startsWith("/projeto") || uri.startsWith("/usuario");

        if (ehApi) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "é necessário estar autenticado/logado");
            return false;
        }

        response.sendRedirect("/login");
        return false;
    }
}