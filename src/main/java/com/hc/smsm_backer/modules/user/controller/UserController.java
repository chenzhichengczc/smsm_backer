/*
package com.hc.smsm_backer.modules.user.controller;


import com.hc.smsm_backer.common.utils.IpConfig;
import com.hc.smsm_backer.common.utils.JWTUtil;
import com.hc.smsm_backer.common.utils.RedisUtil;
import com.hc.smsm_backer.common.utils.ResponseUtil;
import com.hc.smsm_backer.modules.user.entity.RoleUserEntity;
import com.hc.smsm_backer.modules.user.entity.UserEntity;
import com.hc.smsm_backer.modules.user.mapper.RoleUserMapper;
import com.hc.smsm_backer.modules.user.service.RoleUserService;
import com.hc.smsm_backer.modules.user.service.UserService;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * 用户表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:16:46
 *//*


@RestController
@RequestMapping("/backer")
public class UserController {



    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user/getUser",method = RequestMethod.GET)
    public ResponseUtil getUser(@RequestParam(value = "id",required = false) Integer id){

        List<UserEntity> userEntity = userService.getUser(id);

        return ResponseUtil.success(userEntity);
    }

}


*/
