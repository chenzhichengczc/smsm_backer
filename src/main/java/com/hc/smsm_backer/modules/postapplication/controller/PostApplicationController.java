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
@RequestMapping("/backer")
public class PostApplicationController {


    @Autowired
    private PostApplicationService postApplicationService;


    // 后台 - 获取工作岗位列表
    @RequestMapping(value = "/api/postApplication/getPostList",method = RequestMethod.GET)
    public ResponseUtil getPostList(@RequestParam(value = "id",required = false) Integer id){

        List<PostApplicationEntity> list = postApplicationService.getPostList(id);

        return ResponseUtil.success(list);
    }

    // 后台 - 获取工作编号
    @RequestMapping(value = "/api/postApplication/getCode",method = RequestMethod.GET)
    public ResponseUtil getCode(){

        String code = postApplicationService.getCode();

        return ResponseUtil.success(code);
    }

    // 后台 - 获取工作编号
    @RequestMapping(value = "/api/postApplication/insertPost",method = RequestMethod.POST)
    public ResponseUtil insertPost(PostApplicationEntity postApplicationEntity){

        postApplicationService.insertPost(postApplicationEntity);

        return ResponseUtil.success();
    }


}
