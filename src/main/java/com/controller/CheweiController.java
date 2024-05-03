
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 车位
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/chewei")
public class CheweiController {
    private static final Logger logger = LoggerFactory.getLogger(CheweiController.class);

    private static final String TABLE_NAME = "chewei";

    @Autowired
    private CheweiService cheweiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CheliangService cheliangService;//车辆
    @Autowired
    private CheweiYuyueService cheweiYuyueService;//车位预订
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private WeiguiService weiguiService;//违规
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private YuangongService yuangongService;//员工
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("员工".equals(role))
            params.put("yuangongId",request.getSession().getAttribute("userId"));
        params.put("cheweiDeleteStart",1);params.put("cheweiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = cheweiService.queryPage(params);

        //字典表数据转换
        List<CheweiView> list =(List<CheweiView>)page.getList();
        for(CheweiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CheweiEntity chewei = cheweiService.selectById(id);
        if(chewei !=null){
            //entity转view
            CheweiView view = new CheweiView();
            BeanUtils.copyProperties( chewei , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody CheweiEntity chewei, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,chewei:{}",this.getClass().getName(),chewei.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<CheweiEntity> queryWrapper = new EntityWrapper<CheweiEntity>()
            .eq("chewei_name", chewei.getCheweiName())
            .eq("chewei_address", chewei.getCheweiAddress())
            .eq("chewei_types", chewei.getCheweiTypes())
            .eq("shangxia_types", chewei.getShangxiaTypes())
            .eq("chewei_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheweiEntity cheweiEntity = cheweiService.selectOne(queryWrapper);
        if(cheweiEntity==null){
            chewei.setShangxiaTypes(1);
            chewei.setCheweiDelete(1);
            chewei.setInsertTime(new Date());
            chewei.setCreateTime(new Date());
            cheweiService.insert(chewei);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CheweiEntity chewei, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,chewei:{}",this.getClass().getName(),chewei.toString());
        CheweiEntity oldCheweiEntity = cheweiService.selectById(chewei.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(chewei.getCheweiPhoto()) || "null".equals(chewei.getCheweiPhoto())){
                chewei.setCheweiPhoto(null);
        }
        if("".equals(chewei.getCheweiContent()) || "null".equals(chewei.getCheweiContent())){
                chewei.setCheweiContent(null);
        }

            cheweiService.updateById(chewei);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<CheweiEntity> oldCheweiList =cheweiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<CheweiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            CheweiEntity cheweiEntity = new CheweiEntity();
            cheweiEntity.setId(id);
            cheweiEntity.setCheweiDelete(2);
            list.add(cheweiEntity);
        }
        if(list != null && list.size() >0){
            cheweiService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<CheweiEntity> cheweiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            CheweiEntity cheweiEntity = new CheweiEntity();
//                            cheweiEntity.setCheweiName(data.get(0));                    //车位名称 要改的
//                            cheweiEntity.setCheweiUuidNumber(data.get(0));                    //车位编号 要改的
//                            cheweiEntity.setCheweiPhoto("");//详情和图片
//                            cheweiEntity.setCheweiAddress(data.get(0));                    //车位地点 要改的
//                            cheweiEntity.setCheweiTingchefei(data.get(0));                    //金额/小时 要改的
//                            cheweiEntity.setCheweiTypes(Integer.valueOf(data.get(0)));   //车位类型 要改的
//                            cheweiEntity.setCheweiContent("");//详情和图片
//                            cheweiEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            cheweiEntity.setCheweiDelete(1);//逻辑删除字段
//                            cheweiEntity.setInsertTime(date);//时间
//                            cheweiEntity.setCreateTime(date);//时间
                            cheweiList.add(cheweiEntity);


                            //把要查询是否重复的字段放入map中
                                //车位编号
                                if(seachFields.containsKey("cheweiUuidNumber")){
                                    List<String> cheweiUuidNumber = seachFields.get("cheweiUuidNumber");
                                    cheweiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> cheweiUuidNumber = new ArrayList<>();
                                    cheweiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("cheweiUuidNumber",cheweiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //车位编号
                        List<CheweiEntity> cheweiEntities_cheweiUuidNumber = cheweiService.selectList(new EntityWrapper<CheweiEntity>().in("chewei_uuid_number", seachFields.get("cheweiUuidNumber")).eq("chewei_delete", 1));
                        if(cheweiEntities_cheweiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CheweiEntity s:cheweiEntities_cheweiUuidNumber){
                                repeatFields.add(s.getCheweiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [车位编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        cheweiService.insertBatch(cheweiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = cheweiService.queryPage(params);

        //字典表数据转换
        List<CheweiView> list =(List<CheweiView>)page.getList();
        for(CheweiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CheweiEntity chewei = cheweiService.selectById(id);
            if(chewei !=null){


                //entity转view
                CheweiView view = new CheweiView();
                BeanUtils.copyProperties( chewei , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody CheweiEntity chewei, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,chewei:{}",this.getClass().getName(),chewei.toString());
        Wrapper<CheweiEntity> queryWrapper = new EntityWrapper<CheweiEntity>()
            .eq("chewei_name", chewei.getCheweiName())
            .eq("chewei_uuid_number", chewei.getCheweiUuidNumber())
            .eq("chewei_address", chewei.getCheweiAddress())
            .eq("chewei_types", chewei.getCheweiTypes())
            .eq("shangxia_types", chewei.getShangxiaTypes())
            .eq("chewei_delete", chewei.getCheweiDelete())
//            .notIn("chewei_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheweiEntity cheweiEntity = cheweiService.selectOne(queryWrapper);
        if(cheweiEntity==null){
            chewei.setCheweiDelete(1);
            chewei.setInsertTime(new Date());
            chewei.setCreateTime(new Date());
        cheweiService.insert(chewei);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

