package com.hc.smsm_backer.basic;

import com.hc.smsm_backer.common.utils.ResponseUtil;
import com.hc.smsm_backer.common.utils.ShiroUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/6 23:23
 * @Version 1.0
 */

@Controller
public class CommonController {

    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page){
        return page;
    }

    @GetMapping("/favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
        System.out.println("true = " + true);
    }

    @RequestMapping(value = "/backer/auth/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        System.out.println("httpServletRequest = " + httpServletRequest);
        Subject subject = ShiroUtils.getSubject();
        subject.logout();
        System.out.println("subject = " + subject);
        return "login.html";
    }
}
