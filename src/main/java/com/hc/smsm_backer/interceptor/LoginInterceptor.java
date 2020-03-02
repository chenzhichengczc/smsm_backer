/*
package com.hc.smsm_backer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        System.out.println(" hello= " );
        if (session.getAttribute("username") == null) {

            System.out.println("request.getSession() = " + request.getSession().getAttribute("username"));

            response.sendRedirect("http://localhost/login.html");

            return false;
        }
        return true;
    }

}
*/
