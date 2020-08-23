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
    @Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insert.do")
    public R insertOrUpdate(ResScrape entity, String busitimestr, String items) throws ParseException {

        ArrayList<ResScrapeItem> cols = new ArrayList<ResScrapeItem>();
        String uuid = zcService.createUuid(ZcCommonService.UUID_BF);
        entity.setUuid(uuid);
        entity.setStatus(ResScrapeService.STATUS_SUCCESS);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(busitimestr);
        entity.setBusidate(date);
        if (ToolUtil.isEmpty(entity.getProcessuserid())) {
            entity.setProcessuserid(this.getUserId());
        }
        if (ToolUtil.isEmpty(entity.getProcessusername())) {
            entity.setProcessusername(this.getUserName());
        }
        JSONArray itemsarr = JSONArray.parseArray(items);
        for (int i = 0; i < itemsarr.size(); i++) {
            UpdateWrapper<Res> ups = new UpdateWrapper<Res>();
            ups.set("recycle", ZcRecycleEnum.RECYCLE_SCRAP.getValue());
            ups.set("isscrap", "1");
            ups.set("uuidbf", uuid);
            ups.set("scrapdate", busitimestr);
            ups.eq("id", itemsarr.getJSONObject(i).getString("id"));
            ResServiceImpl.update(ups);
            ResScrapeItem e = new ResScrapeItem();
            e.setUuid(uuid);
            e.setResid(itemsarr.getJSONObject(i).getString("id"));
            e.setPrestatus(itemsarr.getJSONObject(i).getString("status"));
            cols.add(e);
        }
        entity.setCnt(new BigDecimal(cols.size()));
        ResScrapeItemServiceImpl.saveBatch(cols);
        ResScrapeServiceImpl.save(entity);

        zcChangeService.zcSureChange(uuid, ZcCommonService.ZC_BUS_TYPE_BF);
        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList() {
        QueryWrapper<ResScrape> ew = new QueryWrapper<ResScrape>();
        ew.orderByDesc("create_time");
        return R.SUCCESS_OPER(ResScrapeServiceImpl.list(ew));
    }

}

