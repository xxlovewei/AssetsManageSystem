package com.dt.module.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.ToolUtil;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.mall.service.BrandService;

@Controller
@RequestMapping("/api")
public class BrandController extends BaseController {
	@Autowired
	private BrandService brandService;

	@RequestMapping("/brand/brandQuery.do")
	@ResponseBody
	@Acl(value = Acl.ACL_ALLOW,info="查询品牌")
	public R brandQuery() {
		return brandService.queryBrand();
	}
	@RequestMapping("/brand/brandQueryById.do")
	@ResponseBody
	@Acl(info="根据Id查询品牌")
	public R brandQueryById(String brand_id) {
		return brandService.queryBrandById(brand_id);
	}
	@RequestMapping("/brand/brandSave.do")
	@ResponseBody
	@Acl(info="保存品牌")
	public R brandSave() {
		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();
		String id = ps.getString("brand_id");
		if (ToolUtil.isEmpty(id)) {
			return brandService.addBrand(ps);
		} else {
			return brandService.updateBrand(ps);
		}
	}
	@RequestMapping("/brand/brandDelete.do")
	@ResponseBody
	@Acl(info="删除品牌")
	public R brandDelete(String brand_id) {
		return brandService.deleteBrand(brand_id);
	}
}
