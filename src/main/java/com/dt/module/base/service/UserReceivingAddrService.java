package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.ConvertUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: Oct 25, 2017 5:03:52 PM
 * @Description: 收货地址服务
 */
@Service
public class UserReceivingAddrService extends BaseService {
	/**
	 * @Description: 根据id获取地址
	 */
	public JSONObject queryReceivingAddrById(String addr_id) {
		Rcd rs = db.uniqueRecord("select * from sys_user_receivingaddr where is_deleted='N' and id=?", addr_id);
		return ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
	}
	/**
	 * @Description: 获取所有地址
	 */
	public JSONArray queryReceivingAddr(String user_id) {
		RcdSet rs = db.query(
				"select a.*,case when a.id=b.receaddr_def then 1 else 0 end is_def from sys_user_receivingaddr a,sys_user_info b where a.user_id=b.user_id and a.is_deleted='N' and a.user_id=? order by a.od",
				user_id);
		return ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject());
	}
	/**
	 * @Description: 删除地址,force，false至少保留一个地址，true强制删除
	 */
	public ResData delReceivingAddr(String user_id, String addr_id, Boolean force) {
		Update me = new Update("sys_user_receivingaddr");
		me.set("is_deleted", "Y");
		me.where().and("user_id=?", user_id).and("id=?", addr_id);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 添加地址,id是系统生产的序列，code是国家编码
	 */
	public ResData addReceivingAddr(String user_id, TypedHashMap<String, Object> ps) {
		Insert me = new Insert("sys_user_receivingaddr");
		me.set("id", db.getUUID());
		me.set("user_id", user_id);
		me.setIf("provinceid", ps.getString("PROVINCEID"));
		me.setIf("provincecode", ps.getString("PROVINCECODE"));
		me.setIf("provincename", ps.getString("PROVINCENAME"));
		me.setIf("cityid", ps.getString("CITYID"));
		me.setIf("citycode", ps.getString("CITYCODE"));
		me.setIf("cityname", ps.getString("CITYNAME"));
		me.setIf("areaid", ps.getString("AREAID"));
		me.setIf("areacode", ps.getString("AREACODE"));
		me.setIf("areaname", ps.getString("AREANAME"));
		me.setIf("ct", ps.getString("CT"));
		me.setIf("contactuser", ps.getString("CONTACTUSER"));
		me.setIf("contact", ps.getString("CONTACT"));
		me.setIf("zcode", ps.getString("ZCODE"));
		me.set("is_deleted", "N");
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 更新地址
	 */
	public ResData updateReceivingAddr(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_user_receivingaddr");
		me.setIf("provinceid", ps.getString("PROVINCEID"));
		me.setIf("provincecode", ps.getString("PROVINCECODE"));
		me.setIf("provincename", ps.getString("PROVINCENAME"));
		me.setIf("cityid", ps.getString("CITYID"));
		me.setIf("citycode", ps.getString("CITYCODE"));
		me.setIf("cityname", ps.getString("CITYNAME"));
		me.setIf("areaid", ps.getString("AREAID"));
		me.setIf("areacode", ps.getString("AREACODE"));
		me.setIf("areaname", ps.getString("AREANAME"));
		me.setIf("ct", ps.getString("CT"));
		me.setIf("contactuser", ps.getString("CONTACTUSER"));
		me.setIf("contact", ps.getString("CONTACT"));
		me.setIf("zcode", ps.getString("ZCODE"));
		me.where().and("id=?", ps.getString("ID", ""));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description: 修改默认地址
	 */
	public ResData updateDefReceivingAddr(String user_id, String addr_id) {
		Update me = new Update("sys_user_receivingaddr");
		me.setIf("receaddr_dev", addr_id);
		me.where().and("user_id=?", user_id);
		return ResData.SUCCESS_OPER();
	}
}
