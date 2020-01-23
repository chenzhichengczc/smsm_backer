package com.hc.smsm_backer.modules.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.smsm_backer.common.exception.JcException;
import com.hc.smsm_backer.common.utils.IpConfig;
import com.hc.smsm_backer.common.utils.JWTUtil;
import com.hc.smsm_backer.modules.user.entity.RoleUserEntity;
import com.hc.smsm_backer.modules.user.mapper.RoleUserMapper;
import com.hc.smsm_backer.modules.user.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUserEntity> implements RoleUserService {

    @Resource
    private RoleUserMapper roleUserMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Map<String, Object> getRoleUser(String username, String password, HttpServletRequest request) {
        RoleUserEntity roleUserEntity = roleUserMapper.getRoleUser(username);
        System.out.println("roleUserEntity = " + roleUserEntity);
        Map<String, Object> map = new HashMap<>();
        if(roleUserEntity == null){
            throw new JcException(401, "用户名不存在或密码错误");
        }else if (roleUserEntity.getPassword() == null) {
            throw new JcException(401, "用户名不存在或密码错误");
        }else if (!roleUserEntity.getPassword().equals(password)) {
            throw new JcException(401, "用户名不存在或密码错误");
        }else {
            String remoteAddrIp = IpConfig.getRemoteAddr(request);
            String token = jwtUtil.createToken(username, remoteAddrIp);
            map.put("token", token);
            map.put("roleUser", roleUserEntity);
        }
        return map;
    }
}
