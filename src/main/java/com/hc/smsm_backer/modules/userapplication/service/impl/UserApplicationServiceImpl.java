package com.hc.smsm_backer.modules.userapplication.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.smsm_backer.common.exception.JcException;
import com.hc.smsm_backer.modules.postapplication.mapper.PostApplicationMapper;
import com.hc.smsm_backer.modules.user.entity.UserEntity;
import com.hc.smsm_backer.modules.userapplication.entity.UserApplicationEntity;
import com.hc.smsm_backer.modules.userapplication.entity.UserApplicationPO;
import com.hc.smsm_backer.modules.userapplication.mapper.UserApplicationMapper;
import com.hc.smsm_backer.modules.userapplication.service.UserApplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserApplicationServiceImpl extends ServiceImpl<UserApplicationMapper, UserApplicationEntity> implements UserApplicationService {

    @Resource
    private UserApplicationMapper userApplicationMapper;

    @Resource
    private PostApplicationMapper postApplicationMapper;


    @Override
    public List<UserApplicationEntity> getUserApplicationList() {
        List<UserApplicationEntity> userApplicationEntityList = userApplicationMapper.getUserApplicationList();
        return userApplicationEntityList;
    }

    @Override
    public void insertUserApplication(UserApplicationEntity userApplicationEntity) {
        Integer result = userApplicationMapper.insertUserApplication(userApplicationEntity);
        if (result == null || result == 0) {
            throw new JcException("");
        }
    }

    @Override
    public UserApplicationEntity getUserApplicationById(Integer userApplicationId) {
        UserApplicationEntity userApplicationEntity = userApplicationMapper.getUserApplicationById(userApplicationId);
        return userApplicationEntity;
    }

    @Override
    public void removeUserApplicationById(Integer userApplicationId) {
        Integer result = userApplicationMapper.removeUserApplicationById(userApplicationId);
        if (result == null || result == 0) {
            throw new JcException("");
        }
    }

    @Override
    public void updateUserApplication(UserApplicationEntity userApplicationEntity) {
        Integer result = userApplicationMapper.updateById(userApplicationEntity);
        if (result == null || result == 0) {
            throw new JcException("");
        }
    }

    @Override
    public List<UserApplicationPO> getUserApplication(Integer id) {

        List<UserApplicationPO> list = userApplicationMapper.getUserApplication(id);
        return list;
    }

    @Override
    public void changeStatus(UserApplicationEntity userApplicationEntity) {
        Integer row = userApplicationMapper.update(userApplicationEntity, new EntityWrapper<UserApplicationEntity>().eq("id", userApplicationEntity.getId()));

        if(row != 1){
            throw new JcException("审核错误");
        }

    }
}
