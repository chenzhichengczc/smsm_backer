/*
package com.hc.smsm_backer.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class LoginInterceptorConfigurer
        implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截路径：必须登录才可以访问
        List<String> patterns = new ArrayList<>();
        patterns.add("/**");

        // 白名单：在黑名单范围内，却不需要登录就可以访问
        List<String> excludePatterns = new ArrayList<>();
        excludePatterns.add("/static/**");
        excludePatterns.add("/javascript/**");
        excludePatterns.add("/lib/**");
        excludePatterns.add("/img/**");
        excludePatterns.add("/favicon.ico");
        excludePatterns.add("/login");
        excludePatterns.add("/login.html");
        excludePatterns.add("/backer/api/manager/login");
        excludePatterns.add("/unauthorized/**");
        registry
                .addInterceptor(new LoginInterceptor())
                .addPathPatterns(patterns)
                .excludePathPatterns(excludePatterns);
    }

}
*/
