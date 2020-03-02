package com.hc.smsm_backer.modules.user.controller;


import com.hc.smsm_backer.common.utils.*;
import com.hc.smsm_backer.modules.user.service.RoleUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:16:46
 */

@RestController
@RequestMapping("/backer")
public class RoleUserController {

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private RoleUserService roleUserService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/manager/login", method = RequestMethod.POST)
    public ResponseUtil login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password,
                              HttpServletRequest request) {

        try{
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return ResponseUtil.error(401, e.getMessage());
        }
        String ip = IpConfig.getRemoteAddr(request);
        String token = jwtUtil.createToken(username, ip);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);

        return ResponseUtil.success(map);

    }

    /*@RequestMapping(value = "/auth/logout",method = RequestMethod.GET)
    public ResponseUtil logout(HttpServletRequest httpServletRequest){
        Subject subject = ShiroUtils.getSubject();
        subject.logout();
        System.out.println("subject = " + subject);
        return ResponseUtil.success();
    }*/

    



}


