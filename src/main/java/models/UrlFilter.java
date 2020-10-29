package models;


import javax.servlet.*;
import java.io.IOException;


public class UrlFilter implements Filter {

    public static int counter;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        counter++;
    }

    @Override
    public void destroy() {

    }
}
