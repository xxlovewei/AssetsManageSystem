package com.dt.module.hrm.service.impl;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.RcdSet;
import org.springframework.stereotype.Service;

@Service
public class HrmPositionService extends BaseService {


    public R listPositions() {
        RcdSet rs = db.query("select t.*,(select ptname from hrm_position_type where id=t.type) typestr from hrm_position t where dr='0' ");
        return R.SUCCESS_OPER(rs.toJsonArrayWithJsonObject());
    }

}
