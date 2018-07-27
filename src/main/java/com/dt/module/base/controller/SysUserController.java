package com.dt.module.base.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.net.HttpClient;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
 
import com.dt.module.base.service.SysUserService;
import com.dt.module.base.service.UserService;

@Controller
@RequestMapping("/api")
public class SysUserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private SysUserService sysUserService;
 
	@ResponseBody
	@Acl(info = "我的雅戈尔上班考勤", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/user/queryKqInYoungr.do")
	public R queryFundTix(String uid) {
		if (ToolUtil.isEmpty(uid)) {
			return R.FAILURE();
		}
		HttpClient v = new HttpClient();
		String url = "http://mayorwx.youngor.com/mayormp/query/getUserKq.do";
		Rcd rs = db.uniqueRecord("select * from sys_params where id='dk_url'");
		if (rs != null) {
			url = rs.getString("value");
		}
		BaseCommon.print("url:" + url);
		String body = v.fetchDataByUTF8(url + "?uid=" + uid);
		JSONArray bodyarr = JSONArray.parseArray(body);
		return R.SUCCESS_OPER(bodyarr);
	}

	 

	@RequestMapping(value = "/user/userSave.do")
	@ResponseBody
	@Acl(info = "保存用户")
	@Transactional
	public R userSave() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String user_id = ps.getString("user_id");
		if (ToolUtil.isEmpty(user_id)) {
			return sysUserService.addSysUser(ps);
		} else {
			return sysUserService.updateSysUser(ps);
		}
	}

	@RequestMapping(value = "/user/userDelete.do")
	@ResponseBody
	@Acl(info = "删除用户")
	@Transactional
	public R userDelete(String user_ids) {
		if (ToolUtil.isEmpty(user_ids)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		JSONArray ids_arr = JSONArray.parseArray(user_ids);
		if (ids_arr == null || ids_arr.size() == 0) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		boolean flag = true;
		for (int i = 0; i < ids_arr.size(); i++) {
			if (!sysUserService.deleteSysUser(ids_arr.getString(i)).isSuccess()) {
				flag = false;
			}
		}
		if (flag) {
			return R.SUCCESS_OPER();
		} else {
			return R.FAILURE("部分用户删除失败,请留意");
		}
	}

	@RequestMapping("/user/userQueryById.do")
	@ResponseBody
	@Acl(info = "根据Id查询用户", value = Acl.ACL_USER)
	public R userQueryById(String user_id) {
		return sysUserService.queryUserById(user_id);
	}

	@RequestMapping("/user/queryRole.do")
	@ResponseBody
	@Acl(info = "查询用户权限", value = Acl.ACL_USER)
	public R queryRole(String user_id) {
		if (ToolUtil.isEmpty(user_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		JSONArray res = new JSONArray();
		HashMap<String, String> map = userService.queryUserRole(user_id);
		Iterator<Entry<String, String>> i = map.entrySet().iterator();
		while (i.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) i.next();
			res.add(entry.getKey().toString());
		}
		return R.SUCCESS_OPER(res);
	}

	@RequestMapping("/user/userRoleChange.do")
	@ResponseBody
	@Acl(info = "修改用户权限")
	@Transactional
	public R userRoleChange() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return userService.changeUserRole(ps);
	}

	@RequestMapping("/user/userQueryByGroup.do")
	@ResponseBody
	@Acl(info = "查询用户组")
	public R userQueryByGroup(String group_id, String start, String length, String pageSize, String pageIndex) {
		JSONObject respar = DbUtil.formatPageParameter(start, length, pageSize, pageIndex);
		if (ToolUtil.isEmpty(respar)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		int pagesize = respar.getIntValue("pagesize");
		int pageindex = respar.getIntValue("pageindex");
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();

		R rsdata = userService.queryUserByGroup(ps, group_id, null, pagesize, pageindex);
		int count = userService.queryUserByGroupCount(ps, group_id, null);
		JSONArray data = rsdata.queryDataToJSONArray();
		JSONObject retrunObject = new JSONObject();
		retrunObject.put("iTotalRecords", count);
		retrunObject.put("iTotalDisplayRecords", count);
		retrunObject.put("data", data);
		return R.clearAttachDirect(retrunObject);
	}

	@RequestMapping("/user/getUserMenus.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "查询用户菜单")
	public R getUserMenus(String menu_id) {
		if (ToolUtil.isEmpty(menu_id)) {
			return R.FAILURE_REQ_PARAM_ERROR();
		}
		return R.SUCCESS_OPER(userService.getMenuTree(getUserId(), menu_id));
	}

	@RequestMapping("/user/saveCommonSetting.do")
	@ResponseBody
	@Acl(value = Acl.ACL_DENY, info = "修改用户设置")
	public R saveCommonSetting() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return sysUserService.saveCommonSettings(getUserId(), ps);
	}

	@RequestMapping("/user/changePwd.do")
	@ResponseBody
	@Acl(value = Acl.ACL_USER, info = "修改用户密码")
	public R changePwd() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		return userService.changeUserPwd(ps.getString("opwd", ""), ps.getString("npwd", ""), getUserId());
	}

	public static void main(String[] args) {
		HttpClient c = new HttpClient();
		String body = c.fetchData("http://www.baidu.com", "UTF-8");
		System.out.println(body);
	}
}
