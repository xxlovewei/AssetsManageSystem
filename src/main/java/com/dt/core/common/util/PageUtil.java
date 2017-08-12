package com.dt.core.common.util;

/**
 * @author: algernonking
 * @date: 2017年8月12日 上午10:21:06
 * @Description: TODO
 */
public class PageUtil {
	/**
	 * @Description: pageSize:每页显示数
	 */
	public static String rebuildOracleSql(String sql, int pageSize, int pageIndex) {
		if (ToolUtil.isOneEmpty(pageSize, pageIndex) || pageSize == -1 || pageIndex == -1) {
			return sql;
		}
		int first = (pageSize * pageIndex);
		int second = (pageIndex - 1) * pageSize + 1;
		String ressql = " select * from(select rownum num,u.* from(" + sql + ") u where rownum<=" + first
				+ ") where num>=" + second;
		return ressql;
	}
	public static int getTotalPage(int total, int rows) {
		if (rows <= 0) {
			return 1;
		}
		int totalPage = (total % rows) == 0 ? (total / rows) : ((total / rows) + 1);
		return totalPage;
	}
	public static void main(String[] args) {
		System.out.println(rebuildOracleSql("select * from CT_CONTENT", -1, 0));
		System.out.println(getTotalPage(6, 4));
	}
}
