package com.dt.core.common.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.core.common.base.BaseResult;
import com.dt.tool.util.support.HttpKit;
import com.dt.tool.util.support.StrKit;

/**
 * Requires the requesting user to be authenticated for the request to continue, and if they are
 * not, forces the user to login via by redirecting them to the {@link #setLoginUrl(String)
 * loginUrl} you configure.
 * <p/>
 * <p>
 * This filter constructs a {@link UsernamePasswordToken UsernamePasswordToken} with the values
 * found in {@link #setUsernameParam(String) username}, {@link #setPasswordParam(String) password},
 * and {@link #setRememberMeParam(String) rememberMe} request parameters. It then calls
 * {@link org.apache.shiro.subject.Subject#login(org.apache.shiro.authc.AuthenticationToken)
 * Subject.login(usernamePasswordToken)}, effectively automatically performing a login attempt. Note
 * that the login attempt will only occur when the
 * {@link #isLoginSubmission(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
 * isLoginSubmission(request,response)} is <code>true</code>, which by default occurs when the
 * request is for the {@link #setLoginUrl(String) loginUrl} and is a POST request.
 * <p/>
 * <p>
 * If the login attempt fails, the resulting <code>AuthenticationException</code> fully qualified
 * class name will be set as a request attribute under the {@link #setFailureKeyAttribute(String)
 * failureKeyAttribute} key. This FQCN can be used as an i18n key or lookup mechanism to explain to
 * the user why their login attempt failed (e.g. no account, incorrect password, etc).
 * <p/>
 * <p>
 * If you would prefer to handle the authentication validation and login in your own code, consider
 * using the {@link PassThruAuthenticationFilter} instead, which allows requests to the
 * {@link #loginUrl} to pass through to your application's code directly.
 * @see PassThruAuthenticationFilter
 * @since 0.9
 */
public class MyAuthenticationFilter extends AuthenticatingFilter {
	// TODO - complete JavaDoc
	public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";
	public static final String DEFAULT_USERNAME_PARAM = "username";
	public static final String DEFAULT_PASSWORD_PARAM = "password";
	public static final String DEFAULT_REMEMBER_ME_PARAM = "rememberMe";
	private static final Logger log = LoggerFactory.getLogger(MyAuthenticationFilter.class);
	private String usernameParam = DEFAULT_USERNAME_PARAM;
	private String passwordParam = DEFAULT_PASSWORD_PARAM;
	private String rememberMeParam = DEFAULT_REMEMBER_ME_PARAM;
	private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;

