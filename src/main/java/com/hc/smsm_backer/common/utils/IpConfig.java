package com.hc.smsm_backer.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ：fenghuang
 * @date ：Created in 2020/1/14 18:41
 * @description：
 * @modified By：
 * @version:
 */
public class IpConfig {

    /**
     * 获取客户端的IP
     *
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = "";
        try {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                    try {
                        InetAddress inet = InetAddress.getLocalHost();
                        ip = inet.getHostAddress();
                    } catch (UnknownHostException e) {
                    }
                }
            }
            return ip.split(",")[0];
        } catch (Exception e) {
        }
        return ip;
    }


}
