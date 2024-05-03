package com.dao;

import com.entity.CheweiYuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CheweiYuyueView;

/**
 * 车位预订 Dao 接口
 *
 * @author 
 */
public interface CheweiYuyueDao extends BaseMapper<CheweiYuyueEntity> {

   List<CheweiYuyueView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
