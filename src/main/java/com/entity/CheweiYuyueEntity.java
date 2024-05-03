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
 * 车位预订
 *
 * @author 
 * @email
 */
@TableName("chewei_yuyue")
public class CheweiYuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CheweiYuyueEntity() {

	}

	public CheweiYuyueEntity(T t) {
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
     * 报名编号
     */
    @ColumnInfo(comment="报名编号",type="varchar(200)")
    @TableField(value = "chewei_yuyue_uuid_number")

    private String cheweiYuyueUuidNumber;


    /**
     * 车位
     */
    @ColumnInfo(comment="车位",type="int(11)")
    @TableField(value = "chewei_id")

    private Integer cheweiId;


    /**
     * 车辆
     */
    @ColumnInfo(comment="车辆",type="int(11)")
    @TableField(value = "cheliang_id")

    private Integer cheliangId;


    /**
     * 报名理由
     */
    @ColumnInfo(comment="报名理由",type="longtext")
    @TableField(value = "chewei_yuyue_text")

    private String cheweiYuyueText;


    /**
     * 车牌
     */
    @ColumnInfo(comment="车牌",type="varchar(200)")
    @TableField(value = "chewei_yuyue_file")

    private String cheweiYuyueFile;


    /**
     * 预计停车时间
     */
    @ColumnInfo(comment="预计停车时间",type="int(11)")
    @TableField(value = "chewei_yuyue_shichang")

    private Integer cheweiYuyueShichang;


    /**
     * 活动报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="活动报名时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 报名状态
     */
    @ColumnInfo(comment="报名状态",type="int(11)")
    @TableField(value = "chewei_yuyue_yesno_types")

    private Integer cheweiYuyueYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "chewei_yuyue_yesno_text")

    private String cheweiYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "chewei_yuyue_shenhe_time")

    private Date cheweiYuyueShenheTime;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="预约时间",type="timestamp")
    @TableField(value = "chewei_yuyue_time")

    private Date cheweiYuyueTime;


    /**
     * 创建时间  listShow
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
	 * 获取：报名编号
	 */
    public String getCheweiYuyueUuidNumber() {
        return cheweiYuyueUuidNumber;
    }
    /**
	 * 设置：报名编号
	 */

    public void setCheweiYuyueUuidNumber(String cheweiYuyueUuidNumber) {
        this.cheweiYuyueUuidNumber = cheweiYuyueUuidNumber;
    }
    /**
	 * 获取：车位
	 */
    public Integer getCheweiId() {
        return cheweiId;
    }
    /**
	 * 设置：车位
	 */

    public void setCheweiId(Integer cheweiId) {
        this.cheweiId = cheweiId;
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
	 * 获取：报名理由
	 */
    public String getCheweiYuyueText() {
        return cheweiYuyueText;
    }
    /**
	 * 设置：报名理由
	 */

    public void setCheweiYuyueText(String cheweiYuyueText) {
        this.cheweiYuyueText = cheweiYuyueText;
    }
    /**
	 * 获取：车牌
	 */
    public String getCheweiYuyueFile() {
        return cheweiYuyueFile;
    }
    /**
	 * 设置：车牌
	 */

    public void setCheweiYuyueFile(String cheweiYuyueFile) {
        this.cheweiYuyueFile = cheweiYuyueFile;
    }
    /**
	 * 获取：预计停车时间
	 */
    public Integer getCheweiYuyueShichang() {
        return cheweiYuyueShichang;
    }
    /**
	 * 设置：预计停车时间
	 */

    public void setCheweiYuyueShichang(Integer cheweiYuyueShichang) {
        this.cheweiYuyueShichang = cheweiYuyueShichang;
    }
    /**
	 * 获取：活动报名时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：活动报名时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：报名状态
	 */
    public Integer getCheweiYuyueYesnoTypes() {
        return cheweiYuyueYesnoTypes;
    }
    /**
	 * 设置：报名状态
	 */

    public void setCheweiYuyueYesnoTypes(Integer cheweiYuyueYesnoTypes) {
        this.cheweiYuyueYesnoTypes = cheweiYuyueYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getCheweiYuyueYesnoText() {
        return cheweiYuyueYesnoText;
    }
    /**
	 * 设置：审核回复
	 */

    public void setCheweiYuyueYesnoText(String cheweiYuyueYesnoText) {
        this.cheweiYuyueYesnoText = cheweiYuyueYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getCheweiYuyueShenheTime() {
        return cheweiYuyueShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setCheweiYuyueShenheTime(Date cheweiYuyueShenheTime) {
        this.cheweiYuyueShenheTime = cheweiYuyueShenheTime;
    }
    /**
	 * 获取：预约时间
	 */
    public Date getCheweiYuyueTime() {
        return cheweiYuyueTime;
    }
    /**
	 * 设置：预约时间
	 */

    public void setCheweiYuyueTime(Date cheweiYuyueTime) {
        this.cheweiYuyueTime = cheweiYuyueTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CheweiYuyue{" +
            ", id=" + id +
            ", cheweiYuyueUuidNumber=" + cheweiYuyueUuidNumber +
            ", cheweiId=" + cheweiId +
            ", cheliangId=" + cheliangId +
            ", cheweiYuyueText=" + cheweiYuyueText +
            ", cheweiYuyueFile=" + cheweiYuyueFile +
            ", cheweiYuyueShichang=" + cheweiYuyueShichang +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", cheweiYuyueYesnoTypes=" + cheweiYuyueYesnoTypes +
            ", cheweiYuyueYesnoText=" + cheweiYuyueYesnoText +
            ", cheweiYuyueShenheTime=" + DateUtil.convertString(cheweiYuyueShenheTime,"yyyy-MM-dd") +
            ", cheweiYuyueTime=" + DateUtil.convertString(cheweiYuyueTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
