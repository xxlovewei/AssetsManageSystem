function GetDateNowId() {
    var vNow = new Date();
    var sNow = "";
    sNow += String(vNow.getFullYear());
    sNow += String(vNow.getMonth() + 1);
    sNow += String(vNow.getDate());
    sNow += String(vNow.getHours());
    sNow += String(vNow.getMinutes());
    sNow += String(vNow.getSeconds());
    sNow += String(vNow.getMilliseconds());
    return sNow;
}

function zcjyghlistCtl($confirm, $timeout, $localStorage, notify, $log, $uibModal,
                       $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                       DTColumnBuilder, $compile) {
    var item = meta;
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 600)
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
        }).withButtons([
            {
                extend: 'csv',
                text: 'Excel(当前页)',
                exportOptions: {
                    columns: ':visible',
                    trim: true,
                    modifier: {
                        page: 'current'
                    }
                }
            },
            {
                extend: 'print',
                text: '打印(当前页)',
                exportOptions: {
                    columns: ':visible',
                    stripHtml: false,
                    columns: ':visible',
                    modifier: {
                        page: 'current'
                    }
                }
            }
        ]);
    $scope.dtInstance = {}
    $scope.dtColumns = [];
    var dtColumns = [];

    function renderZcReturn(data, type, full) {
        if (data == "1") {
            return "已归还"
        } else if (data == "0") {
            return "未归还"
        } else {
            return data;
        }
    }

    $scope.dtColumns = [
        DTColumnBuilder.newColumn('busuuid').withTitle('单据编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('uuid').withTitle('资产编号').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('model').withTitle('规格型号').withOption(
            'sDefaultContent', '').withOption('width', '50'),
        DTColumnBuilder.newColumn('recyclestr').withTitle('资产状态').withOption(
            'sDefaultContent', '').withOption('width', '30').renderWith(renderZcRecycle),
        DTColumnBuilder.newColumn('sn').withTitle('序列').withOption(
            'sDefaultContent', '').withOption('width', '50'),
        DTColumnBuilder.newColumn('busdatestr').withTitle('借用时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('returndatestr').withTitle('预计归还时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('rreturndatestr').withTitle('实际归还时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('isreturn').withTitle('是否归还').withOption(
            'sDefaultContent', '').renderWith(renderZcReturn),
        DTColumnBuilder.newColumn('returnuuid').withTitle('归还单据号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('create_time').withTitle('创建时间').withOption(
            'sDefaultContent', '')]

    function flush() {
        $http.post($rootScope.project + "/api/zc/resLoanreturn/ext/selectByUuid.do",
            item).success(function (res) {
            if (res.success) {
                $scope.dtOptions.aaData = res.data;
            } else {
                notify({
                    message: res.message
                });
            }
        })
    }

    flush();
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}

function ghSaveCtl($timeout, $localStorage, notify, $log, $uibModal,
                   $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                   DTColumnBuilder, $compile, $confirm) {
    $scope.item = {};
    $scope.processuserOpt = meta.dict.partusers;
    $scope.processuserSel = "";
    $scope.date = {
        rreturndate: moment()
    }
    if ($scope.processuserOpt.length > 0) {
        $scope.processuserSel = $scope.processuserOpt[0];
    }
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.sure = function () {
        $scope.item.rreturndate = $scope.date.rreturndate.format('YYYY-MM-DD');
        $scope.item.rprocessuserid = $scope.processuserSel.user_id;
        $scope.item.rprocessusername = $scope.processuserSel.name;
        $scope.item.bustype = "gh";
        $scope.item.id = meta.id;
        $confirm({
            text: '是否确定归还?'
        }).then(
            function () {
                $http.post($rootScope.project + "/api/zc/resLoanreturn/ext/zcgh.do",
                    $scope.item).success(function (res) {
                    if (res.success) {
                        $uibModalInstance.close('OK');
                    }
                    notify({
                        message: res.message
                    });
                })
            });
    }
    $scope.review = function () {
        var mdata = {};
        mdata.id = "";
        mdata.type = "many";
        mdata.datarange = "TK";
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_common_zclist.html',
            controller: modal_common_ZcListCtl,
            size: 'blg',
            resolve: {
                data: function () {
                    return mdata
                }
            }
        });
        modalInstance.result.then(function (result) {
            $scope.dtOptions.aaData = result;
        }, function (reason) {
            $log.log("reason", reason)
        });
    }
}

