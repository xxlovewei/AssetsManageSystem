package com.dt.core.common.shiro.sessiondao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.session.Session;

import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.session.mgt.SimpleSession;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.db.DB;

/**
 * @author: algernonking
 * @date: 2017年11月7日 下午2:18:05
 * @Description: TODO
 */
public class SessionEntityDao extends EnterpriseCacheSessionDAO {

	private static Logger _log = LoggerFactory.getLogger(SessionEntityDao.class);

	@Override
	public Serializable create(Session session) {
		// 先保存到缓存中
		Serializable cookie = super.create(session);
		// 新建一个entity保存到数据库
		SimpleSessionEntity entity = new SimpleSessionEntity();
		entity.setSession(SerializableUtils.serialize(session));
		entity.setCookie(cookie.toString());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(session.getStartTimestamp());
		entity.setStart_time(dateString);
		entity.save();
		_log.info("create session:" + cookie);
		return cookie;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {

		super.update(session);
		// 如果会话过期,停止 没必要再更新了
		if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
			_log.info("会话无效,不更新存储");
			return;
		}

		SimpleSessionEntity entity = new SimpleSessionEntity();
		entity.setId(session.getId().toString());
		entity.setCookie(session.getId().toString());
		entity.setSession(SerializableUtils.serialize(session));
		entity.update(entity);

	}

	@Override
	public Session readSession(Serializable sessionId) throws UnknownSessionException {
		Session session = null;
		try {
			session = super.readSession(sessionId);
		} catch (Exception e) {

		}
		// 如果session已经被删除，则从数据库中查询session
		if (session == null) {
			_log.info("session:" + sessionId + "已删除,尝试从数据库中恢复");
			SimpleSessionEntity entity = getEntity(sessionId);
			if (entity != null) {
				_log.info("session:" + sessionId + "已在数据库中找到");
				session = SerializableUtils.deserialize(entity.getSession());
				if (isExpire(session)) {
					_log.info("session 已经过期");
					((SimpleSession) session).setLastAccessTime(new Date());
				} else {
					_log.info("session 未过期");
				}
			} else {
				_log.info("session:" + sessionId + "未在数据库中找到");
			}
		}

		// User user = getUser(sessionId);
		// if(user != null){
		// // 如果该用户是APP用户（user不为空说明就是），则判断session是否过期，如果过期则修改最后访问时间
		// ((SimpleSession)session).setLastAccessTime(new Date());
		// }
		return session;
	}

	private boolean isExpire(Session session) {
		long timeout = session.getTimeout();
		long lastTime = session.getLastAccessTime().getTime();
		long current = new Date().getTime();
		if ((lastTime + timeout) > current) {
			return false;
		}
		return true;
	}

	@Override
	public void delete(Session session) {
		_log.info("delete session");
		super.delete(session);
	}

	private SimpleSessionEntity getEntity(Serializable sessionId) {
		String sql = "select * from sys_session where cookie='" + sessionId + "'";
		Rcd rs = DB.instance().uniqueRecord(sql);
		if (ToolUtil.isNotEmpty(rs)) {
			SimpleSessionEntity res = new SimpleSessionEntity();
			res.setCookie(rs.getString("cookie"));
			res.setSession(rs.getString("dtsession"));
			res.setId(rs.getString("id"));
			res.setUser_id(rs.getString("user_id"));
			return res;
		} else {
			_log.info("没有从数据库中获取session:" + sessionId);
			return null;
		}

	}
}
