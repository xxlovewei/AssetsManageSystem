function cmdbdevsearchCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
                          $confirm, $log, notify, $scope, $http, $rootScope, $uibModal, $window) {
    // 分类
    $scope.URL = $rootScope.project + "/api/base/res/queryPageResAll.do";
    $scope.dtOptions = DTOptionsBuilder.newOptions()
        .withOption('ajax', {
            url: $scope.URL,
            type: 'POST',
            data: {classroot: "-1", start: 0, category: 3}
        })
        .withDataProp('data').withDataProp('data').withDOM('frtlip').withPaginationType('full_numbers')
        .withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", false).withOption('scrollY', 420)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withOption('bStateSave', false).withOption('bProcessing', true)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', true).withOption('createdRow', function (row) {
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
                text: 'Excel',
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
                text: '打印',
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
    $scope.selectCheckBoxAll = function (selected) {
        if (selected) {
            $scope.dtInstance.DataTable.rows().select();
        } else {
            $scope.dtInstance.DataTable.rows().deselect();
        }
    }
    $scope.dtColumns = [];
    $scope.dtColumns = zcBaseColsCreate(DTColumnBuilder, 'withselect');
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
                id: "btn5",
                label: "",
                type: "btn",
                show: true,
                priv: "detail",
                template: ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详情</button>'
            },
            {
                id: "btn2",
                label: "",
                type: "btn",
                show: true,
                priv: "export",
                template: ' <button ng-click="exportfile()" class="btn btn-sm btn-primary" type="submit">导出</button>'
            }],
        tools: [{
            id: "select",
            label: "区域",
            type: "select",
            width: "180",
            disablesearch: true,
            show: true,
            dataOpt: [],
            dataSel: ""
        }, {
            id: "select",
            label: "环境",
            type: "select",
            disablesearch: true,
            show: true,
            dataOpt: [],
            dataSel: ""
        }, {
            id: "select",
            label: "维保",
            type: "select",
            disablesearch: true,
            show: true,
            dataOpt: [],
            dataSel: ""
        }, {
            id: "select",
            label: "状态",
            type: "select",
            disablesearch: true,
            show: true,
            dataOpt: [],
            dataSel: ""
        }, {
            id: "input",
            show: true,
            label: "内容",
            placeholder: "输入型号、编号、序列号",
            type: "input",
            ct: ""
        }]
    };
    $scope.meta = meta;

    function flush() {
        var ps = {}
        var time = new Date().getTime();
        ps.loc = $scope.meta.tools[0].dataSel.dict_item_id;
        ps.env = $scope.meta.tools[1].dataSel.dict_item_id;
        ps.wb = $scope.meta.tools[2].dataSel.dict_item_id;
        ps.recycle = $scope.meta.tools[3].dataSel.dict_item_id;
        ps.search = $scope.meta.tools[4].ct;
        ps.time = time;
        ps.category = 3;
        $scope.dtOptions.ajax.data = ps
    }

    $scope.query = function () {
        flush();
    }
    $scope.exportfile = function () {
        var ps = {}
        ps.loc = $scope.meta.tools[0].dataSel.dict_item_id;
        ps.env = $scope.meta.tools[1].dataSel.dict_item_id;
        ps.wb = $scope.meta.tools[2].dataSel.dict_item_id;
        ps.recycle = $scope.meta.tools[3].dataSel.dict_item_id;
        ps.search = $scope.meta.tools[4].ct;
        $window.open($rootScope.project + "/api/base/res/exportAllRes.do?category=3&loc="
            + ps.loc + "&env=" + ps.env + "&wb=" + ps.wb + "&recycle="
            + ps.recycle + "&search=" + ps.search);
    }
    var gdicts = {};
    $http
        .post(
            $rootScope.project + "/api/zc/queryDictFast.do",
            {
                dicts: "devbrand,devrisk,devenv,devrecycle,devwb,devdc,devservertype,devrack",
                parts: "Y",
                partusers: "Y",
                uid: "devsearch"
            }).success(function (res) {
        if (res.success) {
            gdicts = res.data;
            // 填充行数据
            var tenv = [];
            angular.copy(gdicts.devenv, tenv);
            var twb = [];
            angular.copy(gdicts.devwb, twb);
            var tloc = [];
            angular.copy(gdicts.devdc, tloc);
            var trecycle = [];
            angular.copy(gdicts.devrecycle, trecycle);
            var parts = [];
            angular.copy(gdicts.parts, parts);
            var partusers = [];
            angular.copy(gdicts.partusers, partusers);
            tloc.unshift({
                dict_item_id: "all",
                name: "全部"
            });
            $scope.meta.tools[0].dataOpt = tloc;
            $scope.meta.tools[0].dataSel = tloc[0];
            tenv.unshift({
                dict_item_id: "all",
                name: "全部"
            });
            $scope.meta.tools[1].dataOpt = tenv;
            $scope.meta.tools[1].dataSel = tenv[0];
            twb.unshift({
                dict_item_id: "all",
                name: "全部"
            });
            $scope.meta.tools[2].dataOpt = twb;
            $scope.meta.tools[2].dataSel = twb[0];
            trecycle.unshift({
                dict_item_id: "all",
                name: "全部"
            });
            $scope.meta.tools[3].dataOpt = trecycle;
            $scope.meta.tools[3].dataSel = trecycle[0];
        } else {
            notify({
                message: res.message
            });
        }
    })
    // $scope.detail = function () {
    //     var id = "";
    //     var selrow = getSelectRow();
    //     if (angular.isDefined(selrow)) {
    //         id = selrow.id;
    //     } else {
    //         return;
    //     }
    //     var ps = {};
    //     ps.id = id;
    //     var modalInstance = $uibModal.open({
    //         backdrop: true,
    //         templateUrl: 'views/cmdb/modal_dtl.html',
    //         controller: modalcmdbdtlCtl,
    //         size: 'blg',
    //         resolve: {
    //             meta: function () {
    //                 return ps;
    //             }
    //         }
    //     });
    //     modalInstance.result.then(function (result) {
    //         if (result == "OK") {
    //         }
    //     }, function (reason) {
    //         $log.log("reason", reason)
    //     });
    // }
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
            var d = $scope.dtInstance.DataTable.context[0].json.data;
            return d[data[0]]
        }
    }

    $scope.detail = function () {
        var id = "";
        var selrow = getSelectRow();
        if (angular.isDefined(selrow)) {
            id = selrow.id;
        } else {
            return;
        }
        var ps = {};
        ps.id = id;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_dtl.html',
            controller: modalcmdbdtlCtl,
            size: 'blg',
            resolve: {
                meta: function () {
                    return ps;
                }
            }
        });
        modalInstance.result.then(function (result) {
            if (result == "OK") {
            }
        }, function (reason) {
            $log.log("reason", reason)
        });
    }
    flush();
};
app.register.controller('cmdbdevsearchCtl', cmdbdevsearchCtl);