package com.dt.core.common.base;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.core.tool.util.support.HttpKit;

public class BaseController extends BaseSC {
	private static Logger _log = LoggerFactory.getLogger(BaseController.class);

	protected HttpServletRequest getHttpServletRequest() {
		return HttpKit.getRequest();
	}
	protected HttpServletResponse getHttpServletResponse() {
		return HttpKit.getResponse();
	}
	protected HttpSession getSession() {
		return HttpKit.getRequest().getSession();
	}
	protected HttpSession getSession(Boolean flag) {
		return HttpKit.getRequest().getSession(flag);
	}
	protected String getPara(String name) {
		return HttpKit.getRequest().getParameter(name);
	}
	protected void setAttr(String name, Object value) {
		HttpKit.getRequest().setAttribute(name, value);
	}
	public String getUserId() {
		String user_id = (String) HttpKit.getRequest().getSession().getAttribute("user_id");
		return user_id;
	}
	protected Integer getSystemInvokCount() {
		return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
	}
	/**
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	// protected <T> PageInfoBT<T> packForBT(Page<T> page) {
	// return new PageInfoBT<T>(page);
	// }
	/**
	 * 包装一个list，让list增加额外属性
	 */
	// protected Object warpObject(BaseControllerWarpper warpper) {
	// return warpper.warp();
	// }
	/**
	 * 删除cookie
	 */
	protected void deleteCookieByName(String cookieName) {
		Cookie[] cookies = this.getHttpServletRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				Cookie temp = new Cookie(cookie.getName(), "");
				temp.setMaxAge(0);
				this.getHttpServletResponse().addCookie(temp);
			}
		}
	}
 
	// protected ResponseEntity<byte[]> renderFile(String fileName, String
	// filePath) {
	// byte[] bytes = FileUtil.toByteArray(filePath);
	// return renderFile(fileName, bytes);
	// }
	 
	/*
	 * protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) { String
	 * dfileName = null; try { dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1"); }
	 * catch (UnsupportedEncodingException e) { e.printStackTrace(); } HttpHeaders headers = new
	 * HttpHeaders(); headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	 * headers.setContentDispositionFormData("attachment", dfileName); return new
	 * ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED); }
	 * @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class }) public
	 * String authenticationException(HttpServletRequest request, HttpServletResponse response) {
	 * JSONObject obj = new JSONObject(); obj.put("code", "-999"); obj.put("message", "未登录");
	 * writeJson(obj, response); return null; } private void writeJson(JSONObject obj,
	 * HttpServletResponse response) { PrintWriter out = null; try {
	 * response.setCharacterEncoding("UTF-8"); response.setContentType(
	 * "application/json; charset=utf-8"); out = response.getWriter();
	 * out.write(obj.toJSONString()); } catch (IOException e) { e.printStackTrace(); } finally { if
	 * (out != null) { out.close(); } } }
	 */
	public String warpObject(Object o) {
		if (o instanceof R) {
			return ((R) o).asJsonStr();
		} else {
			return o.toString();
		}
	}
}
