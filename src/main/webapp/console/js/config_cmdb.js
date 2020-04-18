function config_cmdb($stateProvider, $ocLazyLoadProvider) {
    console.log("App cmdb config");
    $ocLazyLoadProvider.config({
        debug: true
    });
    $stateProvider.state('zcindex', {
        url: "/zcindex",
        templateUrl: "views/cmdb/zcindex.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                    {
                        serie: true,
                        name: 'angular-flot',
                        files: ['plugin/flot/jquery.flot.js', 'plugin/flot/jquery.flot.time.js', 'plugin/flot/jquery.flot.tooltip.min.js', 'plugin/flot/jquery.flot.spline.js', 'plugin/flot/jquery.flot.resize.js', 'plugin/flot/jquery.flot.pie.js', 'plugin/flot/curvedLines.js', 'plugin/flot/angular-flot.js',]
                    },
                    {
                        serie: true,
                        files: ['views/cmdb/zcindex.js?v=' + version]
                    }]);
            }
        }
    });

    // 流程管理
    $stateProvider.state('zcprocess', {
        abstract: true,
        url: "/zcprocess",
        templateUrl: "views/common/content.html?v=" + version
    }).state('zcprocess.allprocess', {
        url: "/zcprocess_allprocess?psBtns",
        data: {pageTitle: '所有流程'},
        templateUrl: "views/cmdb/flow/allprocess.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/flow/allprocess.js?v=' + version]
                }]);
            }
        }
    })


    // cmdb
    $stateProvider.state('maintain', {
        abstract: true,
        url: "/maintain",
        templateUrl: "views/common/content.html?v=" + version
    }).state('maintain.faultrecord', {
        url: "/maintain_faultrecord?psBtns",
        data: {pageTitle: '报修工作'},
        templateUrl: "views/cmdb/faultrecord.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/faultrecord.js?v=' + version]
                }]);
            }
        }
    }).state('maintain.devsearch', {
        url: "/maintain_devsearch?psBtns",
        data: {pageTitle: '设备查询'},
        templateUrl: "views/cmdb/devsearch.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/devsearch.js?v=' + version]
                }]);
            }
        }
    }).state('maintain.review', {
        url: "/maintain_review?psBtns",
        data: {pageTitle: '资产复核'},
        templateUrl: "views/cmdb/review.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/review.js?v=' + version]
                }]);
            }
        }
    }).state('maintain.dataimport', {
        url: "/maintain_dataimport?psBtns",
        data: {pageTitle: '资产导入'},
        templateUrl: "views/cmdb/dataimport.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/dataimport.js?v=' + version]
                }]);
            }
        }
    }).state('maintain.dataexport', {
        url: "/maintain_dataexport",
        data: {pageTitle: '资产导出'},
        templateUrl: "views/cmdb/dataexport.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/dataexport.js?v=' + version]
                }]);
            }
        }
    })


    // 报表
    $stateProvider.state('cmdbresp', {
        abstract: true,
        url: "/cmdbresp",
        templateUrl: "views/common/content.html?v=" + version
    }).state('cmdbresp.partzc', {
        url: "/maintain_partzc?psBtns",
        data: {pageTitle: '部门资产'},
        template: '<div ng-controller="cmdbrepPartZcCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/rep/partzj.js?v=' + version]
                }]);
            }
        }
    }).state('cmdbresp.zctjshow', {
        url: "/cmdbresp_zctjshow?psBtns",
        data: {pageTitle: '资产统计'},
        templateUrl: "views/cmdb/rep/zctj.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/rep/zctj.js?v=' + version]
                }]);
            }
        }
    }).state('cmdbresp.bashboard', {
        url: "/cmdbresp_bashboard?psBtns",
        data: {pageTitle: '展示面板'},
        templateUrl: "views/cmdb/rep/dashboard.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                    {
                        serie: true,
                        name: 'angular-flot',
                        files: ['plugin/flot/jquery.flot.js', 'plugin/flot/jquery.flot.time.js', 'plugin/flot/jquery.flot.tooltip.min.js', 'plugin/flot/jquery.flot.spline.js', 'plugin/flot/jquery.flot.resize.js', 'plugin/flot/jquery.flot.pie.js', 'plugin/flot/curvedLines.js', 'plugin/flot/angular-flot.js',]
                    },
                    {
                        serie: true,
                        files: ['views/cmdb/rep/dashboard.js?v=' + version]
                    }]);
            }
        }
    });


    $stateProvider.state('cmsetting', {
        abstract: true,
        url: "/cmsetting",
        templateUrl: "views/common/content.html?v=" + version
    }).state('cmsetting.zccat', {
        url: "/cmsetting_zccat?psBtns",
        data: {pageTitle: '资产分类', code:"3"},
        templateUrl: "views/cmdb/zccategory.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zccategory.js?v=' + version]
                }]);
            }
        }
    }).state('cmsetting.hccat', {
        url: "/cmsetting_hccat?psBtns",
        data: {pageTitle: '耗材分类', code:"7"},
        templateUrl: "views/cmdb/zccategory.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zccategory.js?v=' + version]
                }]);
            }
        }
    }).state('cmsetting.bjcat', {
        url: "/cmsetting_bjcat?psBtns",
        data: {pageTitle: '备件分类', code:"8"},
        templateUrl: "views/cmdb/zccategory.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zccategory.js?v=' + version]
                }]);
            }
        }
    }).state('cmsetting.dataimport', {
        url: "/cmsetting_dataimport?psBtns",
        data: {pageTitle: '资产导入'},
        templateUrl: "views/cmdb/dataimport.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/dataimport.js?v=' + version]
                }]);
            }
        }
    })


    $stateProvider.state('myprocess', {
        abstract: true,
        url: "/myprocess",
        templateUrl: "views/common/content.html?v=" + version
    }).state('myprocess.waittask', {
        url: "/myprocess_waittask?psBtns",
        data: {pageTitle: '我的待处理任务'},
        templateUrl: "views/cmdb/flow/todo.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/flow/todo.js?v=' + version]
                }]);
            }
        }
    }).state('myprocess.taskfinish', {
        url: "/myprocess_taskfinish?psBtns",
        data: {pageTitle: '已处理任务'},
        templateUrl: "views/cmdb/flow/processfinish.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/flow/processfinish.js?v=' + version]
                }]);
            }
        }
    }).state('myprocess.myprocess', {
        url: "/myprocess_myprocess?psBtns",
        data: {pageTitle: '我的流程'},
        templateUrl: "views/cmdb/flow/myprocess.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/flow/myprocess.js?v=' + version]
                }]);
            }
        }
    })
    ;
    // cmdb
    $stateProvider.state('xt', {
        abstract: true,
        url: "/xt",
        templateUrl: "views/common/content.html?v=" + version
    }).state('xt.outercontact', {
        url: "/xt_outercontact?psBtns",
        data: {pageTitle: '外部联系人'},
        templateUrl: "views/cmdb/outercontact.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/outercontact.js?v=' + version]
                }]);
            }
        }
    })
        .state('xt.systemlist', {
            url: "/xt_systemlist?psBtns",
            data: {pageTitle: '信息系统清单'},
            template: '<div ng-controller="cmdbsystemListCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([{
                        serie: true,
                        files: ['views/cmdb/systemlist.js?v=' + version]
                    }]);
                }
            }
        });


    // 备件管理
    $stateProvider.state('bjmgr', {
        abstract: true,
        url: "/bjmgr",
        templateUrl: "views/common/content.html?v=" + version
    }).state('bjmgr.bjpj', {
        url: "/bjmgr_bjpj",
        data: {pageTitle: '备件配件'},
        templateUrl: "views/cmdb/zcbj.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zcbj.js?v=' + version]
                }]);
            }
        }
    });

    // 耗材管理
    $stateProvider.state('hcmgr', {
        abstract: true,
        url: "/hcmgr",
        templateUrl: "views/common/content.html?v=" + version
    }).state('hcmgr.hc', {
        url: "/hcmgr_hc",
        data: {pageTitle: '耗材'},
        templateUrl: "views/cmdb/zchc.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zchc.js?v=' + version]
                }]);
            }
        }
    });

    $stateProvider.state('zcmgr', {
        abstract: true,
        url: "/zcmgr",
        templateUrl: "views/common/content.html?v=" + version
    }).state('zcmgr.zctz', {
        url: "/zcmgr_zctz",
        data: {pageTitle: '资产台账'},
        templateUrl: "views/cmdb/devsearch.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/devsearch.js?v=' + version]
                }]);
            }
        }
    }).state('zcmgr.zcdj', {
        url: "/zcmgr_zcdj",
        data: {pageTitle: '资产登记'},
        templateUrl: "views/cmdb/zcdj.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zcdj.js?v=' + version]
                }]);
            }
        }
    });


    $stateProvider.state('zcchange', {
        abstract: true,
        url: "/zcchange",
        templateUrl: "views/common/content.html?v=" + version
    }).state('zcchange.lygh', {
        url: "zcchange_lygh",
        data: {pageTitle: '资产领用归还', actiontype: "LY"},
        templateUrl: "views/cmdb/zcaction.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zcaction.js?v=' + version]
                }]);
            }
        }
    }).state('zcchange.jygh', {
        url: "/zcchange_jygh",
        data: {pageTitle: '资产借用归还', actiontype: "JY"},
        templateUrl: "views/cmdb/zcaction.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zcaction.js?v=' + version]
                }]);
            }
        }
    }).state('zcchange.zczy', {
        url: "/zcchange_zczy",
        data: {pageTitle: '我的资产转移', actiontype: "ZY"},
        templateUrl: "views/cmdb/zcaction.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zcaction.js?v=' + version]
                }]);
            }
        }
    })


    // cmdb
    $stateProvider.state('cf', {
        abstract: true,
        url: "/cf",
        templateUrl: "views/common/content.html?v=" + version
    }).state('cf.server', {
        url: "/cf_server?psBtns",
        data: {pageTitle: '服务器', classid: 'zc_server', subclass: "Y"},
        templateUrl: "views/cmdb/html_genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/js_genericdev.js?v=' + version]
                }]);
            }
        }
    })
        .state('cf.lightsw', {
            url: "/cf_lightsw?psBtns",
            data: {pageTitle: '光纤交换机', classid: 'zc_lsw'},
            templateUrl: "views/cmdb/html_genericdev.html?v=" + version,
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([{
                        serie: true,
                        files: ['views/cmdb/js_genericdev.js?v=' + version]
                    }]);
                }
            }
        })
        .state('cf.outlets', {
            url: "/cf_outlets?psBtns",
            data: {pageTitle: '网点设备', classid: "zc_website", subclass: "Y"},
            templateUrl: "views/cmdb/html_genericdev.html?v=" + version,
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([{
                        serie: true,
                        files: ['views/cmdb/js_genericdev.js?v=' + version]
                    }]);
                }
            }
        }).state('cf.safety', {
        url: "/cf_safety?psBtns",
        data: {pageTitle: '安全设备', classid: 'zc_safety', subclass: "Y"},
        templateUrl: "views/cmdb/html_genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/js_genericdev.js?v=' + version]
                }]);
            }
        }
    }).state('cf.storage', {
        url: "/cf_storage?psBtns",
        data: {pageTitle: '存储设备', classid: 'zc_storage'},
        templateUrl: "views/cmdb/html_genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/js_genericdev.js?v=' + version]
                }]);
            }
        }
    })
        .state('cf.switch', {
            url: "/cf_switch?psBtns",
            data: {pageTitle: '交换机', classid: "51"},
            templateUrl: "views/cmdb/html_genericdev.html?v=" + version,
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([{
                        serie: true,
                        files: ['views/cmdb/js_genericdev.js?v=' + version]
                    }]);
                }
            }
        }).state('cf.zcotherhard', {
        url: "/cf_zcotherhard?psBtns",
        data: {pageTitle: '其他资产', classid: "zc_other", subclass: "Y"},
        templateUrl: "views/cmdb/html_genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/js_genericdev.js?v=' + version]
                }]);
            }
        }
    }).state('cf.network', {
        url: "/cf_network?psBtns",
        data: {pageTitle: '网络设备', classid: "zc_network", subclass: "Y"},
        templateUrl: "views/cmdb/html_genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/js_genericdev.js?v=' + version]
                }]);
            }
        }
    });


    $stateProvider.state('softzc', {
        abstract: true,
        url: "/softzc",
        templateUrl: "views/common/content.html?v=" + version
    }).state('softzc.soft', {
        url: "/softzc.soft",
        data: {pageTitle: '软件资产', classid: "softzc", input_type: "zcsofttype"},
        templateUrl: "views/cmdb/html_genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/js_genericdev.js?v=' + version]
                }]);
            }
        }
    })


}



