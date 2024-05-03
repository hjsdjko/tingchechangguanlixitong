package com.entity.vo;

import com.entity.WeiguiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 违规
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("weigui")
public class WeiguiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 车辆
     */

    @TableField(value = "cheliang_id")
    private Integer cheliangId;


    /**
     * 违规名称
     */

    @TableField(value = "weigui_name")
    private String weiguiName;


    /**
     * 违规编号
     */

    @TableField(value = "weigui_uuid_number")
    private String weiguiUuidNumber;


    /**
     * 违规照片
     */

    @TableField(value = "weigui_photo")
    private String weiguiPhoto;


    /**
     * 违规地点
     */

    @TableField(value = "weigui_address")
    private String weiguiAddress;


    /**
     * 违规类型
     */

    @TableField(value = "weigui_types")
    private Integer weiguiTypes;


    /**
     * 违规介绍
     */

    @TableField(value = "weigui_content")
    private String weiguiContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "weigui_delete")
    private Integer weiguiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：车辆
	 */
    public Integer getCheliangId() {
        return cheliangId;
    }


    /**
	 * 获取：车辆
	 */

    public void setCheliangId(Integer cheliangId) {
        this.cheliangId = cheliangId;
    }
    /**
	 * 设置：违规名称
	 */
    public String getWeiguiName() {
        return weiguiName;
    }


    /**
	 * 获取：违规名称
	 */

    public void setWeiguiName(String weiguiName) {
        this.weiguiName = weiguiName;
    }
    /**
	 * 设置：违规编号
	 */
    public String getWeiguiUuidNumber() {
        return weiguiUuidNumber;
    }


    /**
	 * 获取：违规编号
	 */

    public void setWeiguiUuidNumber(String weiguiUuidNumber) {
        this.weiguiUuidNumber = weiguiUuidNumber;
    }
    /**
	 * 设置：违规照片
	 */
    public String getWeiguiPhoto() {
        return weiguiPhoto;
    }


    /**
	 * 获取：违规照片
	 */

    public void setWeiguiPhoto(String weiguiPhoto) {
        this.weiguiPhoto = weiguiPhoto;
    }
    /**
	 * 设置：违规地点
	 */
    public String getWeiguiAddress() {
        return weiguiAddress;
    }


    /**
	 * 获取：违规地点
	 */

    public void setWeiguiAddress(String weiguiAddress) {
        this.weiguiAddress = weiguiAddress;
    }
    /**
	 * 设置：违规类型
	 */
    public Integer getWeiguiTypes() {
        return weiguiTypes;
    }


    /**
	 * 获取：违规类型
	 */

    public void setWeiguiTypes(Integer weiguiTypes) {
        this.weiguiTypes = weiguiTypes;
    }
    /**
	 * 设置：违规介绍
	 */
    public String getWeiguiContent() {
        return weiguiContent;
    }


    /**
	 * 获取：违规介绍
	 */

    public void setWeiguiContent(String weiguiContent) {
        this.weiguiContent = weiguiContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getWeiguiDelete() {
        return weiguiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setWeiguiDelete(Integer weiguiDelete) {
        this.weiguiDelete = weiguiDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
