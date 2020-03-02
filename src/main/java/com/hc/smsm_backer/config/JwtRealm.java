package com.hc.smsm_backer.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.hc.smsm_backer.common.utils.JWTUtil;
import com.hc.smsm_backer.modules.user.mapper.RoleUserMapper;
import com.hc.smsm_backer.shiro.JWTToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lixiao
 * @date 2019/8/6 10:02
 */
@Slf4j
@Component
public class JwtRealm extends AuthorizingRealm {

    @Resource
    private RoleUserMapper roleUserMapper;

    @Resource
    private JWTUtil jwtUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String username = jwtUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role = roleUserMapper.getRole(username);
        //每个角色拥有默认的权限
        String rolePermission = roleUserMapper.getRolePermission(username);
        //每个用户可以设置新的权限
        String permission = roleUserMapper.getPermission(username);
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

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        JWTToken token = (JWTToken) auth;

        // 解密获得token
         System.out.println("认证token:"+token);
         // 解密获得username，用于和数据库进行对比
         String username = jwtUtil.getUsername(token.getToken());
         System.out.println(username);
         if (username == null || !jwtUtil.verify(token.getToken(), username, token.getIp())) {
              throw new IncorrectCredentialsException("登录信息已失效");
         }
        return new SimpleAuthenticationInfo(token.getToken(), token.getToken(), "JwtRealm");
    }
}
