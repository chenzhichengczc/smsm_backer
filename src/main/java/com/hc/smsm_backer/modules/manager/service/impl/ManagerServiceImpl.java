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
    public List<ManagerEntity> managerList(Integer id) {
        List<ManagerEntity> managerEntities = null;
        if (id == null) {
            managerEntities = managerMapper.selectList(new EntityWrapper<ManagerEntity>());
        } else {
            managerEntities = managerMapper.selectList(new EntityWrapper<ManagerEntity>().eq("id", id));
        }
        return managerEntities;
    }

    @Override
    public void insertManager(ManagerEntity managerEntity) {

        List<ManagerEntity> login_account = managerMapper.selectList(new EntityWrapper<ManagerEntity>().eq("login_account", managerEntity.getLoginAccount()));

        if (login_account.size() != 0) {
            throw new JcException("账号已存在，请重新输入");
        }

        Integer insert = managerMapper.insert(managerEntity);

        if (insert != 1) {
            throw new JcException("管理员插入失败");
        }
    }

    @Override
    public void updateStatus(ManagerEntity managerEntity) {
        Integer row = managerMapper.update(managerEntity, new EntityWrapper<ManagerEntity>().eq("id", managerEntity.getId()));

        if (row != 1) {
            throw new JcException("更新状态失败");
        }

    }

    @Override
    public void deleteManager(Integer id) {
        Integer row = managerMapper.delete(new EntityWrapper<ManagerEntity>().eq("id", id));

        if (row != 1) {
            throw new JcException("删除信息失败");
        }
    }

    @Override
    public void updateManager(ManagerEntity managerEntity) {
        Integer row = managerMapper.update(managerEntity,new EntityWrapper<ManagerEntity>().eq("id",managerEntity.getId()));
        if (row != 1) {
            throw new JcException("信息更新失败");
        }
    }
}
