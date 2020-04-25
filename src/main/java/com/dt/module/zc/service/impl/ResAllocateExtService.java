package com.dt.module.zc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.module.zc.entity.ResAllocate;
import com.dt.module.zc.mapper.ResAllocateMapper;
import com.dt.module.zc.service.IResAllocateService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2020-04-25
 */
@Service
public class ResAllocateExtService extends BaseService {

    public R cancelAllocationById(String id){
        return R.SUCCESS_OPER();
    }
    public R sureAllocationById(String id,String items){
        return R.SUCCESS_OPER();
    }

}
