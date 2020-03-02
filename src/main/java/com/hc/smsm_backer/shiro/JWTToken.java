package com.hc.smsm_backer.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    private String ip;

    public JWTToken(String token, String ip) {
        this.token = token;
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public String getToken() {
        return token;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

    public void setToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
