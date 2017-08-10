package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.support.TypedHashMap;

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
		return ResData.SUCCESS(db.query("select * from sys_role where deleted='N' ").toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 查询某个角色
	 */
	public ResData queryRoleById(String id) {
		String sql = "select * from sys_role where deleted='N' and role_id=?";
		RcdSet r = db.query(sql, id);
		if (r.size() > 0) {
			return ResData.SUCCESS(r.getRcd(0).toJsonObject());
		} else {
			return ResData.FAILURE_OPER();
		}
	}
	/**
	 * @Description: 删除角色
	 */
	public ResData deleteRole(String id) {
		Update ups = new Update("sys_role");
		ups.set("deleted", "Y");
		ups.where().and("role_id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 增加角色
	 */
	public ResData addRole(TypedHashMap<String, Object> ps) {
		Insert ins = new Insert("sys_role");
		ins.set("role_id", db.getUUID());
		ins.set("deleted", "N");
		ins.set("is_action", ps.getString("IS_ACTION"));
		ins.setIf("role_name", ps.getString("ROLE_NAME"));
		ins.setIf("MARK", ps.getString("MARK"));
		db.execute(ins);
		return ResData.SUCCESS_OPER();
	}
	
	/**
	 * @Description: 修改角色
	 */
	public ResData updateRole(TypedHashMap<String, Object> ps) {
		Update ups = new Update("sys_role");
		ups.set("is_action", ps.getString("IS_ACTION"));
		ups.setIf("role_name", ps.getString("ROLE_NAME"));
		ups.setIf("MARK", ps.getString("MARK"));
		ups.where().and("ROLE_ID=?", ps.getString("ROLE_ID"));
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}
}
