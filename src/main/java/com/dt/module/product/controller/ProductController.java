package com.dt.module.product.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dt.core.annotion.Acl;
import com.dt.core.annotion.Res;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.ResData;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.product.service.ProductService;

@Controller
@RequestMapping("/api")
public class ProductController extends BaseController {
	@Autowired
	ProductService productService;

	@RequestMapping("/product/getProdPics.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "获取产品轮训图片")
	public ResData getProdPics(String spu) {
		return ResData.SUCCESS_OPER(productService.getProdPics(spu));
	}

	@RequestMapping("/product/updateProdPics.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "更新产品轮训图片")
	public ResData updateProdPics(String spu, String pics) {
		return productService.updateProdPics(spu, pics);
	}

	@RequestMapping("/product/prodOffOn.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "产品上下架")
	public ResData prodOffOn(String prods, String is_off) {
		return productService.prodOffOn(prods, is_off);
	}

	@RequestMapping("/product/prodModifySaleAttr.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "修改产品销售属性")
	@Transactional
	public ResData prodModifySaleAttr() {
		return productService.updateProdSaleAttr(HttpKit.getRequestParameters());
	}

	@RequestMapping("/product/prodModifyBaseAttr.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "修改产品基本属性")
	public ResData prodModifyBaseAttr() {
		return productService.updateProdBaseAttr(HttpKit.getRequestParameters());
	}

	@RequestMapping("/product/prodDelete.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "删除产品")
	public ResData prodDelete(String prods) {
		return productService.deleteProds(prods);
	}

	@RequestMapping("/product/prodQueryByCat.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "按照后台类目查找产品")
	public ResData prodQueryByCat(String cat_id) {
		return productService.queryProdByCat(cat_id);
	}

	@RequestMapping("/product/prodQueryBySpu.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON, info = "根据产品Id产品信息[后台]")
	public ResData prodQueryBySpu(String spu) throws IOException {
		return productService.queryProdBySpu(spu);
	}

	@RequestMapping("/product/prodPublish.do")
	@Res
	@Acl(value = Acl.TYPE_DENY, info = "后台发布产品")
	@Transactional
	public ResData prodPublish() {
		return productService.publishProduct(HttpKit.getRequestParameters());
	}

	@RequestMapping("/product/queryProdBySpuForMall.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "根据产品Id查询产品信息[微商城]")

	public ResData queryProdBySpuForMall(String spu) {
		return productService.queryProdBySpuForMall(spu);
	}

	@RequestMapping("/product/queryProdSkuDetail.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "产品销售属性选择后获取sku详细数据")
	public ResData queryProdSkuDetail(String spu, String propertychildids) {
		return productService.queryProdSkuDetail(spu, propertychildids);
	}

	@RequestMapping("/product/queryProdBySpuNotSkuForMall.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "根据产品SPU获取数据[微商城]")
	public ResData queryProdBySpuNotSkuForMall(String spu) {
		return productService.queryProdBySpuNotSkuForMall(spu);
	}

	@RequestMapping("/product/queryProdBySpuSkuForMall.do")
	@Res
	@Acl(value = Acl.TYPE_ALLOW, info = "根据产品SPU和SKU获取数据[微商城]")
	public ResData queryProdBySpuNotSkuForMall(String spu, String sku) {
		return productService.queryProdBySpuSkuForMall(spu, sku);
	}

}
