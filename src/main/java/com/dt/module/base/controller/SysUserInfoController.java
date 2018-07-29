package com.dt.module.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dt.module.base.entity.SysUserInfo;
import com.dt.module.base.entity.SysUserRole;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.base.service.ISysUserRoleService;

import oracle.net.aso.e;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.R;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dt.core.tool.util.DbUtil;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.tool.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Controller;
import com.dt.core.common.base.BaseController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/api/sysUserInfo")
public class SysUserInfoController extends BaseController {

	@Autowired
	ISysUserInfoService SysUserInfoServiceImpl;

	@ResponseBody
	@Acl(info = "根据Id删除", value = Acl.ACL_DENY)
	@RequestMapping(value = "/deleteById.do")
	public R deleteById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		return R.SUCCESS_OPER(SysUserInfoServiceImpl.deleteById(id));
	}

	@ResponseBody
	@Acl(info = "根据Ids删除", value = Acl.ACL_DENY)
	@RequestMapping(value = "/deleteByIds.do")
	public R deleteByIds(@RequestParam(value = "ids", required = true, defaultValue = "[]") String ids) {
		JSONArray res = JSONArray.parseArray(ids);
		for (int i = 0; i < res.size(); i++) {
			SysUserInfoServiceImpl.deleteById(res.getString(i));
		}
		return R.SUCCESS_OPER();
	}

	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectById.do")
	public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		SysUserInfo r = SysUserInfoServiceImpl.selectById(id);
		return R.SUCCESS_OPER(SysUserInfoServiceImpl.selectById(id));
	}

	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_DENY)
	@RequestMapping(value = "/insert.do")
	public R insert(SysUserInfo entity) {
		return R.SUCCESS_OPER(SysUserInfoServiceImpl.insert(entity));
	}

	@ResponseBody
	@Acl(info = "根据Id更新", value = Acl.ACL_DENY)
	@RequestMapping(value = "/updateById.do")
	public R updateById(SysUserInfo entity) {
		return R.SUCCESS_OPER(SysUserInfoServiceImpl.updateById(entity));
	}

	@ResponseBody
	@Acl(info = "存在则更新,否则插入", value = Acl.ACL_DENY)
	@RequestMapping(value = "/insertOrUpdate.do")
	public R insertOrUpdate(SysUserInfo entity) {
		return R.SUCCESS_OPER(SysUserInfoServiceImpl.insertOrUpdate(entity));
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectList.do")
	public R selectList() {
		return R.SUCCESS_OPER(SysUserInfoServiceImpl.selectList(null));
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/selectByOpenId.do")
	public R selectByOpenId(String open_id) {
		return SysUserInfoServiceImpl.selectByOpenId(open_id);
	}

	@ResponseBody
	@Acl(info = "查询所有,有分页", value = Acl.ACL_DENY)
	@RequestMapping(value = "/selectPage.do")
	public R selectPage(String groupId, String start, String length,
			@RequestParam(value = "pageSize", required = true, defaultValue = "10") String pageSize,
			@RequestParam(value = "pageIndex", required = true, defaultValue = "1") String pageIndex) {
		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}

		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");
		QueryWrapper<SysUserInfo> ew = new QueryWrapper<SysUserInfo>();
		ew.and(b -> b.eq("1", "1").apply(ToolUtil.isNotEmpty(groupId),
				" user_id in (select user_id from sys_user_group_item where dr=0 and group_id='" + groupId + "')", ""));
		IPage<SysUserInfo> pdata = SysUserInfoServiceImpl.selectPage(new Page<SysUserInfo>(pageindex, pagesize), ew);
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", pdata.getTotal());
		retrunObject.put("iTotalDisplayRecords", pdata.getTotal());
		retrunObject.put("data", JSONArray.parseArray(JSON.toJSONString(pdata.getRecords(),
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect)));
		return R.clearAttachDirect(retrunObject);
	}

	@RequestMapping("/queryRoles.do")
	@ResponseBody
	@Acl(info = "查询用户权限", value = Acl.ACL_DENY)
	public R queryRoles(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		JSONArray res = new JSONArray();
		List<HashMap<String, Object>> map = SysUserInfoServiceImpl.listUserRoles(user_id);
		for (int i = 0; i < map.size(); i++) {
			HashMap<String, Object> mapt = map.get(i);
			Iterator<Entry<String, Object>> it = mapt.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = (Entry<String, Object>) it.next();
				if (entry.getKey().equals("ROLE_ID")) {
					res.add(entry.getValue());
				}
			}
		}
		return R.SUCCESS_OPER(res);
	}
	
	
	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_DENY)
	@RequestMapping(value = "/addUser.do")
	public R addUser(SysUserInfo entity) {
		return SysUserInfoServiceImpl.addUser(entity);
	}
	@Autowired
	ISysUserRoleService SysUserRoleServiceImpl;

	@RequestMapping("/changeRoles.do")
	@ResponseBody
	@Acl(info = "修改用户权限", value = Acl.ACL_DENY)
	public R changeRoles(@RequestParam(value = "userIds", required = true, defaultValue = "[]") String userIds,
			@RequestParam(value = "roles", required = true, defaultValue = "[]") String roles) {

		JSONArray user_arr = JSONArray.parseArray(userIds);
		JSONArray roles_arr = JSONArray.parseArray(roles);

		if (user_arr.size() == 0 || roles_arr.size() == 0) {
			return R.FAILURE("请求参数异常");
		}

		for (int i = 0; i < user_arr.size(); i++) {
			String user_id = user_arr.getString(i);
			QueryWrapper<SysUserRole> we = new QueryWrapper<SysUserRole>();
			we.and(b -> b.eq("user_id", user_id));
			SysUserRoleServiceImpl.delete(we);
			for (int j = 0; j < roles_arr.size(); j++) {
				SysUserRole temp = new SysUserRole();
				temp.setUserId(user_id);
				temp.setRoleId(roles_arr.getString(j));
				SysUserRoleServiceImpl.insert(temp);
			}
		}
		return R.SUCCESS_OPER();
	}

}
