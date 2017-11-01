package com.dt.module.base.service;

import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: Nov 1, 2017 8:49:51 AM
 * @Description: TODO
 */
@Service
public class StoreSqlService extends BaseService {
	private static Logger _log = LoggerFactory.getLogger(StoreSqlService.class);

	/**
	 * @Description: 根据条件返回数据，无分页功能
	 */
	@SuppressWarnings("rawtypes")
	public ResData commandQuery(TypedHashMap<String, Object> ps) {
		String sql = "";
		String store_id = ps.getString("store_id");
		if (ToolUtil.isNotEmpty(store_id)) {
			Rcd brs = db.uniqueRecord("select * from ct_uri where store_id=?", store_id);
			// String uri = brs.getString("uri");
			// String uri_parameter = brs.getString("uri_parameter");
			if (ToolUtil.isNotEmpty(brs)) {
				sql = brs.getString("sql");
			}
		}
		Iterator<Entry<String, Object>> i = ps.entrySet().iterator();
		while (i.hasNext()) {
			Entry entry = (java.util.Map.Entry) i.next();
			String key = entry.getKey().toString();
			String value = (String) entry.getValue();
			_log.info("key:" + key + ",value:" + value);
			if (key.startsWith("$")) {
				sql = sql.replaceAll(key + "$", value);
			}
		}
		_log.info("execute sql:" + sql);
		if (ToolUtil.isNotEmpty(sql)) {
			RcdSet rs = db.query(sql);
			return ResData.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
		} else {
			return ResData.FAILURE("sql发生错误");
		}
	}
}
