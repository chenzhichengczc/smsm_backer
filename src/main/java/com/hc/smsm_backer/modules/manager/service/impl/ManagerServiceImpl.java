package com.hc.smsm_backer.modules.manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.smsm_backer.common.exception.JcException;
import com.hc.smsm_backer.modules.manager.entity.ManagerEntity;
import com.hc.smsm_backer.modules.manager.mapper.ManagerMapper;
import com.hc.smsm_backer.modules.manager.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, ManagerEntity> implements ManagerService {

    @Resource
    private ManagerMapper managerMapper;


    @Override
    public ManagerEntity login(String loginAccount, String loginPassword) {

        List<ManagerEntity> login_account = managerMapper.selectList(new EntityWrapper<ManagerEntity>().eq("login_account", loginAccount));

        ManagerEntity managerEntity = null;

        if(login_account == null){
            throw new JcException("账号不存在");
        }else{
            managerEntity = login_account.get(0);
            if(!(managerEntity.getLoginPassword().equals(loginPassword))){
                throw new JcException("账号或密码错误");
            }
        }

        return managerEntity;
    }
}
