package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.CheweiYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 车位预订
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("chewei_yuyue")
public class CheweiYuyueView extends CheweiYuyueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 报名状态的值
	*/
	@ColumnInfo(comment="报名状态的字典表值",type="varchar(200)")
	private String cheweiYuyueYesnoValue;

	//级联表 车辆
					 
		/**
		* 车辆 的 用户
		*/
		@ColumnInfo(comment="用户",type="int(11)")
		private Integer cheliangYonghuId;
		/**
		* 车辆名称
		*/

		@ColumnInfo(comment="车辆名称",type="varchar(200)")
		private String cheliangName;
		/**
		* 车辆编号
		*/

		@ColumnInfo(comment="车辆编号",type="varchar(200)")
		private String cheliangUuidNumber;
		/**
		* 车辆照片
		*/

		@ColumnInfo(comment="车辆照片",type="varchar(200)")
		private String cheliangPhoto;
		/**
		* 车辆牌照
		*/

		@ColumnInfo(comment="车辆牌照",type="varchar(200)")
		private String cheliangPaizhao;
		/**
		* 车牌
		*/

		@ColumnInfo(comment="车牌",type="varchar(200)")
		private String cheliangFile;
		/**
		* 车辆类型
		*/
		@ColumnInfo(comment="车辆类型",type="int(11)")
		private Integer cheliangTypes;
			/**
			* 车辆类型的值
			*/
			@ColumnInfo(comment="车辆类型的字典表值",type="varchar(200)")
			private String cheliangValue;
		/**
		* 车辆介绍
		*/

		@ColumnInfo(comment="车辆介绍",type="longtext")
		private String cheliangContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer cheliangDelete;
	//级联表 车位
		/**
		* 车位名称
		*/

		@ColumnInfo(comment="车位名称",type="varchar(200)")
		private String cheweiName;
		/**
		* 车位编号
		*/

		@ColumnInfo(comment="车位编号",type="varchar(200)")
		private String cheweiUuidNumber;
		/**
		* 车位照片
		*/

		@ColumnInfo(comment="车位照片",type="varchar(200)")
		private String cheweiPhoto;
		/**
		* 车位地点
		*/

		@ColumnInfo(comment="车位地点",type="varchar(200)")
		private String cheweiAddress;
		/**
		* 金额/小时
		*/
		@ColumnInfo(comment="金额/小时",type="decimal(10,2)")
		private Double cheweiTingchefei;
		/**
		* 车位类型
		*/
		@ColumnInfo(comment="车位类型",type="int(11)")
		private Integer cheweiTypes;
			/**
			* 车位类型的值
			*/
			@ColumnInfo(comment="车位类型的字典表值",type="varchar(200)")
			private String cheweiValue;
		/**
		* 车位介绍
		*/

		@ColumnInfo(comment="车位介绍",type="longtext")
		private String cheweiContent;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer cheweiDelete;



	public CheweiYuyueView() {

	}

	public CheweiYuyueView(CheweiYuyueEntity cheweiYuyueEntity) {
		try {
			BeanUtils.copyProperties(this, cheweiYuyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 报名状态的值
	*/
	public String getCheweiYuyueYesnoValue() {
		return cheweiYuyueYesnoValue;
	}
	/**
	* 设置： 报名状态的值
	*/
	public void setCheweiYuyueYesnoValue(String cheweiYuyueYesnoValue) {
		this.cheweiYuyueYesnoValue = cheweiYuyueYesnoValue;
	}


	//级联表的get和set 车辆
		/**
		* 获取：车辆 的 用户
		*/
		public Integer getCheliangYonghuId() {
			return cheliangYonghuId;
		}
		/**
		* 设置：车辆 的 用户
		*/
		public void setCheliangYonghuId(Integer cheliangYonghuId) {
			this.cheliangYonghuId = cheliangYonghuId;
		}

		/**
		* 获取： 车辆名称
		*/
		public String getCheliangName() {
			return cheliangName;
		}
		/**
		* 设置： 车辆名称
		*/
		public void setCheliangName(String cheliangName) {
			this.cheliangName = cheliangName;
		}

		/**
		* 获取： 车辆编号
		*/
		public String getCheliangUuidNumber() {
			return cheliangUuidNumber;
		}
		/**
		* 设置： 车辆编号
		*/
		public void setCheliangUuidNumber(String cheliangUuidNumber) {
			this.cheliangUuidNumber = cheliangUuidNumber;
		}

		/**
		* 获取： 车辆照片
		*/
		public String getCheliangPhoto() {
			return cheliangPhoto;
		}
		/**
		* 设置： 车辆照片
		*/
		public void setCheliangPhoto(String cheliangPhoto) {
			this.cheliangPhoto = cheliangPhoto;
		}

		/**
		* 获取： 车辆牌照
		*/
		public String getCheliangPaizhao() {
			return cheliangPaizhao;
		}
		/**
		* 设置： 车辆牌照
		*/
		public void setCheliangPaizhao(String cheliangPaizhao) {
			this.cheliangPaizhao = cheliangPaizhao;
		}

		/**
		* 获取： 车牌
		*/
		public String getCheliangFile() {
			return cheliangFile;
		}
		/**
		* 设置： 车牌
		*/
		public void setCheliangFile(String cheliangFile) {
			this.cheliangFile = cheliangFile;
		}
		/**
		* 获取： 车辆类型
		*/
		public Integer getCheliangTypes() {
			return cheliangTypes;
		}
		/**
		* 设置： 车辆类型
		*/
		public void setCheliangTypes(Integer cheliangTypes) {
			this.cheliangTypes = cheliangTypes;
		}


			/**
			* 获取： 车辆类型的值
			*/
			public String getCheliangValue() {
				return cheliangValue;
			}
			/**
			* 设置： 车辆类型的值
			*/
			public void setCheliangValue(String cheliangValue) {
				this.cheliangValue = cheliangValue;
			}

		/**
		* 获取： 车辆介绍
		*/
		public String getCheliangContent() {
			return cheliangContent;
		}
		/**
		* 设置： 车辆介绍
		*/
		public void setCheliangContent(String cheliangContent) {
			this.cheliangContent = cheliangContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getCheliangDelete() {
			return cheliangDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setCheliangDelete(Integer cheliangDelete) {
			this.cheliangDelete = cheliangDelete;
		}
	//级联表的get和set 车位

		/**
		* 获取： 车位名称
		*/
		public String getCheweiName() {
			return cheweiName;
		}
		/**
		* 设置： 车位名称
		*/
		public void setCheweiName(String cheweiName) {
			this.cheweiName = cheweiName;
		}

		/**
		* 获取： 车位编号
		*/
		public String getCheweiUuidNumber() {
			return cheweiUuidNumber;
		}
		/**
		* 设置： 车位编号
		*/
		public void setCheweiUuidNumber(String cheweiUuidNumber) {
			this.cheweiUuidNumber = cheweiUuidNumber;
		}

		/**
		* 获取： 车位照片
		*/
		public String getCheweiPhoto() {
			return cheweiPhoto;
		}
		/**
		* 设置： 车位照片
		*/
		public void setCheweiPhoto(String cheweiPhoto) {
			this.cheweiPhoto = cheweiPhoto;
		}

		/**
		* 获取： 车位地点
		*/
		public String getCheweiAddress() {
			return cheweiAddress;
		}
		/**
		* 设置： 车位地点
		*/
		public void setCheweiAddress(String cheweiAddress) {
			this.cheweiAddress = cheweiAddress;
		}

		/**
		* 获取： 金额/小时
		*/
		public Double getCheweiTingchefei() {
			return cheweiTingchefei;
		}
		/**
		* 设置： 金额/小时
		*/
		public void setCheweiTingchefei(Double cheweiTingchefei) {
			this.cheweiTingchefei = cheweiTingchefei;
		}
		/**
		* 获取： 车位类型
		*/
		public Integer getCheweiTypes() {
			return cheweiTypes;
		}
		/**
		* 设置： 车位类型
		*/
		public void setCheweiTypes(Integer cheweiTypes) {
			this.cheweiTypes = cheweiTypes;
		}


			/**
			* 获取： 车位类型的值
			*/
			public String getCheweiValue() {
				return cheweiValue;
			}
			/**
			* 设置： 车位类型的值
			*/
			public void setCheweiValue(String cheweiValue) {
				this.cheweiValue = cheweiValue;
			}

		/**
		* 获取： 车位介绍
		*/
		public String getCheweiContent() {
			return cheweiContent;
		}
		/**
		* 设置： 车位介绍
		*/
		public void setCheweiContent(String cheweiContent) {
			this.cheweiContent = cheweiContent;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getCheweiDelete() {
			return cheweiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setCheweiDelete(Integer cheweiDelete) {
			this.cheweiDelete = cheweiDelete;
		}


	@Override
	public String toString() {
		return "CheweiYuyueView{" +
			", cheweiYuyueYesnoValue=" + cheweiYuyueYesnoValue +
			", cheweiName=" + cheweiName +
			", cheweiUuidNumber=" + cheweiUuidNumber +
			", cheweiPhoto=" + cheweiPhoto +
			", cheweiAddress=" + cheweiAddress +
			", cheweiTingchefei=" + cheweiTingchefei +
			", cheweiContent=" + cheweiContent +
			", cheweiDelete=" + cheweiDelete +
			", cheliangName=" + cheliangName +
			", cheliangUuidNumber=" + cheliangUuidNumber +
			", cheliangPhoto=" + cheliangPhoto +
			", cheliangPaizhao=" + cheliangPaizhao +
			", cheliangFile=" + cheliangFile +
			", cheliangContent=" + cheliangContent +
			", cheliangDelete=" + cheliangDelete +
			"} " + super.toString();
	}
}
