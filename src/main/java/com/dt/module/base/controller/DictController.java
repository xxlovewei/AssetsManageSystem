package com.dt.module.base.controller;

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
import com.dt.module.base.service.DictService;

@Controller
@RequestMapping(value = "/api")
public class DictController extends BaseController {
	@Autowired
	private DictService dictService = null;

	@RequestMapping(value = "/dict/queryDict.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON)
	public ResData queryDict() {
		return dictService.queryDict();
	}
	@RequestMapping(value = "/dict/saveDict.do")
	@Res
	@Acl
	public ResData saveDict() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String dict_id = ps.getString("dict_id");
		if (ToolUtil.isEmpty(dict_id)) {
			return dictService.addDict(ps);
		} else {
			return dictService.updateDict(ps);
		}
	}
	@RequestMapping(value = "/dict/deleteDict.do")
	@Res
	@Acl
	public ResData deleteDict(String id) {
		return dictService.deleteDict(id);
	}
	@RequestMapping(value = "/dict/queryByDictId.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON)
	public ResData queryByDictId(String id) {
		return dictService.queryDictById(id);
	}
	@RequestMapping(value = "/dict/deleteDictItem.do")
	@Res
	@Acl
	public ResData deleteDictItem(String id) {
		return dictService.deleteDictItem(id);
	}
	@RequestMapping(value = "/dict/queryDictItem.do")
	@Res
	@Acl(value = Acl.TYPE_USER_COMMON)
	public ResData queryDictItem(String id) {
		return dictService.queryDictItem(id);
	}
	@RequestMapping(value = "/dict/queryDictItemById.do")
	@Res
	@Acl
	public ResData queryDictItemById(String id) {
		return dictService.queryDictItemById(id);
	}
	@RequestMapping(value = "/dict/saveDictItem.do")
	@Res
	@Acl
	public ResData saveDictItem() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String id = ps.getString("dict_item_id");
		if (ToolUtil.isEmpty(id)) {
			return dictService.addDictItem(ps);
		} else {
			return dictService.updateDictItem(ps);
		}
	}
}
