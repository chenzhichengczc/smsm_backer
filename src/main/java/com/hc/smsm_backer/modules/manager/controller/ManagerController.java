
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


    @RequestMapping(value = "/api/manager/managerList", method = RequestMethod.GET)
    public ResponseUtil managerList(@RequestParam(value = "id",required = false) Integer id) {
        List<ManagerEntity> managerEntityList = managerService.managerList(id);
        return ResponseUtil.success(managerEntityList);
    }

    @RequestMapping(value = "/api/manager/insertManager", method = RequestMethod.POST)
    public ResponseUtil insertManager(ManagerEntity managerEntity) {
        managerService.insertManager(managerEntity);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/api/manager/updateStatus", method = RequestMethod.POST)
    public ResponseUtil updateStatus(ManagerEntity managerEntity) {
        managerService.updateStatus(managerEntity);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/api/manager/deleteManager", method = RequestMethod.POST)
    public ResponseUtil deleteManager(Integer id) {
        managerService.deleteManager(id);
        return ResponseUtil.success();
    }


    @RequestMapping(value = "/api/manager/updateManager", method = RequestMethod.POST)
    public ResponseUtil updateManager(ManagerEntity managerEntity) {
        managerService.updateManager(managerEntity);
        return ResponseUtil.success();
    }
}




