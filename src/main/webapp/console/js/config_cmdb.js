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
    // 盘点管理
    $stateProvider.state('pandian', {
        abstract: true,
        url: "/pandian",
        templateUrl: "views/common/content.html?v=" + version
    }).state('pandian.zcpd', {
        url: "/pandian_pcpd",
        data: {pageTitle: '资产盘点'},
        templateUrl: "views/cmdb/zcinventory.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zcinventory.js?v=' + version]
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
        data: {pageTitle: '报修工作', datatype: "full"},
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
                    files: ['views/cmdb/rep/partzc.js?v=' + version]
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
        data: {pageTitle: '资产分类', code: "3"},
        templateUrl: "views/cmdb/zccategory.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zccategory.js?v=' + version]
                }]);
            }
        }
    }).state('cmsetting.goodscat', {
        url: "/cmsetting_goodscat",
        data: {pageTitle: '物品档案', code: "7"},
        templateUrl: "views/cmdb/goodscategory.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/goodscategory.js?v=' + version]
                }]);
            }
        }
    }).state('cmsetting.bjcat', {
        url: "/cmsetting_bjcat?psBtns",
        data: {pageTitle: '备件分类', code: "8"},
        templateUrl: "views/cmdb/zcbjcategory.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zcbjcategory.js?v=' + version]
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
    }).state('cmsetting.labeltpl', {
        url: "/cmsetting_labeltpl",
        data: {pageTitle: '资产标签'},
        templateUrl: "views/cmdb/labeltpl.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/labeltpl.js?v=' + version]
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
    }).state('myprocess.myjy', {
        url: "/myprocess_myjy",
        data: {pageTitle: '我的借用归还', actiontype: "JY", datatype: "self"},
        templateUrl: "views/cmdb/zcaction.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['vendor/basket/basket.0.5.2.full.min.js', 'plugin/form/k-form-design.css']
                }, {
                    serie: true,
                    files: ['views/cmdb/zcaction.js?v=' + version]
                }]);
            }
        }
    }).state('myprocess.myly', {
        url: "/myprocess_myly",
        data: {pageTitle: '我的领用退库', actiontype: "LY", datatype: "self"},
        templateUrl: "views/cmdb/zcaction.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['vendor/basket/basket.0.5.2.full.min.js', 'plugin/form/k-form-design.css']
                }, {
                    serie: true,
                    files: ['views/cmdb/zcaction.js?v=' + version]
                }]);
            }
        }
    }).state('myprocess.mybx', {
        url: "/myprocess_mybx",
        data: {pageTitle: '我的报修', datatype: "self"},
        templateUrl: "views/cmdb/faultrecord.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/faultrecord.js?v=' + version]
                }]);
            }
        }
    });
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
    }).state('hcmgr.hcin', {
        url: "/hcmgr_hcin",
        data: {pageTitle: '耗材入库单'},
        templateUrl: "views/cmdb/hcin.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/hcin.js?v=' + version]
                }]);
            }
        }
    }).state('hcmgr.hcout', {
        url: "/hcmgr_hcout",
        data: {pageTitle: '耗材出库单'},
        templateUrl: "views/cmdb/hcout.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/hcout.js?v=' + version]
                }]);
            }
        }
    }).state('hcmgr.hcsecstore', {
        url: "/hcmgr_hcsecstore",
        data: {pageTitle: '安全库存'},
        template: '<div ng-controller="hcsecStoreCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/hcsecstore.js?v=' + version]
                }]);
            }
        }
    }).state('hcmgr.hcdb', {
        url: "/hcmgr_hcdb",
        data: {pageTitle: '调拨单'},
        templateUrl: "views/cmdb/hcdb.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/hcdb.js?v=' + version]
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
        templateUrl: "views/cmdb/zcsearch.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zcsearch.js?v=' + version]
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
                return $ocLazyLoad.load([
                    {
                        serie: true,
                        files: ['vendor/basket/basket.0.5.2.full.min.js', 'plugin/form/k-form-design.css']
                    }, {
                        serie: true,
                        files: ['views/cmdb/zcaction.js?v=' + version]
                    }]);
            }
        }
    }).state('zcchange.jygh', {
        url: "/zcchange_jygh",
        data: {pageTitle: '资产借用归还', actiontype: "JY", datatype: "full"},
        templateUrl: "views/cmdb/zcaction.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                    {
                        serie: true,
                        files: ['vendor/basket/basket.0.5.2.full.min.js', 'plugin/form/k-form-design.css']
                    }, {
                        serie: true,
                        files: ['views/cmdb/zcaction.js?v=' + version]
                    }]);
            }
        }
    }).state('zcchange.zczy', {
        url: "/zcchange_zczy",
        data: {pageTitle: '资产调拨'},
        templateUrl: "views/cmdb/zcallocation.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                    {
                        serie: true,
                        files: ['views/cmdb/zcallocation.js?v=' + version]
                    }]);
            }
        }
    }).state('zcchange.zcbf', {
        url: "/zcchange_zcbf",
        data: {pageTitle: '资产报废'},
        templateUrl: "views/cmdb/zcbf.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/zcbf.js?v=' + version]
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
        templateUrl: "views/cmdb/genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/genericdev.js?v=' + version]
                }]);
            }
        }
    })
        .state('cf.lightsw', {
            url: "/cf_lightsw?psBtns",
            data: {pageTitle: '光纤交换机', classid: 'zc_lsw'},
            templateUrl: "views/cmdb/genericdev.html?v=" + version,
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([{
                        serie: true,
                        files: ['views/cmdb/genericdev.js?v=' + version]
                    }]);
                }
            }
        })
        .state('cf.outlets', {
            url: "/cf_outlets?psBtns",
            data: {pageTitle: '网点设备', classid: "zc_website", subclass: "Y"},
            templateUrl: "views/cmdb/genericdev.html?v=" + version,
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([{
                        serie: true,
                        files: ['views/cmdb/genericdev.js?v=' + version]
                    }]);
                }
            }
        }).state('cf.safety', {
        url: "/cf_safety?psBtns",
        data: {pageTitle: '安全设备', classid: 'zc_safety', subclass: "Y"},
        templateUrl: "views/cmdb/genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/genericdev.js?v=' + version]
                }]);
            }
        }
    }).state('cf.storage', {
        url: "/cf_storage?psBtns",
        data: {pageTitle: '存储设备', classid: 'zc_storage'},
        templateUrl: "views/cmdb/genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/genericdev.js?v=' + version]
                }]);
            }
        }
    })
        .state('cf.switch', {
            url: "/cf_switch?psBtns",
            data: {pageTitle: '交换机', classid: "51"},
            templateUrl: "views/cmdb/genericdev.html?v=" + version,
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([{
                        serie: true,
                        files: ['views/cmdb/genericdev.js?v=' + version]
                    }]);
                }
            }
        }).state('cf.zcotherhard', {
        url: "/cf_zcotherhard?psBtns",
        data: {pageTitle: '其他资产', classid: "zc_other", subclass: "Y"},
        templateUrl: "views/cmdb/genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/genericdev.js?v=' + version]
                }]);
            }
        }
    }).state('cf.network', {
        url: "/cf_network?psBtns",
        data: {pageTitle: '网络设备', classid: "zc_network", subclass: "Y"},
        templateUrl: "views/cmdb/genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/genericdev.js?v=' + version]
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
        templateUrl: "views/cmdb/genericdev.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/genericdev.js?v=' + version]
                }]);
            }
        }
    })
    $stateProvider.state('report', {
        abstract: true,
        url: "/report",
        templateUrl: "views/common/content.html?v=" + version
    }).state('report.departsummary', {
        url: "/report_departsummary",
        data: {pageTitle: '部门资产汇总'},
        template: '<div ng-controller="cmdbrepPartZcCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/rep/partzc.js?v=' + version]
                }]);
            }
        }
    }).state('report.Taxonomy', {
        url: "/report_Taxonomy",
        data: {pageTitle: '分类使用汇总表'},
        template: '<div ng-controller="catusedreportCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/rep/catused.js?v=' + version]
                }]);
            }
        }
    }).state('report.cleaninglist', {
        url: "/report_cleaninglist",
        data: {pageTitle: '清理清单'},
        templateUrl: "views/cmdb/rep/cleaninglist.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/rep/cleaninglist.js?v=' + version]
                }]);
            }
        }
    }).state('report.catreport', {
        url: "/report_catreport",
        data: {pageTitle: '资产分类汇总表'},
        template: '<div ng-controller="catreportCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/rep/catreport.js?v=' + version]
                }]);
            }
        }
    }).state('report.employeezc', {
        url: "/report_employeezc",
        data: {pageTitle: '员工资产汇总表'},
        // templateUrl: "views/cmdb/rep/catreport.html?v=" + version,
        template: '<div ng-controller="employeezcCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/rep/employeezc.js?v=' + version]
                }]);
            }
        }
    }).state('report.wbexpire', {
        url: "/report_wbexpire",
        data: {pageTitle: '维保到期统计表'},
        // templateUrl: "views/cmdb/rep/catreport.html?v=" + version,
        template: '<div ng-controller="wbexpireCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/cmdb/rep/wbexpire.js?v=' + version]
                }]);
            }
        }
    });
}

