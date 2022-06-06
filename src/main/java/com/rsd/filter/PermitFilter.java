package com.rsd.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = "/*")
public class PermitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (uri.equals("/login.jsp") || uri.equals("/login")) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("sysUser");
            if (obj != null) {
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                PrintWriter out = servletResponse.getWriter();
                out.println("<html>");
                out.println("<script>");
                out.println("window.open('/login.jsp','_top')");
                out.println("</script>");
                out.println("</html>");
            }
        }
    }
}
