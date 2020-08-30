

function zcbfCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
                 $log, notify, $scope, $http, $rootScope, $uibModal, $window, $state) {
    var pbtns = $rootScope.curMemuBtns;
    console.log('11111111', $rootScope);
    var gclassroot = '7';
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
        .withPaginationType('full_numbers')
        .withDisplayLength(25)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 420)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withOption('bStateSave', true).ithOption('bProcessing', true)
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
                extend: 'colvis',
                text: '显示隐藏列',
                fnLabel: function (dt, idx, title) {
                    return (idx + 1) + ': ' + title;
                }
            },
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
    $scope.dtColumns = [];
    $scope.dtColumns = [];
    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
        'select-checkbox checkbox_center').renderWith(function () {
        return ""
    }));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('单据编号').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('status').withTitle('办理状态').withOption(
        'sDefaultContent', '').withOption("width", '30').renderWith(function (data, type, full) {
        if (data == "success") {
            return "完成"
        } else {
            return data;
        }
    }));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('title').withTitle('标题').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('ct').withTitle('报废原因').withOption(
        'sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
        'sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('cnt').withTitle('数量').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('busidate').withTitle('业务日期')
        .withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('processusername').withTitle('制单人')
        .withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('createTime').withTitle('创建时间')
        .withOption('sDefaultContent', ''));
    $scope.query = function () {
        flush();
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

    var meta = {
        tablehide: false,
        toolsbtn: [
            {
                id: "btn",
                label: "",
                type: "btn",
                show: true,
                template: ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">搜索</button>'
            },
            {
                id: "btn2",
                label: "",
                type: "btn",
                show: true,
                priv: "insert",
                template: ' <button ng-click="save(0)" class="btn btn-sm btn-primary" type="submit">报废</button>'
            },
            {
                id: "btn3",
                label: "",
                type: "btn",
                show: false,
                priv: "update",
                template: ' <button ng-click="save(1)" class="btn btn-sm btn-primary" type="submit">更新</button>'
            },
            {
                id: "btn4",
                label: "",
                type: "btn",
                show: true,
                priv: "detail",
                template: ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详情</button>'
            },
            {
                id: "btn5",
                label: "",
                type: "btn",
                show: false,
                priv: "remove",
                template: ' <button ng-click="del()" class="btn btn-sm btn-primary" type="submit">删除</button>'
            },
            {
                id: "btn6",
                label: "",
                type: "btn",
                show: false,
                priv: "exportfile",
                template: ' <button ng-click="filedown()" class="btn btn-sm btn-primary" type="submit">全部导出(Excel)</button>'
            }],
        tools: [
            {
                id: "input",
                show: true,
                label: "内容",
                placeholder: "输入型号、编号、序列号",
                type: "input",
                ct: ""
            }
        ]
    };
    $scope.meta = meta;
    privNormalCompute($scope.meta.toolsbtn, pbtns);

    function flush() {
        var ps = {};
        $http.post($rootScope.project + "/api/zc/resScrape/ext/selectList.do", ps)
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

    function callback(json) {
        console.log(json)
    }

    function getSelectRows() {
        var data = $scope.dtInstance.DataTable.rows({
            selected: true
        })[0];
        if (data.length == 0) {
            notify({
                message: "请至少选择一项"
            });
            return;
        } else if (data.length > 1000) {
            notify({
                message: "不允许超过1000个"
            });
            return;
        } else {
            var res = [];
            var d = $scope.dtInstance.DataTable.context[0].json.data;
            for (var i = 0; i < data.length; i++) {
                res.push(d[data[i]].id)
            }
            return angular.toJson(res);
        }
    }

    $scope.del = function () {
        var selrows = getSelectRows();
        if (angular.isDefined(selrows)) {
            $confirm({
                text: '是否删除?'
            }).then(
                function () {
                    $http.post(
                        $rootScope.project
                        + "/api/base/res/deleteByIds.do", {
                            ids: selrows
                        }).success(function (res) {
                        if (res.success) {
                            flush();
                        } else {
                            notify({
                                message: res.message
                            });
                        }
                    });
                });
        }
    }
    $scope.detail = function () {
        var id;
        var selrow = getSelectRow();
        if (angular.isDefined(selrow)) {
            id = selrow.id;
        } else {
            return;
        }
        var meta = {};
        meta.type = "dtl";
        meta.id = id;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_zcbf.html',
            controller: modalzcbfCtl,
            size: 'blg',
            resolve: {
                meta: function () {
                    return meta
                }
            }
        });
        modalInstance.result.then(function (result) {
        }, function (reason) {
        });
    }
    // //////////////////////////save/////////////////////
    $scope.save = function (type) {
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_zcbf.html',
            controller: modalzcbfCtl,
            size: 'blg',
            resolve: {
                meta: function () {
                    return ""
                }
            }
        });
        modalInstance.result.then(function (result) {
            if (result == "OK") {
                flush();
            }
        }, function (reason) {
            $log.log("reason", reason)
        });
    }
    flush();
};
app.register.controller('zcbfCtl', zcbfCtl);