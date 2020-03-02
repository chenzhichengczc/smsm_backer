package com.hc.smsm_backer.modules.applicant.service;

import com.baomidou.mybatisplus.service.IService;
import com.hc.smsm_backer.modules.applicant.entity.UserEntity;

import java.util.List;


/** 用户表
*
* @author fenghuang
* @email
* @date 2020-01-11 19:16:46
*/

public interface UserService extends IService<UserEntity> {

   public List<UserEntity> getUser(Integer id);

}

