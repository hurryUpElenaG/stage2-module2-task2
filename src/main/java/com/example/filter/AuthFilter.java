package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/user/*")
public class AuthFilter implements Filter {
    //write your code here!

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponce = (HttpServletResponse) servletResponse;

        HttpSession session = httpRequest.getSession(false);//don't create a new session if not exists
        if (session == null || session.getAttribute("user") == null) {
            httpResponce.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}