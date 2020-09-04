package com.dt.module.ct.service.impl;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import org.springframework.stereotype.Service;

@Service
public class DocMgrService extends BaseService {

    public R listDoc() {
        RcdSet rs = db.query("select t.*,(select name from sys_dict_item where dict_item_id=t.type)typestr from doc_mgr t where dr='0' order by create_time desc");
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }
}
