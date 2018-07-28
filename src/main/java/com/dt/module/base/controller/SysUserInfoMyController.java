package com.dt.module.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dt.module.base.entity.SysUserInfo;
import com.dt.module.base.service.ISysUserInfoService;
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
 * 前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/api/sysUserInfo/my")
public class SysUserInfoMyController extends BaseController {

	@Autowired
	ISysUserInfoService SysUserInfoServiceImpl;

	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_USER)
	@RequestMapping(value = "/select.do")
	public R selectById(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		return R.SUCCESS_OPER(SysUserInfoServiceImpl.selectById(id));
	}

	@ResponseBody
	@Acl(info = "根据Id更新", value = Acl.ACL_USER)
	@RequestMapping(value = "/update.do")
	public R updateById(SysUserInfo entity) {
		return R.SUCCESS_OPER(SysUserInfoServiceImpl.updateById(entity));
	}

	@ResponseBody
	@Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insertOrUpdate.do")
	public R insertOrUpdate(SysUserInfo entity) {
		return R.SUCCESS_OPER(SysUserInfoServiceImpl.insertOrUpdate(entity));
	}

	@ResponseBody
	@Acl(info = "修改密码", value = Acl.ACL_USER)
	@RequestMapping(value = "/modifypwd.do")
	public R modifypwd(@RequestParam(value = "oldpwd", required = true, defaultValue = "") String oldpwd,
			@RequestParam(value = "pwd", required = true, defaultValue = "") String pwd) {
		SysUserInfo user = SysUserInfoServiceImpl.selectById(this.getUserId());
		if (oldpwd.equals(user.getPwd())) {
			return SysUserInfoServiceImpl.modifyPassword(this.getUserId(), pwd);
		} else {
			return R.FAILURE("请输入正确的密码");
		}

	}
}
