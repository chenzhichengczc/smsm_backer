package com.hc.smsm_backer.modules.postapplication.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.hc.smsm_backer.modules.postapplication.entity.PostApplicationEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;


/**
 * 申请岗位表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-09 19:05:56
 */
public interface PostApplicationService extends IService<PostApplicationEntity> {

    public List<PostApplicationEntity> getPostList(Integer id);

    public String getCode();

    public void insertPost(PostApplicationEntity postApplicationEntity);

    public void deletePostList(String[] ids);

    public void updatePost(PostApplicationEntity postApplicationEntity);
}

