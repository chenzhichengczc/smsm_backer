package com.hc.smsm_backer.modules.manager.service;


import com.baomidou.mybatisplus.service.IService;
import com.hc.smsm_backer.modules.manager.entity.ManagerEntity;

import java.util.List;


/**
 * 用户表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:16:46
 */
public interface ManagerService extends IService<ManagerEntity> {


    public List<ManagerEntity> managerList(Integer id);

    public void insertManager(ManagerEntity managerEntity);

    public void updateStatus(ManagerEntity managerEntity);

    public void deleteManager(Integer id);

    public void updateManager(ManagerEntity managerEntity);
}

