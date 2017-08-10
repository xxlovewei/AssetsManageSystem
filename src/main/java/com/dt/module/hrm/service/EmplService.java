package com.dt.module.hrm.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.base.service.UserService;

/** 
 * @author: algernonking
 * @date: 2017年8月9日 上午11:11:19 
 * @Description: TODO 
 */
@Service
public class EmplService extends BaseService {
	
	public ResData queryEmplByOrg(String node_id){
		 
		if (ToolUtil.isEmpty(node_id)) {
			return ResData.FAILURE("无节点");
		}
		String sql = "select b.* from hrm_org_employee a, hrm_employee b ,SYS_USER_INFO c where a.empl_id=b.empl_id and c.USER_ID=b.USER_ID and c.TYPE= ? and a.node_id=?";
		RcdSet rs = db.query(sql,UserService.USER_TYPE_EMPL, node_id);
		return ResData.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
		
	}
	
	public ResData queryEmplList(TypedHashMap<String, Object> ps ){
		 
		String node_id = ps.getString("NODE_ID");
		String name = ps.getString("NAME");
		String bsql = "";
		if (node_id != null && (!node_id.equals("-1"))) {
			// 选择需要的节点
			Rcd routev = db.uniqueRecord("select route from hrm_org_part where node_id=?", node_id);
			if (routev == null) {
				ResData.FAILURE("该节点不存在");
			}
			String route = routev.getString("route").replaceAll("-", ",");
			bsql = " select b.*,c.node_name from hrm_org_employee a,hrm_employee b,hrm_org_part c where b.deleted='N' and a.empl_id = b.empl_id and c.node_id=a.node_id ";
			bsql = bsql + " and a.node_id in(" + route + ") ";
		} else {
			bsql = " select b.*,c.node_name from hrm_org_employee a,hrm_employee b,hrm_org_part c where b.deleted='N' and a.empl_id = b.empl_id and c.node_id=a.node_id ";
		}
		if (name != null && (!name.trim().equals(""))) {
			bsql = bsql + " and b.name like '%" + name + "%'";
		}
		bsql = bsql + " order by name";
		return ResData.SUCCESS(db.query(bsql).toJsonArrayWithJsonObject()); 
	}
		 

	public ResData queryEmplById(String empl_id){
		 
		JSONObject res = new JSONObject();
		if (ToolUtil.isEmpty(empl_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Rcd info = db.uniqueRecord("select * from hrm_employee where deleted='N' and empl_id=?  ", empl_id);
		if (info == null) {
			return ResData.FAILURE("员工不存在");
		}
		// 获取组织信息
		res = JSONObject.parseObject(info.toJsonObject().toString());
		res.put("PARTS",
				db.query(
						"select a.*,b.node_name from hrm_org_employee a,hrm_org_part b where a.node_id=b.node_id and empl_id=?",
						empl_id).toJsonArrayWithJsonObject());
		return ResData.SUCCESS("获取成功", res);	
	}
}

