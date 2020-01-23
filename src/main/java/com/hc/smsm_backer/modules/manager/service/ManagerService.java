package com.hc.smsm_backer.modules.manager.service;


import com.baomidou.mybatisplus.service.IService;
import com.hc.smsm_backer.modules.manager.entity.ManagerEntity;


/**
 * 用户表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:16:46
 */
public interface ManagerService extends IService<ManagerEntity> {

    public ManagerEntity login(String loginAccount, String loginPassword);
}