function loanSaveCtl($timeout, $localStorage, notify, $log, $uibModal,
                     $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                     DTColumnBuilder, $compile, $confirm) {
    $scope.item = {};
    $scope.useduserOpt = meta.dict.partusers;
    $scope.useduserSel = "";
    if ($scope.useduserOpt.length > 0) {
        $scope.useduserSel = $scope.useduserOpt[0];
    }
    $scope.processuserOpt = meta.dict.partusers;
    $scope.processuserSel = "";
    if ($scope.processuserOpt.length > 0) {
        $scope.processuserSel = $scope.processuserOpt[0];
    }
    $scope.date = {
        busdate: moment(),
        returndate: moment(),
    }
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 600)
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
        });
    $scope.dtInstance = {}
    var dtColumns = [];

    function renderZCAction(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";
        acthtml = acthtml + " <span ng-click=\"delitem('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">删除</span>   ";
        return acthtml;
    }

    $scope.delitem = function (id) {
        var del = 0;
        for (var i = 0; i < $scope.dtOptions.aaData.length; i++) {
            if ($scope.dtOptions.aaData[i].id == id) {
                del = i;
                break;
            }
        }
        $scope.dtOptions.aaData.splice(del, 1);
    }
    dtColumns.push(DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
        'sDefaultContent', '').withOption("width", '30').renderWith(renderZCAction));
    dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('资产编号').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('model').withTitle('规格型号').withOption(
        'sDefaultContent', '').withOption('width', '50'));
    dtColumns.push(DTColumnBuilder.newColumn('recyclestr').withTitle('资产状态').withOption(
        'sDefaultContent', '').withOption('width', '30').renderWith(renderZcRecycle));
    dtColumns.push(DTColumnBuilder.newColumn('zc_cnt').withTitle('数量')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('usefullifestr').withTitle('使用年限')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('wbsupplierstr').withTitle('维保供应商').withOption(
        'sDefaultContent', '').withOption('width', '30').renderWith(renderDTFontColoBluerH));
    dtColumns.push(DTColumnBuilder.newColumn('wbstr').withTitle('维保状态').withOption(
        'sDefaultContent', '').withOption('width', '30').renderWith(renderWb));
    dtColumns.push(DTColumnBuilder.newColumn('wbout_datestr').withTitle('脱保日期')
        .withOption('sDefaultContent', '').renderWith(renderDTFontColoBluerH));
    dtColumns.push(DTColumnBuilder.newColumn('wb_autostr').withTitle('脱保计算')
        .withOption('sDefaultContent', '').renderWith(renderDTFontColoBluerH));
    dtColumns.push(DTColumnBuilder.newColumn('brandstr').withTitle('品牌').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(DTColumnBuilder.newColumn('belongcom_name').withTitle('所属公司').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('comp_name').withTitle('使用公司').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('part_name').withTitle('使用部门').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('used_username').withTitle('使用人').withOption(
        'sDefaultContent', ''));
    $scope.dtColumns = dtColumns;
    $scope.dtOptions.aaData = [];
    $scope.sure = function () {
        if ($scope.dtOptions.aaData.length == 0) {
            notify({
                message: "请选择资产"
            });
            return;
        }
        $scope.item.busdate = $scope.date.busdate.format('YYYY-MM-DD');
        $scope.item.returndate = $scope.date.returndate.format('YYYY-MM-DD');
        $scope.item.lruserid = $scope.useduserSel.user_id;
        $scope.item.lrusername = $scope.useduserSel.name;
        $scope.item.lprocessuserid = $scope.processuserSel.user_id;
        $scope.item.lprocessusername = $scope.processuserSel.name;
        $scope.item.bustype = "jy";
        $scope.item.items = angular.toJson($scope.dtOptions.aaData);
        $confirm({
            text: '是否确定借用?'
        }).then(
            function () {
                $http.post($rootScope.project + "/api/zc/resLoanreturn/ext/zcjy.do",
                    $scope.item).success(function (res) {
                    if (res.success) {
                        $uibModalInstance.close('OK');
                    }
                    notify({
                        message: res.message
                    });
                })
            });
    }
    $scope.review = function () {
        var mdata = {};
        mdata.id = "";
        mdata.type = "many";
        mdata.datarange = "JY";
        mdata.showusefullife = "true";
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_common_zclist.html',
            controller: modal_common_ZcListCtl,
            size: 'blg',
            resolve: {
                data: function () {
                    return mdata
                }
            }
        });
        modalInstance.result.then(function (result) {
            $scope.dtOptions.aaData = result;
        }, function (reason) {
            $log.log("reason", reason)
        });
    }
}

function loanreturnCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $window,
                       $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
    var gdict = {};
    var dicts = "devdc";
    $http
        .post($rootScope.project + "/api/zc/queryDictFast.do", {
            dicts: dicts,
            parts: "Y",
            partusers: "Y",
            comp: "Y",
            belongcomp: "N",
            zccatused: "N",
            uid: "zcjydata"
        })
        .success(
            function (res) {
                if (res.success) {
                    gdict = res.data;
                } else {
                    notify({
                        message: res.message
                    });
                }
            })
    // 分类
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 600)
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
    $scope.selectCheckBoxAll = function (selected) {
        if (selected) {
            $scope.dtInstance.DataTable.rows().select();
        } else {
            $scope.dtInstance.DataTable.rows().deselect();
        }
    }

    function renderAction(data, type, full) {
        //分配，删除，详情
        if (data == "wait") {
            return "维修中"
        } else if (data == "finish") {
            return "已完成"
        } else {
            return data;
        }
    }

    function renderCheckStatus(data, type, full) {
        if (data == "init") {
            return "等待校验"
        } else if (data == "success") {
            return "成功"
        } else if (data == "failed") {
            return "失败"
        } else {
            return data;
        }
    }

    function renderCGStatus(data, type, full) {
        if (data == "wait") {
            return "未处理"
        } else if (data == "cancel") {
            return "取消"
        } else if (data == "success") {
            return "完成"
        } else if (data == "failed") {
            return "失败"
        } else {
            return data;
        }
    }

    function renderAction(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";
        acthtml = acthtml + " <button ng-click=\"detail('"
            + full.busuuid
            + "','" + full.status + "','" + full.bustype + "')\" class=\"btn-white btn btn-xs\">单据明细</button>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    function renderprocess(data, type, full) {
        if (angular.isDefined(data) && data.length() > 0) {
            return "申请详情"
        } else {
            return "无审批"
        }
    }

    function renderBusStatus(data, type, full) {
        if (data == "JY") {
            return "借用"
        } else if (data == "GH") {
            return "归还"
        } else {
            return data;
        }
    }

    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns = [
        DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function () {
            return ""
        }),
        DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
            'sDefaultContent', '').renderWith(renderAction),
        DTColumnBuilder.newColumn('busuuid').withTitle('单据编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('status').withTitle('办理状态').withOption(
            'sDefaultContent', '').renderWith(renderCGStatus),
        DTColumnBuilder.newColumn('busstatus').withTitle('业务状态').withOption(
            'sDefaultContent', '').renderWith(renderBusStatus),
        DTColumnBuilder.newColumn('pinst').withTitle('流程详情').withOption(
            'sDefaultContent', '').renderWith(renderprocess),
        DTColumnBuilder.newColumn('lrusername').withTitle('借用人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('busdatestr').withTitle('借用时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('returndatestr').withTitle('预计归还时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('rreturndatestr').withTitle('实际归还时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('lprocessusername').withTitle('借用处理人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('rprocessusername').withTitle('归还处理人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('createusername').withTitle('创建人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('create_time').withTitle('创建时间').withOption(
            'sDefaultContent', '')
    ]
    $scope.query = function () {
        flush();
    }
    var meta = {
        tablehide: false,
        tools: [
            {
                id: "btn",
                label: "",
                type: "btn",
                show: true,
                priv: "select",
                template: ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">查询</button>'
            },
            {
                id: "btn3",
                label: "",
                type: "btn",
                show: true,
                priv: "insert",
                template: ' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">借用</button>'
            },
            {
                id: "btn3",
                label: "",
                type: "btn",
                show: true,
                priv: "insert",
                template: ' <button ng-click="gh()" class="btn btn-sm btn-primary" type="submit">归还</button>'
            }]
    }
    $scope.meta = meta;

    function flush() {
        var ps = {};
        $http
            .post($rootScope.project + "/api/zc/resLoanreturn/ext/selectList.do",
                ps).success(function (res) {
            if (res.success) {
                $scope.dtOptions.aaData = res.data;
            } else {
                notify({
                    message: res.message
                });
            }
        })
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

    $scope.detail = function (uuid, status, type) {
        meta.uuid = uuid;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_jyghlist.html',
            controller: zcjyghlistCtl,
            size: 'blg',
            resolve: {
                meta: function () {
                    return meta;
                }
            }
        });
    }

    function action(id) {
        var meta = {};
        meta.id = id;
        meta.dict = gdict;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_loanreturnSave.html',
            controller: loanSaveCtl,
            size: 'blg',
            resolve: {
                meta: function () {
                    return meta;
                }
            }
        });
        modalInstance.result.then(function (result) {
            flush();
        }, function (reason) {
            $log.log("reason", reason)
        });
    }

    $scope.add = function () {
        action();
    }
    $scope.gh = function () {
        var row = getSelectRow();
        if (angular.isDefined(row)) {
            var meta = {};
            meta.dict = gdict;
            meta.id = row.id;
            var modalInstance = $uibModal.open({
                backdrop: true,
                templateUrl: 'views/cmdb/modal_ghSave.html',
                controller: ghSaveCtl,
                size: 'blg',
                resolve: {
                    meta: function () {
                        return meta;
                    }
                }
            });
            modalInstance.result.then(function (result) {
                flush();
            }, function (reason) {
                $log.log("reason", reason)
            });
        }
    }
    flush();
};
app.register.controller('loanreturnCtl', loanreturnCtl);
