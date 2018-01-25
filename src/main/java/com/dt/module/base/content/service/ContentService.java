package com.dt.module.base.content.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.util.DBUtil;
import com.dt.util.ToolUtil;
import com.dt.util.UuidUtil;
import com.dt.util.support.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月12日 上午7:04:31
 * @Description: TODO
 */
@Service
public class ContentService extends BaseService {
	public static String TYPE_NEWS = "news";
	public static String TYPE_OHTER = "other";
	public static String TYPE_DOC = "doc";
	public static String SORT_CREATE = "create";
	public static String SORT_MODIFY = "modify";
	public static String SORT_SORT = "sort";

	/**
	 * @Description:新增CT
	 */
	public ResData addContent(TypedHashMap<String, Object> ps, String type) {
		Insert me = new Insert("ct_content");
		String idctl = ps.getString("selfid", "N");
		String id = UuidUtil.getUUID();
		if (idctl.equals("Y")) {
			id = ps.getString("id", id);
		}
		me.set("id", id);
		me.set("deleted", "N");
		me.set("type", type);
		me.set("display", ToolUtil.parseYNValueDefN(ps.getString("display")));
		me.set("digest", ToolUtil.parseYNValueDefN(ps.getString("digest")));
		me.setIf("cat_id", ps.getString("cat_id"));
		me.setIf("title", ps.getString("title"));
		me.setIf("profile", ps.getString("profile"));
		me.setIf("urltype", ps.getString("urltype"));
		me.setIf("url", ps.getString("url"));
		me.setIf("mpic", ps.getString("mpic"));
		me.setIf("mpic_loc", ps.getString("mpic_loc"));
		me.setIf("hits", ps.getString("hits"));
		me.setIf("author", ps.getString("author"));
		me.setIf("tag", ps.getString("tag"));
		me.setIf("content", ps.getString("content"));
		me.setIf("mark", ps.getString("mark"));
		me.setSE("createtime", DBUtil.getDBDateString(db.getDBType()));
		me.setSE("modifytime", DBUtil.getDBDateString(db.getDBType()));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:修改CT
	 */
	public ResData updateContent(TypedHashMap<String, Object> ps) {
		Update me = new Update("ct_content");
		me.set("display", ToolUtil.parseYNValueDefN(ps.getString("display")));
		me.set("digest", ToolUtil.parseYNValueDefN(ps.getString("digest")));
		me.setIf("cat_id", ps.getString("cat_id"));
		me.setIf("title", ps.getString("title"));
		me.setIf("profile", ps.getString("profile"));
		me.setIf("content", ps.getString("content"));
		me.setIf("urltype", ps.getString("urltype"));
		me.setIf("url", ps.getString("url"));
		me.setIf("mpic", ps.getString("mpic"));
		me.setIf("mpic_loc", ps.getString("mpic_loc"));
		me.setIf("hits", ps.getString("hits"));
		me.setIf("author", ps.getString("author"));
		me.setIf("tag", ps.getString("tag"));
		me.setIf("mark", ps.getString("mark"));
		me.setSE("modifytime", DBUtil.getDBDateString(db.getDBType()));
		me.where().and("id=?", ps.getString("id"));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:删除CT
	 */
	public ResData deleteContent(String id, String type) {
		if (ToolUtil.isOneEmpty(id, type)) {
			return ResData.FAILURE_ERRREQ_PARAMS();
		}
		Update me = new Update("ct_content");
		me.set("deleted", "Y");
		me.where().and("id=?", id).and("type=?", type);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:根据ID查找CT
	 */
	public ResData queryContentById(String id) {
		Rcd rs = db.uniqueRecord("select * from ct_content where id=?", id);
		if (ToolUtil.isEmpty(rs)) {
			return ResData.FAILURE_NODATA();
		}
		return ResData.SUCCESS_OPER(rs.toJsonObject());
	}
	private String rebuildQueryContentSql(TypedHashMap<String, Object> ps, String type) {
		String sdate = ps.getString("sdate");
		String edate = ps.getString("edate");
		String sort = ps.getString("sort");
		String noContent = ps.getString("noContent", "N");
		String sql = "select <#CONTENT#> id,cat_id,digest,title,profile,urltype,url,type,mpic,mpic_loc,hits,author,createtime,modifytime ,display,mark,tag from ct_content where deleted='N' and type='"
				+ type + "' ";
		if (noContent.equals("Y")) {
			sql = sql.replaceAll("<#CONTENT#>", "");
		} else {
			sql = sql.replaceAll("<#CONTENT#>", "CONTENT,");
		}
		if (ToolUtil.isNotEmpty(sdate)) {
			sql = sql + " and createtime>=to_date('" + sdate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
		}
		if (ToolUtil.isNotEmpty(edate)) {
			sql = sql + " and createtime<=to_date('" + edate + " 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
		}
		if (ToolUtil.isEmpty(sort)) {
			// 如果是新闻
			if (type.equals(ContentService.TYPE_NEWS)) {
				sql = sql + " order by createtime desc";
			}
		} else {
			// 按照预定的排序
			if (sort.equals(SORT_CREATE)) {
				sql = sql + " order by createtime desc";
			}
		}
		return sql;
	}
	/**
	 * @Description:查找CT数量
	 */
	public int queryContentCount(TypedHashMap<String, Object> ps, String type) {
		String sql = rebuildQueryContentSql(ps, type);
		sql = "select count(1) value from (" + sql + ") ";
		int total = db.uniqueRecord(sql).getInteger("value");
		return total;
	}
	/**
	 * @Description:查找页数
	 */
	public int queryContentPageCount(TypedHashMap<String, Object> ps, String type, int pageSize) {
		int total = queryContentCount(ps, type);
		return DBUtil.getTotalPage(total, pageSize);
	}
	/**
	 * @Description:查找CT
	 */
	public ResData queryContentPage(TypedHashMap<String, Object> ps, int pageSize, int pageIndex, String type) {
		String sql = rebuildQueryContentSql(ps, type);
		return ResData.SUCCESS_OPER(
				db.query(DBUtil.getDBPageSql(db.getDBType(),sql, pageSize, pageIndex)).toJsonArrayWithJsonObject());
	}
}
