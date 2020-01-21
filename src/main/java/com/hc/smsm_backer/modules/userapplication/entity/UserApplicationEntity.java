

package com.hc.smsm_backer.modules.userapplication.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户岗位申请表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-11 19:46:44
 */
@TableName("user_application")
@Data
public class UserApplicationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 申请者id
     */
    private Integer userId;
    /**
     * 申请岗位id
     */
    private Integer postApplicationId;
    /**
     *
     */
    private Date createTime;
    /**
     * 支付状态 0.未支付 1.已支付
     */
    private Integer paymentStatus;
    /**
     * 支付id
     */
    private Integer payId;
    /**
     * 审批结果 0.审批中 1.审批通过 2.审批不通过
     */
    private Integer checkResult;

    private String checkReport;

    private String applicantName;

    private Integer applicantGender;

    private String applicantBirth;

    private Integer applicantPoliticalStatus;

    private String applicantHouseholdRegister;

    private Integer applicantMarriageStatus;

    private String applicantIdentityCard;

    private String applicantGraduatedTime;

    private String applicantGraduatedCollege;

    private Integer applicantEducationalBackground;

    private String applicantOccupationalQualification;

    private String applicantMajor;

    private String applicantEnglishLevel;

    private String applicantComputerLevel;

    private String applicantContactAddress;

    private String applicantContactPhone;

    private String applicantApplicationPost;

    private String applicantWorkExprience;

    private String applicantErgentContact;

    private String applicantErgentPhone;

    private String applicantFamilyRelationship;

    private String applicantSignName;

    private String applicantSignTime;

    private String applicantPhotoSrc;

    private String applicantIdentityCardPhoneSrc;

    private String applicantIdentityCardPhoneReverseSrc;

    private String applicantDiplomaSrc;

}