//
//
// function zcBaseInOutColsCreate(DTColumnBuilder,selectype){
// //selectype:withoutselect,withselect
//     dtColumns=[];
//     var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
//     dtColumns = [];
//     if(selectype=="withselect"){
//         dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
//             'select-checkbox checkbox_center').renderWith(function() {
//             return ""
//         }));
//     }
//     dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('单据编号').withOption(
//         'sDefaultContent', '').withOption("width", '30'));
//     dtColumns.push(DTColumnBuilder.newColumn('title').withTitle('标题').withOption(
//         'sDefaultContent', '').withOption("width", '30'));
//     dtColumns.push(DTColumnBuilder.newColumn('action').withTitle('出入库').withOption(
//         'sDefaultContent', '').withOption("width", '30').renderWith( function(data, type, full) {
//             if(data=="HCRK"){
//                 return "入库"
//             }else if(data=="HCCK"){
//                 return "出库"
//             }
//             else{
//                 return data;
//             }
//     }));
//     dtColumns.push(DTColumnBuilder.newColumn('status').withTitle('状态').withOption(
//         'sDefaultContent', '').withOption("width", '30').renderWith( function(data, type, full) {
//                 if(data=="none"){
//                     return "无需审批"
//                 }else if(data=="back"){
//                     data=="打回"
//                 }else if(data=="deny"){
//                     data=="拒绝"
//                 }else if(data=="agreen"){
//                     data=="同意"
//                 }else if(data=="wait"){
//                     data=="待审批"
//                 }else{
//                     return data;
//                 }
//     }));
//     dtColumns.push(DTColumnBuilder.newColumn('cnt').withTitle('物品类型数量').withOption(
//         'sDefaultContent', '').withOption("width", '30'));
//
//     dtColumns.push(DTColumnBuilder.newColumn('suppliername').withTitle('资产供应商').withOption(
//         'sDefaultContent', '').withOption("width", '30'));
//     dtColumns.push( DTColumnBuilder.newColumn('buytime').withTitle('采购时间')
//         .withOption('sDefaultContent', ''));
//     dtColumns.push( DTColumnBuilder.newColumn('createTime').withTitle('创建时间')
//         .withOption('sDefaultContent', ''));
//     dtColumns.push( DTColumnBuilder.newColumn('operusername').withTitle('制单人')
//         .withOption('sDefaultContent', ''));
//     dtColumns.push(  DTColumnBuilder.newColumn('remark').withTitle('备注').withOption(
//         'sDefaultContent', ''));
//     return dtColumns;
// }
function zcBaseColsHCCreate(DTColumnBuilder, selectype) {
//selectype:withoutselect,withselect
    dtColumns = [];
    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    dtColumns = [];
    if (selectype == "withselect") {
        dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function () {
            return ""
        }));
    }
    dtColumns.push(DTColumnBuilder.newColumn('classrootname').withTitle('类目').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('单据编号').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('ctid').withTitle('物品编号').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('classname').withTitle('物品类型').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('ctmodel').withTitle('规格型号').withOption(
        'sDefaultContent', '').withOption('width', '50'));
    dtColumns.push(DTColumnBuilder.newColumn('ctunit').withTitle('单位').withOption(
        'sDefaultContent', '').withOption('width', '50'));
    dtColumns.push(DTColumnBuilder.newColumn('ctbrandmark').withTitle('品牌商标').withOption(
        'sDefaultContent', '').withOption('width', '50'));
    dtColumns.push(DTColumnBuilder.newColumn('supplierstr').withTitle('供应商').withOption(
        'sDefaultContent', '').withOption('width', '50'));
    dtColumns.push(DTColumnBuilder.newColumn('ctdowncnt').withTitle('安全库存下限').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('ctupcnt').withTitle('安全库存上限').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('recyclestr').withTitle('资产状态').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    // dtColumns.push(DTColumnBuilder.newColumn('crkstatus').withTitle('单据状态').withOption(
    //     'sDefaultContent', '').withOption("width", '30').renderWith( function(data, type, full) {
    //     if(data=="none"){
    //         return "无需审批"
    //     }else if(data=="back"){
    //         data=="打回"
    //     }else if(data=="deny"){
    //         data=="拒绝"
    //     }else if(data=="agreen"){
    //         data=="同意"
    //     }else if(data=="wait"){
    //         data=="待审批"
    //     }else{
    //         return data;
    //     }
    // }));
    dtColumns.push(DTColumnBuilder.newColumn('batchno').withTitle('批次号').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('belongcom_name').withTitle('所属公司').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('locstr').withTitle('区域').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(DTColumnBuilder.newColumn('warehousestr').withTitle('仓库').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(DTColumnBuilder.newColumn('zc_cnt').withTitle('数量')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('buy_price').withTitle('采购总额')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('lastinventorytimestr').withTitle('最近盘点')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('fs1').withTitle('标签1').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('fs2').withTitle('标签2').withOption(
        'sDefaultContent', ''));
    return dtColumns;
}

function rendeZcLoc(data, type, full) {
    var html = "";
    if (angular.isDefined(full.rackstr)) {
        html = html + full.rackstr;
    }
    if (angular.isDefined(full.frame)) {
        html = html + "[" + full.frame + "]"
    }
    if (angular.isDefined(full.locdtl)) {
        html = html + " " + full.locdtl
    }
    return html;
}

function zcBaseColsCreate(DTColumnBuilder, selectype) {
//selectype:withoutselect,withselect
    dtColumns = [];
    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    dtColumns = [];
    if (selectype == "withselect") {
        dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function () {
            return ""
        }));
    }
    dtColumns.push(DTColumnBuilder.newColumn('classrootname').withTitle('类目').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('资产编号').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('classname').withTitle('资产类型').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('zcsourcestr').withTitle('资产来源').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('supplierstr').withTitle('资产供应商').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('brandstr').withTitle('品牌').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(DTColumnBuilder.newColumn('model').withTitle('规格型号').withOption(
        'sDefaultContent', '').withOption('width', '50'));
    dtColumns.push(DTColumnBuilder.newColumn('recyclestr').withTitle('资产状态').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(DTColumnBuilder.newColumn('sn').withTitle('序列号').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('confdesc').withTitle('配置描述').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('zc_cnt').withTitle('资产数量')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('belongcom_name').withTitle('所属公司').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('comp_name').withTitle('使用公司').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('part_name').withTitle('使用部门').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('used_username').withTitle('使用人').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('locstr').withTitle('区域').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(DTColumnBuilder.newColumn('locdtl').withTitle('位置详情').withOption(
        'sDefaultContent', '').renderWith(rendeZcLoc));
    dtColumns.push(DTColumnBuilder.newColumn('buy_timestr').withTitle('采购时间')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('buy_price').withTitle('采购总额')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('net_worth').withTitle('资产净值')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('wbsupplierstr').withTitle('维保供应商').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(DTColumnBuilder.newColumn('wbstr').withTitle('维保状态').withOption(
        'sDefaultContent', '').withOption('width', '30').renderWith(renderWb));
    dtColumns.push(DTColumnBuilder.newColumn('wbout_datestr').withTitle('脱保时间')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('wb_autostr').withTitle('脱保计算')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('lastinventorytimestr').withTitle('最近盘点')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('fs1').withTitle('标签1').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('fs2').withTitle('标签2').withOption(
        'sDefaultContent', ''));
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

function renderUfloTaskStatus(data, type, full) {
    if (data == "Created") {
        return "新建";
    } else if (data == "Ready") {
        return "准备"
    } else if (data == "Reserved") {
        return "保留"
    } else if (data == "InProgress") {
        return "进行中"
    } else if (data == "Completed") {
        return "已完成"
    } else if (data == "Suspended") {
        return "挂起"
    } else if (data == "Canceled") {
        return "取消"
    } else if (data == "Forwarded") {
        return "转发"
    } else if (data == "Rollback") {
        return "回滚"
    } else if (data == "Withdraw") {
        return "撤回"
    } else {
        return data;
    }
}

function loadOpt(modal_meta, gdicts) {
    var item = modal_meta.meta.item;
    console.log("LoadOpt,Item:", item);
    // 维保
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
            //  if (gdicts.devbrand.length > 0) {
            // }
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
            // if (gdicts.partusers.length > 0) {
            // modal_meta.meta.usedunameSel = gdicts.partusers[0];
            //}
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
    //仓库cmsetting_zccat
    modal_meta.meta.warehouseOpt = gdicts.warehouse;
    if (angular.isDefined(gdicts.warehouse) && gdicts.warehouse.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.warehouse)) {
            for (var i = 0; i < gdicts.warehouse.length; i++) {
                if (gdicts.warehouse[i].dict_item_id == item.warehouse) {
                    modal_meta.meta.warehouseSel = gdicts.warehouse[i];
                }
            }
        } else {
            if (gdicts.warehouse.length > 0) {
                modal_meta.meta.warehouseSel = gdicts.warehouse[0];
            }
        }
    }
    // 区域
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
    //供应商
    modal_meta.meta.zcsupperOpt = gdicts.zcsupper;
    if (angular.isDefined(gdicts.zcsupper) && gdicts.zcsupper.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.supplier)) {
            for (var i = 0; i < gdicts.zcsupper.length; i++) {
                if (gdicts.zcsupper[i].dict_item_id == item.supplier) {
                    modal_meta.meta.zcsupperSel = gdicts.zcsupper[i];
                }
            }
        } else {
            if (gdicts.zcsupper.length > 0) {
                modal_meta.meta.zcsupperSel = gdicts.zcsupper[0];
            }
        }
    }
    //资产来源
    modal_meta.meta.zcsourceOpt = gdicts.zcsource;
    if (angular.isDefined(gdicts.zcsource) && gdicts.zcsource.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.zcsource)) {
            for (var i = 0; i < gdicts.zcsource.length; i++) {
                if (gdicts.zcsource[i].dict_item_id == item.zcsource) {
                    modal_meta.meta.zcsourceSel = gdicts.zcsource[i];
                }
            }
        } else {
            if (gdicts.zcsource.length > 0) {
                modal_meta.meta.zcsourceSel = gdicts.zcsource[0];
            }
        }
    }
    //维保供应商
    modal_meta.meta.zcwbsupperOpt = gdicts.zcwbsupper;
    if (angular.isDefined(gdicts.zcwbsupper) && gdicts.zcwbsupper.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.wbsupplier)) {
            for (var i = 0; i < gdicts.zcwbsupper.length; i++) {
                if (gdicts.zcwbsupper[i].dict_item_id == item.wbsupplier) {
                    modal_meta.meta.zcwbsupperSel = gdicts.zcwbsupper[i];
                }
            }
        } else {
            if (gdicts.zcwbsupper.length > 0) {
                modal_meta.meta.zcwbsupperSel = gdicts.zcwbsupper[0];
            }
        }
    }
    //属于公司
    modal_meta.meta.belongcompOpt = gdicts.belongcomp;
    if (angular.isDefined(gdicts.belongcomp) && gdicts.belongcomp.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.belong_company_id)) {
            for (var i = 0; i < gdicts.belongcomp.length; i++) {
                if (gdicts.belongcomp[i].id == item.belong_company_id) {
                    modal_meta.meta.belongcompSel = gdicts.belongcomp[i];
                }
            }
        } else {
            if (gdicts.belongcomp.length > 0) {
                modal_meta.meta.belongcompSel = gdicts.belongcomp[0];
            }
        }
    }
    //使用公司
    modal_meta.meta.compOpt = gdicts.comp;
    if (angular.isDefined(gdicts.comp) && gdicts.comp.length > 0) {
        if (angular.isDefined(item) && angular.isDefined(item.used_company_id)) {
            for (var i = 0; i < gdicts.comp.length; i++) {
                if (gdicts.comp[i].id == item.used_company_id) {
                    modal_meta.meta.compSel = gdicts.comp[i];
                }
            }
        } else {
            if (gdicts.comp.length > 0) {
                modal_meta.meta.compSel = gdicts.comp[0];
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
    // 部门
    // if(angular.isDefined(modal_meta.meta.compSel) &&angular.isDefined(modal_meta.meta.compSel.id)){
    //     modal_meta.meta.partOpt = gdicts.parts[modal_meta.meta.compSel.id];
    //     if (angular.isDefined(modal_meta.meta.partOpt) &&  modal_meta.meta.partOpt.length > 0) {
    //         if (angular.isDefined(item) && angular.isDefined(item.part_id)) {
    //             for (var i = 0; i <  modal_meta.meta.partOpt.length; i++) {
    //                 if ( modal_meta.meta.partOpt[i].partid == item.part_id) {
    //                     modal_meta.meta.partSel =  modal_meta.meta.partOpt[i];
    //                 }
    //             }
    //         }
    //     }
    // }
    //
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

function modalreviewProcessCtl(meta, $rootScope, $window, $scope, $http, $timeout, $uibModalInstance) {
    var url = $rootScope.project + "uflo/diagram?processKey=" + meta.ptplkey;
    $scope.url = url;
    if (angular.isDefined(meta.form)) {
        $http
            .post(
                $rootScope.project
                + "/api/form/sysForm/selectById.do",
                {
                    id: meta.form
                })
            .success(
                function (res) {
                    if (res.success) {
                        if (angular.isDefined(res.data.ct)) {
                            $timeout(function () {
                                var jd = decodeURI(res.data.ct);
                                let jsonData = angular.fromJson(jd);
                                console.log(jsonData);
                                vm = new Vue({
                                    el: '#app',
                                    data: {
                                        jsonData
                                    },
                                    mounted() {
                                        this.init()
                                    },
                                    methods: {
                                        init() {
                                            console.log(this);
                                        },
                                        handleSubmit(p) {
                                            // 通过表单提交按钮触发，获取promise对象
                                            p().then(res => {
                                                // 获取数据成功
                                                alert(JSON.stringify(res))
                                            })
                                                .catch(err => {
                                                    console.log(err, '校验失败')
                                                })
                                        },
                                        getData() {
                                            // 通过函数获取数据
                                            this.$refs.kfb.getData().then(res => {
                                                // 获取数据成功
                                                alert(JSON.stringify(res))
                                            })
                                                .catch(err => {
                                                    console.log(err, '校验失败')
                                                })
                                        }
                                    }
                                })
                            }, 800);
                        }
                    }
                })
    }
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
    console.log(meta, pagetype, task);
    //pagetype:sp,query
    //审批流程，已审批意见，审批意见,默认隐藏
    $scope.hidectl = {"pagespbtnhide": true, "flowchart": true, "flowsuggestlist": true, "flowsuggest": true};
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.url = "";
    //审批已经
    $scope.spsugguest = [];
    $scope.spsuggest = "";
    //页面是否审批类型打开
    $scope.pagetype = pagetype;
    if ($scope.pagetype == "sp") {
        $scope.hidectl.pagespbtnhide = false;
    } else {
        $scope.hidectl.pagespbtnhide = true;
    }

    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withDOM('frtlip').withPaginationType('simple').withDisplayLength(
            50).withOption("ordering", false).withOption("responsive",
            false).withOption("searching", false).withOption('scrollY',
            300).withOption('scrollX', true).withOption(
            'bAutoWidth', true).withOption('scrollCollapse', true)
        .withOption('paging', false).withOption('bStateSave', true).withOption('bProcessing', false)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', false).withOption('createdRow', function (row) {
            $compile(angular.element(row).contents())($scope);
        });
    $scope.dtColumns = [];
    $scope.dtColumns = zcBaseColsCreate(DTColumnBuilder, 'withoutselect');
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
                    if (res.data.ifsp == "1") {
                        //需要审批的单据
                        $scope.hidectl.flowchart = false;
                        $scope.hidectl.flowsuggestlist = false;
                        $scope.hidectl.flowsuggest = false;
                    } else {
                        $scope.hidectl.pagespbtnhide = true;
                    }
                    //获取审批
                    if (angular.isDefined(res.data.processinstanceid) && res.data.processinstanceid != "") {
                        $http
                            .post(
                                $rootScope.project
                                + "/api/flow/loadProcessInstanceData.do",
                                {
                                    processInstanceId: res.data.processinstanceid
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
                            + res.data.processinstanceid;
                    }
                    var dynamicData = res.data.formdata;
                    let vm;
                    $timeout(function () {
                        var jd = decodeURI(res.data.formconf);
                        if (angular.isDefined(jd) && jd != "undefined" && jd != "") {
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
                                        this.$refs.kfb.setData(angular.fromJson(res.data.formdata));
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
        DTColumnBuilder.newColumn('create_time').withTitle('操作时间')
            .withOption('sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('fuuid').withTitle('报修编号').withOption(
            'sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', '').withOption('width', '30')
    ]
    $scope.dtOptions2 = DTOptionsBuilder.fromFnPromise().withOption(
        'createdRow', function (row) {
            // Recompiling so we can bind Angular,directive to the
            $compile(angular.element(row).contents())($scope);
        });
    $scope.dtInstance2 = {}

    function renderType(data, type, full) {
        if (data == "DB") {
            return "资产调拨";
        } else if (data == "LY") {
            return "资产领用/退库"
        } else if (data == "JY") {
            return "资产借用/归还"
        } else {
            return data;
        }
    }

    $scope.dtColumns2 = [
        DTColumnBuilder.newColumn('create_time').withTitle('操作时间')
            .withOption('sDefaultContent', '').withOption('width', '30'),
        DTColumnBuilder.newColumn('busuuid').withTitle('单据编号').withOption(
            'sDefaultContent', '').withOption('width', '30').renderWith(renderType),
        DTColumnBuilder.newColumn('type').withTitle('变更类型').withOption(
            'sDefaultContent', '').withOption('width', '30').renderWith(renderType),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', '')]
    if (angular.isDefined(meta.id)) {
        // 加载数据
        $http.post($rootScope.project + "/api/base/res/queryResAllById.do", {
            id: meta.id
        }).success(function (res) {
            if (res.success) {
                $scope.item = res.data.data;
                var extdata = [];
                var extdataitem = [];
                for (var i = 0; i < res.data.extattr.length; i++) {
                    if (i % 4 == 3 && i > 0) {
                        extdataitem.push(res.data.extattr[i]);
                        extdata.push(extdataitem);
                        extdataitem = [];
                    } else {
                        extdataitem.push(res.data.extattr[i]);
                    }
                }
                if (extdataitem.length > 0) {
                    extdata.push(extdataitem);
                }
                $scope.extdata = extdata;
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

function modal_common_ZcListCtl($timeout, $localStorage, notify, $log, $uibModal,
                                $uibModalInstance, $scope, $http, $rootScope, DTOptionsBuilder,
                                DTColumnBuilder, $compile, data) {
    // type:one|many
    // datatype: LY|
    console.log("data:" + data);
    $scope.partOpt = [];
    $scope.partSel = {};
    $scope.compOpt = [];
    $scope.compSel = {};
    $scope.areaOpt = [];
    $scope.areaSel = {};
    $scope.recycleOpt = [];
    $scope.recycleSel = {};
    var gdicts = {};
    var dicts = "devrecycle,devdc";
    $http
        .post($rootScope.project + "/api/zc/queryDictFast.do", {
            uid: "zclistmodal",
            dicts: dicts,
            parts: "Y",
            comp: "Y"
        })
        .success(
            function (res) {
                if (res.success) {
                    gdicts = res.data;
                    angular.copy(gdicts.devrecycle, $scope.recycleOpt);
                    $scope.recycleOpt.unshift({
                        dict_item_id: "all",
                        name: "全部"
                    });
                    $scope.recycleSel = $scope.recycleOpt[0];
                    angular.copy(gdicts.comp, $scope.compOpt);
                    $scope.compOpt.unshift({
                        id: "all",
                        name: "全部"
                    });
                    $scope.compSel = $scope.compOpt[0];
                    angular.copy(gdicts.parts, $scope.partOpt);
                    $scope.partOpt.unshift({
                        partid: "all",
                        name: "全部"
                    });
                    $scope.partSel = $scope.partOpt[0];
                    angular.copy(gdicts.devdc, $scope.areaOpt);
                    $scope.areaOpt.unshift({
                        dict_item_id: "all",
                        name: "全部"
                    });
                    $scope.areaSel = $scope.areaOpt[0];
                    flush();
                } else {
                    notify({
                        message: res.message
                    });
                }
            })
    if (!angular.isDefined(data.type)) {
        data.type = "many"
    }
    $scope.search = "";
    // 分类
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 200)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withOption('bStateSave', true).withOption('bProcessing', false)
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
    $scope.dtColumns = zcBaseColsCreate(DTColumnBuilder, 'withselect');

    function flush() {
        var ps = {};
        ps.id = data.id;
        ps.type = data.type;
        ps.datarange = data.datarange;
        if (angular.isDefined($scope.search)) {
            ps.search = $scope.search;
        }
        if (angular.isDefined($scope.compSel.id) && $scope.compSel.id != 'all') {
            ps.comp = $scope.compSel.id;
        }
        console.log($scope.partSel);
        if (angular.isDefined($scope.partSel.partid) && $scope.partSel.partid != 'all') {
            ps.part = $scope.partSel.partid;
        }
        if (angular.isDefined($scope.recycleSel.dict_item_id) && $scope.recycleSel.dict_item_id != 'all') {
            ps.recycle = $scope.recycleSel.dict_item_id;
        }
        if (angular.isDefined($scope.areaSel.dict_item_id) && $scope.areaSel.dict_item_id != 'all') {
            ps.loc = $scope.areaSel.dict_item_id;
        }
        ps.category = 3;
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
    $scope.sure = function () {
        var dtdata = $scope.dtInstance.DataTable.rows({
            selected: true
        })[0];
        if (dtdata.length == 0) {
            notify({
                message: "请至少选择一项"
            });
            return;
        }
        if (data.type == "one") {
            if (dtdata.length > 1) {
                notify({
                    message: "请最多选择一项"
                });
            }
            var item = $scope.dtOptions.aaData[dtdata[0]];
            if (angular.isDefined(item)) {
                $uibModalInstance.close(item);
            }
            return;
        }
        if (data.type == "many") {
            var res = [];
            for (var i = 0; i < dtdata.length; i++) {
                var item = $scope.dtOptions.aaData[dtdata[i]];
                res.push(item);
            }
            if (angular.isDefined(res)) {
                $uibModalInstance.close(res);
            }
            return;
        }
    }
}

//资产表单选择
function modalFormListSelCtl($timeout, $localStorage, notify, $log, $uibModal,
                             $window, $uibModalInstance, $scope, $http, $rootScope,
                             DTOptionsBuilder, DTColumnBuilder, $compile, data) {
    $scope.typeOpt = [];
    $scope.typeSel = "";
    var formtype = [];
    $http.post($rootScope.project + "/api/ctCategory/ext/selectListByRoot.do",
        {
            root: "6"
        }).success(function (res) {
        if (res.success) {
            $scope.typeOpt = res.data;
            if ($scope.typeOpt.length > 0) {
                $scope.typeSel = $scope.typeOpt[0];
                flush();
            }
        } else {
            notify({
                message: res.message
            });
        }
    });
    // 分类
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 200)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withOption('bStateSave', true).withOption('bProcessing', false)
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

    function renderAction(data, type, full) {
        var html = "";
        return html;
    }

    $scope.selectCheckBoxAll = function (selected) {
        if (selected) {
            $scope.dtInstance.DataTable.rows().select();
        } else {
            $scope.dtInstance.DataTable.rows().deselect();
        }
    }
    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns = [
        DTColumnBuilder.newColumn(null).withTitle("").withClass(
            'select-checkbox checkbox_center').renderWith(function () {
            return ""
        }),
        DTColumnBuilder.newColumn('id').withTitle('编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('createTime').withTitle('创建时间').withOption(
            'sDefaultContent', '')
    ]

    function flush() {
        var ps = {};
        ps.owner = $scope.typeSel.id;
        $http.post(
            $rootScope.project + "/api/form/sysForm/ext/selectByOwner.do",
            ps).success(function (res) {
            $scope.dtOptions.aaData = res.data;
        })
    }

    $scope.query = function () {
        flush()
    }
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    function getSelectRow() {
        var data = $scope.dtInstance.DataTable.rows({
            selected: true
        })[0];
        if (data.length == 0) {
            notify({
                message: "请至少选择一项"
            });
            return;
        } else if (data.length > 1) {
            notify({
                message: "请最多选择一项"
            });
            return;
        } else {
            return $scope.dtOptions.aaData[data[0]];
        }
    }

    $scope.review = function () {
        var obj = getSelectRow();
        if (angular.isDefined(obj)) {
            $window
                .open($rootScope.project
                    + "/console/views/system/form/formdesign.html?id="
                    + obj.id)
        }
    }
    $scope.sure = function () {
        var obj = getSelectRow();
        if (angular.isDefined(obj)) {
            $uibModalInstance.close(obj);
        }
    }
}

//资产流程选择
function modalFlowSelectCtl($timeout, $localStorage, notify, $log, $uibModal,
                            $uibModalInstance, $scope, $http, $rootScope, DTOptionsBuilder,
                            DTColumnBuilder, $compile) {
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withDOM('frtlip').withPaginationType('simple').withDisplayLength(
            50).withOption("ordering", false).withOption("responsive",
            false).withOption("searching", false).withOption('scrollY',
            300).withOption('scrollX', true).withOption(
            'bAutoWidth', true).withOption('scrollCollapse', true)
        .withOption('paging', false).withOption('bStateSave', true).withOption('bProcessing', false)
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
        });

    function stateChange(iColumn, bVisible) {
    }

    $scope.dtInstance = {}
    $scope.selectCheckBoxAll = function (selected) {
        if (selected) {
            $scope.dtInstance.DataTable.rows().select();
        } else {
            $scope.dtInstance.DataTable.rows().deselect();
        }
    }

    function renderStatus(data, type, full) {
        var res = "";
        if (data == "normal") {
            res = "正常";
        } else if (data == "stop") {
            res = "停用";
        } else {
            res = data;
        }
        return res;
    }

    function renderType(data, type, full) {
        var res = "";
        if (data == "form") {
            res = "表单模式";
        } else if (data == "withoutform") {
            res = "无表单模式";
        } else {
            res = data;
        }
        return res;
    }

    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns = [];
    $scope.dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml)
        .withClass('select-checkbox checkbox_center').renderWith(
            function () {
                return ""
            }));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('id').withTitle('ID')
        .withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('name').withTitle('名称')
        .withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('type').withTitle('类型')
        .withOption('sDefaultContent', '').renderWith(renderType)),
        $scope.dtColumns.push(DTColumnBuilder.newColumn('status').withTitle('状态')
            .withOption('sDefaultContent', '').renderWith(renderStatus)),
        $scope.dtColumns.push(DTColumnBuilder.newColumn('ptplkey')
            .withTitle('流程模版').withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('formname').withTitle('表单')
        .withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('mark').withTitle('备注')
        .withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('createTime').withTitle('创建时间')
        .withOption('sDefaultContent', ''));
    $scope.catRootOpt = [];
    $scope.catRootSel = "";
    $scope.item = {};
    var ps = {};
    ps.ids = angular.toJson([5]);
    $http
        .post($rootScope.project + "/api/ctCategoryRoot/ext/selectList.do",
            ps).success(function (res) {
        if (res.success) {
            $scope.catRootOpt = res.data;
            if ($scope.catRootOpt.length > 0) {
                $scope.catRootSel = $scope.catRootOpt[0];
                flushTree($scope.catRootSel.id)
            }
        } else {
            notify({
                message: res.message
            });
        }
    });
    // 树配置
    $scope.treeConfig = {
        core: {
            multiple: false,
            animation: true,
            error: function (error) {
                $log.error('treeCtrl: error from js tree - '
                    + angular.toJson(error));
            },
            check_callback: true,
            worker: true
        },
        loading: "加载中……",
        ui: {
            theme_name: "classic" // 设置皮肤样式
        },
        rules: {
            type_attr: "rel", // 设置节点类型
            valid_children: "root" // 只有root节点才能作为顶级结点
        },
        callback: {
            onopen: function (node, tree_obj) {
                return true;
            }
        },
        types: {
            "default": {
                icon: 'glyphicon glyphicon-th'
            },
            root: {
                icon: 'glyphicon glyphicon-home'
            },
            "node": {
                "icon": "glyphicon glyphicon-tag"
            },
            "category": {
                "icon": "glyphicon glyphicon-equalizer"
            }
        },
        version: 1,
        plugins: ['themes', 'types', 'contextmenu', 'changed'],
        contextmenu: {
            items: {}
        }
    }
    $scope.addNewNode = function () {
        $scope.treeData.push({
            id: (newId++).toString(),
            parent: $scope.newNode.parent,
            text: $scope.newNode.text
        });
    };
    $scope.modelChanges = function (t) {
        return true;
    }
    $scope.curSelNode = "";
    $scope.readyCB = function () {
        $scope.tree = $scope.treeInstance.jstree(true)
        // 展开所有节点
        $scope.tree.open_all();
        // 响应节点变化
        $scope.treeInstance.on("changed.jstree", function (e, data) {
            if (data.action == "select_node") {
                // 加载数据
                var snodes = $scope.tree.get_selected();
                if (snodes.length == 1) {
                    var node = snodes[0];
                    $scope.curSelNode = node;
                    flush();
                }
            }
        });
    }
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    function flushTree(id) {
        $http
            .post(
                $rootScope.project
                + "/api/ctCategroy/queryCategoryTreeList.do", {
                    root: id
                }).success(function (res) {
            if (res.success) {
                $scope.ignoreChanges = true;
                $scope.treeData = angular.copy(res.data);
                $scope.treeConfig.version++;
            } else {
                notify({
                    message: res.message
                });
            }
        });
    }

    $scope.query = function () {
        flushTree($scope.catRootSel.id);
    }

    function flush() {
        $http.post(
            $rootScope.project
            + "/api/flow/sysProcessDef/ext/selectList.do", {
                owner: $scope.curSelNode
            }).success(function (res) {
            if (res.success) {
                $scope.dtOptions.aaData = res.data;
            } else {
                notify({
                    message: res.message
                });
            }
        });
    }

    function getSelectRow() {
        var data = $scope.dtInstance.DataTable.rows({
            selected: true
        })[0];
        if (data.length == 0) {
            notify({
                message: "请至少选择一项"
            });
            return;
        } else if (data.length > 1) {
            notify({
                message: "请最多选择一项"
            });
            return;
        } else {
            return $scope.dtOptions.aaData[data[0]];
        }
    }

    $scope.sure = function () {
        var item = getSelectRow();
        if (angular.isDefined(item) && angular.isDefined(item.id)) {
            $uibModalInstance.close(item);
        }
    };
}

//流程实例选择
function modalFlowInstanceListSelCtl($timeout, $localStorage, notify, $log, $uibModal,
                                     $uibModalInstance, $scope, $http, $rootScope, DTOptionsBuilder,
                                     DTColumnBuilder, $compile) {
    // 分类
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 200)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withOption('bStateSave', true).withOption('bProcessing', false)
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
    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns = [
        DTColumnBuilder.newColumn(null).withTitle("").withClass(
            'select-checkbox checkbox_center').renderWith(function () {
            return ""
        }),
        DTColumnBuilder.newColumn('id').withTitle('编号').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('name').withTitle('流程模版').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('key').withTitle('流程实例').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('version').withTitle('版本号').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('createDate').withTitle('创建时间')
            .withOption('sDefaultContent', '')
            .withOption('width', '30')]

    function flush() {
        var ps = {}
        ps.pageIndex = 1;
        ps.pageSize = 100;
        $http.post($rootScope.project + "uflo/central/loadProcess", ps)
            .success(function (res) {
                $scope.dtOptions.aaData = res.result;
            })
    }

    $scope.query = function () {
        flush()
    }
    flush();
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    function getSelectRow() {
        var data = $scope.dtInstance.DataTable.rows({
            selected: true
        })[0];
        if (data.length == 0) {
            notify({
                message: "请至少选择一项"
            });
            return;
        } else if (data.length > 1) {
            notify({
                message: "请最多选择一项"
            });
            return;
        } else {
            return $scope.dtOptions.aaData[data[0]];
        }
    }

    $scope.sure = function () {
        var id = getSelectRow();
        if (angular.isDefined(id)) {
            $uibModalInstance.close(id);
        }
    }
}
