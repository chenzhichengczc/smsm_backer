package com.hc.smsm_backer.modules.user.service;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.hc.smsm_backer.modules.user.entity.UserEntity;

import java.util.List;


/**
 * 用户表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:16:46
 */
public interface UserService extends IService<UserEntity> {


    public void regUser(UserEntity userEntity);

    public UserEntity getUser(String username);

    public void updateUser(UserEntity userEntity);

    public UserEntity getUserById(Integer id);
}

