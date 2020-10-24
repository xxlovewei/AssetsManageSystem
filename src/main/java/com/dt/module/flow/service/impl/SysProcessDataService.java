package com.dt.module.flow.service.impl;


import com.dt.core.common.base.BaseService;
import com.dt.module.zc.service.impl.ZcCommonService;
import org.springframework.stereotype.Service;

@Service
public class SysProcessDataService extends BaseService {


    //流程是否带表单
    public static String BUSTYPE_ASSET = "asset";

    //流程类型
    public static String PTYPE_ASSET_ZCRK = ZcCommonService.ZC_BUS_TYPE_RK;
    public static String PTYPE_ASSET_ZCDB = ZcCommonService.ZC_BUS_TYPE_DB;
    public static String PTYPE_ASSET_ZCBF = ZcCommonService.ZC_BUS_TYPE_BF;
    // public static String PSUBTYPE_ASSET_ZCWX=ZcCommonService.ZC_BUS_TYPE_WX;
    // public static String PSUBTYPE_ASSET_ZCLY=ZcCommonService.ZC_BUS_TYPE_LY;
    public static String PTYPE_ASSET_ZCTK = ZcCommonService.ZC_BUS_TYPE_TK;

    public static String PTYPE_ASSET_ZCJY = ZcCommonService.ZC_BUS_TYPE_JY;
    public static String PTYPE_ASSET_ZCGH = ZcCommonService.ZC_BUS_TYPE_GH;

    //申请
    public static String PSTATUS_APPLY = "apply";
    //审批中
    public static String PSTATUS_INAPPROVAL = "inapproval";
    //无需审批
    public static String PSTATUS_NONE = "none";
    //取消
    public static String PSTATUS_CANCEL = "cancel";
    //办理完成，无需审批
    public static String PSTATUS_FINISH_NO_APPROVAL = "finish_na";
    //审批结束
    public static String PSTATUS_FINISH = "finish";


    //单据中使用PSTATUS_DTL_的状态
    public static String PSTATUS_DTL_APPLY = "apply";
    public static String PSTATUS_DTL_INAPPROVAL = "inapproval";
    //办理完成，无需审批
    public static String PSTATUS_DTL_FINISH_NO_APPROVAL = "finish_na";
    public static String PSTATUS_DTL_NONE = "none";
    public static String PSTATUS_DTL_CANCEL = "cancel";
    public static String PSTATUS_DTL_SUCCESS = "success";
    public static String PSTATUS_DTL_FAILED = "failed";


    //无表单模式
    public static String FORMTYPE_NONE = "none";

}