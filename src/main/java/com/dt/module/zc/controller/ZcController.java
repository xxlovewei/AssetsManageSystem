package com.dt.module.zc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dt.core.annotion.Acl;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.base.R;
import com.dt.core.tool.util.ConvertUtil;
import com.dt.core.tool.util.ToolUtil;
import com.dt.module.base.service.ISysUserInfoService;
import com.dt.module.cmdb.entity.ResActionItem;
import com.dt.module.cmdb.service.IResActionItemService;
import com.dt.module.ct.entity.CtCategoryRoot;
import com.dt.module.ct.service.ICtCategoryRootService;
import com.dt.module.flow.entity.SysProcessData;
import com.dt.module.flow.entity.SysProcessDef;
import com.dt.module.flow.entity.SysProcessForm;
import com.dt.module.flow.service.ISysProcessDataService;
import com.dt.module.flow.service.ISysProcessDefService;
import com.dt.module.flow.service.ISysProcessFormService;
import com.dt.module.flow.service.impl.SysUfloProcessService;
import com.dt.module.form.entity.SysForm;
import com.dt.module.form.service.ISysFormService;
import com.dt.module.form.service.impl.FormServiceImpl;
import com.dt.module.zc.service.impl.ZcChangeService;
import com.dt.module.zc.service.impl.ZcCommonService;
import com.dt.module.zc.service.impl.ZcService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: algernonking
 * @date: Dec 2, 2019 2:31:20 PM
 * @Description: TODO
 */
@Controller
@RequestMapping("/api/zc")
public class ZcController extends BaseController {

    @Autowired
    FormServiceImpl formServiceImpl;


    @Autowired
    ZcChangeService zcChangeService;

    @Autowired
    ISysProcessDefService SysProcessDefServiceImpl;


    @Autowired
    ISysProcessDataService SysProcessDataServiceImpl;

    @Autowired
    ISysProcessFormService SysProcessFormServiceImpl;

    @Autowired
    ISysFormService SysFormServiceImpl;

    @Autowired
    IResActionItemService ResActionItemServiceImpl;

    @Autowired
    ZcService zcService;

    @Autowired
    ISysUserInfoService SysUserInfoServiceImpl;

    @Autowired
    ICtCategoryRootService CtCategoryRootServiceImpl;


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/fastProcessItemCheck.do")
    public R fastProcessItemCheck(String type, String items) {
        return zcService.fastProcessItemCheck(type, items);
    }

    //uid 每组加缓存
    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryDictFast.do")
    @Transactional
    public R queryDictFast(String uid, String zchccat, String comppart, String comp, String belongcomp, String dicts, String parts, String partusers, String classid, String classroot, String zccatused) {

        return zcService.queryDictFast(uid, zchccat, comppart, comp, belongcomp, dicts, parts, partusers, classid, classroot, zccatused);

    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/downloadZcImage.do")
    public void downloadAllQr(String type, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam("data") String data) throws IOException, WriterException {

        BarcodeFormat format = BarcodeFormat.QR_CODE;
        int w = 500;
        int h = 500;
        if ("rwm".equals(type)) {
            format = BarcodeFormat.QR_CODE;
            w = 450;
            h = 450;
        } else if ("txm".equals(type)) {
            format = BarcodeFormat.CODE_128;
            h = 180;
            w = 450;
        }
        httpServletResponse.setContentType("application/zip");
        httpServletResponse.setHeader("Content-disposition",
                "attachment; filename=" + new String("erm".getBytes(),
                        "ISO-8859-1") + ".zip");

        OutputStream outputStream = httpServletResponse.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        JSONArray data_arr = JSONArray.parseArray(data);
        for (int i = 0; i < data_arr.size(); i++) {

            BitMatrix bitMatrix = new MultiFormatWriter().encode(data_arr.getString(i), format, w, h);
            BufferedImage buffImg = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ZipEntry entry = new ZipEntry(data_arr.getString(i) + ".jpg");
            zipOutputStream.putNextEntry(entry);
            ImageIO.write(buffImg, "jpg", zipOutputStream);
            zipOutputStream.flush();
        }

        zipOutputStream.close();

        outputStream.flush();
        outputStream.close();
    }


    @ResponseBody
    @Acl(info = "zc", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryzclabelcols.do")
    public R queryzclabelcols() {

        JSONArray res = new JSONArray();
        JSONObject e1 = new JSONObject();
        e1.put("name", "资产名称");
        res.add(e1);

        JSONObject e2 = new JSONObject();
        e2.put("model", "资产型号");
        res.add(e2);

        JSONObject e3 = new JSONObject();
        e3.put("buy_time", "采购日期");
        res.add(e3);

        JSONObject e4 = new JSONObject();
        e4.put("part_id", "使用部门");
        res.add(e4);

        JSONObject e5 = new JSONObject();
        e5.put("loc", "存放区域");
        res.add(e5);
        return R.SUCCESS_OPER(res);

    }


    @ResponseBody
    @Acl(info = "获取后台类目", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectZcCats.do")
    public R selectZcCats() {
        QueryWrapper<CtCategoryRoot> ew = new QueryWrapper<CtCategoryRoot>();
        ew.in("id", '8', '3', '7');
        return R.SUCCESS_OPER(CtCategoryRootServiceImpl.list(ew));
    }


    @ResponseBody
    @Acl(info = "查询单据", value = Acl.ACL_USER)
    @RequestMapping(value = "/selectBillById.do")
    public R selectBillById(String id) {

        SysProcessData sd = SysProcessDataServiceImpl.getById(id);
        JSONObject res = JSONObject.parseObject(JSON.toJSONString(sd, SerializerFeature.WriteDateUseDateFormat));
        String sql = "select " + ZcCommonService.resSqlbody + " t.* from res t,res_action_item item where item.dr='0' and  t.id=item.resid and item.busuuid=?";
        res.put("items", ConvertUtil.OtherJSONObjectToFastJSONArray(db.query(sql, sd.getBusid()).toJsonArrayWithJsonObject()));
        QueryWrapper<SysProcessForm> ew = new QueryWrapper<SysProcessForm>();
        ew.and(i -> i.eq("processdataid", id));
        SysProcessForm form = SysProcessFormServiceImpl.getOne(ew);
        if (form != null) {
            res.put("formdata", form.getFdata());
            res.put("formconf", form.getFtpldata());
        }
        return R.SUCCESS_OPER(res);
    }


    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/queryZcColCtlShow.do")
    public R queryZcColCtlShow() {
        return zcService.queryZcColCtlShow();
    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_ALLOW)
    @RequestMapping(value = "/modifyZcColCtlShow.do")
    public R modifyZcColCtlShow(String id, String json) {
        return zcService.modifyZcColCtlShow(id, json);

    }

    @ResponseBody
    @Acl(info = "", value = Acl.ACL_USER)
    @RequestMapping(value = "/queryZcColCtlById.do")
    public R queryZcColCtlById(String id) {
        return zcService.queryZcColCtlById(id);
    }

}
