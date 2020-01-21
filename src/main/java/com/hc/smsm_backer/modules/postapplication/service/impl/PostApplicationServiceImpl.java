package com.hc.smsm_backer.modules.postapplication.service.impl;

import com.hc.smsm_backer.common.exception.JcException;
import com.hc.smsm_backer.modules.postapplication.mapper.PostApplicationMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.hc.smsm_backer.modules.postapplication.entity.PostApplicationEntity;
import com.hc.smsm_backer.modules.postapplication.service.PostApplicationService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class PostApplicationServiceImpl extends ServiceImpl<PostApplicationMapper, PostApplicationEntity> implements PostApplicationService {

    @Resource
    private PostApplicationMapper postApplicationMapper;


    @Override
    public List<PostApplicationEntity> getPostList(Integer id) {
        List<PostApplicationEntity> postApplicationEntities = null;
        if (id == null) {
            postApplicationEntities = postApplicationMapper.selectList(new EntityWrapper<PostApplicationEntity>().orderBy("application_deadline"));
        } else {
            postApplicationEntities = postApplicationMapper.selectList(new EntityWrapper<PostApplicationEntity>().eq("id", id).orderBy("application_deadline"));
        }

        return postApplicationEntities;
    }

    @Override
    public String getCode() {

        String code = randon();

        List<PostApplicationEntity> list = postApplicationMapper.selectList(new EntityWrapper<PostApplicationEntity>().eq("post_code", code));

        if (list.size() != 0) {
            return getCode();
        }

        return code;
    }

    @Override
    public void insertPost(PostApplicationEntity postApplicationEntity) {
        Integer row = postApplicationMapper.insert(postApplicationEntity);
        if (row != 1) {
            throw new JcException("新增岗位失败");
        }
    }

    @Override
    public void deletePostList(String[] ids) {

        Integer integer = postApplicationMapper.deleteBatchIds(Arrays.asList(ids));

        if (integer == 0 ) {
            throw new JcException("删除岗位失败");
        }
    }

    @Override
    public void updatePost(PostApplicationEntity postApplicationEntity) {
        Integer row = postApplicationMapper.update(postApplicationEntity, new EntityWrapper<PostApplicationEntity>().eq("id", postApplicationEntity.getId()));
        if (row != 1) {
            throw new JcException("更新岗位失败");
        }
    }

    public String randon() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();

        String code = "";
        for (int i = 0; i < 6; i++) {
            sb.append(rand.nextInt(10));
        }

        return sb.toString();
    }
}
