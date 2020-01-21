package com.hc.smsm_backer.modules.user.service.impl;

import com.hc.smsm_backer.common.exception.JcException;
import com.hc.smsm_backer.modules.user.mapper.UserMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.hc.smsm_backer.modules.user.entity.UserEntity;
import com.hc.smsm_backer.modules.user.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void regUser(UserEntity userEntity) {

        String uname = userEntity.getUserAcountName();
        //查询是否存在相同用户名
        Integer result = userMapper.findByUname(uname);

        if (result == 0) {
            Integer newResult = userMapper.insert(userEntity);
            if (newResult == null || newResult != 1) {
                throw new JcException("创建用户失败");
            }
        } else {
            throw new JcException(332, "当前用户名已存在");
        }
    }

    @Override
    public UserEntity getUser(String username) {
        UserEntity userEntity = userMapper.getUser(username);
        if (userEntity == null) {
            userEntity = new UserEntity();
        }
        return userEntity;
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        Integer id = userMapper.update(userEntity, new EntityWrapper<UserEntity>().eq("id", userEntity.getId()));

        if (id != 1) {
            throw new JcException("更新信息失败！");
        }

    }

    @Override
    public UserEntity getUserById(Integer id) {

        UserEntity userEntity = userMapper.selectById(id);

        if(userEntity == null){
            throw new JcException("获取个人信息失败！");
        }
        return userEntity;
    }
}
