package com.dt.module.base.controller;

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
import com.dt.module.base.service.DictService;

@Controller
@RequestMapping(value = "/api")
public class DictController extends BaseController {
	@Autowired
	private DictService dictService = null;

	@RequestMapping(value = "/dict/queryDict.do")
	@ResponseBody
	@Acl(value = Acl.TYPE_USER_COMMON)
	public R queryDict() {
		return dictService.queryDict();
	}
	@RequestMapping(value = "/dict/saveDict.do")
	@ResponseBody
	@Acl
	public R saveDict() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String dict_id = ps.getString("dict_id");
		if (ToolUtil.isEmpty(dict_id)) {
			return dictService.addDict(ps);
		} else {
			return dictService.updateDict(ps);
		}
	}
	@RequestMapping(value = "/dict/deleteDict.do")
	@ResponseBody
	@Acl
	public R deleteDict(String id) {
		return dictService.deleteDict(id);
	}
	@RequestMapping(value = "/dict/queryByDictId.do")
	@ResponseBody
	@Acl(value = Acl.TYPE_USER_COMMON)
	public R queryByDictId(String id) {
		return dictService.queryDictById(id);
	}
	@RequestMapping(value = "/dict/deleteDictItem.do")
	@ResponseBody
	@Acl
	public R deleteDictItem(String id) {
		return dictService.deleteDictItem(id);
	}
	@RequestMapping(value = "/dict/queryDictItem.do")
	@ResponseBody
	@Acl(value = Acl.TYPE_USER_COMMON)
	public R queryDictItem(String id) {
		return dictService.queryDictItem(id);
	}
	@RequestMapping(value = "/dict/queryDictItemById.do")
	@ResponseBody
	@Acl
	public R queryDictItemById(String id) {
		return dictService.queryDictItemById(id);
	}
	@RequestMapping(value = "/dict/saveDictItem.do")
	@ResponseBody
	@Acl
	public R saveDictItem() {
		TypedHashMap<String, Object> ps = HttpKit.getRequestParameters();
		String id = ps.getString("dict_item_id");
		if (ToolUtil.isEmpty(id)) {
			return dictService.addDictItem(ps);
		} else {
			return dictService.updateDictItem(ps);
		}
	}
}
