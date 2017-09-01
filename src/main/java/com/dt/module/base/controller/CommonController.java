package com.dt.module.base.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.dt.core.common.annotion.Acl;

/**
 * @author: algernonking
 * @date: 2017年8月31日 下午9:11:28
 * @Description: TODO
 */
@Controller
@RequestMapping(value = "/api")
public class CommonController {
	// Collection<Session> sessions = sessionDAO.getActiveSessions();
	// for (Session session : sessions) {
	// System.out.println("session:" + session.getId());
	// }
	// _log.info("userId:" + ShiroKit.getUser().id + "|" +
	// super.getSession().getAttribute("user_id"));
	// System.out.println(ShiroKit.getSubject().isRemembered());
	// @RequestMapping("/getAllUrl.do")
	// @ResponseBody
 
	// public Set<String> getAllUrl(HttpServletRequest request) {
	// Set<String> result = new HashSet<String>();
	// WebApplicationContext wc = (WebApplicationContext) request
	// .getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	// Map<String, Object> beans = wc.getBeansWithAnnotation(Acl.class);
	// System.out.println(beans.size());
	// for (Object bean : beans.values()) {
	// Acl am = (Acl) bean;
	// System.out.println(am.value());
	// System.err.println(bean == null ? "null" : bean.getClass().getName());
	// }
	// RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
	// // Acl ac=wc.getBean(Acl.class);
	// Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
	// for (RequestMappingInfo rmi : handlerMethods.keySet()) {
	// PatternsRequestCondition pc = rmi.getPatternsCondition();
	// Set<String> pSet = pc.getPatterns();
	// result.addAll(pSet);
	// }
	// for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
	// System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
	// //获取url
	// RequestMappingInfo rmi=entry.getKey();
	// PatternsRequestCondition pc = rmi.getPatternsCondition();
	// Set<String> pSet = pc.getPatterns();
	// result.addAll(pSet);
	//
	// HandlerMethod hm=entry.getValue();
	// Acl am = ((HandlerMethod) hm).getMethodAnnotation(Acl.class);
	// System.out.println(am.value());
	//
	// }
	// return result;
	// }
}
