package com.hc.smsm_backer.modules.userapplication.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.smsm_backer.modules.userapplication.entity.UserApplicationEntity;
import com.hc.smsm_backer.modules.userapplication.entity.UserApplicationPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户岗位申请表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:46:44
 */
public interface UserApplicationMapper extends BaseMapper<UserApplicationEntity> {

    public List<UserApplicationEntity> getUserApplicationList();

    public Integer insertUserApplication(UserApplicationEntity userApplicationEntity);

    public UserApplicationEntity getUserApplicationById(@Param(value = "userApplicationId") Integer userApplicationId);

    public Integer removeUserApplicationById(@Param(value = "userApplicationId") Integer userApplicationId);

    public List<UserApplicationPO> getUserApplication(@Param(value = "id") Integer id);


}
