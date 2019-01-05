package com.atguigu.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
进行登录检查
 */

/**
 * 配置拦截器进行登录检查，实现HandlerInterceptor接口
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //在访问之前进行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session中的属性
        Object user = request.getSession().getAttribute("loginUser");
        if (user==null){
            //未登录
            request.setAttribute("msg","没有权限访问");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            //一登录，放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
