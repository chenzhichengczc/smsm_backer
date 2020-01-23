package com.hc.smsm_backer.modules.manager.controller;


import com.hc.smsm_backer.common.utils.ResponseUtil;
import com.hc.smsm_backer.modules.manager.entity.ManagerEntity;
import com.hc.smsm_backer.modules.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:16:46
 */

@RestController
@RequestMapping("/backer")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    /*@RequestMapping(value = "/api/manager/login", method = RequestMethod.POST)
    public ResponseUtil login(String loginAccount, String loginPassword) {
        ManagerEntity managerEntity = managerService.login(loginAccount,loginPassword);
        return ResponseUtil.success(managerEntity);
    }*/

}



