package com.yys.test.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.yys.test.entity.Dtuser;
import com.yys.test.service.IDtuserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.R;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.stereotype.Controller;
import com.dt.core.common.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author algernonking
 * @since 2018-07-23
 */
@Controller
@RequestMapping("/api/dtuser")
public class DtuserController extends BaseController {


	@Autowired
	IDtuserService IDtuserServiceImpl;


	@ResponseBody
	@Acl(info = "根据Id删除", value = Acl.ACL_USER)
	@RequestMapping(value = "/deleteById.do")
	public R deleteById(String id) {
		return R.SUCCESS_OPER(IDtuserServiceImpl.deleteById(id));
	}

	@ResponseBody
	@Acl(info = "根据Id查询", value = Acl.ACL_ALLOW)
	@RequestMapping(value = "/selectById.do")
	public R selectById(String id) {
		return R.SUCCESS_OPER(IDtuserServiceImpl.selectById(id));
	}

	@ResponseBody
	@Acl(info = "插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insert.do")
	public R insert(Dtuser  entity) {
		return R.SUCCESS_OPER(IDtuserServiceImpl.insert(entity));
	}

	@ResponseBody
	@Acl(info = "根据Id更新", value = Acl.ACL_USER)
	@RequestMapping(value = "/updateById.do")
	public R updateById(Dtuser  entity) {
		return R.SUCCESS_OPER(IDtuserServiceImpl.updateById(entity));
	}

	@ResponseBody
	@Acl(info = "存在则更新,否则插入", value = Acl.ACL_USER)
	@RequestMapping(value = "/insertOrUpdate.do")
	public R insertOrUpdate(Dtuser  entity) {
		return R.SUCCESS_OPER(IDtuserServiceImpl.insertOrUpdate(entity));
	}

	@ResponseBody
	@Acl(info = "查询所有无分页", value = Acl.ACL_USER)
	@RequestMapping(value = "/selectList.do")
	public R selectList() {
		return R.SUCCESS_OPER(IDtuserServiceImpl.selectList(null));
	}

}

