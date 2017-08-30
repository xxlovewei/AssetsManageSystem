package com.dt.module.product.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.ToolUtil;
import com.dt.core.common.util.support.HttpKit;
import com.dt.core.common.util.support.TypedHashMap;
import com.dt.module.product.service.BrandService;

@Controller
public class BrandController extends BaseController {
	 
	@Autowired
	private BrandService brandService;

	@RequestMapping("/api/brand/brandQuery.do")
	@Res
	@Acl(value="allow")
	@RequiresPermissions("account:create")
	public ResData brandQuery() {
		return brandService.queryBrand();
	}
	@RequestMapping("/api/brand/brandQueryById.do")
	@Res
	@Acl
	public ResData brandQueryById(String brand_id) {
		return brandService.queryBrandById(brand_id);
	}
	@RequestMapping("/api/brand/brandSave.do")
	@Res
	@Acl
	public ResData brandSave() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("BRAND_ID");
		if (ToolUtil.isEmpty(id)) {
			return brandService.addBrand(ps);
		} else {
			return brandService.updateBrand(ps);
		}
	}
	@RequestMapping("/api/brand/brandDelete.do")
	@Res
	@Acl
	public ResData brandDelete(String brand_id) {
		return brandService.deleteBrand(brand_id);
	}
}
