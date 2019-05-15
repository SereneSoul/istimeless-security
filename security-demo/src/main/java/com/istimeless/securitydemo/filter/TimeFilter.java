package com.istimeless.securitydemo.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author lijiayin
 */
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("time filter cost:" + cost);
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
