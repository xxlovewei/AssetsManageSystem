package com.dt.module.mall.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.tool.lang.TypedHashMap;
import com.dt.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年9月8日 下午4:03:31
 * @Description: TODO
 */
@Service
public class ShopService extends BaseService {
	public static String TYPE_STOP = "stop";
	public static String TYPE_NORMAL = "normal";

	public ResData addShop(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("mall_shop");
		me.set("shop_id", ToolUtil.getUUID());
		me.setIf("shop_name", ps.getString("shop_name"));
		me.setIf("deleted", "N");
		me.setIf("status", ps.getString("status"));
		me.setIf("logo", ps.getString("logo"));
		me.setIf("mark", ps.getString("mark"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	public ResData deleteShop(String shop_id) {
		if (ToolUtil.isEmpty(shop_id)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update me = new Update("mall_shop");
		me.setIf("deleted", "Y");
		me.where().and("shop_id=?", shop_id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	public ResData updateShop(TypedHashMap<String, Object> ps) {
		if (ToolUtil.isEmpty(ps.getString("shop_id"))) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update me = new Update("mall_shop");
		me.setIf("shop_name", ps.getString("shop_name"));
		me.setIf("status", ps.getString("status"));
		me.setIf("logo", ps.getString("logo"));
		me.setIf("mark", ps.getString("mark"));
		me.where().and("shop_id=?", ps.getString("shop_id"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	public ResData queryShop() {
		return ResData.SUCCESS_OPER(db.query("select * from mall_shop where deleted='N'").toJsonArrayWithJsonObject());
	}
	public ResData queryShopById(String shop_id) {
		Rcd rs = db.uniqueRecord("select * from mall_shop where deleted='N' and shop_id=?", shop_id);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE_NODATA();
		} else {
			return ResData.SUCCESS_OPER(rs.toJsonObject());
		}
	}
}
