package com.dt.module.zc.controller;

import com.deepoove.poi.XWPFTemplate;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/api/zc/export")
public class ZcExportController extends BaseController {

    @ResponseBody
    @Acl(info = "zc", value = Acl.ACL_USER)
    @RequestMapping(value = "/test.do")
    public R test() {

        return R.SUCCESS_OPER();
    }

    public static void main(String[] args) {
        System.out.println("hello world");

        try {
            HashMap<String, Object> m = new HashMap<String, Object>();
            m.put("name", "poi-tl 模板引擎");
            XWPFTemplate tpl = XWPFTemplate.compile("/Users/lank/Desktop/labeltpl.docx").render(m);
            FileOutputStream out = new FileOutputStream("output.docx");
            tpl.write(out);
            out.flush();
            out.close();
            tpl.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
