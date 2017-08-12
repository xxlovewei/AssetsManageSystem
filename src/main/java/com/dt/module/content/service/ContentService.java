package com.dt.module.content.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.PageUtil;
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
	public static String TYPE_DOC = "doc";
	public static String SORT_CREATE = "create";
	public static String SORT_MODIFY = "modify";
	public static String SORT_SORT = "sort";

	/**
	 * @Description:新增CT
	 */
	public ResData addContent(TypedHashMap<String, Object> ps, String type) {
		Insert me = new Insert("CT_CONTENT");
		me.set("ID", UuidUtil.getUUID());
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
		me.setSE("CREATETIME", "sysdate");
		me.setSE("MODIFYTIME", "sysdate");
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
		me.setSE("MODIFYTIME", "sysdate");
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
		me.where().and("id=?", id).and("type=?", type);
		db.execute(me);
		return ResData.SUCCESS_OPER();
	}
	/**
	 * @Description:根据ID查找CT
	 */
	public ResData queryContentById(String id) {
		return ResData.SUCCESS_OPER(db.uniqueRecord("select * from  CT_CONTENT where id=?", id));
	}
	private String rebuildQueryContentSql(TypedHashMap<String, Object> ps, String type) {
		String sdate = ps.getString("sdate");
		String edate = ps.getString("edate");
		String sort = ps.getString("sort");
		String sql = "select * from CT_CONTENT  where DELETED='N' and type='" + type + "' ";
		if (ToolUtil.isNotEmpty(sdate)) {
			sql = sql + " and CREATETIME>=to_date('" + sdate + " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
		}
		if (ToolUtil.isNotEmpty(edate)) {
			sql = sql + " and CREATETIME<=to_date('" + edate + " 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
		}
		if (ToolUtil.isNotEmpty(sort)) {
			// 如果是新闻
			if (type.equals(ContentService.TYPE_NEWS)) {
				sql = sql + " order by  CREATETIME desc";
			} else {
				if (sort.equals(SORT_CREATE)) {
					sql = sql + " order by  CREATETIME desc";
				}
			}
			
			//
		}
		System.out.println(sql);
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
	public int queryContentPage(TypedHashMap<String, Object> ps, String type, int pageSize) {
		int total = queryContentCount(ps, type);
		return PageUtil.getTotalPage(total, pageSize);
	}
	/**
	 * @Description:查找CT
	 */
	public ResData queryContents(TypedHashMap<String, Object> ps, int pageSize, int pageIndex, String type) {
		String sql = rebuildQueryContentSql(ps, type);
		return ResData.SUCCESS_OPER(
				db.query(PageUtil.rebuildOracleSql(sql, pageSize, pageIndex)).toJsonArrayWithJsonObject());
	}
}
