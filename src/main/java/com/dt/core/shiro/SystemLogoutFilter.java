package com.dt.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: algernonking
 * @date: 2017年8月31日 下午5:46:32
 * @Description: TODO
 */
public class SystemLogoutFilter extends LogoutFilter {
	private static final Logger log = LoggerFactory.getLogger(SystemLogoutFilter.class);

	/**
	 * @Title:SystemLogoutFilter
	 * @Description:TODO
	 * @param logoutSuccessHandler
	 * @param handlers
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		String redirectUrl = getRedirectUrl(request, response, subject);
		log.info(subject.getSession().getId() + " to logout");
		try {
			subject.logout();
		} catch (SessionException ise) {
			log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
		}
		issueRedirect(request, response, redirectUrl);
		return false;
	}
}
