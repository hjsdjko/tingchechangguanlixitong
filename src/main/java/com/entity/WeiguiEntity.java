package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 违规
 *
 * @author 
 * @email
 */
@TableName("weigui")
public class WeiguiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WeiguiEntity() {

	}

	public WeiguiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 车辆
     */
    @ColumnInfo(comment="车辆",type="int(11)")
    @TableField(value = "cheliang_id")

    private Integer cheliangId;


    /**
     * 违规名称
     */
    @ColumnInfo(comment="违规名称",type="varchar(200)")
    @TableField(value = "weigui_name")

    private String weiguiName;


    /**
     * 违规编号
     */
    @ColumnInfo(comment="违规编号",type="varchar(200)")
    @TableField(value = "weigui_uuid_number")

    private String weiguiUuidNumber;


    /**
     * 违规照片
     */
    @ColumnInfo(comment="违规照片",type="varchar(200)")
    @TableField(value = "weigui_photo")

    private String weiguiPhoto;


    /**
     * 违规地点
     */
    @ColumnInfo(comment="违规地点",type="varchar(200)")
    @TableField(value = "weigui_address")

    private String weiguiAddress;


    /**
     * 违规类型
     */
    @ColumnInfo(comment="违规类型",type="int(11)")
    @TableField(value = "weigui_types")

    private Integer weiguiTypes;


    /**
     * 违规介绍
     */
    @ColumnInfo(comment="违规介绍",type="longtext")
    @TableField(value = "weigui_content")

    private String weiguiContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "weigui_delete")

    private Integer weiguiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间   listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：车辆
	 */
    public Integer getCheliangId() {
        return cheliangId;
    }
    /**
	 * 设置：车辆
	 */

    public void setCheliangId(Integer cheliangId) {
        this.cheliangId = cheliangId;
    }
    /**
	 * 获取：违规名称
	 */
    public String getWeiguiName() {
        return weiguiName;
    }
    /**
	 * 设置：违规名称
	 */

    public void setWeiguiName(String weiguiName) {
        this.weiguiName = weiguiName;
    }
    /**
	 * 获取：违规编号
	 */
    public String getWeiguiUuidNumber() {
        return weiguiUuidNumber;
    }
    /**
	 * 设置：违规编号
	 */

    public void setWeiguiUuidNumber(String weiguiUuidNumber) {
        this.weiguiUuidNumber = weiguiUuidNumber;
    }
    /**
	 * 获取：违规照片
	 */
    public String getWeiguiPhoto() {
        return weiguiPhoto;
    }
    /**
	 * 设置：违规照片
	 */

    public void setWeiguiPhoto(String weiguiPhoto) {
        this.weiguiPhoto = weiguiPhoto;
    }
    /**
	 * 获取：违规地点
	 */
    public String getWeiguiAddress() {
        return weiguiAddress;
    }
    /**
	 * 设置：违规地点
	 */

    public void setWeiguiAddress(String weiguiAddress) {
        this.weiguiAddress = weiguiAddress;
    }
    /**
	 * 获取：违规类型
	 */
    public Integer getWeiguiTypes() {
        return weiguiTypes;
    }
    /**
	 * 设置：违规类型
	 */

    public void setWeiguiTypes(Integer weiguiTypes) {
        this.weiguiTypes = weiguiTypes;
    }
    /**
	 * 获取：违规介绍
	 */
    public String getWeiguiContent() {
        return weiguiContent;
    }
    /**
	 * 设置：违规介绍
	 */

    public void setWeiguiContent(String weiguiContent) {
        this.weiguiContent = weiguiContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getWeiguiDelete() {
        return weiguiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setWeiguiDelete(Integer weiguiDelete) {
        this.weiguiDelete = weiguiDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间   listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间   listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Weigui{" +
            ", id=" + id +
            ", cheliangId=" + cheliangId +
            ", weiguiName=" + weiguiName +
            ", weiguiUuidNumber=" + weiguiUuidNumber +
            ", weiguiPhoto=" + weiguiPhoto +
            ", weiguiAddress=" + weiguiAddress +
            ", weiguiTypes=" + weiguiTypes +
            ", weiguiContent=" + weiguiContent +
            ", weiguiDelete=" + weiguiDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
