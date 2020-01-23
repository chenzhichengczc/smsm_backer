package com.hc.smsm_backer.modules.userapplication.controller;

import com.hc.smsm_backer.common.utils.ResponseUtil;
import com.hc.smsm_backer.modules.userapplication.entity.UserApplicationEntity;
import com.hc.smsm_backer.modules.userapplication.entity.UserApplicationPO;
import com.hc.smsm_backer.modules.userapplication.service.UserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



/**
 * 用户岗位申请表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:46:44
 */
@RestController
@RequestMapping("/backer")
public class UserApplicationController {

    @Autowired
    private UserApplicationService userApplicationService;

    /**
     * 列表
     */
    @RequestMapping(value = "/api/userApplication/list", method = RequestMethod.GET)
    public ResponseUtil getUserApplicationList(){
        List<UserApplicationEntity> userApplicationEntities = userApplicationService.getUserApplicationList();
        return ResponseUtil.success(userApplicationEntities);
    }

    /**
    *  获取
    */
    @RequestMapping(value = "/api/userApplication/getById", method = RequestMethod.GET)
    public ResponseUtil getUserApplicationById(Integer userApplicationId){
        UserApplicationEntity userApplicationEntity = userApplicationService.getUserApplicationById(userApplicationId);
        return ResponseUtil.success(userApplicationEntity);
    }


    @RequestMapping(value = "/api/userApplication/getUserApplication", method = RequestMethod.GET)
    public ResponseUtil getUserApplication(@RequestParam(required = false) Integer id){
        List<UserApplicationPO> userApplication = userApplicationService.getUserApplication(id);
        return ResponseUtil.success(userApplication);
    }

    @RequestMapping(value = "/api/userApplication/changeStatus", method = RequestMethod.POST)
    public ResponseUtil changeStatus(UserApplicationEntity userApplicationEntity){
        userApplicationService.changeStatus(userApplicationEntity);
        return ResponseUtil.success();
    }



}
