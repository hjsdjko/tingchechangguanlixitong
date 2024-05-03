package com.dao;

import com.entity.WeiguiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WeiguiView;

/**
 * 违规 Dao 接口
 *
 * @author 
 */
public interface WeiguiDao extends BaseMapper<WeiguiEntity> {

   List<WeiguiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
