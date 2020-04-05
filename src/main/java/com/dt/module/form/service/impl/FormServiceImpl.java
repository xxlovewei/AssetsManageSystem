package com.dt.module.form.service.impl;

import java.util.HashMap;
import java.util.Iterator;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.tool.encrypt.MD5Util;
import com.dt.core.tool.util.ToolUtil;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2020-03-28
 */
@Service
public class FormServiceImpl extends BaseService {

    public static String OPER_TYPE_INSERT = "insert";

    public static String OPER_TYPE_UPDATE = "update";

    private HashMap<String, String> parseFromJsonMetaCol() {
        HashMap<String, String> map = new HashMap<String, String>();
        JSONArray cols = new JSONArray();
        JSONObject e0 = new JSONObject();
        e0.put("col", "dattach1");
        cols.add(e0);
        JSONObject e1 = new JSONObject();
        e1.put("col", "daddr");
        cols.add(e1);
        JSONObject e2 = new JSONObject();
        e2.put("col", "dattach2");
        cols.add(e2);
        JSONObject e3 = new JSONObject();
        e3.put("col", "dattach3");
        cols.add(e3);
        JSONObject e4 = new JSONObject();
        e4.put("col", "dbacktime");
        cols.add(e4);
        JSONObject e5 = new JSONObject();
        e5.put("col", "dcontact");
        cols.add(e5);
        JSONObject e6 = new JSONObject();
        e6.put("col", "dct");
        cols.add(e6);
        JSONObject e7 = new JSONObject();
        e7.put("col", "df1");
        cols.add(e7);
        JSONObject e8 = new JSONObject();
        e8.put("col", "df2");
        cols.add(e8);
        JSONObject e9 = new JSONObject();
        e9.put("col", "df3");
        cols.add(e9);
        JSONObject e10 = new JSONObject();
        e10.put("col", "df4");
        cols.add(e10);
        JSONObject e11 = new JSONObject();
        e11.put("col", "df5");
        cols.add(e11);
        JSONObject e12 = new JSONObject();
        e12.put("col", "df6");
        cols.add(e12);
        JSONObject e13 = new JSONObject();
        e13.put("col", "df7");
        cols.add(e13);
        JSONObject e14 = new JSONObject();
        e14.put("col", "df8");
        cols.add(e14);
        JSONObject e15 = new JSONObject();
        e15.put("col", "df9");
        cols.add(e15);
        JSONObject e16 = new JSONObject();
        e16.put("col", "df10");
        cols.add(e16);
        JSONObject e17 = new JSONObject();
        e17.put("col", "dfile");
        cols.add(e17);

        JSONObject e19 = new JSONObject();
        e19.put("col", "dlevel");
        cols.add(e19);
        JSONObject e20 = new JSONObject();
        e20.put("col", "dmark");
        cols.add(e20);
        JSONObject e21 = new JSONObject();
        e21.put("col", "dmessage");
        cols.add(e21);
        JSONObject e22 = new JSONObject();
        e22.put("col", "dmethod");
        cols.add(e22);
        JSONObject e23 = new JSONObject();
        e23.put("col", "dn1");
        cols.add(e23);
        JSONObject e24 = new JSONObject();
        e24.put("col", "dn2");
        cols.add(e24);
        JSONObject e25 = new JSONObject();
        e25.put("col", "dn3");
        cols.add(e25);
        JSONObject e26 = new JSONObject();
        e26.put("col", "dn4");
        cols.add(e26);
        JSONObject e27 = new JSONObject();
        e27.put("col", "dn5");
        cols.add(e27);
        JSONObject e28 = new JSONObject();
        e28.put("col", "dn6");
        cols.add(e28);
        JSONObject e29 = new JSONObject();
        e29.put("col", "dn7");
        cols.add(e29);
        JSONObject e30 = new JSONObject();
        e30.put("col", "dn8");
        cols.add(e30);
        JSONObject e31 = new JSONObject();
        e31.put("col", "dn9");
        cols.add(e31);
        JSONObject e32 = new JSONObject();
        e32.put("col", "dn10");
        cols.add(e32);
        JSONObject e33 = new JSONObject();
        e33.put("col", "dname");
        cols.add(e33);
        JSONObject e34 = new JSONObject();
        e34.put("col", "dpic1");
        cols.add(e34);
        JSONObject e35 = new JSONObject();
        e35.put("col", "dpic2");
        cols.add(e35);
        JSONObject e36 = new JSONObject();
        e36.put("col", "dpic3");
        cols.add(e36);
        JSONObject e37 = new JSONObject();
        e37.put("col", "dpwd");
        cols.add(e37);
        JSONObject e38 = new JSONObject();
        e38.put("col", "dresult");
        cols.add(e38);
        JSONObject e39 = new JSONObject();
        e39.put("col", "dsex");
        cols.add(e39);
        JSONObject e40 = new JSONObject();
        e40.put("col", "dstatus");
        cols.add(e40);
        JSONObject e41 = new JSONObject();
        e41.put("col", "dsubtype");
        cols.add(e41);
        JSONObject e42 = new JSONObject();
        e42.put("col", "dtitle");
        cols.add(e42);
        JSONObject e43 = new JSONObject();
        e43.put("col", "dtotal");
        cols.add(e43);
        JSONObject e44 = new JSONObject();
        e44.put("col", "dtype");
        cols.add(e44);
        JSONObject e45 = new JSONObject();
        e45.put("col", "durl");
        cols.add(e45);
        JSONObject e46 = new JSONObject();
        e46.put("col", "duser");
        cols.add(e46);
        JSONObject e47 = new JSONObject();
        e47.put("col", "duuid");
        cols.add(e47);
        for (int i = 0; i < cols.size(); i++) {
            map.put(cols.getJSONObject(i).getString("col"), cols.getJSONObject(i).getString("col"));
        }
        return map;
    }

