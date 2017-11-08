package com.dt.core.common.shiro.sessiondao;

import java.io.Serializable;

import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.common.util.DBUtil;
import com.dt.core.db.DB;

/**
 * @author: algernonking
 * @date: 2017年11月7日 下午2:17:06
 * @Description: TODO
 */
public class SimpleSessionEntity {
	private String id;
	private String user_id;
	private String cookie;
	private String session;
	private String start_time;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Serializable entity() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getSession() {
		return session;
	}

	public void save() {
		Insert me = new Insert("sys_session");
		me.set("id", cookie);
		me.set("cookie", cookie);
		me.set("dtsession", session + "");
		me.setIf("start_time", start_time);
		DB.instance().execute(me);
	}

	public void update(SimpleSessionEntity entity) {
		Update me = new Update("sys_session");
		me.set("id", entity.id);
		me.set("dtsession", entity.session + "");
		me.setSE("lastaccess", DBUtil.getDBDateString(DB.instance().getDBType()));
		me.where().and("cookie=?", entity.cookie);
		DB.instance().execute(me);
	}

	/**
	 * @return the start_time
	 */
	public String getStart_time() {
		return start_time;
	}

	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
