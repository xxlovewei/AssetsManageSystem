package com.dt.core.common.base;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.dt.core.tool.lang.PropertiesFileUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.core.tool.util.support.StrKit;

public class BaseController extends BaseSC {
 
	@ExceptionHandler
	public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		String msg = ExceptionUtils.getRootCauseMessage(exception) == null ? ""
				: ExceptionUtils.getRootCauseMessage(exception);
		request.setAttribute("ex", exception);
		if (null != request.getHeader("X-Requested-With")
				&& "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			request.setAttribute("requestHeader", "ajax");
		}
		if (isReturnJSON(request)) {
			try {
				response.setCharacterEncoding("UTF-8");
				response.setHeader("content-type", "text/html;charset=UTF-8");
				response.getWriter().print(R.FAILURE(msg));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		// shiro没有权限异常
		if (exception instanceof UnauthorizedException) {
			return "/40311.jsp";
		}
		// shiro会话已过期异常
		if (exception instanceof InvalidSessionException) {
			return "/erroraa.jsp";
		}
		return "/errorbb.jsp";
	}

	private Boolean isReturnJSON(HttpServletRequest httpRequest) {
		Boolean res = false;
		if (HttpKit.isAjax(httpRequest) || StrKit.endWith(httpRequest.getRequestURL() + "", ".do", true)) {
			res = true;
		}
		return res;
	}

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
	 * protected ResponseEntity<byte[]> renderFile(String fileName, byte[]
	 * fileBytes) { String dfileName = null; try { dfileName = new
	 * String(fileName.getBytes("gb2312"), "iso8859-1"); } catch
	 * (UnsupportedEncodingException e) { e.printStackTrace(); } HttpHeaders
	 * headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	 * headers.setContentDispositionFormData("attachment", dfileName); return
	 * new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED); }
	 * 
	 * @ExceptionHandler({ UnauthenticatedException.class,
	 * AuthenticationException.class }) public String
	 * authenticationException(HttpServletRequest request, HttpServletResponse
	 * response) { JSONObject obj = new JSONObject(); obj.put("code", "-999");
	 * obj.put("message", "未登录"); writeJson(obj, response); return null; }
	 * private void writeJson(JSONObject obj, HttpServletResponse response) {
	 * PrintWriter out = null; try { response.setCharacterEncoding("UTF-8");
	 * response.setContentType( "application/json; charset=utf-8"); out =
	 * response.getWriter(); out.write(obj.toJSONString()); } catch (IOException
	 * e) { e.printStackTrace(); } finally { if (out != null) { out.close(); } }
	 * }
	 */
	public String warpObject(Object o) {
		if (o instanceof R) {
			return ((R) o).asJsonStr();
		} else {
			return o.toString();
		}
	}

	/**
	 * 返回jsp视图
	 * 
	 * @param path
	 * @return
	 */
	public static String jsp(String path) {
		return path.concat("");
	}

	/**
	 * 返回thymeleaf视图
	 * 
	 * @param path
	 * @return
	 */
	public static String thymeleaf(String path) {
		String folder = PropertiesFileUtil.getInstance().get("app.name");
		return "/".concat(folder).concat(path).concat(".html");
	}
}
