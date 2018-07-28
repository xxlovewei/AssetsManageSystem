package com.dt.module.base.service.impl;

import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dt.core.cache.CacheConfig;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.util.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2018年6月2日 下午11:11:49
 * @Description: TODO
 */
@Service
public class FileService extends BaseService {
	@Cacheable(value = CacheConfig.CACHE_PUBLIC_1d_1h, key = "'queryFileInfoById'+#id")
	public JSONObject queryFileInfoById(String id) {
		return db.uniqueRecord("select * from sys_files where id=?", id).toJsonObject();
	}

	@Cacheable(value = CacheConfig.CACHE_PUBLIC_1d_1h, key = "'queryFileByid'+#id")
	public R queryFileByid(String id) {
		return R.SUCCESS_OPER(db.uniqueRecord("select * from sys_files where id=?", id).toJsonObject());
	}

	public R deleteFile(String id) {
		db.execute("delete from sys_files where id='" + id + "' ");
		return R.SUCCESS_OPER();
	}

	public R addFile(TypedHashMap<String, Object> ps) {
		Insert me = new Insert();
		me.set("id", db.getUUID());
		me.setIf("path", ps.getString("path", ""));
		me.setIf("type", ps.getString("type", "file"));
		me.setIf("bus", ps.getString("bus", "def"));
		db.execute(me);
		return R.SUCCESS_OPER();
	}

}
