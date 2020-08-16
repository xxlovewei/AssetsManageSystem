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

function renderbcomp(data, type, full) {
    if (angular.isUndefined(data)) {
        data = ""
    }
    if (full.tbelongcompstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderbuyprice(data, type, full) {
    if (angular.isUndefined(data)) {
        data = ""
    }
    if (full.tbuypricestatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function rendernetworth(data, type, full) {
    if (angular.isUndefined(data)) {
        data = ""
    }
    if (full.tnetworthstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderaccumulateddepreciation(data, type, full) {
    if (angular.isUndefined(data)) {
        data = ""
    }
    if (full.taccumulatedstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderesidualvalue(data, type, full) {
    if (angular.isUndefined(data)) {
        data = ""
    }
    if (full.tresidualvaluestatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function cgcwlistCtl($confirm, $timeout, $localStorage, notify, $log, $uibModal,
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
    dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('资产编号').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('classname').withTitle('资产类别').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('model').withTitle('规格型号').withOption(
        'sDefaultContent', '').withOption('width', '50'));
    dtColumns.push(DTColumnBuilder.newColumn('recyclestr').withTitle('资产状态').withOption(
        'sDefaultContent', '').withOption('width', '30').renderWith(renderZcRecycle));
    dtColumns.push(DTColumnBuilder.newColumn('fbelongcom_fullname').withTitle('所属公司(变更前)').withOption(
        'sDefaultContent', '').renderWith(renderDTFontColorGreenH));
    dtColumns.push(DTColumnBuilder.newColumn('tbelongcom_fullname').withTitle('所属公司(变更后)').withOption(
        'sDefaultContent', '').renderWith(renderbcomp));
    dtColumns.push(DTColumnBuilder.newColumn('fbuyprice').withTitle('采购单价(变更前)')
        .withOption('sDefaultContent', '').renderWith(renderDTFontColorGreenH));
    dtColumns.push(DTColumnBuilder.newColumn('tbuyprice').withTitle('采购单价(变更后)')
        .withOption('sDefaultContent', '').renderWith(renderbuyprice));
    dtColumns.push(DTColumnBuilder.newColumn("fnetworth").withTitle('资产净值(变更前)')
        .withOption('sDefaultContent', '').renderWith(renderDTFontColoPurpleH));
    dtColumns.push(DTColumnBuilder.newColumn("tnetworth").withTitle('资产净值(变更后)')
        .withOption('sDefaultContent', '').renderWith(rendernetworth));
    dtColumns.push(DTColumnBuilder.newColumn("faccumulateddepreciation").withTitle('累计折旧(变更前)')
        .withOption('sDefaultContent', '').renderWith(renderDTFontColorGreenH));
    dtColumns.push(DTColumnBuilder.newColumn("taccumulateddepreciation").withTitle('累计折旧(变更后)')
        .withOption('sDefaultContent', '').renderWith(renderaccumulateddepreciation));
    dtColumns.push(DTColumnBuilder.newColumn('fresidualvalue').withTitle('设置残值(变更前)')
        .withOption('sDefaultContent', '').renderWith(renderDTFontColorGreenH));
    dtColumns.push(DTColumnBuilder.newColumn('tresidualvalue').withTitle('设置残值(变更后)')
        .withOption('sDefaultContent', '').renderWith(renderesidualvalue));
    dtColumns.push(DTColumnBuilder.newColumn('comp_name').withTitle('使用公司').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('part_name').withTitle('使用部门').withOption(
        'sDefaultContent', ''));
    $scope.dtColumns = dtColumns;

    function flush() {
        $http.post($rootScope.project + "/api/zc/resCFinance/ext/selectByUuid.do",
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

function zccgcwSaveCtl($timeout, $localStorage, notify, $log, $uibModal,
                       $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                       DTColumnBuilder, $compile, $confirm) {
    $scope.ctl = {};
    $scope.item = {};

    function selectnall() {
        $scope.item.tbelongcompstatus = false;
        $scope.item.tbelongpartstatus = false;
        $scope.item.tbuypricestatus = false;
        $scope.item.tnetworthstatus = false;
        $scope.item.tnetworthstatus = false;
        $scope.item.taccumulatedstatus = false;
        $scope.item.tresidualvaluestatus = false;
    }

    function selectall() {
        $scope.item.tbelongcompstatus = true;
        $scope.item.tbelongpartstatus = true;
        $scope.item.tbuypricestatus = true;
        $scope.item.tnetworthstatus = true;
        $scope.item.tnetworthstatus = true;
        $scope.item.taccumulatedstatus = true;
        $scope.item.tresidualvaluestatus = true;
    }

    selectnall();
    $scope.selectall = function () {
        selectall();
    }
    $scope.selectnall = function () {
        selectnall();
    }
    $scope.adminuserOpt = meta.dict.partusers;
    $scope.adminuserSel = "";
    if ($scope.adminuserOpt.length > 0) {
        $scope.adminuserSel = $scope.adminuserOpt[0];
    }
    $scope.belongcompOpt = meta.dict.belongcomp;
    $scope.belongcompSel = "";
    if ($scope.belongcompOpt.length > 0) {
        $scope.belongcompSel = $scope.belongcompOpt[0];
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
        acthtml = acthtml + " <span ng-click=\"filldata('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">填充</span>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    $scope.filldata = function (id, data2) {
        var data = {};
        for (var i = 0; i < $scope.dtOptions.aaData.length; i++) {
            if ($scope.dtOptions.aaData[i].id == id) {
                data = $scope.dtOptions.aaData[i];
                break;
            }
        }
        $scope.belongcompSel = "";
        $scope.item.tbuyprice = "";
        $scope.item.tnetworth = "";
        $scope.item.tresidualvalue = "";
        $scope.item.taccumulateddepreciation = "";
        if (angular.isDefined(data.net_worth)) {
            $scope.item.tnetworth = data.net_worth;
        }
        if (angular.isDefined(data.buy_price)) {
            $scope.item.tbuyprice = data.buy_price;
        }
        if (angular.isDefined(data.residualvalue)) {
            $scope.item.tresidualvalue = data.residualvalue;
        }
        if (angular.isDefined(data.accumulateddepreciation)) {
            $scope.item.taccumulateddepreciation = data.accumulateddepreciation;
        }
        if (angular.isDefined(data.belong_company_id)) {
            for (var i = 0; i < $scope.belongcompOpt.length; i++) {
                if (data.belong_company_id == $scope.belongcompOpt[i].id) {
                    $scope.belongcompSel = $scope.belongcompOpt[i];
                    break;
                }
            }
        }
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
        'sDefaultContent', '').withOption("width", '100').renderWith(renderZCAction));
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
    dtColumns.push(DTColumnBuilder.newColumn('buy_price').withTitle('采购单价')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn("net_worth").withTitle('资产净值')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn("accumulateddepreciation").withTitle('累计折旧')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('residualvalue').withTitle('设置残值')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('lastdepreciationdatestr').withTitle('最近折旧时间')
        .withOption('sDefaultContent', ''));
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
        $scope.item.tbelongcomp = $scope.belongcompSel.id;
        $scope.item.processuserid = $scope.adminuserSel.user_id;
        $scope.item.processusername = $scope.adminuserSel.name;
        $scope.item.items = angular.toJson($scope.dtOptions.aaData);
        if ($scope.item.tbuypricestatus) {
            if (angular.isDefined($scope.item.tbuyprice)) {
            } else {
                notify({
                    message: "请输入采购单价"
                });
                return;
            }
        }
        if ($scope.item.tnetworthstatus) {
            if (angular.isDefined($scope.item.tnetworth)) {
            } else {
                notify({
                    message: "请输入资产净值"
                });
                return;
            }
        }
        if ($scope.item.tresidualvaluestatus) {
            if (angular.isDefined($scope.item.tresidualvalue)) {
            } else {
                notify({
                    message: "请输入残值"
                });
                return;
            }
        }
        if ($scope.item.taccumulatedstatus) {
            if (angular.isDefined($scope.item.taccumulateddepreciation)) {
            } else {
                notify({
                    message: "请输入累计折扣"
                });
                return;
            }
        }
        $confirm({
            text: '是否确定变更?'
        }).then(
            function () {
                $http.post($rootScope.project + "/api/zc/resCFinance/ext/insert.do",
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
        mdata.datarange = "CG";
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

function zccgcwCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $window,
                   $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
    var gdict = {};
    var dicts = "devdc";
    $http
        .post($rootScope.project + "/api/zc/queryDictFast.do", {
            dicts: dicts,
            parts: "Y",
            partusers: "Y",
            comp: "Y",
            belongcomp: "Y",
            zccatused: "Y",
            uid: "zccgcw"
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
            + "','" + full.status + "')\" class=\"btn-white btn btn-xs\">变更明细</button>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns = [
        DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function () {
            return ""
        }),
        DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
            'sDefaultContent', '').renderWith(renderAction),
        DTColumnBuilder.newColumn('busuuid').withTitle('变更单号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('status').withTitle('办理状态').withOption(
            'sDefaultContent', '').renderWith(renderCGStatus),
        DTColumnBuilder.newColumn('processusername').withTitle('处理人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tbelongcom_fullname').withTitle('所属公司').withOption(
            'sDefaultContent', '').renderWith(renderbcomp),
        DTColumnBuilder.newColumn('tbuyprice').withTitle('采购单价').withOption(
            'sDefaultContent', '').renderWith(renderbuyprice),
        DTColumnBuilder.newColumn('tnetworth').withTitle('资产净值').withOption(
            'sDefaultContent', '').renderWith(rendernetworth),
        DTColumnBuilder.newColumn('taccumulateddepreciation').withTitle('累计折旧').withOption(
            'sDefaultContent', '').renderWith(renderaccumulateddepreciation),
        DTColumnBuilder.newColumn('tresidualvalue').withTitle('设置残值').withOption(
            'sDefaultContent', '').renderWith(renderesidualvalue),
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
                template: ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">刷新</button>'
            },
            {
                id: "btn3",
                label: "",
                type: "btn",
                show: true,
                priv: "insert",
                template: ' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">新建</button>'
            }]
    }
    $scope.meta = meta;

    function flush() {
        var ps = {};
        $http
            .post($rootScope.project + "/api/zc/resCFinance/ext/selectList.do",
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

    $scope.detail = function (uuid, status) {
        meta.uuid = uuid;
        meta.status = status;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_cglist.html',
            controller: cgcwlistCtl,
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

    function action(id) {
        var meta = {};
        meta.id = id;
        meta.dict = gdict;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_zccgcwSave.html',
            controller: zccgcwSaveCtl,
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
    flush();
};
app.register.controller('zccgcwCtl', zccgcwCtl);
