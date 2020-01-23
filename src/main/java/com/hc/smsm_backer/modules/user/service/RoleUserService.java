package com.hc.smsm_backer.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.hc.smsm_backer.modules.user.entity.RoleUserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface RoleUserService extends IService<RoleUserEntity> {

    public Map<String, Object> getRoleUser(String username, String password, HttpServletRequest request);
}
