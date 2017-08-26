package com.dt.module.content.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.DBUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.UuidUtil;
import com.dt.core.common.util.support.TypedHashMap;

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
		Insert me = new Insert("CT_CONTENT");
		String idctl = ps.getString("SELFID", "N");
		String id = UuidUtil.getUUID();
		if (idctl.equals("Y")) {
			// 用覆盖的ID
			id = ps.getString("ID", id);
		}
		me.set("ID", id);
		me.set("DELETED", "N");
		me.set("TYPE", type);
		me.set("DISPLAY", ToolUtil.parseYNValueDefN(ps.getString("DISPLAY")));
		me.set("DIGEST", ToolUtil.parseYNValueDefN(ps.getString("DIGEST")));
		me.setIf("CAT_ID", ps.getString("CAT_ID"));
		me.setIf("TITLE", ps.getString("TITLE"));
		me.setIf("PROFILE", ps.getString("PROFILE"));
		me.setIf("URLTYPE", ps.getString("URLTYPE"));
		me.setIf("URL", ps.getString("URL"));
		me.setIf("MPIC", ps.getString("MPIC"));
		me.setIf("MPIC_LOC", ps.getString("MPIC_LOC"));
		me.setIf("HITS", ps.getString("HITS"));
		me.setIf("AUTHOR", ps.getString("AUTHOR"));
		me.setIf("TAG", ps.getString("TAG"));
		me.setIf("CONTENT", ps.getString("CONTENT"));
		me.setIf("MARK", ps.getString("MARK"));
		me.setSE("CREATETIME", DBUtil.getDBDateString(db.getDBType()));
		me.setSE("MODIFYTIME", DBUtil.getDBDateString(db.getDBType()));
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:修改CT
	 */
	public ResData updateContent(TypedHashMap<String, Object> ps) {
		Update me = new Update("CT_CONTENT");
		me.set("DISPLAY", ToolUtil.parseYNValueDefN(ps.getString("DISPLAY")));
		me.set("DIGEST", ToolUtil.parseYNValueDefN(ps.getString("DIGEST")));
		me.setIf("CAT_ID", ps.getString("CAT_ID"));
		me.setIf("TITLE", ps.getString("TITLE"));
		me.setIf("PROFILE", ps.getString("PROFILE"));
		me.setIf("CONTENT", ps.getString("CONTENT"));
		me.setIf("URLTYPE", ps.getString("URLTYPE"));
		me.setIf("URL", ps.getString("URL"));
		me.setIf("MPIC", ps.getString("MPIC"));
		me.setIf("MPIC_LOC", ps.getString("MPIC_LOC"));
		me.setIf("HITS", ps.getString("HITS"));
		me.setIf("AUTHOR", ps.getString("AUTHOR"));
		me.setIf("TAG", ps.getString("TAG"));
		me.setIf("MARK", ps.getString("MARK"));
		me.setSE("MODIFYTIME", DBUtil.getDBDateString(db.getDBType()));
		me.where().and("ID=?", ps.getString("ID"));
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
		Update me = new Update("CT_CONTENT");
		me.set("deleted", "Y");
		me.where().and("id=?", id).and("type=?", type);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:根据ID查找CT
	 */
	public ResData queryContentById(String id) {
		Rcd rs = db.uniqueRecord("select * from  CT_CONTENT where id=?", id);
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
		String sql = "select <#CONTENT#> ID,CAT_ID,DIGEST,TITLE,PROFILE,URLTYPE,URL,TYPE,MPIC,MPIC_LOC,HITS,AUTHOR,CREATETIME,MODIFYTIME ,DISPLAY,MARK,TAG from CT_CONTENT  where DELETED='N' and type='"
				+ type + "' ";
		if (noContent.equals("Y")) {
			sql = sql.replaceAll("<#CONTENT#>", "");
		} else {
			sql = sql.replaceAll("<#CONTENT#>", "CONTENT,");
		}
		if (ToolUtil.isNotEmpty(sdate)) {
			sql = sql + " and CREATETIME>=to_date('" + sdate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
		}
		if (ToolUtil.isNotEmpty(edate)) {
			sql = sql + " and CREATETIME<=to_date('" + edate + " 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
		}
		if (ToolUtil.isEmpty(sort)) {
			// 如果是新闻
			if (type.equals(ContentService.TYPE_NEWS)) {
				sql = sql + " order by  CREATETIME desc";
			}
		} else {
			// 按照预定的排序
			if (sort.equals(SORT_CREATE)) {
				sql = sql + " order by  CREATETIME desc";
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
