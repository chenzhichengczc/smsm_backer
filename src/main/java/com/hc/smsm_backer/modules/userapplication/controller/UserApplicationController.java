package com.hc.smsm_backer.modules.userapplication.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.hc.smsm_backer.common.utils.ResponseUtil;
import com.hc.smsm_backer.modules.user.entity.UserEntity;
import com.hc.smsm_backer.modules.userapplication.entity.UserApplicationEntity;
import com.hc.smsm_backer.modules.userapplication.entity.UserApplicationPO;
import com.hc.smsm_backer.modules.userapplication.service.UserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    * 新增
    *//*
    @RequestMapping(value = "/api/userApplication/insert", method = RequestMethod.POST)
    public ResponseUtil insertUserApplication(UserApplicationEntity userApplicationEntity){
        userApplicationService.insertUserApplication(userApplicationEntity);
        return ResponseUtil.success();
    }*/

    /**
    *  获取
    */
    @RequestMapping(value = "/api/userApplication/getById", method = RequestMethod.GET)
    public ResponseUtil getUserApplicationById(Integer userApplicationId){
        UserApplicationEntity userApplicationEntity = userApplicationService.getUserApplicationById(userApplicationId);
        return ResponseUtil.success(userApplicationEntity);
    }

/*    *//**
    * 删除
    *//*
    @RequestMapping(value = "/api/userApplication/delete", method = RequestMethod.POST)
    public ResponseUtil removeUserApplicationById(Integer userApplicationId){
        userApplicationService.removeUserApplicationById(userApplicationId);
        return ResponseUtil.success();
    }*/

    /**
    * 更新
    *//*
    @RequestMapping(value = "/api/userApplication/update", method = RequestMethod.POST)
    public ResponseUtil updateUserApplication(UserApplicationEntity userApplicationEntity){
        userApplicationService.updateUserApplication(userApplicationEntity);
        return ResponseUtil.success();
    }*/

    /**
     * 查询所有简历投递列表
     */

    @RequestMapping(value = "/api/userApplication/getUserApplication", method = RequestMethod.GET)
    public ResponseUtil getUserApplication(UserEntity userEntity){
        List<UserApplicationPO> userApplication = userApplicationService.getUserApplication(userEntity);
        return ResponseUtil.success(userApplication);
    }

   /* *//**
     * 图片上传
     *//*
    @RequestMapping(value = "/file/userApplication/uploadFile",method = RequestMethod.POST)
    public ResponseUtil uploadFile(MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = (Map<String, Object>) FileUploadUtils.uploadApk(uploadFile, request, response);
        return ResponseUtil.success(map);
    }*/

}
