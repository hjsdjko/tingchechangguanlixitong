package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.WeiguiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 违规
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("weigui")
public class WeiguiView extends WeiguiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 违规类型的值
	*/
	@ColumnInfo(comment="违规类型的字典表值",type="varchar(200)")
	private String weiguiValue;

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



	public WeiguiView() {

	}

	public WeiguiView(WeiguiEntity weiguiEntity) {
		try {
			BeanUtils.copyProperties(this, weiguiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 违规类型的值
	*/
	public String getWeiguiValue() {
		return weiguiValue;
	}
	/**
	* 设置： 违规类型的值
	*/
	public void setWeiguiValue(String weiguiValue) {
		this.weiguiValue = weiguiValue;
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


	@Override
	public String toString() {
		return "WeiguiView{" +
			", weiguiValue=" + weiguiValue +
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
