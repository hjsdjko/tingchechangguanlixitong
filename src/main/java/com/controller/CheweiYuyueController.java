
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
 * 车位预订
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/cheweiYuyue")
public class CheweiYuyueController {
    private static final Logger logger = LoggerFactory.getLogger(CheweiYuyueController.class);

    private static final String TABLE_NAME = "cheweiYuyue";

    @Autowired
    private CheweiYuyueService cheweiYuyueService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CheliangService cheliangService;//车辆
    @Autowired
    private CheweiService cheweiService;//车位
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
        CommonUtil.checkMap(params);
        PageUtils page = cheweiYuyueService.queryPage(params);

        //字典表数据转换
        List<CheweiYuyueView> list =(List<CheweiYuyueView>)page.getList();
        for(CheweiYuyueView c:list){
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
        CheweiYuyueEntity cheweiYuyue = cheweiYuyueService.selectById(id);
        if(cheweiYuyue !=null){
            //entity转view
            CheweiYuyueView view = new CheweiYuyueView();
            BeanUtils.copyProperties( cheweiYuyue , view );//把实体数据重构到view中
            //级联表 车位
            //级联表
            CheweiEntity chewei = cheweiService.selectById(cheweiYuyue.getCheweiId());
            if(chewei != null){
            BeanUtils.copyProperties( chewei , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setCheweiId(chewei.getId());
            }
            //级联表 车辆
            //级联表
            CheliangEntity cheliang = cheliangService.selectById(cheweiYuyue.getCheliangId());
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
    public R save(@RequestBody CheweiYuyueEntity cheweiYuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,cheweiYuyue:{}",this.getClass().getName(),cheweiYuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<CheweiYuyueEntity> queryWrapper = new EntityWrapper<CheweiYuyueEntity>()
            .eq("chewei_id", cheweiYuyue.getCheweiId())
            .eq("cheliang_id", cheweiYuyue.getCheliangId())
            .eq("chewei_yuyue_shichang", cheweiYuyue.getCheweiYuyueShichang())
            .in("chewei_yuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheweiYuyueEntity cheweiYuyueEntity = cheweiYuyueService.selectOne(queryWrapper);
        if(cheweiYuyueEntity==null){
            cheweiYuyue.setInsertTime(new Date());
            cheweiYuyue.setCheweiYuyueYesnoTypes(1);
            cheweiYuyue.setCreateTime(new Date());
            cheweiYuyueService.insert(cheweiYuyue);
            return R.ok();
        }else {
            if(cheweiYuyueEntity.getCheweiYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(cheweiYuyueEntity.getCheweiYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CheweiYuyueEntity cheweiYuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,cheweiYuyue:{}",this.getClass().getName(),cheweiYuyue.toString());
        CheweiYuyueEntity oldCheweiYuyueEntity = cheweiYuyueService.selectById(cheweiYuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(cheweiYuyue.getCheweiYuyueText()) || "null".equals(cheweiYuyue.getCheweiYuyueText())){
                cheweiYuyue.setCheweiYuyueText(null);
        }
        if("".equals(cheweiYuyue.getCheweiYuyueFile()) || "null".equals(cheweiYuyue.getCheweiYuyueFile())){
                cheweiYuyue.setCheweiYuyueFile(null);
        }
        if("".equals(cheweiYuyue.getCheweiYuyueYesnoText()) || "null".equals(cheweiYuyue.getCheweiYuyueYesnoText())){
                cheweiYuyue.setCheweiYuyueYesnoText(null);
        }

            cheweiYuyueService.updateById(cheweiYuyue);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody CheweiYuyueEntity cheweiYuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,cheweiYuyueEntity:{}",this.getClass().getName(),cheweiYuyueEntity.toString());

        CheweiYuyueEntity oldCheweiYuyue = cheweiYuyueService.selectById(cheweiYuyueEntity.getId());//查询原先数据
        CheweiEntity cheweiEntity = cheweiService.selectById(oldCheweiYuyue.getCheweiId());
        if(cheweiYuyueEntity.getCheweiYuyueYesnoTypes() == 2){//通过
            cheweiEntity.setShangxiaTypes(2);
            cheweiService.updateById(cheweiEntity);
//            cheweiYuyueEntity.setCheweiYuyueTypes();
        }else if(cheweiYuyueEntity.getCheweiYuyueYesnoTypes() == 3){//拒绝
//            cheweiYuyueEntity.setCheweiYuyueTypes();
        }
        cheweiYuyueEntity.setCheweiYuyueShenheTime(new Date());//审核时间
        cheweiYuyueService.updateById(cheweiYuyueEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<CheweiYuyueEntity> oldCheweiYuyueList =cheweiYuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        cheweiYuyueService.deleteBatchIds(Arrays.asList(ids));

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
            List<CheweiYuyueEntity> cheweiYuyueList = new ArrayList<>();//上传的东西
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
                            CheweiYuyueEntity cheweiYuyueEntity = new CheweiYuyueEntity();
//                            cheweiYuyueEntity.setCheweiYuyueUuidNumber(data.get(0));                    //报名编号 要改的
//                            cheweiYuyueEntity.setCheweiId(Integer.valueOf(data.get(0)));   //车位 要改的
//                            cheweiYuyueEntity.setCheliangId(Integer.valueOf(data.get(0)));   //车辆 要改的
//                            cheweiYuyueEntity.setCheweiYuyueText(data.get(0));                    //报名理由 要改的
//                            cheweiYuyueEntity.setCheweiYuyueFile(data.get(0));                    //车牌 要改的
//                            cheweiYuyueEntity.setCheweiYuyueShichang(Integer.valueOf(data.get(0)));   //预计停车时间 要改的
//                            cheweiYuyueEntity.setInsertTime(date);//时间
//                            cheweiYuyueEntity.setCheweiYuyueYesnoTypes(Integer.valueOf(data.get(0)));   //报名状态 要改的
//                            cheweiYuyueEntity.setCheweiYuyueYesnoText(data.get(0));                    //审核回复 要改的
//                            cheweiYuyueEntity.setCheweiYuyueShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            cheweiYuyueEntity.setCheweiYuyueTime(sdf.parse(data.get(0)));          //预约时间 要改的
//                            cheweiYuyueEntity.setCreateTime(date);//时间
                            cheweiYuyueList.add(cheweiYuyueEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("cheweiYuyueUuidNumber")){
                                    List<String> cheweiYuyueUuidNumber = seachFields.get("cheweiYuyueUuidNumber");
                                    cheweiYuyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> cheweiYuyueUuidNumber = new ArrayList<>();
                                    cheweiYuyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("cheweiYuyueUuidNumber",cheweiYuyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<CheweiYuyueEntity> cheweiYuyueEntities_cheweiYuyueUuidNumber = cheweiYuyueService.selectList(new EntityWrapper<CheweiYuyueEntity>().in("chewei_yuyue_uuid_number", seachFields.get("cheweiYuyueUuidNumber")));
                        if(cheweiYuyueEntities_cheweiYuyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(CheweiYuyueEntity s:cheweiYuyueEntities_cheweiYuyueUuidNumber){
                                repeatFields.add(s.getCheweiYuyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        cheweiYuyueService.insertBatch(cheweiYuyueList);
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
        PageUtils page = cheweiYuyueService.queryPage(params);

        //字典表数据转换
        List<CheweiYuyueView> list =(List<CheweiYuyueView>)page.getList();
        for(CheweiYuyueView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CheweiYuyueEntity cheweiYuyue = cheweiYuyueService.selectById(id);
            if(cheweiYuyue !=null){


                //entity转view
                CheweiYuyueView view = new CheweiYuyueView();
                BeanUtils.copyProperties( cheweiYuyue , view );//把实体数据重构到view中

                //级联表
                    CheweiEntity chewei = cheweiService.selectById(cheweiYuyue.getCheweiId());
                if(chewei != null){
                    BeanUtils.copyProperties( chewei , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheweiId(chewei.getId());
                }
                //级联表
                    CheliangEntity cheliang = cheliangService.selectById(cheweiYuyue.getCheliangId());
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
    public R add(@RequestBody CheweiYuyueEntity cheweiYuyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,cheweiYuyue:{}",this.getClass().getName(),cheweiYuyue.toString());
        Wrapper<CheweiYuyueEntity> queryWrapper = new EntityWrapper<CheweiYuyueEntity>()
            .eq("chewei_yuyue_uuid_number", cheweiYuyue.getCheweiYuyueUuidNumber())
            .eq("chewei_id", cheweiYuyue.getCheweiId())
            .eq("cheliang_id", cheweiYuyue.getCheliangId())
            .eq("chewei_yuyue_text", cheweiYuyue.getCheweiYuyueText())
            .eq("chewei_yuyue_shichang", cheweiYuyue.getCheweiYuyueShichang())
            .in("chewei_yuyue_yesno_types", new Integer[]{1,2})
            .eq("chewei_yuyue_yesno_text", cheweiYuyue.getCheweiYuyueYesnoText())
//            .notIn("chewei_yuyue_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        CheweiYuyueEntity cheweiYuyueEntity = cheweiYuyueService.selectOne(queryWrapper);
//        if(cheweiYuyueEntity==null){
            cheweiYuyue.setInsertTime(new Date());
            cheweiYuyue.setCheweiYuyueYesnoTypes(1);
            cheweiYuyue.setCreateTime(new Date());
        cheweiYuyueService.insert(cheweiYuyue);

            return R.ok();
//        }else {
//            if(cheweiYuyueEntity.getCheweiYuyueYesnoTypes()==1)
//                return R.error(511,"有相同的待审核的数据");
//            else if(cheweiYuyueEntity.getCheweiYuyueYesnoTypes()==2)
//                return R.error(511,"有相同的审核通过的数据");
//            else
//                return R.error(511,"表中有相同数据");
//        }
    }

    /**
     * 入库
     */
    @RequestMapping("/ruku")
    public R ruku(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        CheweiYuyueEntity cheweiYuyueEntity = cheweiYuyueService.selectById(id);
        cheweiYuyueEntity.setCheweiYuyueYesnoTypes(4);
        cheweiYuyueService.updateById(cheweiYuyueEntity);
        return R.ok();
    }
    /**
     * 出库
     */
    @RequestMapping("/chuku")
    public R chuku(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        CheweiYuyueEntity cheweiYuyueEntity = cheweiYuyueService.selectById(id);
        cheweiYuyueEntity.setCheweiYuyueYesnoTypes(5);
        cheweiYuyueService.updateById(cheweiYuyueEntity);
        return R.ok();
    }

    /**
     * 缴费
     */
    @RequestMapping("/jiaofei")
    public R jiaofei(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        CheweiYuyueEntity cheweiYuyueEntity = cheweiYuyueService.selectById(id);
        CheweiEntity cheweiEntity = cheweiService.selectById(cheweiYuyueEntity.getCheweiId());
        CheliangEntity cheliangEntity = cheliangService.selectById(cheweiYuyueEntity.getCheliangId());
        YonghuEntity yonghuEntity = yonghuService.selectById(cheliangEntity.getYonghuId());
        Double newMoney = yonghuEntity.getNewMoney();
        Integer cheweiYuyueShichang = cheweiYuyueEntity.getCheweiYuyueShichang();
        Double cheweiTingchefei = cheweiEntity.getCheweiTingchefei();
        double v = cheweiTingchefei * cheweiYuyueShichang;
        if(newMoney<v)
            return R.error("用户余额不足请充值后在缴费");
        yonghuEntity.setNewMoney(newMoney-v);
        cheweiYuyueEntity.setCheweiYuyueYesnoTypes(6);
        cheweiEntity.setShangxiaTypes(1);
        cheweiService.updateById(cheweiEntity);
        cheweiYuyueService.updateById(cheweiYuyueEntity);
        yonghuService.updateById(yonghuEntity);
        return R.ok();
    }


}

