package com.hc.smsm_backer.modules.information.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 资讯信息表
 *
 * @author fenghuang
 * @email
 * @date 2020-01-09 18:07:05
 */
@TableName("information")
@Data
public class InformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		 * 主键
		 */
	@TableId(type = IdType.AUTO)
	private Integer id;
			/**
		 * 资讯类型id
		 */
	private String informationName;
			/**
		 * 资讯图片
		 */
	private String image;
			/**
		 * 资讯点击量
		 */
	private Integer click;
			/**
		 * 资讯详细描述
		 */
	private String text;
			/**
		 * 创建时间
		 */
	private Date createTime;
			/**
		 * 修改时间
		 */
	private Date updateTime;

}
