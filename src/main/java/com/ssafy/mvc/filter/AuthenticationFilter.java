package com.ssafy.mvc.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            session.setAttribute("msg", "로그인이 필요한 서비스입니다. 먼저 로그인해주세요.");
            response.sendRedirect("/");
            return;
        }

        //로그인 되어 있다면, 다음으로 진행
        filterChain.doFilter(request, response);
    }
}
