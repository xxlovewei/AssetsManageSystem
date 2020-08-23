package com.dt.module.zc.service.impl;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2020-04-25
 */
@Service
public class ResAllocateExtService extends BaseService {

    public static String STATUS_DOING = "doing";
    public R cancelAllocationById(String id) {
        return R.SUCCESS_OPER();
    }

    public R sureAllocationById(String id, String items) {
        return R.SUCCESS_OPER();
    }

}
