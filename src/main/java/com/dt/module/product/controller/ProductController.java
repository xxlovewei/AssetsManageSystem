package com.dt.module.product.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.util.support.HttpKit;
import com.dt.module.product.service.ProductService;

@Controller
public class ProductController extends BaseController {
	@Autowired
	ProductService productService;

	@RequestMapping("/api/product/prodOffOn.do")
	@Res
	@Acl
	public ResData prodOffOn(String prods, String is_off) {
		return productService.prodOffOn(prods, is_off);
	}
	@RequestMapping("/api/product/prodModifySaleAttr.do")
	@Res
	@Acl
	@Transactional
	public ResData prodModifySaleAttr() {
		return productService.updateProdSaleAttr(HttpKit.getRequestParameters());
	}
	@RequestMapping("/api/product/prodModifyBaseAttr.do")
	@Res
	@Acl
	public ResData prodModifyBaseAttr() {
		return productService.updateProdBaseAttr(HttpKit.getRequestParameters());
	}
	@RequestMapping("/api/product/prodDelete.do")
	@Res
	@Acl
	public ResData prodDelete(String prods) {
		return productService.deleteProds(prods);
	}
	@RequestMapping("/api/product/prodQueryByCat.do")
	@Res
	@Acl
	public ResData prodQueryByCat(String cat_id) {
		return productService.queryProdByCat(cat_id);
	}
	// 查询本商品所有信息
	@RequestMapping("/api/product/prodQueryBySpu.do")
	@Res
	@Acl
	public ResData prodQueryBySpu(String spu) throws IOException {
		return productService.queryProdBySpu(spu);
	}
	@RequestMapping("/api/product/prodPublish.do")
	@Res
	@Acl
	@Transactional
	public ResData prodPublish() {
		return productService.publishProduct(HttpKit.getRequestParameters());
	}
}
