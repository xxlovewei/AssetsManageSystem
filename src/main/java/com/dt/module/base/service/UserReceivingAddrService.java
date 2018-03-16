package com.dt.module.base.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.RcdSet;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;

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
		Rcd rs = db.uniqueRecord(
				"select t.*,t.provincenm||t.citynm||t.areaname||t.ct ctdtl from (select (select mingc from sys_qud_shengf where id=a.provinceid)provincenm, (select mingc from sys_qud_chengs where id=a.cityid)citynm, (select mingc from sys_qud_qux where id=a.areaid)areanm,a.* from sys_user_receivingaddr a where is_deleted='N' and id=?) t",
				addr_id);
		return ConvertUtil.OtherJSONObjectToFastJSONObject(rs.toJsonObject());
	}

	/**
	 * @Description: 获取所有地址
	 */
	public JSONArray queryReceivingAddr(String user_id) {
		String sql = "select t.*, " + "t.provincenm||t.citynm||t.areanm ctdtl " + "from ( " + "select "
				+ "(select mingc from sys_qud_shengf where id=a.provinceid)provincenm, "
				+ "(select mingc from sys_qud_chengs where id=a.cityid)citynm, "
				+ "(select mingc from sys_qud_qux where id=a.areaid)areanm, " + "a.*, "
				+ "case when a.id=b.receaddr_def then 1 else 0 end is_def "
				+ "from sys_user_receivingaddr a,sys_user_info b "
				+ "where a.user_id=b.user_id and a.is_deleted='N' and a.user_id=?) t " + "order by od ";
		RcdSet rs = db.query(sql, user_id);
		return ConvertUtil.OtherJSONObjectToFastJSONArray(rs.toJsonArrayWithJsonObject());
	}

	/**
	 * @Description: 删除地址,force，false至少保留一个地址，true强制删除
	 */
	public R delReceivingAddr(String user_id, String addr_id, Boolean force) {
		Update me = new Update("sys_user_receivingaddr");
		me.set("is_deleted", "Y");
		me.where().and("user_id=?", user_id).and("id=?", addr_id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 添加地址,id是系统生产的序列，code是国家编码
	 */
	public R addReceivingAddr(String user_id, TypedHashMap<String, Object> ps) {
		Insert me = new Insert("sys_user_receivingaddr");
		me.set("id", db.getUUID());
		me.set("user_id", user_id);
		me.setIf("provinceid", ps.getString("provinceid"));
		me.setIf("provincecode", ps.getString("provincecode"));
		me.setIf("provincename", ps.getString("provincename"));
		me.setIf("cityid", ps.getString("cityid"));
		me.setIf("citycode", ps.getString("citycode"));
		me.setIf("cityname", ps.getString("cityname"));
		me.setIf("areaid", ps.getString("areaid"));
		me.setIf("areacode", ps.getString("areacode"));
		me.setIf("areaname", ps.getString("areaname"));
		me.setIf("ct", ps.getString("ct"));
		me.setIf("contactuser", ps.getString("contactuser"));
		me.setIf("contact", ps.getString("contact"));
		me.setIf("zcode", ps.getString("zcode"));
		me.set("is_deleted", "N");
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 更新地址
	 */
	public R updateReceivingAddr(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_user_receivingaddr");
		me.setIf("provinceid", ps.getString("provinceid"));
		me.setIf("provincecode", ps.getString("provincecode"));
		me.setIf("provincename", ps.getString("provincename"));
		me.setIf("cityid", ps.getString("cityid"));
		me.setIf("citycode", ps.getString("citycode"));
		me.setIf("cityname", ps.getString("cityname"));
		me.setIf("areaid", ps.getString("areaid"));
		me.setIf("areacode", ps.getString("areacode"));
		me.setIf("areaname", ps.getString("areaname"));
		me.setIf("ct", ps.getString("ct"));
		me.setIf("contactuser", ps.getString("contactuser"));
		me.setIf("contact", ps.getString("contact"));
		me.setIf("zcode", ps.getString("zcode"));
		me.where().and("id=?", ps.getString("id", ""));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 修改默认地址
	 */
	public R updateDefReceivingAddr(String user_id, String addr_id) {
		Update me = new Update("sys_user_info");
		me.setIf("receaddr_def", addr_id);
		me.where().and("user_id=?", user_id);
		db.execute(me);
		return R.SUCCESS_OPER();
	}

	/**
	 * @Description: 获取用户默认地址
	 */
	public R queryDefReceivingAddr(String user_id) {

		String sql = "select b.id from sys_user_info a,sys_user_receivingaddr b where a.receaddr_def=b.id and a.user_id=b.user_id and a.user_id=?";
		RcdSet rs = db.query(sql, user_id);
		if (rs.size() > 0) {
			return R.SUCCESS_OPER(queryReceivingAddrById(rs.getRcd(0).getString("id")));
		}
		Rcd rs2 = db.uniqueRecord("select id from sys_user_receivingaddr where user_id=?", user_id);
		if (ToolUtil.isEmpty(rs2)) {
			return R.FAILURE("没有地址,请先完善");
		} else {
			return R.SUCCESS_OPER(queryReceivingAddrById(rs2.getString("id")));
		}
	}

	public JSONObject queryReceivingAddrById1(String id) {
		return null;
	}

}
