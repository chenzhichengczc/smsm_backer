
package com.hc.smsm_backer.filter;


import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hc.smsm_backer.common.utils.IpConfig;
import com.hc.smsm_backer.common.utils.ShiroUtils;
import com.hc.smsm_backer.shiro.JWTToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 */

public class JWTFilter extends BasicHttpAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


/**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     */

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        //判断请求的请求头是否带上 "Token"
        if (isLoginAttempt(request, response)) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (AuthenticationException e) {
                //过期登出shiro
                Subject subject = ShiroUtils.getSubject();
                subject.logout();
                return false;
                //responseError(request, response, e.getMessage());

                //token 错误
                //responseError(request, response, e.getMessage());
            }
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }


/**
     * 判断用户是否想要登入。
     * 检测 header 里面是否包含 Token 字段
     */

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        System.out.println("uri " + req.getRequestURI());
        System.out.println("token = " + token);
        if(token == null && req.getRequestURI().equals("/backer/api/manager/login")){
        
            return false;
        }
        if(token == null){
            throw new AuthenticationException("当前请求错误.请重新登录");
        }
        return true;
    }



    /**
     * 执行登陆操作
     */

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response)  {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        String ip = IpConfig.getRemoteAddr(httpServletRequest);

        JWTToken jwtToken = new JWTToken(token, ip);

        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


    /**
     * 对跨域提供支持
     *//*

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        System.out.println("httpServletRequest = " + httpServletRequest.getMethod());
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }*/


    /**
     * 将非法请求跳转到 /unauthorized/**
     */

    private void responseError(ServletRequest request, ServletResponse response, String message) {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("message = " + message);
        //设置编码，否则中文字符在重定向时会变为空字符串
        System.out.println("message = " + message);
        try {

            httpServletResponse.sendRedirect("/backer/auth/logout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