function zcBaseColsCreate(DTColumnBuilder,selectype){
//selectype:withoutselect,withselect
    dtColumns=[];
    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    dtColumns = [];
    if(selectype=="withselect"){
        dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function() {
            return ""
        }));
    }

    dtColumns.push(DTColumnBuilder.newColumn('classrootname').withTitle('类目').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('资产编号').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('classname').withTitle('资产类型').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('brandstr').withTitle('品牌').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push( DTColumnBuilder.newColumn('model').withTitle('型号').withOption(
        'sDefaultContent', '').withOption('width', '50'));
    dtColumns.push( DTColumnBuilder.newColumn('recyclestr').withTitle('资产状态').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(  DTColumnBuilder.newColumn('sn').withTitle('序列号').withOption(
        'sDefaultContent', ''));
    dtColumns.push(  DTColumnBuilder.newColumn('confdesc').withTitle('配置描述').withOption(
        'sDefaultContent', ''));
    dtColumns.push(  DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
        'sDefaultContent', ''));
    dtColumns.push(  DTColumnBuilder.newColumn('fs1').withTitle('标签1').withOption(
        'sDefaultContent', ''));
    dtColumns.push(  DTColumnBuilder.newColumn('fs2').withTitle('标签2').withOption(
        'sDefaultContent', ''));
    dtColumns.push( DTColumnBuilder.newColumn('locstr').withTitle('区域').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(  DTColumnBuilder.newColumn('locdtl').withTitle('位置详情').withOption(
        'sDefaultContent', ''));
    dtColumns.push( DTColumnBuilder.newColumn('buy_timestr').withTitle('采购时间')
        .withOption('sDefaultContent', ''));
    dtColumns.push( DTColumnBuilder.newColumn('buy_price').withTitle('采购金额')
        .withOption('sDefaultContent', ''));
    dtColumns.push( DTColumnBuilder.newColumn('net_worth').withTitle('资产净值')
        .withOption('sDefaultContent', ''));
    dtColumns.push( DTColumnBuilder.newColumn('wbstr').withTitle('维保状态').withOption(
        'sDefaultContent', '').withOption('width', '30').renderWith(renderWb));
    dtColumns.push(  DTColumnBuilder.newColumn('wbout_datestr').withTitle('脱保时间')
        .withOption('sDefaultContent', ''));
    dtColumns.push(   DTColumnBuilder.newColumn('wb_autostr').withTitle('脱保计算')
        .withOption('sDefaultContent', ''));
    return dtColumns;
}


function renderName(data, type, full) {
    var html = full.model;
    return html;

}

function renderJg(data, type, full) {
    var html = "";
    if (angular.isDefined(full.rackstr)) {
        html = html + full.rackstr + "-";
    }
    if (angular.isDefined(full.frame)) {
        html = html + full.frame;
    }
    return html;
}

function renderReview(data, type, full) {
    if (data == "reviewed") {
        return "已复核"
    } else {
        return "未复核"
    }
}

function renderWb(data, type, full) {
    if (angular.isDefined(full.wb)) {
        if (full.wb == "valid") {
            return full.wbstr
        } else {
            return "<span style=\"color:red\">" + full.wbstr + "</span>"
        }
    } else {
        return "";
    }
}

function loadOpt(modal_meta, gdicts) {


    var item = modal_meta.meta.item;
    console.log("LoadOpt,Item:", item);


    // 维保自定技术
    modal_meta.meta.tbOpt = gdicts.zcwbcomoute;

    if (angular.isDefined(gdicts.zcwbcomoute) && modal_meta.meta.tbOpt.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.wb_auto)) {
            for (var i = 0; i < gdicts.zcwbcomoute.length; i++) {

                if (modal_meta.meta.tbOpt[i].dict_item_id == item.wb_auto) {
                    modal_meta.meta.tbSel = modal_meta.meta.tbOpt[i];
                }
            }
        } else {
            if (gdicts.zcwbcomoute.length > 0) {
                // modal_meta.meta.tbOpt = gdicts.zcwbcomoute[];
            }
        }
    }

    // 品牌
    modal_meta.meta.pinpOpt = gdicts.devbrand;
    if (angular.isDefined(gdicts.devbrand) && modal_meta.meta.pinpOpt.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.brand)) {
            for (var i = 0; i < gdicts.devbrand.length; i++) {

                if (modal_meta.meta.pinpOpt[i].dict_item_id == item.brand) {
                    modal_meta.meta.pinpSel = modal_meta.meta.pinpOpt[i];
                }
            }
        } else {
            if (gdicts.devbrand.length > 0) {
            }
        }
    }

    // 部门
    modal_meta.meta.partOpt = gdicts.parts;
    if (angular.isDefined(gdicts.parts) && gdicts.parts.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.part_id)) {
            for (var i = 0; i < gdicts.parts.length; i++) {
                if (gdicts.parts[i].partid == item.part_id) {
                    modal_meta.meta.partSel = gdicts.parts[i];
                }
            }
        } else {
            if (gdicts.parts.length > 0) {
                // modal_meta.meta.partSel = gdicts.parts[0];
            }
        }
    }

    // 使用人
    modal_meta.meta.usedunameOpt = gdicts.partusers;
    if (angular.isDefined(gdicts.partusers) && gdicts.partusers.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.used_userid)) {
            for (var i = 0; i < gdicts.partusers.length; i++) {
                if (gdicts.partusers[i].user_id == item.used_userid) {
                    modal_meta.meta.usedunameSel = gdicts.partusers[i];
                }
            }
        } else {
            if (gdicts.partusers.length > 0) {
                // modal_meta.meta.usedunameSel = gdicts.partusers[0];
            }
        }
    }

    // 风险等级
    modal_meta.meta.riskOpt = gdicts.devrisk;
    if (angular.isDefined(gdicts.devrisk) && gdicts.devrisk.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.risk)) {
            for (var i = 0; i < gdicts.devrisk.length; i++) {
                if (gdicts.devrisk[i].dict_item_id == item.risk) {
                    modal_meta.meta.riskSel = gdicts.devrisk[i];
                }
            }
        } else {
            if (gdicts.devrisk.length > 0) {
                modal_meta.meta.riskSel = gdicts.devrisk[0];
            }
        }
    }

    // 环境
    modal_meta.meta.envOpt = gdicts.devenv;
    if (angular.isDefined(gdicts.devenv) && gdicts.devenv.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.env)) {
            for (var i = 0; i < gdicts.devenv.length; i++) {
                if (gdicts.devenv[i].dict_item_id == item.env) {
                    modal_meta.meta.envSel = gdicts.devenv[i];
                }
            }
        } else {
            if (gdicts.devenv.length > 0) {
                // modal_meta.meta.envSel = gdicts.devenv[0];
            }
        }
    }

    // 状态
    modal_meta.meta.recycelOpt = gdicts.devrecycle;
    if (angular.isDefined(gdicts.devrecycle) && gdicts.devrecycle.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.recycle)) {
            for (var i = 0; i < gdicts.devrecycle.length; i++) {
                if (gdicts.devrecycle[i].dict_item_id == item.recycle) {
                    modal_meta.meta.recycelSel = gdicts.devrecycle[i];
                }
            }
        } else {
            if (gdicts.devrecycle.length > 0) {
                modal_meta.meta.recycelSel = gdicts.devrecycle[0];
            }
        }
    }

    // 维保
    modal_meta.meta.wbOpt = gdicts.devwb;
    if (angular.isDefined(gdicts.devwb) && gdicts.devwb.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.wb)) {
            for (var i = 0; i < gdicts.devwb.length; i++) {
                if (gdicts.devwb[i].dict_item_id == item.wb) {
                    modal_meta.meta.wbSel = gdicts.devwb[i];
                }
            }
        } else {
            if (gdicts.devwb.length > 0) {
                modal_meta.meta.wbSel = gdicts.devwb[0];
            }
        }
    }

    // 位置
    modal_meta.meta.locOpt = gdicts.devdc;
    if (angular.isDefined(gdicts.devdc) && gdicts.devdc.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.loc)) {
            for (var i = 0; i < gdicts.devdc.length; i++) {
                if (gdicts.devdc[i].dict_item_id == item.loc) {
                    modal_meta.meta.locSel = gdicts.devdc[i];
                }
            }
        } else {
            if (gdicts.devdc.length > 0) {
                modal_meta.meta.locSel = gdicts.devdc[0];
            }
        }
    }

    // 小类
    modal_meta.meta.typeOpt = gdicts.stype;
    if (angular.isDefined(gdicts.stype) && gdicts.stype.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.type)) {
            for (var i = 0; i < gdicts.stype.length; i++) {
                if (gdicts.stype[i].dict_item_id == item.type) {
                    modal_meta.meta.typeSel = gdicts.stype[i];
                }
            }
        } else {
            if (gdicts.stype.length > 0) {
                modal_meta.meta.typeSel = gdicts.stype[0];
            }
        }
    }

    // 大类
    modal_meta.meta.classOpt = gdicts.btype;
    if (angular.isDefined(gdicts.btype) && gdicts.btype.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.class_id)) {
            for (var i = 0; i < gdicts.btype.length; i++) {
                if (gdicts.btype[i].dict_item_id == item.class_id) {
                    modal_meta.meta.classSel = gdicts.btype[i];
                }
            }
        } else {
            if (gdicts.btype.length > 0) {
                modal_meta.meta.classSel = gdicts.btype[0];
            }
        }
    }

    // 机柜
    modal_meta.meta.jgOpt = gdicts.devrack;
    if (angular.isDefined(gdicts.devrack) && gdicts.devrack.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.rack)) {
            for (var i = 0; i < gdicts.devrack.length; i++) {
                if (gdicts.devrack[i].dict_item_id == item.rack) {
                    modal_meta.meta.jgSel = gdicts.devrack[i];
                }
            }
        } else {
            if (gdicts.devrack.length > 0) {
                // modal_meta.meta.jgSel = gdicts.devrack[0];
            }
        }
    }


}

