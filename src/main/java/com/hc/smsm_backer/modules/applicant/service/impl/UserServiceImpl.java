package com.hc.smsm_backer.modules.applicant.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.smsm_backer.modules.applicant.entity.UserEntity;
import com.hc.smsm_backer.modules.applicant.mapper.UserMapper;
import com.hc.smsm_backer.modules.applicant.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getUser(Integer id) {

        List<UserEntity> userList = null;

        if(id == null){
            userList = userMapper.selectList(new EntityWrapper<>());
        }else{
            userList = userMapper.selectList(new EntityWrapper<UserEntity>().eq("id", id));
        }
        return userList;
    }

}
