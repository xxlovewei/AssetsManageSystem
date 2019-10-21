package com.dt.module.cmdb.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONArray;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.support.HttpKit;
import com.dt.module.cmdb.service.ResExtService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * @author: algernonking
 * @date: Oct 21, 2019 7:28:06 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/base/res")
public class ResExtExportController extends BaseController {

	@Autowired
	ResExtService resExtService;

	@RequestMapping("/exportServerData.do")
	@Acl(value = Acl.ACL_USER)
	public void queryOrdersDownload(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		TypedHashMap<String, Object> ps = (TypedHashMap<String, Object>) HttpKit.getRequestParameters();

		R res = resExtService.queryResAllByClassGetData(ps.getString("id"), ps.getString("wb"), ps.getString("env"),
				ps.getString("recycle"), ps.getString("loc"), ps.getString("search"));

		JSONArray data = res.queryDataToJSONArray();
		List<ServerEntity> data_excel = new ArrayList<ServerEntity>();
		for (int i = 0; i < data.size(); i++) {
			data_excel.add(new ServerEntity(data.getJSONObject(i)));
		}

		ExportParams parms = new ExportParams("服务器设备信息", "服务器");

	 
		Workbook workbook;
		workbook = ExcelExportUtil.exportExcel(parms, ServerEntity.class, data_excel);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-download");
		String filedisplay = "服务器.xls";
		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);
		try {
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