function modalreviewProcessCtl(meta, $rootScope, $window, $scope,
                               $uibModalInstance) {
    var url = $rootScope.project + "uflo/diagram?processKey=" + meta.pk;
    $scope.url = url;
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}


function renderZCSPStatus(data, type, full) {
    var html = data;
    if (angular.isDefined(data)) {
        if (data == "submitforapproval") {
            html = "<span style='color:#33FFFF; font-weight:bold'>待送审</span>";
        } else if (data == "inreview") {
            html = "<span style='color:#00F; font-weight:bold'>审批中</span>";
        } else if (data == "running") {
            html = "<span style='color:#00F; font-weight:bold'>审批中</span>";
        } else if (data == "success") {
            html = "<span style='color:green; font-weight:bold'>审批成功</span>";
        } else if (data == "failed") {
            html = "<span style='color:red;font-weight:bold'>审批失败</span>";
        } else if (data == "cancel") {
            html = "<span style='color:red;font-weight:bold'>审批取消</span>"
        } else if (data == "rollback") {
            html = "<span style='color:red;font-weight:bold'>审批退回</span>";
        } else if (data == "finish") {
            html = "<span style='color:red;font-weight:bold'>流程结束</span>";
        } else {
            html = data;
        }
    }
    return html;
}



function modalzcActionDtlCtl($timeout, DTOptionsBuilder, DTColumnBuilder, $compile,
                             $confirm, $log, notify, $scope, $http, $rootScope, $uibModal, meta, pagetype, task,
                             $uibModalInstance) {
    $scope.hidectl={"flowform":true,"flowchart":true,"flowsuggestlist":true,"flowsuggest":true};

    console.log(meta);
    console.log(task);
    console.log(pagetype);
    $scope.actmsg = "操作人";
    if (meta.acttype == "LY") {
        $scope.actmsg = "领用人";
    } else if (meta.acttype == "JY") {
        $scope.actmsg = "借用人";
    } else if (meta.acttype == "ZY") {
        actbtn = "转移人";
    }
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    $scope.url = "";
    $scope.spsugguest = [];
    $scope.ctl = {};
    $scope.ctl.pagespbtnhide = true;
    $scope.spsuggest = "";
    //页面是否审批类型打开
    $scope.pagetype = pagetype;
    if ($scope.pagetype == "sp") {
        $scope.ctl.pagespbtnhide = false;
    }

    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withDOM('frtlip').withPaginationType('simple').withDisplayLength(
            50).withOption("ordering", false).withOption("responsive",
            false).withOption("searching", false).withOption('scrollY',
            300).withOption('scrollX', true).withOption(
            'bAutoWidth', true).withOption('scrollCollapse', true)
        .withOption('paging', false).withFixedColumns({
            leftColumns: 0,
            rightColumns: 0
        }).withOption('bStateSave', true).withOption('bProcessing', false)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', false).withOption('createdRow', function (row) {
            $compile(angular.element(row).contents())($scope);
        });
    $scope.dtColumns = [];
    $scope.dtColumns=zcBaseColsCreate(DTColumnBuilder,'withoutselect');


    $scope.dtOptions.aaData = [];

    // 显示审批页面
    $http
        .post($rootScope.project + "/api/zc/selectBillById.do",
            {
                id: meta.id
            })
        .success(
            function (res) {
                if (res.success) {
                    $scope.data = res.data;
                    $scope.dtOptions.aaData = res.data.items;
                    if(res.data.ifsp=="1"){
                        $scope.hidectl={"flowform":false,"flowchart":false,"flowsuggestlist":false,"flowsuggest":false};
                    }

                    //获取审批
                    if (angular.isDefined(res.data.processInstanceId) && res.data.processInstanceId != "") {
                        $http
                            .post(
                                $rootScope.project
                                + "/api/flow/loadProcessInstanceData.do",
                                {
                                    processInstanceId: res.data.processInstanceId
                                })
                            .success(
                                function (res) {
                                    if (res.success) {
                                        $scope.spsugguest = res.data;
                                    } else {
                                        notify({
                                            message: res.message
                                        });
                                    }
                                })
                        //获取审批
                        $scope.url = $rootScope.project
                            + "uflo/diagram?processInstanceId="
                            + res.data.processInstanceId;
                    }


                    var dynamicData = res.data.formdata;
                    let vm;
                    $timeout(function () {
                        var jd = decodeURI(res.data.formconf);
                        if(angular.isDefined(jd)&&jd!="undefined"&&jd!=""){
                            let jsonData = angular.fromJson(jd);
                            vm = new Vue({
                                el: '#app',
                                data: {
                                    jsonData,
                                    dynamicData
                                },
                                mounted() {
                                    this.init()
                                },
                                methods: {
                                    init() {
                                        this.$refs.kfb.setData(res.data.formdata);
                                    },
                                    handleSubmit(p) {

                                    },
                                    getData() {

                                    }
                                }
                            })
                        }



                    }, 1000)


                } else {
                    notify({
                        message: res.message
                    });
                }
            })

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };


    $scope.agreen = function () {
        if ($scope.spsuggest.length == 0) {
            $scope.spsuggest = "同意";
        }
        if ($scope.spsuggest.length > 248) {
            notify({
                message: "已超过248,请缩减字数。"
            });
            return;
        }
        $http
            .post($rootScope.project + "/api/zc/flow/completeTask.do",
                {taskId: task.id, opinion: $scope.spsuggest}).success(function (res) {
            if (res.success) {
                $uibModalInstance.close('OK');
            } else {
                notify({
                    message: res.message
                });
            }
        })

    }

    $scope.rollback = function () {
        if ($scope.spsuggest.length == 0) {
            $scope.spsuggest = "退回";
        }
        if ($scope.spsuggest.length > 248) {
            notify({
                message: "已超过248,请缩减字数。"
            });
            return;
        }
        $http
            .post($rootScope.project + "/api/zc/flow/rollback.do",
                {taskId: task.id, opinion: $scope.spsuggest}).success(function (res) {
            if (res.success) {
                $uibModalInstance.close('OK');
            } else {
                notify({
                    message: res.message
                });
            }
        })
    }

    $scope.refuse = function () {
        if ($scope.spsuggest.length == 0) {
            $scope.spsuggest = "拒绝";
        }
        if ($scope.spsuggest.length > 248) {
            notify({
                message: "已超过248,请缩减字数。"
            });
            return;
        }

        $http
            .post($rootScope.project + "/api/zc/flow/refuseTask.do",
                {taskId: task.id, opinion: $scope.spsuggest}).success(function (res) {
            if (res.success) {
                $uibModalInstance.close('OK');
            } else {
                notify({
                    message: res.message
                });
            }
        })

    }

    $scope.submit = function () {
        if ($scope.spsuggest.length == 0) {
            $scope.spsuggest = "重新提交审批";
        }
        if ($scope.spsuggest.length > 248) {
            notify({
                message: "已超过248,请缩减字数。"
            });
            return;
        }
        $scope.data.taskId = task.id;
        $scope.data.opinion = $scope.spsuggest;
        $http
            .post($rootScope.project + "/api/cmdb/flow/zc/completeStartTask.do",
                $scope.data).success(function (res) {
            if (res.success) {
                $uibModalInstance.close('OK');
            } else {
                notify({
                    message: res.message
                });
            }
        })
    }

}





