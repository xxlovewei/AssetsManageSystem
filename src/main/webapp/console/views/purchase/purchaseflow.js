function resPurchaseSaveCtl($timeout, $localStorage, notify, $log, $uibModal,
                              $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                              DTColumnBuilder, $compile) {

    //type:detail,sure,add
    $rootScope.flowpagetype = meta.flowpagetype;
    $rootScope.flowbusid = meta.busid;
    $rootScope.flowtaskid = meta.taskid;
    $scope.item={};
    $scope.planOpt=[{id:"inside",name:"计划内"},{id:"outside",name:"计划外"}];
    $scope.planSel=$scope.planOpt[0];
    //save,select,
    if(angular.isUndefined(meta.pagetype)){
        meta.pagetype="select";
    }
    $scope.pagetype=meta.pagetype;

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    function close() {
        $uibModalInstance.close('OK');
    }
    $scope.windowclose = function () {
        $uibModalInstance.close('OK');
    }
    $scope.sure = function () {
        $scope.plan=$scope.planSel.id;
        $http.post($rootScope.project + "/api/zc/resPurchase/ext/insertOrUpdate.do",
            $scope.item).success(function (res) {
            if (res.success) {
                $uibModalInstance.close('OK');
            }
            notify({
                message: res.message
            });
        })
    }

}

function resPurchaseCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
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

    function renderPlan(data, type, full) {
        if (data == "inside") {
            return "计划内"
        } else if (data == "outside") {
            return "计划外"
        }  else {
            return data;
        }
    }

    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns = [
        DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function () {
            return ""
        }),
        DTColumnBuilder.newColumn('id').withTitle('单据详情').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('busid').withTitle('单据编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('status').withTitle('办理状态').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('plan').withTitle('计划情况').withOption(
            'sDefaultContent', '').withOption("width", '30').renderWith(renderPlan),
        DTColumnBuilder.newColumn('zcname').withTitle('资产名称').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('zcmodel').withTitle('资产型号').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('unit').withTitle('单位').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('cnt').withTitle('数量').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('estprice').withTitle('评估单价').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('contractamount').withTitle('合同总价').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('purpose').withTitle('用途').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', '').withOption("width", '30'),
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
                placeholder: "输入内容",
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
                show: true,
                priv: "insert",
                template: ' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">采购</button>'
            },

            {
                id: "btn4",
                label: "",
                type: "btn",
                show: true,
                priv: "remove",
                template: ' <button ng-click="approval()" class="btn btn-sm btn-primary" type="submit">送审</button>'
            },
            {
                id: "btn5",
                label: "",
                type: "btn",
                show: true,
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
            .post($rootScope.project + "/api/zc/resPurchase/ext/selectList.do",
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

    function action( id, busid, status) {
        var meta = {};
        meta.id = id;
        meta.status = status;
        meta.busid = busid;
        meta.pagetype="insert";
        meta.flowpagetype = "lookup";
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/purchase/modal_purchaseSave.html',
            controller: resPurchaseSaveCtl,
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
        // var selrow = getSelectRow();
        // if (angular.isDefined(selrow) && angular.isDefined(selrow.id)) {
        //     $confirm({
        //         text: '确定要将所选单据取消?'
        //     }).then(
        //         function () {
        //             $http.post(
        //                 $rootScope.project
        //                 + "/api/zc/resAllocate/ext/cancelAllocationById.do", {
        //                     id: selrow.id
        //                 }).success(function (res) {
        //                 if (res.success) {
        //                     flush();
        //                 } else {
        //                     notify({
        //                         message: res.message
        //                     });
        //                 }
        //             });
        //         });
        // }
    }
    $scope.finish = function () {
        // var selrow = getSelectRow();
        // if (angular.isDefined(selrow) && angular.isDefined(selrow.id)) {
        //     $confirm({
        //         text: '确定要将所选单据状态修改为已完成?'
        //     }).then(
        //         function () {
        //             $http.post(
        //                 $rootScope.project
        //                 + "/api/zc/resAllocate/ext/sureAllocationById.do", {
        //                     id: selrow.id
        //                 }).success(function (res) {
        //                 if (res.success) {
        //                     flush();
        //                 } else {
        //                     notify({
        //                         message: res.message
        //                     });
        //                 }
        //             });
        //         });
        // }
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
            action('detail', selrow.id, selrow.uuid, selrow.status);
        } else {
            return;
        }
    }
    $scope.add = function () {
        action();
    }
    flush();

    $scope.approval = function () {
        var item = getSelectRow();
        if (angular.isDefined(item)) {
            console.log(item);
            item.ptype = "MYZY";
            item.busuuid = item.uuid;
            if (item.status != "apply") {
                notify({
                    message: "该状态不允许送审"
                });
                return;
            }
            var modalInstance = $uibModal.open({
                backdrop: true,
                templateUrl: 'views/cmdb/flow/modal_chosenFlowTreeView.html',
                controller: chosenFlowTreeCtl,
                size: 'blg',
                resolve: {
                    meta: function () {
                        return item;
                    }
                }
            });
            modalInstance.result.then(function (result) {
                if (result == "OK") {
                    flush();
                }
            }, function (reason) {
            });
        }
    }
};
app.register.controller('flowapprovalCommonCtl', flowapprovalCommonCtl);
app.register.controller('flowsuggestCommonCtl', flowsuggestCommonCtl);
app.register.controller('resPurchaseCtl', resPurchaseCtl);
