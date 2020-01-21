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

    public List<PostApplicationEntity> getPostApplicationList(String postName,
                                                              String hireDepartment,
                                                              String recruitment,
                                                              String educationRequirement,
                                                              String major);

    public void insertPostApplication(PostApplicationEntity postApplicationEntity);

    public PostApplicationEntity getPostApplicationById(Integer postApplicationId);

    public void removePostApplicationById(Integer postApplicationId);

    public void updatePostApplication(PostApplicationEntity postApplicationEntity);

    public HashMap getSelect();
}

