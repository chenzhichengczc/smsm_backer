package com.hc.smsm_backer.modules.postapplication.service.impl;

import com.hc.smsm_backer.common.exception.JcException;
import com.hc.smsm_backer.modules.postapplication.mapper.PostApplicationMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.hc.smsm_backer.modules.postapplication.entity.PostApplicationEntity;
import com.hc.smsm_backer.modules.postapplication.service.PostApplicationService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class PostApplicationServiceImpl extends ServiceImpl<PostApplicationMapper, PostApplicationEntity> implements PostApplicationService {

    @Resource
    private PostApplicationMapper postApplicationMapper;

    @Override
    public List<PostApplicationEntity> getPostApplicationList(String postName,
                                                              String hireDepartment,
                                                              String recruitment,
                                                              String educationRequirement,
                                                              String major){
        List<PostApplicationEntity> postApplicationEntityList = postApplicationMapper.getPostApplicationList(postName,hireDepartment,recruitment,educationRequirement,major);
        return postApplicationEntityList;
    }

    @Override
    public void insertPostApplication(PostApplicationEntity postApplicationEntity){
        Integer result = postApplicationMapper.insertPostApplication(postApplicationEntity);
        if(result == null || result == 0){
            throw new JcException("");
        }
    }

    @Override
    public PostApplicationEntity getPostApplicationById(Integer postApplicationId){
        PostApplicationEntity postApplicationEntity = postApplicationMapper.getPostApplicationById(postApplicationId);
        return postApplicationEntity;
    }

    @Override
    public void removePostApplicationById(Integer postApplicationId){
        Integer result =  postApplicationMapper.removePostApplicationById(postApplicationId);
        if(result == null || result == 0){
            throw new JcException("");
        }
    }

    @Override
    public void updatePostApplication(PostApplicationEntity postApplicationEntity){
        Integer result = postApplicationMapper.updateById(postApplicationEntity);
        if(result == null || result == 0){
            throw new JcException("");
        }
    }

    @Override
    public HashMap getSelect() {

        HashMap hashMap = new HashMap();

        List<String> postNameList = postApplicationMapper.selectPostName();
        List<String> hireDepartmentList = postApplicationMapper.selectHireDepartment();
        List<String> recruitmentList = postApplicationMapper.selectRecruitment();
        List<String> educationRequirementList = postApplicationMapper.selectEducationRequirement();
        List<String> majorList = postApplicationMapper.selectMajor();

        hashMap.put("postNameList",postNameList);
        hashMap.put("hireDepartmentList",hireDepartmentList);
        hashMap.put("recruitmentList",recruitmentList);
        hashMap.put("educationRequirementList",educationRequirementList);
        hashMap.put("majorList",majorList);


        return hashMap;
    }
}
