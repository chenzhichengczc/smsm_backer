package com.hc.smsm_backer.modules.user.controller;


import com.hc.smsm_backer.common.utils.JWTUtil;
import com.hc.smsm_backer.common.utils.RedisUtil;
import com.hc.smsm_backer.common.utils.ResponseUtil;
import com.hc.smsm_backer.modules.user.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private JWTUtil jwtUtil;

    @Resource
    private RoleUserService roleUserService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/api/manager/login", method = RequestMethod.POST)
    public ResponseUtil login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password,
                              HttpServletRequest request) {
        request.getSession().setAttribute("username", username);
        Map<String, Object> map = roleUserService.getRoleUser(username, password, request);

        return ResponseUtil.success(map);

    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public ResponseUtil logout(HttpServletRequest httpServletRequest){
        String token=httpServletRequest.getHeader("token");
        String jwtid=jwtUtil.getJwtIdByToken(token);
        System.out.println("jwtid:"+jwtid);
        redisUtil.deleteCache("JWT-SESSION-"+jwtid);
        httpServletRequest.getSession().setAttribute("username", null);
        return ResponseUtil.success();
    }



}


