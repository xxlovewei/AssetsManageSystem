package com.dt.module.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.module.base.entity.SysRoleInfo;
import com.dt.module.base.service.ISysRoleInfoService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/api/sysRoleInfo")
public class SysRoleInfoController extends BaseController {


	@Autowired
	ISysRoleInfoService SysRoleInfoServiceImpl;


	@ResponseBody
	@Acl(info = "根据Id删除", value = Acl.ACL_DENY)
	@RequestMapping(value = "/deleteById.do")
	public R deleteById(String id) {
		System.out.println(SysRoleInfoServiceImpl.isUsed(id));
		if(SysRoleInfoServiceImpl.isUsed(id)>0) {
			return R.FAILURE("使用中,暂不可删除");
		}
		return R.SUCCESS_OPER(SysRoleInfoServiceImpl.deleteById(id));
	}

	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectById.do")
	public R selectById(String id) {
		return R.SUCCESS_OPER(SysRoleInfoServiceImpl.selectById(id));
	}

	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_DENY)
	@RequestMapping(value = "/insert.do")
	public R insert(SysRoleInfo entity) {
		return R.SUCCESS_OPER(SysRoleInfoServiceImpl.insert(entity));
	}

	@ResponseBody
	@Acl(info = "根据Id更新", value = Acl.ACL_DENY)
	@RequestMapping(value = "/updateById.do")
	public R updateById(SysRoleInfo entity) {
		return R.SUCCESS_OPER(SysRoleInfoServiceImpl.updateById(entity));
	}

	@ResponseBody
	@Acl(info = "存在则更新,否则插入", value = Acl.ACL_DENY)
	@RequestMapping(value = "/insertOrUpdate.do")
	public R insertOrUpdate(SysRoleInfo entity) {
		return R.SUCCESS_OPER(SysRoleInfoServiceImpl.insertOrUpdate(entity));
	}

	@ResponseBody
	@Acl(info = "查询所有,无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectList.do")
	public R selectList() {
		return R.SUCCESS_OPER(SysRoleInfoServiceImpl.selectList(null));
	}
 
}

