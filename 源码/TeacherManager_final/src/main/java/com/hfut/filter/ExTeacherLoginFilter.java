package com.hfut.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hfut.domain.ExTeacher;


public class ExTeacherLoginFilter implements Filter {
    private static final String LOGIN_PREFIX = "/exteacher/",
                                LOGIN_KEYWORD = "Login",
                                LOGIN_PAGE = "index.jsp";
    public static final String ATTR_EXTEACHER = "ExTeacher";
    
    public void init(FilterConfig filterConfig) {}
    
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws ServletException, IOException {
    	System.out.println("Exteacher filter in");
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        
        String requestURI = req.getRequestURI();
        String uri = requestURI.substring(requestURI.indexOf(LOGIN_PREFIX));
        if (uri.indexOf(LOGIN_KEYWORD) == -1) {
            ExTeacher exteacher = (ExTeacher)session.getAttribute(ATTR_EXTEACHER);
            if (exteacher == null) {
                String loginUri = "../" +  LOGIN_PAGE;
                ((HttpServletResponse)response).sendRedirect(loginUri);
                return;               
            }
        }
        System.out.println("student filter out");
        chain.doFilter(request, response);
    }

    public void destroy() {}
}