package com.example.book.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * mvc拦截器
 * zhangyd
 * 2020/4/2 11:37
 */
public class DemoInterceptor extends HandlerInterceptorAdapter {

    /**
     * 请求开始前
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long start = System.currentTimeMillis();
        System.out.println("============== preHandle");
        return true;
    }

    /**
     * 请求结束后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        System.out.println("============== postHandle");
    }

}
