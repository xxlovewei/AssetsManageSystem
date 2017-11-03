package com.dt.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.shiro.ShiroKit;
import com.dt.core.common.util.DBUtil;
import com.dt.core.common.util.TokenUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.db.DB;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static Logger _log = LoggerFactory.getLogger(AuthInterceptor.class);
	@Autowired
	public DB db = null;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Boolean isPass = false;
		String acl = "deny";
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		response.setCharacterEncoding("UTF-8");
		// 输出内容
		String user_id = "";
		if (ToolUtil.isNotEmpty(ShiroKit.getUser())) {
			user_id = ShiroKit.getUser().getId();
		}
		String url = req.getRequestURI();
		String token = TokenUtil.getRequestToken(req);
		// 临时日志记录
		if (url.endsWith("checkLogin.do")) {
		} else {
			Insert ins = new Insert("sys_log_access");
			String ip = HttpKit.getIpAddr(request);
			ins.set("id", db.getUUID());
			ins.setIf("user_id", user_id);
			ins.setIf("ip", ip);
			ins.setIf("url", url);
			ins.setSE("rtime", DBUtil.getDBDateString(db.getDBType()));
			ins.setIf("postorget", req.getQueryString());
			db.execute(ins);
		}
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			// 前端shrio已经判断过,第二次判断
			Acl am = ((HandlerMethod) handler).getMethodAnnotation(Acl.class);
			// 未设置ACL,全部拒绝
			if (am == null) {
				res.setStatus(298);
				JSONObject r = new JSONObject();
				r.put("success", false);
				r.put("message", "Api is not accessed!");
				res.getWriter().print(r.toJSONString());
				res.getWriter().flush();
				res.getWriter().close();
				isPass = false;
			} else {
				// 已经设置ACL
				if (am.value().toLowerCase().equals(Acl.TYPE_ALLOW)) {
					acl = Acl.TYPE_ALLOW;
					isPass = true;
				} else {
					// 判断是否验证
					if (ShiroKit.isAuthenticated()) {
						// 其他选择验证,后期在这里实现
						acl = "all";
						isPass = true;
					} else {
						res.setStatus(299);
						JSONObject r = new JSONObject();
						r.put("success", false);
						r.put("message", "You are not login!");
						res.getWriter().print(r.toJSONString());
						res.getWriter().flush();
						res.getWriter().close();
						isPass = false;
					}
				}
			}
		} 
		_log.info("userId=" + user_id + ",acl=" + acl + ",url=" + url + ",token=" + token + ",isAuth="
				+ ShiroKit.isAuthenticated() + ",isPass=" + isPass);
		return true;
	}
}
