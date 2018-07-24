package com.dt.module.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Delete;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.SQL;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月6日 上午7:31:25
 * @Description: TODO
 */
@Service
public class RoleService extends BaseService {
	/**
	 * @Description: 查询所有角色
	 */
	public R queryRole() {
		return R.SUCCESS_OPER(db.query("select * from sys_role_info where deleted='0'").toJsonArrayWithJsonObject());
	}

//	/**
//	 * @Description: 查询某个角色
//	 */
//	public R queryRoleById(String id) {
//		String sql = "select * from sys_role_info where deleted='0' and role_id=?";
//		RcdSet r = db.query(sql, id);
//		if (r.size() > 0) {
//			return R.SUCCESS_OPER(r.getRcd(0).toJsonObject());
//		} else {
//			return R.FAILURE_OPER();
//		}
//	}

	/**
	 * @Description: 删除角色
	 */
//	@Transactional
//	public R deleteRole(String role_id, Boolean force) {
//		if (!force) {
//			if (db.uniqueRecord(
//					"select count(1) value from sys_user_role a,sys_user_info b where a.user_id=b.user_id and role_id=? and b.deleted='N'",
//					role_id).getInteger("value") > 0) {
//				return R.FAILURE("该角色使用中不能删除");
//			}
//		}
//		Update ups = new Update("sys_role_info");
//		ups.set("deleted", "Y");
//		ups.where().and("role_id=?", role_id);
//		List<SQL> sqls = new ArrayList<SQL>();
//		sqls.add(ups);
//		Delete me = new Delete("sys_user_role");
//		me.where().and("role_id=?", role_id);
//		sqls.add(me);
//		db.executeSQLList(sqls);
//		return R.SUCCESS_OPER();
//	}

//	/**
//	 * @Description: 增加角色
//	 */
//	public R addRole(TypedHashMap<String, Object> ps) {
//		Insert ins = new Insert("sys_role_info");
//		ins.set("role_id", db.getUUID());
//		ins.set("deleted", "N");
//		ins.set("is_action", ps.getString("is_action"));
//		ins.setIf("role_name", ps.getString("role_name"));
//		ins.setIf("mark", ps.getString("mark"));
//		db.execute(ins);
//		return R.SUCCESS_OPER();
//	}

//	/**
//	 * @Description: 修改角色
//	 */
//	public R updateRole(TypedHashMap<String, Object> ps) {
//		Update ups = new Update("sys_role_info");
//		ups.set("is_action", ps.getString("is_action"));
//		ups.setIf("role_name", ps.getString("role_name"));
//		ups.setIf("mark", ps.getString("mark"));
//		ups.where().and("role_id=?", ps.getString("role_id"));
//		db.execute(ups);
//		return R.SUCCESS_OPER();
//	}
}
