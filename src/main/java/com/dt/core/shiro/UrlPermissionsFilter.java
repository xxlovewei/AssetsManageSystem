package com.dt.core.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.core.common.base.BaseCommon;
import com.dt.core.common.base.BaseResult;
import com.dt.tool.util.ToolUtil;
import com.dt.tool.util.support.HttpKit;
import com.dt.tool.util.support.StrKit;

/**
 * @author: algernonking
 * @date: 2017年8月31日 上午8:53:57
 * @Description: TODO
 */
public class UrlPermissionsFilter extends PermissionsAuthorizationFilter {
	private static final Logger log = LoggerFactory.getLogger(UrlPermissionsFilter.class);

	/**
	 * @param mappedValue
	 *            指的是在声明url时指定的权限字符串，如/User/create.do=perms[User:create].我们要动态产生这个权限字符串，所以这个配置对我们没用
	 */
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String user_id = (String) req.getSession().getAttribute("user_id");
		// 如果是sys用户则具有最高权限
		if (ToolUtil.isNotEmpty(user_id) && BaseCommon.isSuperAdmin(user_id)) {
			log.info("sys用户直接访问,无权限判断.");
			return true;
		} else {
			return super.isAccessAllowed(request, response, buildPermissions(request));
		}
		// return true;
	}
	/**
	 * 根据请求URL产生权限字符串，这里只产生，而比对的事交给Realm
	 * @param request
	 * @return
	 */
	protected String[] buildPermissions(ServletRequest request) {
		String[] perms = new String[1];
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		perms[0] = path;// path直接作为权限字符串
		return perms;
	}
	@Override
	public boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {// 表示没有登录，重定向到登录页面
			saveRequest(request);
			if (isReturnJSON(httpRequest)) {
				httpResponse.setStatus(299);
				httpResponse.setCharacterEncoding("UTF-8");
				httpResponse.getWriter().print(BaseResult.JSON_RETURN_NOT_LOGIN());
				httpResponse.getWriter().flush();
				httpResponse.getWriter().close();
			} else {
				WebUtils.issueRedirect(request, response, getLoginUrl());
			}
		} else {
			if (isReturnJSON(httpRequest)) {
				httpResponse.setStatus(298);
				httpResponse.setCharacterEncoding("UTF-8");
				httpResponse.getWriter().print(BaseResult.JSON_RETURN_NO_PERMITION());
				httpResponse.getWriter().flush();
				httpResponse.getWriter().close();
			} else {
				if (StringUtils.hasText(getUnauthorizedUrl())) {// 如果有未授权页面跳转过去
					WebUtils.issueRedirect(request, response, getUnauthorizedUrl());
				} else {// 否则返回401未授权状态码
					WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
		return false;
	}
	private Boolean isReturnJSON(HttpServletRequest httpRequest) {
		Boolean res = false;
		if (HttpKit.isAjax(httpRequest) || StrKit.endWith(httpRequest.getRequestURL() + "", ".do", true)) {
			res = true;
		}
		return res;
	}
}