package com.dt.module.cmdb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dt.core.common.base.BaseService;
import com.dt.module.cmdb.entity.ResHistory;
import com.dt.module.cmdb.mapper.ResHistoryMapper;
import com.dt.module.cmdb.service.IResHistoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2020-04-16
 */
@Service
public class ResHistoryExtService extends BaseService {


    public static String OPER_CHANGE= "变更";
    public static String OPER_UPDATE = "更新";
    public static String OPER_INSERT = "入库";



}
