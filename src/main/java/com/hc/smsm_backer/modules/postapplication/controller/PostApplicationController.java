package com.hc.smsm_backer.modules.postapplication.controller;

import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.smsm_backer.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hc.smsm_backer.modules.postapplication.entity.PostApplicationEntity;
import com.hc.smsm_backer.modules.postapplication.service.PostApplicationService;


/**
 * 申请岗位表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-09 19:05:56
 */
@RestController
public class PostApplicationController {


    @Autowired
    private PostApplicationService postApplicationService;

    /**
     * 列表
     */
    @RequestMapping(value = "/api/postApplication/list", method = RequestMethod.GET)
    public ResponseUtil getPostApplicationList(Integer pageNo, Integer pageSize,
                                               @RequestParam(required = false) String postName,
                                               @RequestParam(required = false) String hireDepartment,
                                               @RequestParam(required = false) String recruitment,
                                               @RequestParam(required = false) String educationRequirement,
                                               @RequestParam(required = false) String major) {
        PageHelper.startPage(pageNo, pageSize);
        List<PostApplicationEntity> postApplicationEntities = postApplicationService.getPostApplicationList(postName,hireDepartment,recruitment,educationRequirement,major);
        PageInfo<PostApplicationEntity> pageInfo = new PageInfo<>(postApplicationEntities);
        return ResponseUtil.success(pageInfo);
    }


   /* *//**
     * 新增
     *//*
    @RequestMapping(value = "/api/postApplication/insert", method = RequestMethod.POST)
    public ResponseUtil insertPostApplication(PostApplicationEntity postApplicationEntity) {
        postApplicationService.insertPostApplication(postApplicationEntity);
        return ResponseUtil.success();
    }
*/
    /**
     * 获取
     */
    @RequestMapping(value = "/api/postApplication/getById", method = RequestMethod.GET)
    public ResponseUtil getPostApplicationById(Integer postApplicationId) {
        PostApplicationEntity postApplicationEntity = postApplicationService.getPostApplicationById(postApplicationId);
        return ResponseUtil.success(postApplicationEntity);
    }

    /**
     * 删除
     *//*
    @RequestMapping(value = "/api/postApplication/delete", method = RequestMethod.POST)
    public ResponseUtil removePostApplicationById(Integer postApplicationId) {
        postApplicationService.removePostApplicationById(postApplicationId);
        return ResponseUtil.success();
    }

    *//**
     * 更新
     *//*
    @RequestMapping(value = "/api/postApplication/update", method = RequestMethod.POST)
    public ResponseUtil updatePostApplication(PostApplicationEntity postApplicationEntity) {
        postApplicationService.updatePostApplication(postApplicationEntity);
        return ResponseUtil.success();
    }*/

    //获取多列下拉列表数据
    @RequestMapping(value = "/api/postApplication/getSelect",method = RequestMethod.GET)
    public ResponseUtil getSelect(){

        HashMap map = postApplicationService.getSelect();

        return ResponseUtil.success(map);
    }

}
