package com.dt.module.base.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.core.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
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
	public ResData queryRole() {
		return ResData.SUCCESS_OPER(db.query("select * from sys_role where deleted='N' ").toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 查询某个角色
	 */
	public ResData queryRoleById(String id) {
		String sql = "select * from sys_role where deleted='N' and role_id=?";
		RcdSet r = db.query(sql, id);
		if (r.size() > 0) {
			return ResData.SUCCESS_OPER(r.getRcd(0).toJsonObject());
		} else {
			return ResData.FAILURE_OPER();
		}
	}
	/**
	 * @Description: 删除角色
	 */
	@Transactional
	public ResData deleteRole(String role_id, Boolean force) {
		if (!force) {
			if (db.uniqueRecord("select count(1) value from sys_user_role where role_id=?", role_id)
					.getInteger("value") > 0) {
				return ResData.FAILURE("该角色使用中不能删除");
			}
		}
		Update ups = new Update("sys_role");
		ups.set("deleted", "Y");
		ups.where().and("role_id=?", role_id);
		db.execute(ups);
		db.execute("delete from sys_user_role where role_id=?",role_id);
		
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 增加角色
	 */
	public ResData addRole(TypedHashMap<String, Object> ps) {
		Insert ins = new Insert("sys_role");
		ins.set("role_id", db.getUUID());
		ins.set("deleted", "N");
		ins.set("is_action", ps.getString("is_action"));
		ins.setIf("role_name", ps.getString("role_name"));
		ins.setIf("mark", ps.getString("mark"));
		db.execute(ins);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 修改角色
	 */
	public ResData updateRole(TypedHashMap<String, Object> ps) {
		Update ups = new Update("sys_role");
		ups.set("is_action", ps.getString("is_action"));
		ups.setIf("role_name", ps.getString("role_name"));
		ups.setIf("mark", ps.getString("mark"));
		ups.where().and("role_id=?", ps.getString("role_id"));
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
}
