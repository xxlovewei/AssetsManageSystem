package com.dt.core.shiro;

 

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.StrKit;

/**
 * oauth2过滤器
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-20 13:00
 */
public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if(StringUtils.isBlank(token)){
            return null;
        }

        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    private Boolean isReturnJSON(HttpServletRequest httpRequest) {
		Boolean res = false;
		if (HttpKit.isAjax(httpRequest) || StrKit.endWith(httpRequest.getRequestURL() + "", ".do", true)) {
			res = true;
		}

		return res;

	}
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

    	
        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isBlank(token)){
          
           // String json = new Gson().toJson(R.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));
        
        	// 判断如果是ajax请求
			if (isReturnJSON(httpRequest)) {
				httpResponse.setStatus(299);
				httpResponse.setCharacterEncoding("UTF-8");
				JSONObject r = new JSONObject();
				r.put("success", false);
				r.put("message", "not Login");
				httpResponse.getWriter().print(r.toJSONString());
			} else {
				System.out.println("saveRequestAndRedirectToLogin");
				saveRequestAndRedirectToLogin(request, response);
			}

		

            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
//            R r = R.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());
//
//            String json = new Gson().toJson(r);
            String json="";
            json="err22";
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {

        }

        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }

        return token;
    }


}
