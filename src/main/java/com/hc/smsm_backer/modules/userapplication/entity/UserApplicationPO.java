package com.hc.smsm_backer.modules.userapplication.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserApplicationPO {

    //指userApplication表的id
    private  Integer id;
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

    /**
     * 岗位编码
     */
    private String postCode;
    /**
     * 岗位名字
     */
    private String postName;
    /**
     * 招聘单位
     */
    private String hireDepartment;
    /**
     * 截止时间
     */
    private Date applicationDeadline;
    /**
     * 岗位职责
     */
    private String postDuty;
    /**
     * 招聘条件
     */
    private String applicationQualifications;
    /**
     * 招聘人数
     */
    private Integer hireAmount;
    /**
     * 专业
     */
    private String major;
    /**
     * 学历要求
     */
    private String educationRequirement;
    /**
     * 年龄
     */
    private String ageRange;
    /**
     * 其他要求
     */
    private String otherRequirement;

    /**
     * 招聘对象
     */
    private String recruitment;

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
    /**
     * 用户手机
     */
    private String userPhone;

}
