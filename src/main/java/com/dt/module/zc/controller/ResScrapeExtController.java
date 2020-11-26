package com.dt.module.zc.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.busenum.ZcRecycleEnum;
import com.dt.module.cmdb.entity.Res;
import com.dt.module.cmdb.service.IResService;
import com.dt.module.zc.entity.ResScrape;
import com.dt.module.zc.entity.ResScrapeItem;
import com.dt.module.zc.service.IResScrapeItemService;
import com.dt.module.zc.service.IResScrapeService;
import com.dt.module.zc.service.impl.ResScrapeService;
import com.dt.module.zc.service.impl.ZcChangeService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2020-05-28
 */
@Controller
@RequestMapping("/api/zc/resScrape/ext")
public class ResScrapeExtController extends BaseController {

    @Autowired
    IResScrapeItemService ResScrapeItemServiceImpl;

    @Autowired
    ResScrapeService resScrapeService;

    @Autowired
    ZcService zcService;

    @Autowired
    IResScrapeService ResScrapeServiceImpl;

    @Autowired
    IResService ResServiceImpl;

    @Autowired
    ZcChangeService zcChangeService;

    @ResponseBody
    @Acl(info = "根据Id查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectById.do")
    public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
        ResScrape in = ResScrapeServiceImpl.getById(id);
        String uuid = in.getUuid();
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(in, SerializerFeature.WriteDateUseDateFormat));
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t where dr='0' and id in (select resid from res_scrape_item where uuid=? and dr='0')";
        res.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, uuid).toJsonArrayWithJsonObject()));
        return R.SUCCESS_OPER(res);
    }

    @ResponseBody
    @Acl(info = "根据Id查询", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectByBusid.do")
    public R selectByBusid(@RequestParam(value = "busid", required = true, defaultValue = "") String busid) {
        QueryWrapper<ResScrape> qw = new QueryWrapper<ResScrape>();
        qw.and(i -> i.eq("uuid", busid));
        ResScrape in = ResScrapeServiceImpl.getOne(qw);
        String uuid = in.getUuid();
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(in, SerializerFeature.WriteDateUseDateFormat));
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t where dr='0' and id in (select resid from res_scrape_item where uuid=? and dr='0')";
        res.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, uuid).toJsonArrayWithJsonObject()));
        return R.SUCCESS_OPER(res);
    }


    @ResponseBody
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insert.do")
    public R insertOrUpdate(ResScrape entity, String busitimestr, String items) throws ParseException {
        resScrapeService.createBf(entity, busitimestr, items);
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        return resScrapeService.selectList(null, null);
    }

    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/myList.do")
    public R myList(String statustype) {
        return resScrapeService.selectList(this.getUserId(), statustype);
    }

}

