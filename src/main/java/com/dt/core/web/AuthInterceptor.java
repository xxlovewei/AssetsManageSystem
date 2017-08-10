package com.dt.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.util.TokenUtil;
import com.dt.core.shiro.ShiroKit;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		response.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();
		String token =TokenUtil.getRequestToken(req);
		System.out.println("AutoInterceptor:" + url + "," + token+",auth:"+ShiroKit.isAuthenticated());
  
		if(true){
			return true;
		}
	
		// 没有登录
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			Acl am = ((HandlerMethod) handler).getMethodAnnotation(Acl.class);

			// acl 全局拒绝,不管有没有登录必须要设置acl,否则不能请求
			if (am == null) {
				res.setStatus(298);
				JSONObject r = new JSONObject();
				r.put("success", false);
				r.put("message", "API未开放,无权访问");
				res.getWriter().print(r.toJSONString());
				return false;
			}

			// acl 公共API,无权限控制
			if (am.value().toLowerCase().equals("allow")) {
				return true;
			}
			
			if(ShiroKit.isAuthenticated()){
				//其他ACL控制,后期修改
				return true;
			 
			}else{
				res.setStatus(299);
				JSONObject r = new JSONObject();
				r.put("success", false);
				r.put("message", "未登录");
				res.getWriter().print(r.toJSONString());
				return false;
			}
	 

		} else {
			System.out.println("Allow Any");
			return true;
		}

	

	}

}