package com.dt.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.shiro.ShiroKit;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.db.DB;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static Logger _log = LoggerFactory.getLogger(AuthInterceptor.class);
	@Autowired
	public DB db = null;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Boolean isPass = false;
		String acl = Acl.TYPE_DENY;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		response.setCharacterEncoding("UTF-8");
		// 输出内容
		String user_id = "";
		if (ToolUtil.isNotEmpty(ShiroKit.getUser())) {
			user_id = ShiroKit.getUser().getId();
		}
		String url = req.getRequestURI();

		// term socket
		if (url.equals("/dt/term")) {
			return true;
		}

		// 临时日志记录
		if (!url.endsWith("checkLogin.do")) {
			Insert ins = new Insert("sys_log_access");
			String ip = HttpKit.getIpAddr(request);
			ins.set("id", db.getUUID());
			ins.setIf("user_id", user_id);
			ins.setIf("ip", ip);
			ins.setIf("url", url);
			ins.setSE("rtime", DbUtil.getDBDateString(db.getDBType()));
			ins.setIf("postorget", req.getQueryString());
			db.execute(ins);
		}

		// 此处基本Acl再做权限验证
		BaseCommon.print(handler.getClass().getName());
		
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			// 前端shrio已经判断过,第二次判断
			Acl am = ((HandlerMethod) handler).getMethodAnnotation(Acl.class);
			// 未设置ACL,全部拒绝
			if (am == null) {
				isPass = false;
				res.getWriter().print(R.FAILURE_NO_PERMITION().asJsonStr());
				res.getWriter().flush();
				res.getWriter().close();
			} else {
				isPass = true;
				acl = am.value();
				// 已经设置ACL
				// if (am.value().toLowerCase().equals(Acl.TYPE_ALLOW)) {
				// acl = Acl.TYPE_ALLOW;
				// isPass = true;
				// } else {
				// // 判断是否验证
				// if (ShiroKit.isAuthenticated()) {
				// // 其他选择验证,后期在这里实现
				// acl = "all";
				// isPass = true;
				// } else {
				// res.setStatus(299);
				// res.getWriter().print(BaseResult.JSON_RETURN_NOT_LOGIN());
				// res.getWriter().flush();
				// res.getWriter().close();
				// isPass = false;
				// }
				// }
			}
		}else{
			_log.info("isAssignableFrom HandlerMethod.class failed");
			isPass=true;
		}
		 
		_log.info("userId=" + user_id + ",acl=" + acl + ",url=" + url + ",isAuth=" + ShiroKit.isAuthenticated()
				+ ",isPass=" + isPass + ",isRemember:" + ShiroKit.isRemember());
		return isPass;
	}
}
