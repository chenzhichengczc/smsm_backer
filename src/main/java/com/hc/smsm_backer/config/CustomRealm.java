/*
package com.hc.smsm_backer.config;



import com.hc.smsm_backer.common.utils.JWTUtil;
import com.hc.smsm_backer.modules.user.entity.RoleUserEntity;
import com.hc.smsm_backer.modules.user.mapper.RoleUserMapper;
import com.hc.smsm_backer.shiro.JWTToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


@Component
public class CustomRealm extends AuthorizingRealm {
    @Resource
    private RoleUserMapper userMapper;

    @Resource
    private JWTUtil jwtUtil;

    */
/**
     * 必须重写此方法，不然会报错
     *//*

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    */
/**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     *//*

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();

        System.out.println("认证token:"+token);
        // 解密获得username，用于和数据库进行对比
        String username = jwtUtil.getUsername(token);
        System.out.println(username);
        if (username == null || !jwtUtil.verify(token, username)) {
            System.out.println("authenticationToken = " + authenticationToken);
            throw new AuthenticationException("token已过期,请重新登录！");
        }
        RoleUserEntity roleUserEntity = userMapper.getRoleUser(username);

        if (roleUserEntity.getPassword() == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        int ban = userMapper.checkUserBanStatus(username);
        if (ban == 1) {
            throw new AuthenticationException("该用户已被封号！");
        }
        return new SimpleAuthenticationInfo(token, token, "MyRealm");
    }

    */
/**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     *//*

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String username = jwtUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role = userMapper.getRole(username);
        //每个角色拥有默认的权限
        String rolePermission = userMapper.getRolePermission(username);
        //每个用户可以设置新的权限
        String permission = userMapper.getPermission(username);
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        roleSet.add(role);
        permissionSet.add(rolePermission);
        permissionSet.add(permission);
        //设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
*/
