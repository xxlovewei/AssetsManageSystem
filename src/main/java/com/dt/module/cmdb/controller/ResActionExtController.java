package com.dt.module.cmdb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.impl.SysUfloProcessService;
import com.dt.module.zc.service.impl.ZcCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2019-12-01
 */
@Controller
@RequestMapping("/api/cmdb/resActionExt")
public class ResActionExtController extends BaseController {

    @Autowired
    ZcCommonService resExtService;

    @Autowired
    ISysUserInfoService SysUserInfoServiceImpl;

    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;

    @ResponseBody
    @Acl(info = "插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/insert.do")
    public R insert(SysProcessData entity, String items) {

        return R.SUCCESS_OPER();
    }

    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectList.do")
    public R selectList(String type, String sdate, String edate) {

        QueryWrapper<SysProcessData> ew = new QueryWrapper<SysProcessData>();
        if (ToolUtil.isNotEmpty(type)) {
            ew.and(i -> i.eq("ptype", type));
        }

        if (ToolUtil.isNotEmpty(sdate)) {
            ew.ge("create_time", sdate);
        }

        if (ToolUtil.isNotEmpty(edate)) {
            ew.le("create_time", edate);
        }

        ew.orderByDesc("create_time");
        return R.SUCCESS_OPER(SysProcessDataServiceImpl.list(ew));
    }

    @ResponseBody
    @Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectById.do")
    public R selectById(String id) {

        SysProcessData r = SysProcessDataServiceImpl.getById(id);
        //String uuid = r.getDuuid();
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(r, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect));
        String sql = "select " + ZcCommonService.resSqlbody
                + " t.*,a.backtime,a.status actitemstatus from res_action_item a,res t where a.resid=t.id and a.dr='0' and actuuid=?";
        //	RcdSet rs = db.query(sql, uuid);
        //	res.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject()));
        return R.SUCCESS_OPER(res);
    }

    // 不用许编辑资产，只能编辑备注，原因
//	@ResponseBody
//	@Acl(info = "插入", value = Acl.ACL_USER)
//	@RequestMapping(value = "/save.do")
//	public R save(ResAction entity) {
//		String status = entity.getSpstatus();
//		if (status.equals(ResActionService.ACT_STATUS_SFA)) {
//			ResActionServiceImpl.updateById(entity);
//		} else {
//			return R.FAILURE("当前状态不允许修改");
//		}
//		return R.SUCCESS_OPER();
//	}

    @ResponseBody
    @Acl(info = "插入", value = Acl.ACL_USER)
    @RequestMapping(value = "/removeById.do")
    public R removeById(String id) {
        SysProcessData r = SysProcessDataServiceImpl.getById(id);

        if (r.getPstatusdtl() == null) {
            SysProcessDataServiceImpl.removeById(id);
        } else {
            if (SysUfloProcessService.P_STATUS_SFA.equals(r.getPstatusdtl())) {
                SysProcessDataServiceImpl.removeById(id);
            } else {
                return R.FAILURE("当前状态不允许删除");
            }
        }

        return R.SUCCESS_OPER();
    }

}
