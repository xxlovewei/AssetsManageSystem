package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.UuidUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月6日 下午3:22:34
 * @Description: TODO
 */
@Service
public class ParamsService extends BaseService {
	/**
	 * @Description: 添加参数
	 */
	//类型system|user|sysinter(系统内置，不可改动)
	public ResData addParams(TypedHashMap<String, Object> ps) {
		Insert me=new Insert("SYS_PARAMS");
		me.set("id", UuidUtil.getUUID());
		me.set("deleted", "N");
		me.setIf("name", ps.getString("NAME", ""));
		me.setIf("value", ps.getString("VALUE", ""));
		me.set("type", ps.getString("TYPE"));
		me.setIf("mark", ps.getString("MARK", ""));
		db.execute(me);
		return ResData.SUCCESS_OPER();
		
	}
	/**
	 * @Description: 查询所有参数
	 */
	public ResData queryParams() {
		//排除内置
		String sql="select * from SYS_PARAMS where deleted='N' and type<>'sysinter' ";
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 修改参数
	 */
	public ResData updateParams(TypedHashMap<String, Object> ps) {
		Update me=new Update("SYS_PARAMS");
		me.setIf("name", ps.getString("NAME", ""));
		me.setIf("value", ps.getString("VALUE", ""));
		me.set("type", ps.getString("TYPE"));
		me.setIf("mark", ps.getString("MARK", ""));
		me.where().and("id=?",ps.getString("ID"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	
	}
	/**
	 * @Description: 删除参数
	 */
	public ResData deleteParams(String id) {
		Update me=new Update("SYS_PARAMS");
		me.set("deleted", "Y");
		me.where().and("id=?",id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 按照Id查询参数
	 */
	public ResData queryParamsById(String id) {
		String sql="select * from SYS_PARAMS where deleted='N' and id=?";
		Rcd rs=db.uniqueRecord(sql,id);
		if(rs==null){
			return ResData.FAILURE_NODATA();
		}
		return ResData.SUCCESS_OPER(rs.toJsonObject());
	}
}
