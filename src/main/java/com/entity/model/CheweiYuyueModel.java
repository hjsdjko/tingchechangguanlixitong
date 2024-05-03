package com.entity.model;

import com.entity.CheweiYuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 车位预订
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class CheweiYuyueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名编号
     */
    private String cheweiYuyueUuidNumber;


    /**
     * 车位
     */
    private Integer cheweiId;


    /**
     * 车辆
     */
    private Integer cheliangId;


    /**
     * 报名理由
     */
    private String cheweiYuyueText;


    /**
     * 车牌
     */
    private String cheweiYuyueFile;


    /**
     * 预计停车时间
     */
    private Integer cheweiYuyueShichang;


    /**
     * 活动报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 报名状态
     */
    private Integer cheweiYuyueYesnoTypes;


    /**
     * 审核回复
     */
    private String cheweiYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date cheweiYuyueShenheTime;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date cheweiYuyueTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
