package com.dt.module.base.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.SQL;
import com.dt.util.DBUtil;
import com.dt.util.SpringContextUtil;
import com.dt.util.ToolUtil;

/**
 * @author: algernonking
 * @date: Nov 4, 2017 9:13:58 AM
 * @Description: TODO
 */
@Service
public class ApiService extends BaseService {
	
	private static Logger _log = LoggerFactory.getLogger(ApiService.class);
	
	public static ApiService me() {
		return SpringContextUtil.getBean(ApiService.class);
	}
	public ResData queryApi() {
		String sql = "select * from sys_api";
		return ResData.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
	}
	public ResData verifyApi() {
		return ResData.SUCCESS_OPER();
	}
	public ResData updateApi() {
		List<SQL> sqls = new ArrayList<SQL>();
		WebApplicationContext wc = (WebApplicationContext) SpringContextUtil.getApplicationContext();
		RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
			RequestMappingInfo rmi = entry.getKey();
			PatternsRequestCondition pc = rmi.getPatternsCondition();
			Set<String> pSet = pc.getPatterns();
			HandlerMethod hm = entry.getValue();
			Acl am = ((HandlerMethod) hm).getMethodAnnotation(Acl.class);
			if (ToolUtil.isNotEmpty(am)) {
				String aclvalue = am.value();
				Iterator<String> it = pSet.iterator();
				while (it.hasNext()) {
					String str = it.next(); 
					_log.info(str + "," + aclvalue);
					Insert me = new Insert("sys_api");
					me.set("id", db.getUUID());
					me.setIf("ct", str);
					me.setIf("ctacl", aclvalue);
					me.setIf("apitype", "url");
					me.setSE("rectime", DBUtil.getDBDateString(db.getDBType()));
					sqls.add(me);
				}
			}
			
		}
		if (sqls.size() > 0) {
			_log.info("Save collect Api.");
			db.tabTruncateExecute("sys_api");
			db.executeSQLList(sqls);
		}
		return ResData.SUCCESS_OPER();
	}
}
