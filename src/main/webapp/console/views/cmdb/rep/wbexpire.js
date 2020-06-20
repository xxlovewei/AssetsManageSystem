function wbexpireCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
                     $confirm, $log, notify, $scope, $http, $rootScope, $uibModal, $window, $stateParams) {

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

    $scope.dtColumns = [];
    $scope.dtColumns = zcBaseColsCreate(DTColumnBuilder, 'withoutselect');


    $scope.query = function () {
        flush();
    }

    var meta = {
        tablehide: false,
        toolsbtn: [],
        tools: [{
            id: "select",
            label: "时间",
            type: "select",
            disablesearch: true,
            dataOpt: [],
            dataSel: "",
            show: true,
        },
            {
                id: "btn",
                show: true,
                label: "",
                type: "btn",
                template: ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">查询</button>'
            }]
    }

    $scope.meta = meta;
    $scope.meta.tools[0].dataOpt = [{id: "0", name: "今日"}, {id: "15", name: "15天"}, {id: "30", name: "30天"}, {
        id: "90",
        name: "90天"
    }, {id: "365", name: "365天"}]
    $scope.meta.tools[0].dataSel = $scope.meta.tools[0].dataOpt[0];

    function flush() {
        var ps = {}
        ps.day = $scope.meta.tools[0].dataSel.id;
        $http.post($rootScope.project + "/api/zc/report/queryWbExpredReport.do",
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
    $scope.btn_query = function () {
        flush();
    }

};

app.register.controller('wbexpireCtl', wbexpireCtl);