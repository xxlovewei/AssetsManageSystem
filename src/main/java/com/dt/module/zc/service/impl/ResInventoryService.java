package com.dt.module.zc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dt.core.common.base.BaseService;
import com.dt.module.zc.entity.ResInventory;
import com.dt.module.zc.mapper.ResInventoryMapper;
import com.dt.module.zc.service.IResInventoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author algernonking
 * @since 2020-05-14
 */
@Service
public class ResInventoryService extends BaseService {

    public static String INVENTORY_STATAUS_WAIT="wait";
    public static String INVENTORY_STATAUS_START="start";
    public static String INVENTORY_STATAUS_CANCEL="cancel";
    public static String INVENTORY_STATAUS_FINISH="finish";

    public static String INVENTORY_ITEM_STATAUS_WAIT="wait";
    public static String INVENTORY_ITEM_STATAUS_FINISH="finish";
    public static String INVENTORY_ITEM_ACTION_SYNC="sync";
    public static String INVENTORY_ITEM_ACTION_NOSYNC="nosync";


}
