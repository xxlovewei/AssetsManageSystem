package com.dt.module.base.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年8月8日 上午11:25:53
 * @Description: TODO
 */
@Service
public class UserGroupService extends BaseService {
	/**
	 * @Description: 添加一个用户组
	 */
	public R addUserGroup(TypedHashMap<String, Object> ps) {

		Insert me = new Insert("sys_user_group");
		me.set("group_id", ToolUtil.getUUID());
		me.set("deleted", "N");
		me.setIf("name", ps.getString("name"));
		me.setIf("sort", ConvertUtil.toInt(ps.getString("sort"), 999));
		me.set("mark", ps.getString("mark"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 修改一个用户组
	 */
	public R updateUserGroup(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_user_group");
		me.set("deleted", "N");
		me.setIf("name", ps.getString("name"));
		me.setIf("sort", ConvertUtil.toInt(ps.getString("sort"), 999));
		me.set("mark", ps.getString("mark"));
		me.where().and("group_id=?", ps.getString("group_id"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 删除一个用户组
	 */
	@Transactional
	public R deleteUserGroup(String group_id) {
		if (db.uniqueRecord(" select count(1) cnt from sys_user_group_item where group_id=?", group_id)
				.getInteger("cnt") > 0) {
			R.FAILURE("请先删除用户组中的用户");
		}
		db.execute("delete from sys_user_group where group_id=?", group_id);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 强制一个用户组
	 */
	@Transactional
	public R deleteUserGroupForce(String group_id) {
		db.execute("delete from sys_user_group where group_id=?", group_id);
		db.execute("delete from sys_user_group_item where group_id=?", group_id);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 查询所有用户组
	 */
	public R queryUserGroup() {
		return R.SUCCESS_OPER(db.query("select * from sys_user_group where dr='0' ").toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 查询用户组的一条记录
	 */
	public R queryUserGroupById(String group_id) {
		Rcd rs = db.uniqueRecord("select * from sys_user_group where dr='0' and group_id=?", group_id);
		if (ToolUtil.isEmpty(rs)) {
			return R.FAILURE_NO_DATA();
		}
		return R.SUCCESS_OPER(rs.toJsonObject());
	}
}
