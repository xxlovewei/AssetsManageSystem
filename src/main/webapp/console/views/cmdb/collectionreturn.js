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

function zclylistCtl($confirm, $timeout, $localStorage, notify, $log, $uibModal,
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

    function renderZCAction(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";
        acthtml = acthtml + " <button ng-click=\"delitem('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">删除</button>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    function renderZcReturn(data, type, full) {
        if (data == "1") {
            return "已退库"
        } else if (data == "0") {
            return "未退库"
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
        DTColumnBuilder.newColumn('busdatestr').withTitle('领用时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('returndatestr').withTitle('预计退库时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('rreturndatestr').withTitle('实际退库时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('isreturn').withTitle('是否退库').withOption(
            'sDefaultContent', '').renderWith(renderZcReturn),
        DTColumnBuilder.newColumn('returnuuid').withTitle('退库编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tcompfullname').withTitle($rootScope.USEDCOMP_A).withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tpartfullame').withTitle($rootScope.USEDPART_A).withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tusedusername').withTitle('使用人(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tlocstr').withTitle('区域(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tlocdtl').withTitle('位置(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('fcompfullname').withTitle($rootScope.USEDCOMP_B).withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('fpartfullame').withTitle($rootScope.USEDPART_B).withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('fusedusername').withTitle('使用人(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('flocstr').withTitle('区域(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('flocdtl').withTitle('位置(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('create_time').withTitle('创建时间').withOption(
            'sDefaultContent', '')]

    function flush() {
        $http.post($rootScope.project + "/api/zc/resCollectionreturn/ext/selectByUuid.do",
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

function zctklistCtl($confirm, $timeout, $localStorage, notify, $log, $uibModal,
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

    function renderZCAction(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";
        acthtml = acthtml + " <button ng-click=\"delitem('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">删除</button>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    function renderZcReturn(data, type, full) {
        if (data == "1") {
            return "已退库"
        } else if (data == "0") {
            return "未退库"
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
        DTColumnBuilder.newColumn('rreturndatestr').withTitle('实际退库时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tcompfullname').withTitle($rootScope.USEDCOMP_A).withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tpartfullame').withTitle($rootScope.USEDPART_A).withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tusedusername').withTitle('使用人(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tlocstr').withTitle('区域(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tlocdtl').withTitle('位置(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('fcompfullname').withTitle($rootScope.USEDCOMP_B).withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('fpartfullame').withTitle($rootScope.USEDPART_B).withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('fusedusername').withTitle('使用人(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('flocstr').withTitle('区域(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('flocdtl').withTitle('位置(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColoBluerH),
        DTColumnBuilder.newColumn('create_time').withTitle('创建时间').withOption(
            'sDefaultContent', '')]

    function flush() {
        $http.post($rootScope.project + "/api/zc/resCollectionreturn/ext/selectByUuid.do",
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
function collectionreturnCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $window,
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
            uid: "zclydata"
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

    function renderType(data, type, full) {
        if (data == "ly") {
            return "领用"
        } else if (data == "tk") {
            return "退库"
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
        DTColumnBuilder.newColumn('bustype').withTitle('单据类型').withOption(
            'sDefaultContent', '').renderWith(renderType),
        DTColumnBuilder.newColumn('status').withTitle('办理状态').withOption(
            'sDefaultContent', '').renderWith(renderCGStatus),
        DTColumnBuilder.newColumn('pinst').withTitle('流程详情').withOption(
            'sDefaultContent', '').renderWith(renderprocess),
        DTColumnBuilder.newColumn('crusername').withTitle('领用人/退库人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('busdatestr').withTitle('领用时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('returndatestr').withTitle('预计退库时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('rreturndatestr').withTitle('实际退库时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tcompfullname').withTitle($rootScope.USEDCOMP_LYTK).withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tpartname').withTitle($rootScope.USEDPART_LYTK).withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tlocstr').withTitle('领用/退库后区域').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tlocdtl').withTitle('领用/退库后位置').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('processusername').withTitle('领用处理人/退库处理人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
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
                template: ' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">领用</button>'
            },
            {
                id: "btn3",
                label: "",
                type: "btn",
                show: true,
                priv: "insert",
                template: ' <button ng-click="tk()" class="btn btn-sm btn-primary" type="submit">退库</button>'
            }]
    }
    $scope.meta = meta;

    function flush() {
        var ps = {};
        $http
            .post($rootScope.project + "/api/zc/resCollectionreturn/ext/selectList.do",
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
        meta.status = status;
        if (type == "ly") {
            var modalInstance = $uibModal.open({
                backdrop: true,
                templateUrl: 'views/cmdb/modal_lytklist.html',
                controller: zclylistCtl,
                size: 'blg',
                resolve: {
                    meta: function () {
                        return meta;
                    }
                }
            });
        } else if (type == "tk") {
            var modalInstance = $uibModal.open({
                backdrop: true,
                templateUrl: 'views/cmdb/modal_lytklist.html',
                controller: zctklistCtl,
                size: 'blg',
                resolve: {
                    meta: function () {
                        return meta;
                    }
                }
            });
        }
    }

    function action(id) {
        var meta = {};
        meta.id = id;
        meta.dict = gdict;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_collectionSave.html',
            controller: collectionSaveCtl,
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
    $scope.tk = function () {
        var meta = {};
        meta.dict = gdict;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_tkSave.html',
            controller: tkSaveCtl,
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
    flush();
};
app.register.controller('collectionreturnCtl', collectionreturnCtl);