function modalcmdbdtlCtl($timeout, $localStorage, notify, $log, $uibModal,
                         $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                         DTColumnBuilder, $compile, $window) {

    $scope.item = {};

    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption(
        'createdRow', function (row) {
            // Recompiling so we can bind Angular,directive to the
            $compile(angular.element(row).contents())($scope);

        });
    $scope.dtInstance = {}


    function renderAttach(data, type, full) {
        if (data > 0) {
            var acthtml = " <button ng-click=\"attachdown('" + full.id
                + "')\" class=\"btn-white btn btn-xs\">下载(" + data + ")</button>   ";
            return acthtml;
        } else {
            return data;
        }
    }


    $scope.attachdown = function (faultid) {

        $http.post($rootScope.project + "/api/base/res/queryResFaultById.do", {
            id: faultid
        }).success(function (res) {
            if (res.success) {


                // $window
                if (res.data.attachdata.length > 0) {
                    for (var i = 0; i < res.data.attachdata.length; i++) {
                        var objectUrl = $rootScope.project + "/api/file/filedown.do?id=" + res.data.attachdata[i].fileid;
                        $window.open(objectUrl)
                    }
                }
            } else {
                notify({
                    message: res.message
                });
            }
        });
    }

    $scope.dtColumns = [
        DTColumnBuilder.newColumn('oper_time').withTitle('操作时间')
            .withOption('sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('name').withTitle('记录人').withOption(
            'sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('uuid').withTitle('维护编号').withOption(
            'sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('processuser').withTitle('维护人').withOption(
            'sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('processtime').withTitle('维护时间')
            .withOption('sDefaultContent', '').withOption('width', '90'),
        DTColumnBuilder.newColumn('attach_cnt').withTitle('附件数')
            .withOption('sDefaultContent', '').withOption('width', '30').renderWith(renderAttach),
        DTColumnBuilder.newColumn('reason').withTitle('原因').withOption(
            'sDefaultContent', '')
    ]

    $scope.dtOptions2 = DTOptionsBuilder.fromFnPromise().withOption(
        'createdRow', function (row) {
            // Recompiling so we can bind Angular,directive to the
            $compile(angular.element(row).contents())($scope);
        });
    $scope.dtInstance2 = {}

    function renderCT(data, type, full) {
        if (angular.isDefined(data)) {
            return data.substr(0, 100) + "...";
        } else {
            return "";
        }

    }


    $scope.dtColumns2 = [
        DTColumnBuilder.newColumn('oper_time').withTitle('操作时间')
            .withOption('sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('name').withTitle('操作人').withOption(
            'sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('oper_type').withTitle('操作类型').withOption(
            'sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('fullct').withTitle('内容').withOption(
            'sDefaultContent', '').withOption('width', '100').renderWith(renderCT),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', '')]

    if (angular.isDefined(meta.id)) {
        // 加载数据
        $http.post($rootScope.project + "/api/base/res/queryResAllById.do", {
            id: meta.id
        }).success(function (res) {
            if (res.success) {
                $scope.item = res.data.data;
                $scope.dtOptions.aaData = res.data.faultdata;
                $scope.dtOptions2.aaData = res.data.updatadata;
            } else {
                notify({
                    message: res.message
                });
            }
        });
    }

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}


function modal_faultZcListCtl($timeout, $localStorage, notify, $log, $uibModal,
                              $uibModalInstance, $scope, id, type, $http, $rootScope, DTOptionsBuilder,
                              DTColumnBuilder, $compile) {
    // type:one|many
    if (!angular.isDefined(type)) {
        type = "many"
    }
    $scope.search = "";
    // 分类
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 200)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withFixedColumns({
            leftColumns: 0,
            rightColumns: 0
        }).withOption('bStateSave', true).withOption('bProcessing', false)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', false).withOption('createdRow', function (row) {
            $compile(angular.element(row).contents())($scope);
        }).withOption(
            'headerCallback',
            function (header) {
                if ((!angular.isDefined($scope.headerCompiled))
                    || $scope.headerCompiled) {
                    $scope.headerCompiled = true;
                    $compile(angular.element(header).contents())
                    ($scope);
                }
            }).withOption("select", {
            style: 'multi',
            selector: 'td:first-child'
        })

    $scope.dtInstance = {}

    function renderName(data, type, full) {

        var html = full.model;
        return html;

    }

    function renderJg(data, type, full) {

        var html = full.rackstr + "-" + full.frame;
        return html;

    }

    $scope.selectCheckBoxAll = function (selected) {
        if (selected) {
            $scope.dtInstance.DataTable.rows().select();
        } else {
            $scope.dtInstance.DataTable.rows().deselect();
        }
    }
    $scope.dtColumns = [];
    $scope.dtColumns=zcBaseColsCreate(DTColumnBuilder,'withselect');


    function flush() {
        var ps = {}

        ps.search = $scope.search;

        if ($scope.search == "") {
            notify({
                message: "请输入搜索内容"
            });
            return;
        }
        $http.post($rootScope.project + "/api/base/res/queryResAll.do", ps)
            .success(function (res) {
                if (res.success) {
                    $scope.dtOptions.aaData = res.data;
                } else {
                    notify({
                        message: res.message
                    });
                }
            })
    }

    $scope.query = function () {
        flush()
    }

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

// function getSelectRow() {
// var data = $scope.dtInstance.DataTable.rows({
// selected : true
// })[0];
// if (data.length == 0) {
// notify({
// message : "请至少选择一项"
// });
// return;
// } else if (data.length > 1) {
// notify({
// message : "请最多选择一项"
// });
// return;
// } else {
// return $scope.dtOptions.aaData[data[0]];
// }
// }

    $scope.sure = function () {

        var data = $scope.dtInstance.DataTable.rows({
            selected: true
        })[0];

        if (data.length == 0) {
            notify({
                message: "请至少选择一项"
            });
            return;
        }

        if (type == "one") {
            if (data.length > 1) {
                notify({
                    message: "请最多选择一项"
                });
            }
            var item = $scope.dtOptions.aaData[data[0]];
            if (angular.isDefined(item)) {
                $uibModalInstance.close(item);
            }
            return;
        }

        if (type == "many") {
            var res = [];
            for (var i = 0; i < data.length; i++) {
                var item = $scope.dtOptions.aaData[data[i]];
                res.push(item);
            }
            if (angular.isDefined(res)) {
                $uibModalInstance.close(res);
            }
            return;
        }

    }

}