    // type ,insert,update
    public R parseFromJsonToSqlTpl(String json_data, String json_value, String opertype, String process_data_id, String primary_value) {
        if (ToolUtil.isOneEmpty(json_data, json_value, opertype)) {
            return R.FAILURE();
        }
        // select COLUMN_NAME col from information_schema.COLUMNS where table_name =
        // 'sys_process_data' and COLUMN_NAME like 'd%';
        json_value = "{\n" + "	\"asdf\": \"asdf\",\n" + "	\"dpic1\": \"saf\",\n"
                + "	\"textarea_1585926392110\": \"asdf\",\n" + "	\"asfs\": \"asdf\",\n" + "	\"fasdfsa\": \"asdf\"\n"
                + "}";
        HashMap<String, String> metacols = parseFromJsonMetaCol();
        JSONObject e = JSONObject.parseObject(json_value);
        Iterator<String> keys = e.keySet().iterator();// jsonObject.keys();
        HashMap<String, String> map = new HashMap<String, String>();
        while (keys.hasNext()) {
            String key = keys.next();
            map.put(key, e.getString(key));
        }
        Iterator<String> keySetIterator = map.keySet().iterator();
        Insert ins = new Insert("sys_process_form");
        Update ups = new Update("sys_process_form");

        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            String v = map.get(key);
            if (metacols.containsKey(key)) {
                if (opertype.equals(OPER_TYPE_INSERT)) {
                    ins.setIf(key, v);
                } else if (opertype.equals(OPER_TYPE_UPDATE)) {
                    ups.setIf(key, v);
                }
            }
        }
        String ressql = "";
        if (opertype.equals(OPER_TYPE_UPDATE)) {
            ups.setIf("fdata", json_value);
            ups.where().andIf("fid=?", primary_value);
            ressql = ups.getSQL();

        } else if (opertype.equals(OPER_TYPE_INSERT)) {

            ins.setIf("ftpldatamd5", MD5Util.encrypt(json_data));
            ins.setIf("ftpldata", json_data);
            ins.setIf("fdata", json_value);
            ins.setIf("processdataid", process_data_id);
            ressql = ins.getSQL();
        }
        return R.SUCCESS_OPER(ressql);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FormServiceImpl a = new FormServiceImpl();


        System.out.println(a.parseFromJsonToSqlTpl("asdfsadf", "adfsadfasdf", "insert", "process", "###3"));

    }

}
