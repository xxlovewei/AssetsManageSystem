package com.dt.core.shiro;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.dt.core.common.util.support.HttpKit;

public class RoleAuthorizationFilter  extends AuthorizationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Subject subject = getSubject(request, response);
		System.out.println("role");
		if (subject.getPrincipal() == null) {

			if (HttpKit.isAjax(httpRequest)) {
				System.out.println("ajax, subject null");
				httpResponse.getWriter().write("adfasf");

			} else {
				System.out.println("ajax not, subject null");
				saveRequestAndRedirectToLogin(request, response);
			}
		} else {
			if (HttpKit.isAjax(httpRequest)) {
				System.out.println("ajax , subject");
				httpResponse.getWriter().write("adfasdfasfsdf");
			} else {
				System.out.println("ajax not, subject");
				String unauthorizedUrl = getUnauthorizedUrl();
				if (StringUtils.hasText(unauthorizedUrl)) {
					WebUtils.issueRedirect(request, response, unauthorizedUrl);
				} else {
					WebUtils.toHttp(response).sendError(401);
				}
			}
		}

		return false;
	}

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		System.out.println("role allowd");
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;

		if (rolesArray == null || rolesArray.length == 0) {
			// no roles specified, so nothing to check - allow access.
			return true;
		}

		Set<String> roles = CollectionUtils.asSet(rolesArray);
		for (String role : roles) {
			if (subject.hasRole(role)) {
				return true;
			}
		}
		return false;
	}

}