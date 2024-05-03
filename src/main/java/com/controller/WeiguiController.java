
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
 * 违规
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/weigui")
public class WeiguiController {
    private static final Logger logger = LoggerFactory.getLogger(WeiguiController.class);

    private static final String TABLE_NAME = "weigui";

    @Autowired
    private WeiguiService weiguiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CheliangService cheliangService;//车辆
    @Autowired
    private CheweiService cheweiService;//车位
    @Autowired
    private CheweiYuyueService cheweiYuyueService;//车位预订
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
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
        params.put("weiguiDeleteStart",1);params.put("weiguiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = weiguiService.queryPage(params);

        //字典表数据转换
        List<WeiguiView> list =(List<WeiguiView>)page.getList();
        for(WeiguiView c:list){
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
        WeiguiEntity weigui = weiguiService.selectById(id);
        if(weigui !=null){
            //entity转view
            WeiguiView view = new WeiguiView();
            BeanUtils.copyProperties( weigui , view );//把实体数据重构到view中
            //级联表 车辆
            //级联表
            CheliangEntity cheliang = cheliangService.selectById(weigui.getCheliangId());
            if(cheliang != null){
            BeanUtils.copyProperties( cheliang , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setCheliangId(cheliang.getId());
            }
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
    public R save(@RequestBody WeiguiEntity weigui, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,weigui:{}",this.getClass().getName(),weigui.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<WeiguiEntity> queryWrapper = new EntityWrapper<WeiguiEntity>()
            .eq("cheliang_id", weigui.getCheliangId())
            .eq("weigui_name", weigui.getWeiguiName())
            .eq("weigui_address", weigui.getWeiguiAddress())
            .eq("weigui_types", weigui.getWeiguiTypes())
            .eq("weigui_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeiguiEntity weiguiEntity = weiguiService.selectOne(queryWrapper);
        if(weiguiEntity==null){
            weigui.setWeiguiDelete(1);
            weigui.setInsertTime(new Date());
            weigui.setCreateTime(new Date());
            weiguiService.insert(weigui);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WeiguiEntity weigui, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,weigui:{}",this.getClass().getName(),weigui.toString());
        WeiguiEntity oldWeiguiEntity = weiguiService.selectById(weigui.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(weigui.getWeiguiPhoto()) || "null".equals(weigui.getWeiguiPhoto())){
                weigui.setWeiguiPhoto(null);
        }
        if("".equals(weigui.getWeiguiContent()) || "null".equals(weigui.getWeiguiContent())){
                weigui.setWeiguiContent(null);
        }

            weiguiService.updateById(weigui);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<WeiguiEntity> oldWeiguiList =weiguiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<WeiguiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            WeiguiEntity weiguiEntity = new WeiguiEntity();
            weiguiEntity.setId(id);
            weiguiEntity.setWeiguiDelete(2);
            list.add(weiguiEntity);
        }
        if(list != null && list.size() >0){
            weiguiService.updateBatchById(list);
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
            List<WeiguiEntity> weiguiList = new ArrayList<>();//上传的东西
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
                            WeiguiEntity weiguiEntity = new WeiguiEntity();
//                            weiguiEntity.setCheliangId(Integer.valueOf(data.get(0)));   //车辆 要改的
//                            weiguiEntity.setWeiguiName(data.get(0));                    //违规名称 要改的
//                            weiguiEntity.setWeiguiUuidNumber(data.get(0));                    //违规编号 要改的
//                            weiguiEntity.setWeiguiPhoto("");//详情和图片
//                            weiguiEntity.setWeiguiAddress(data.get(0));                    //违规地点 要改的
//                            weiguiEntity.setWeiguiTypes(Integer.valueOf(data.get(0)));   //违规类型 要改的
//                            weiguiEntity.setWeiguiContent("");//详情和图片
//                            weiguiEntity.setWeiguiDelete(1);//逻辑删除字段
//                            weiguiEntity.setInsertTime(date);//时间
//                            weiguiEntity.setCreateTime(date);//时间
                            weiguiList.add(weiguiEntity);


                            //把要查询是否重复的字段放入map中
                                //违规编号
                                if(seachFields.containsKey("weiguiUuidNumber")){
                                    List<String> weiguiUuidNumber = seachFields.get("weiguiUuidNumber");
                                    weiguiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> weiguiUuidNumber = new ArrayList<>();
                                    weiguiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("weiguiUuidNumber",weiguiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //违规编号
                        List<WeiguiEntity> weiguiEntities_weiguiUuidNumber = weiguiService.selectList(new EntityWrapper<WeiguiEntity>().in("weigui_uuid_number", seachFields.get("weiguiUuidNumber")).eq("weigui_delete", 1));
                        if(weiguiEntities_weiguiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WeiguiEntity s:weiguiEntities_weiguiUuidNumber){
                                repeatFields.add(s.getWeiguiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [违规编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        weiguiService.insertBatch(weiguiList);
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
        PageUtils page = weiguiService.queryPage(params);

        //字典表数据转换
        List<WeiguiView> list =(List<WeiguiView>)page.getList();
        for(WeiguiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WeiguiEntity weigui = weiguiService.selectById(id);
            if(weigui !=null){


                //entity转view
                WeiguiView view = new WeiguiView();
                BeanUtils.copyProperties( weigui , view );//把实体数据重构到view中

                //级联表
                    CheliangEntity cheliang = cheliangService.selectById(weigui.getCheliangId());
                if(cheliang != null){
                    BeanUtils.copyProperties( cheliang , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheliangId(cheliang.getId());
                }
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
    public R add(@RequestBody WeiguiEntity weigui, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,weigui:{}",this.getClass().getName(),weigui.toString());
        Wrapper<WeiguiEntity> queryWrapper = new EntityWrapper<WeiguiEntity>()
            .eq("cheliang_id", weigui.getCheliangId())
            .eq("weigui_name", weigui.getWeiguiName())
            .eq("weigui_uuid_number", weigui.getWeiguiUuidNumber())
            .eq("weigui_address", weigui.getWeiguiAddress())
            .eq("weigui_types", weigui.getWeiguiTypes())
            .eq("weigui_delete", weigui.getWeiguiDelete())
//            .notIn("weigui_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeiguiEntity weiguiEntity = weiguiService.selectOne(queryWrapper);
        if(weiguiEntity==null){
            weigui.setWeiguiDelete(1);
            weigui.setInsertTime(new Date());
            weigui.setCreateTime(new Date());
        weiguiService.insert(weigui);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

