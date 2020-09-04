
function zcallocationCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
                         $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
    var gdict = {};
    var dicts = "devdc"
    $http
        .post($rootScope.project + "/api/zc/queryDictFast.do",
            {
                dicts: dicts,
                parts: "Y",
                partusers: "Y",
                comp: "Y",
                belongcomp: "Y",
                comppart: "Y",
                uid: "allocation"
            }).success(function (res) {
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

    function renderStatus(data, type, full) {
        if (data == "doing") {
            return "未完成"
        } else if (data == "finish") {
            return "已完成"
        } else if (data == "cancel") {
            return "已取消"
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
        DTColumnBuilder.newColumn('uuid').withTitle('单据编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('status').withTitle('办理状态').withOption(
            'sDefaultContent', '').renderWith(renderStatus),
        DTColumnBuilder.newColumn('allocateusername').withTitle('调入管理员').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('fcompname').withTitle('调出公司').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tousedcompname').withTitle('调入公司').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tolocname').withTitle('调入区域').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tolocdtl').withTitle('位置').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('busdate').withTitle('调出日期').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('acttime').withTitle('调入日期').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('createTime').withTitle('创建时间').withOption(
            'sDefaultContent', '')]
    $scope.query = function () {
        flush();
    }
    var meta = {
        tablehide: false,
        tools: [
            {
                id: "input",
                label: "内容",
                placeholder: "输入型号、编号、序列号",
                type: "input",
                show: true,
                ct: ""
            },
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
                show: false,
                priv: "insert",
                template: ' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">申请调拨</button>'
            },
            {
                id: "btn4",
                label: "",
                type: "btn",
                show: false,
                priv: "act1",
                template: ' <button ng-click="finish()" class="btn btn-sm btn-primary" type="submit">确认调拨</button>'
            },
            {
                id: "btn4",
                label: "",
                type: "btn",
                show: false,
                priv: "remove",
                template: ' <button ng-click="cancel()" class="btn btn-sm btn-primary" type="submit">取消调拨</button>'
            },
            {
                id: "btn5",
                label: "",
                type: "btn",
                show: false,
                priv: "detail",
                template: ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详细</button>'
            }]
    }
    $scope.meta = meta;
    privNormalCompute($scope.meta.tools, $rootScope.curMemuBtns);

    function flush() {
        var ps = {};
        ps.search = $scope.meta.tools[0].ct;
        $http
            .post($rootScope.project + "/api/zc/resAllocate/ext/selectList.do",
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

    function action(actiontype, id) {
        var meta = {};
        meta.actiontype = actiontype;
        meta.id = id;
        meta.gdict = gdict;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_zcallocation.html',
            controller: modalzcallocationCtl,
            size: 'lg',
            resolve: {
                meta: function () {
                    return meta;
                }
            }
        });
        modalInstance.result.then(function (result) {
            flush();
        }, function (reason) {
        });
    }

    $scope.cancel = function () {
        var selrow = getSelectRow();
        if (angular.isDefined(selrow) && angular.isDefined(selrow.id)) {
            $confirm({
                text: '确定要将所选单据取消?'
            }).then(
                function () {
                    $http.post(
                        $rootScope.project
                        + "/api/zc/resAllocate/ext/cancelAllocationById.do", {
                            id: selrow.id
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
    $scope.finish = function () {
        var selrow = getSelectRow();
        if (angular.isDefined(selrow) && angular.isDefined(selrow.id)) {
            $confirm({
                text: '确定要将所选单据状态修改为已完成?'
            }).then(
                function () {
                    $http.post(
                        $rootScope.project
                        + "/api/zc/resAllocate/ext/sureAllocationById.do", {
                            id: selrow.id
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
    //
    // $scope.modify = function() {
    //     var selrow = getSelectRow();
    //
    //     if (angular.isDefined(selrow)&&angular.isDefined(selrow.id)) {
    //         if(selrow.fstatus=="finish"){
    //             notify({
    //                 message : "当前状态不允许修改"
    //             });
    //             return
    //         }
    //         action('modify',selrow.id);
    //     } else {
    //         return;
    //     }
    //
    //
    // }
    $scope.detail = function () {
        var selrow = getSelectRow();
        if (angular.isDefined(selrow) && angular.isDefined(selrow.id)) {
            action('detail', selrow.id);
        } else {
            return;
        }
    }
    $scope.add = function () {
        action('add');
    }
    flush();
};
app.register.controller('zcallocationCtl', zcallocationCtl);
