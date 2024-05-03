package com.entity.model;

import com.entity.WeiguiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 违规
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WeiguiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 车辆
     */
    private Integer cheliangId;


    /**
     * 违规名称
     */
    private String weiguiName;


    /**
     * 违规编号
     */
    private String weiguiUuidNumber;


    /**
     * 违规照片
     */
    private String weiguiPhoto;


    /**
     * 违规地点
     */
    private String weiguiAddress;


    /**
     * 违规类型
     */
    private Integer weiguiTypes;


    /**
     * 违规介绍
     */
    private String weiguiContent;


    /**
     * 逻辑删除
     */
    private Integer weiguiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
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
	 * 获取：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
