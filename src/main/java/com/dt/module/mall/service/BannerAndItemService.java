package com.dt.module.mall.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.ResData;
import com.dt.core.dao.Rcd;
import com.dt.core.dao.sql.Delete;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: algernonking
 * @date: 2017年11月15日 上午11:29:35
 * @Description: TODO
 */
@Service
public class BannerAndItemService extends BaseService {
	public static String BANNER_TYPE_MALL = "mall";
	public static String MALL_BANNER_ID = "mall_banner";

	public static String BANNER_ITEM_TYPE_BLANK = "blank";
	public static String BANNER_ITEM_TYPE_URL = "url";

	public ResData addBanner(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("sys_banner");
		me.set("banner_id", db.getUUID());
		me.setIf("name", ps.getString("name", ""));
		me.set("type", ps.getString("type", ""));
		me.set("is_used", ps.getString("is_used", "Y"));
		me.set("is_delete", 'N');
		db.execute(me);
		return ResData.SUCCESS();
	}

	public ResData updateBanner(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_banner");
		me.setIf("name", ps.getString("name", ""));
		me.set("is_used", ps.getString("is_used", "Y"));
		me.where().and("banner_id=?", ps.getString("banner_id", ""));
		db.execute(me);
		return ResData.SUCCESS();
	}

	public ResData delBanner(String banner_id) {
		Delete me = new Delete();
		me.from("sys_banner");
		me.where().and("banner_id=?", banner_id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData queryBannerById(String banner_id) {
		String sql = "select * from sys_banner where banner_id=? and is_delete='N'";
		Rcd rs = db.uniqueRecord(sql);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE_NODATA();
		} else {
			return ResData.SUCCESS_OPER(rs.toJsonObject());
		}
	}

	public ResData queryBanner(String type) {

		String sql = "select * from sys_banner where is_delete='N' ";
		if (ToolUtil.isNotEmpty(type)) {
			sql = sql + " and type='" + type + "'";
		}
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}

	public ResData addBannerItem(TypedHashMap<String, Object> ps) {
		Insert me = new Insert("sys_banner_item");
		me.set("id", db.getUUID());
		me.set("banner_id", ps.getString("banner_id", ""));
		me.set("type", ps.getString("type", ""));
		me.setIf("name", ps.getString("name", ""));
		me.set("pic_id", ps.getString("pic_id", ""));
		me.setIf("ct", ps.getString("ct", ""));
		me.setIf("rk", ps.getString("rk", "1"));
		me.set("is_used", ps.getString("is_used", "Y"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData updateBannerItem(TypedHashMap<String, Object> ps) {
		Update me = new Update("sys_banner_item");
		me.setIf("name", ps.getString("name", ""));
		me.set("pic_id", ps.getString("pic_id", ""));
		me.setIf("ct", ps.getString("ct", ""));
		me.setIf("rk", ps.getString("rk", "1"));
		me.set("is_used", ps.getString("is_used", "Y"));
		me.where().and("id=?", ps.getString("id", ""));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData delBannerItem(String id) {
		Delete me = new Delete();
		me.from("sys_banner_item");
		me.where().and("id=?", id);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}

	public ResData queryBannerItemById(String id) {
		String sql = "select * from sys_banner_item where id=?";
		Rcd rs = db.uniqueRecord(sql, id);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE_NODATA();
		} else {
			return ResData.SUCCESS_OPER(rs.toJsonObject());
		}
	}

	public ResData queryBannerItems(String banner_id, String is_used) {
		String sql = "select * from sys_banner_item where banner_id=? ";
		if (ToolUtil.isNotEmpty(is_used)) {
			sql = sql + "  and is_used='" + is_used + "' ";
		}
		sql = sql + " order by rk";
		return ResData.SUCCESS_OPER(db.query(sql, banner_id).toJsonArrayWithJsonObject());

	}

}