	public MyAuthenticationFilter() {
		setLoginUrl(DEFAULT_LOGIN_URL);
	}
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		// subject.isAuthenticated()表示用户进行了身份验证登录的，即使有Subject.login进行了登
		// subject.isRemembered()：表示用户是通过记住我登录的，此时可能并不是真正的你,且两者二选一,即subject.isAuthenticated()==true，则subject.isRemembered()==false；反之一样
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String uri = httpReq.getRequestURI();
		Subject subject = getSubject(request, response);
		log.info("url:" + uri + ",isRemember:" + subject.isRemembered() + ",isAuth:" + subject.isAuthenticated());
		return subject.isAuthenticated();
	}
	@Override
	public void setLoginUrl(String loginUrl) {
		log.info("setLoginUrl");
		String previous = getLoginUrl();
		if (previous != null) {
			this.appliedPaths.remove(previous);
		}
		super.setLoginUrl(loginUrl);
		if (log.isTraceEnabled()) {
			log.trace("Adding login url to applied paths.");
		}
		this.appliedPaths.put(getLoginUrl(), null);
	}
	public String getUsernameParam() {
		log.info("getUsernameParam");
		return usernameParam;
	}
	/**
	 * Sets the request parameter name to look for when acquiring the username. Unless overridden by
	 * calling this method, the default is <code>username</code>.
	 * @param usernameParam the name of the request param to check for acquiring the username.
	 */
	public void setUsernameParam(String usernameParam) {
		log.info("setUsernameParam");
		this.usernameParam = usernameParam;
	}
	public String getPasswordParam() {
		log.info("getPasswordParam");
		return passwordParam;
	}
	/**
	 * Sets the request parameter name to look for when acquiring the password. Unless overridden by
	 * calling this method, the default is <code>password</code>.
	 * @param passwordParam the name of the request param to check for acquiring the password.
	 */
	public void setPasswordParam(String passwordParam) {
		log.info("setPasswordParam");
		this.passwordParam = passwordParam;
	}
	public String getRememberMeParam() {
		log.info("getRememberMeParam");
		return rememberMeParam;
	}
	/**
	 * Sets the request parameter name to look for when acquiring the rememberMe boolean value.
	 * Unless overridden by calling this method, the default is <code>rememberMe</code>.
	 * <p/>
	 * RememberMe will be <code>true</code> if the parameter value equals any of those supported by
	 * {@link org.apache.shiro.web.util.WebUtils#isTrue(javax.servlet.ServletRequest, String)
	 * WebUtils.isTrue(request,value)}, <code>false</code> otherwise.
	 * @param rememberMeParam the name of the request param to check for acquiring the rememberMe
	 *            boolean value.
	 */
	public void setRememberMeParam(String rememberMeParam) {
		log.info("setRememberMeParam");
		this.rememberMeParam = rememberMeParam;
	}
	public String getFailureKeyAttribute() {
		return failureKeyAttribute;
	}
	public void setFailureKeyAttribute(String failureKeyAttribute) {
		this.failureKeyAttribute = failureKeyAttribute;
	}
	// 如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		log.info("onAccessDenied");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (isLoginRequest(request, response)) {
			// 登录
			if (isLoginSubmission(request, response)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				return executeLogin(request, response);
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				// allow them to see the login page ;)
				return true;
			}
		} else {
			// 未登录
			if (log.isTraceEnabled()) {
				log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
						+ "Authentication url [" + getLoginUrl() + "]");
			}
			log.info(BaseResult.JSON_RETURN_NOT_LOGIN().toJSONString());
			// 判断如果是返回json
			if (isReturnJSON(httpRequest)) {
				httpResponse.setStatus(299);
				httpResponse.setCharacterEncoding("UTF-8");
				httpResponse.getWriter().print(BaseResult.JSON_RETURN_NOT_LOGIN());
				httpResponse.getWriter().flush();
				httpResponse.getWriter().close();
			} else {
				System.out.println("saveRequestAndRedirectToLogin");
				saveRequestAndRedirectToLogin(request, response);
			}
			return false;
		}
	}
	private Boolean isReturnJSON(HttpServletRequest httpRequest) {
		Boolean res = false;
		if (HttpKit.isAjax(httpRequest) || StrKit.endWith(httpRequest.getRequestURL() + "", ".do", true)) {
			res = true;
		}
		return res;
	}
	/**
	 * This default implementation merely returns <code>true</code> if the request is an HTTP
	 * <code>POST</code>, <code>false</code> otherwise. Can be overridden by subclasses for custom
	 * login submission detection behavior.
	 * @param request the incoming ServletRequest
	 * @param response the outgoing ServletResponse.
	 * @return <code>true</code> if the request is an HTTP <code>POST</code>, <code>false</code>
	 *         otherwise.
	 */
	protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
		log.info("isLoginSubmission");
		return (request instanceof HttpServletRequest)
				&& WebUtils.toHttp(request).getMethod().equalsIgnoreCase(POST_METHOD);
	}
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		log.info("createToken");
		String username = getUsername(request);
		String password = getPassword(request);
		return createToken(username, password, request, response);
	}
	protected boolean isRememberMe(ServletRequest request) {
		log.info("isRememberMe:" + getRememberMeParam());
		return WebUtils.isTrue(request, getRememberMeParam());
	}
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		log.info("onLoginSuccess");
		issueSuccessRedirect(request, response);
		// we handled the success redirect directly, prevent the chain from
		// continuing:
		return false;
	}
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		if (log.isDebugEnabled()) {
			log.debug("Authentication exception", e);
		}
		setFailureAttribute(request, e);
		// login failed, let request continue back to the login page:
		return true;
	}
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		String className = ae.getClass().getName();
		request.setAttribute(getFailureKeyAttribute(), className);
	}
	protected String getUsername(ServletRequest request) {
		return WebUtils.getCleanParam(request, getUsernameParam());
	}
	protected String getPassword(ServletRequest request) {
		return WebUtils.getCleanParam(request, getPasswordParam());
	}
}
