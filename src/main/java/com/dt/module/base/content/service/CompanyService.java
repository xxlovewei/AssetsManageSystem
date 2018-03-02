package com.dt.module.base.content.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;

/**
 * @author: algernonking
 * @date: 2017年8月12日 下午9:19:01
 * @Description: TODO
 */
@Service
public class CompanyService extends BaseService {
	@Autowired
	ContentService contentService;
	public static String COMPANY_ID = "sys_company";

	/**
	 * @Description: 查询公司内容
	 */
	public R queryCompany() {
		return contentService.queryContentById(COMPANY_ID);
	}
	/**
	 * @Description: 更新公司内容
	 */
	public R updateCompany(TypedHashMap<String, Object> ps) {
		if(!initCompany()){
			return R.FAILURE_NODATA();
		}
		if (ps.containsKey("id")) {
			ps.remove("id");
		}
		ps.put("id", COMPANY_ID);
		contentService.updateContent(ps);
		return R.SUCCESS_OPER();
	}
	/**
	 * @Description: 初始化公司数据
	 */
	public Boolean initCompany() {
		R rs = contentService.queryContentById(COMPANY_ID);
		if (rs.isSuccess()) {
			return true;
		} else {
			TypedHashMap<String, Object> ps = new TypedHashMap<String, Object>();
			ps.put("id", COMPANY_ID);
			ps.put("selfid", "Y");
			ps.put("title", "");
			contentService.addContent(ps, ContentService.TYPE_OHTER);
			R rs2 = contentService.queryContentById(COMPANY_ID);
			if (rs2.isSuccess()) {
				return true;
			} else {
				return false;
			}
		}
	}
}
