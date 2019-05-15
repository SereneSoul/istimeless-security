package com.istimeless.securitydemo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lijiayin
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        if(handler instanceof HandlerMethod){
            System.out.println(((HandlerMethod)handler).getBean().getClass().getSimpleName());
            System.out.println(((HandlerMethod)handler).getMethod().getName());
        }
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        long start = (long)request.getAttribute("startTime");
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("time interceptor cost:" + cost);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        long start = (long)request.getAttribute("startTime");
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("time interceptor cost:" + cost);
        System.out.println("ex is:" + ex);
    }
}
