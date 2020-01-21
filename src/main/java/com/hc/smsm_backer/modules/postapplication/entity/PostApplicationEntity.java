package com.hc.smsm_backer.modules.postapplication.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import lombok.Data;
import java.util.Date;
/**
 * 申请岗位表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-09 19:05:56
 */
@TableName("post_application")
@Data
public class PostApplicationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
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
	private Integer age;
		/**
	 * 其他要求
	 */
	private String otherRequirement;
		/**
	 * 创建时间
	 */
	private Date createTime;
		/**
	 * 更改时间
	 */
	private Date updateTime;
		/**
	 * 招聘对象
	 */
	private String recruitment;

}
