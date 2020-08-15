function myProcessTodoCtl($window, $state, DTOptionsBuilder, DTColumnBuilder, $compile,
                          $confirm, $log, notify, $scope, $http, $rootScope, $uibModal, $window) {
    $scope.meta = {
        tablehide: false,
        tools: [
            {
                id: "1",
                label: "查询",
                type: "btn",
                show: true,
                priv: 'select',
                template: ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">查询</button>'
            },
            {
                id: "1",
                priv: "act1",
                label: "处理",
                type: "btn",
                template: ' <button ng-click="oper()" class="btn btn-sm btn-primary" type="submit">处理</button>',
                show: true,
            }

            // , {
            //     id: "1",
            //     priv: "act1",
            //     label: "demo",
            //     type: "btn",
            //     template: ' <button ng-click="demo()" class="btn btn-sm btn-primary" type="submit">demo</button>',
            //     show: true,
            // }
        ]
    }
    $scope.demo = function () {
        // let url = $state.href('app.user');
        //window.open(url, '_blank')
        var url = $state.href('fullpage.flowdetail', {basicType: 8});
        var win = $window.open(url, "_bank", "menubar:no,status:no,location:no,menubar:no")
        // win.onunload = function() {
        //     console.log('@@@@@@@@@@@@@');
        //     console.log(a)
        // };
    }
    privNormalCompute($scope.meta.tools, $rootScope.curMemuBtns);
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withPaginationType('full_numbers').withDisplayLength(50)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 600)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withOption('bStateSave', true).withOption('bProcessing', false)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', false).withOption('createdRow', function (row) {
            // Recompiling so we can bind Angular,directive to the
            $compile(angular.element(row).contents())($scope);
        }).withOption(
            'headerCallback',
            function (header) {
                if ((!angular.isDefined($scope.headerCompiled))
                    || $scope.headerCompiled) {
                    // Use this headerCompiled field to only compile
                    // header once
                    $scope.headerCompiled = true;
                    $compile(angular.element(header).contents())
                    ($scope);
                }
            }).withOption("select", {
            style: 'multi',
            selector: 'td:first-child'
        });
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
        DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function () {
            return ""
        }),
        DTColumnBuilder.newColumn('id').withTitle('任务编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('rootProcessInstanceId').withTitle('流程编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('businessId').withTitle('单据编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('subject').withTitle('标题').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('state').withTitle('任务状态').withOption(
            'sDefaultContent', '').renderWith(renderUfloTaskStatus),
        DTColumnBuilder.newColumn('nodeName').withTitle('任务名称').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('description').withTitle('任务描述')
            .withOption('sDefaultContent', ''),
        DTColumnBuilder.newColumn('createDate').withTitle('发起时间')
            .withOption('sDefaultContent', '')]
    $scope.query = function () {
        flush();
    }

    function flush() {
        var ps = {}
        ps.search = "";
        ps.pageSize = 1000;
        ps.pageIndex = 1;
        $http.post($rootScope.project + "/api/zc/flow/myProcessTodo.do",
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

    flush();

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

    function getSelectRows() {
        var data = $scope.dtInstance.DataTable.rows({
            selected: true
        })[0];
        if (data.length == 0) {
            notify({
                message: "请至少选择一项"
            });
            return;
        } else if (data.length > 100) {
            notify({
                message: "不允许超过500个"
            });
            return;
        } else {
            var res = [];
            for (var i = 0; i < data.length; i++) {
                res.push($scope.dtOptions.aaData[data[i]].id)
            }
            return angular.toJson(res);
        }
    }

    var win = null;
    $scope.oper = function () {
        var item = getSelectRow();
        if (angular.isDefined(item) && angular.isDefined(item.businessId)) {
            $http
                .post(
                    $rootScope.project
                    + "/api/flow/sysProcessDataExt/selectByBusinessId.do",
                    {
                        businessid: item.businessId
                    })
                .success(
                    function (res) {
                        if (res.success) {
                            var url = "#/fullpage/fullpage_flowdetail?taskid=" + item.id + "&id=" + res.data.id + "&pagetype=approval";
                            win = $window.open(url, "_bank", "fullscreen:yes,menubar:no,status:no,location:no,menubar:no")
                            var loop = setInterval(function () {//监听子页面关闭事件,轮询时间1000毫秒
                                console.log(win);
                                if (win.closed) {
                                    clearInterval(loop);
                                    if (angular.isDefined(win.opener) && win.opener == "ok") {
                                        flush()
                                        console.log('ffffffllll')
                                    }
                                }
                            }, 1000);
                            //
                        } else {
                            notify({
                                message: res.message
                            });
                        }
                    })
        } else {
        }
    }
};
app.register.controller('myProcessTodoCtl', myProcessTodoCtl);