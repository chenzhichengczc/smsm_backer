package com.hc.smsm_backer.modules.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:16:46
 */
@TableName("user")
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户手机
     */
    private String userPhone;
    /**
     * 用户密码
     */
    private String userAcountPassword;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户账号名
     */
    private String userAcountName;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户电子邮箱
     */
    private String userEmail;
    /**
     * 性别 0.男 1.女
     */
    private Integer gender;
    /**
     * 出生日期
     */
    private Date birthTime;
    /**
     * 身份证
     */
    private String identityCard;
    /**
     * 毕业院校
     */
    private String graduatedSchool;

}
