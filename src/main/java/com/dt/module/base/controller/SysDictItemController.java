package com.dt.module.base.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.dt.module.base.entity.SysDictItem;
import com.dt.module.base.service.ISysDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.R;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.tool.util.DbUtil;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.tool.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Controller;
import com.dt.core.common.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/api/sysDictItem")
public class SysDictItemController extends BaseController {


	@Autowired
	ISysDictItemService SysDictItemServiceImpl;


	@ResponseBody
	@Acl(info = "根据Id删除", value = Acl.ACL_DENY)
	@RequestMapping(value = "/deleteById.do")
	public R deleteById(String id) {
		return R.SUCCESS_OPER(SysDictItemServiceImpl.deleteById(id));
	}

	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectById.do")
	public R selectById(String id) {
		return R.SUCCESS_OPER(SysDictItemServiceImpl.selectById(id));
	}

	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_DENY)
	@RequestMapping(value = "/insert.do")
	public R insert(SysDictItem entity) {
		return R.SUCCESS_OPER(SysDictItemServiceImpl.insert(entity));
	}

	@ResponseBody
	@Acl(info = "根据Id更新", value = Acl.ACL_DENY)
	@RequestMapping(value = "/updateById.do")
	public R updateById(SysDictItem entity) {
		return R.SUCCESS_OPER(SysDictItemServiceImpl.updateById(entity));
	}

	@ResponseBody
	@Acl(info = "存在则更新,否则插入", value = Acl.ACL_DENY)
	@RequestMapping(value = "/insertOrUpdate.do")
	public R insertOrUpdate(SysDictItem entity) {
		return R.SUCCESS_OPER(SysDictItemServiceImpl.insertOrUpdate(entity));
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectList.do")
	public R selectList() {
		return R.SUCCESS_OPER(SysDictItemServiceImpl.selectList(null));
	}

	@ResponseBody
	@Acl(info = "根据Dict查询", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectDictItemByDict.do")
	public R selectDictItemByDict(String dictId) {
		return R.SUCCESS_OPER(SysDictItemServiceImpl.selectDictItemByDict(dictId));
	}
	
	
	@ResponseBody
	@Acl(info = "查询所有,有分页", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectPage.do")
	public R selectPage(String start, String length, String pageSize, String pageIndex) {
		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");
		QueryWrapper<SysDictItem> ew = new QueryWrapper<SysDictItem>();
		//ew.and(i -> i.eq("user_id", getUserId()).apply(pagesize>10, "rtime>sysdate-1","23"));
		IPage<SysDictItem> pdata = SysDictItemServiceImpl.selectPage(new Page<SysDictItem>(pageindex, pagesize), ew);
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", pdata.getTotal());
		retrunObject.put("iTotalDisplayRecords", pdata.getTotal());
		retrunObject.put("data", JSONArray.parseArray(JSON.toJSONString(pdata.getRecords(),SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect)));
		return R.clearAttachDirect(retrunObject);
	}


}

