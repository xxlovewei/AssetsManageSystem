package com.dt.module.zc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.module.zc.entity.ResResidual;
import com.dt.module.zc.mapper.ResResidualMapper;
import com.dt.module.zc.service.IResResidualService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2020-08-04
 */
@Service
public class ResResidualExtService extends BaseService {


    public static String ITEMCHECKSTATUS_INIT = "init";
    public static String ITEMCHECKSTATUS_SUCCESS = "success";
    public static String ITEMCHECKSTATUS_FAILED = "failed";

    public static String CKSTATUS_INIT = "init";
    public static String CKSTATUS_SUCCESS = "success";
    public static String CKSTATUS_FAILED = "failed";


    public static String STATUS_FAILED = "failed";
    public static String STATUS_SUCCESS = "success";
    public static String STATUS_WAIT = "wait";
    public static String STATUS_CANCEL = "cancel";


    public R checkRes(String id) {


        return R.SUCCESS_OPER();
    }

}
