package com.dt.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.util.TokenUtil;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.shiro.ShiroKit;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static Logger _log = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Boolean isPass = false;
		String acl = "deny";
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		response.setCharacterEncoding("UTF-8");
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
						isPass = false;
					}
				}
			}
		}
		// 输出内容
		String user_id = "";
		if (ToolUtil.isNotEmpty(ShiroKit.getUser())) {
			user_id = ShiroKit.getUser().getId();
		}
		String url = req.getRequestURI();
		String token = TokenUtil.getRequestToken(req);
		_log.info("userId=" + user_id + ",acl=" + acl + ",url=" + url + ",token=" + token + ",isAuth="
				+ ShiroKit.isAuthenticated() + ",isPass=" + isPass);
		return true;
	}
}
