package com.rog.adminpermission.configuration.webconfiguration.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 15:33
 **/
public class TestInterceptor implements HandlerInterceptor {//另一种MethodInterceptor是AOP项目中的拦截器
    //HandlerInterceptor是springMVC项目中的拦截器
    @Override//请求前调用
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println(request.getRemoteAddr());
        System.out.println("请求路径=====>"+request.getServletPath());
        return true;
    }

    @Override//请求之后调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override//完成时调用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}

