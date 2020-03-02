package com.hc.smsm_backer.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.hc.smsm_backer.modules.user.entity.RoleUserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface RoleUserService extends IService<RoleUserEntity> {

    public Map<String, Object> getRoleUser(String username, String password, HttpServletRequest request);

    public String getUserPassword(String username);

    public Integer checkUserBanStatus(String username);

    /**
     * 获得用户角色默认的权限
     */
   public String getRolePermission(String username);

    /**
     * 获得用户的权限
     */
   public String getPermission(String username);

    public String getRole(String username);
}
