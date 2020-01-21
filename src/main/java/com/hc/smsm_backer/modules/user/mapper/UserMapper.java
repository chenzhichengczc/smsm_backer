package com.hc.smsm_backer.modules.user.mapper;

import com.hc.smsm_backer.modules.user.entity.UserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * 用户表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:16:46
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 获得密码
     * @param username 用户名
     */
    public UserEntity getUser(@Param(value = "username") String username);

    public Integer findByUname(@Param(value = "uname") String uname);

}
