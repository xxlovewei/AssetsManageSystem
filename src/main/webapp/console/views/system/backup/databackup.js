function databackupCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
                       $confirm, $log, notify, $scope, $http, $rootScope, $uibModal, $window) {
    $scope.meta = {
        tablehide: false,
        tools: [
            {
                id: "1",
                label: "刷新",
                type: "btn",
                show: true,
                priv: 'select',
                template: ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">查询</button>'
            },
            {
                id: "1",
                priv: "act1",
                label: "",
                type: "btn",
                template: ' <button ng-click="action()" class="btn btn-sm btn-primary" type="submit">手工调度</button>',
                show: true,
            }]
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

    $scope.dtColumns = [
        DTColumnBuilder.newColumn('dbname').withTitle('数据库').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('result').withTitle('备份结果').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('duration').withTitle('备份时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('filesize').withTitle('文件大小').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('filepath').withTitle('文件路径').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('createTime').withTitle('创建时间')
            .withOption('sDefaultContent', '')]
    $scope.query = function () {
        flush();
    }

    function flush() {
        var ps = {}
        ps.pageSize = "365";
        ps.pageIndex = "1";
        $http.post($rootScope.project + "/api/sysDbbackupRec/selectPage.do",
            ps).success(function (res) {
            $scope.dtOptions.aaData = res.data;
        })
    }

    flush();
    $scope.action = function () {
        var ps = {}
        $http.post($rootScope.project + "/api/databackup.do",
            ps).success(function (res) {
            notify({
                message: res.message
            });
        })
    }
};
app.register.controller('databackupCtl', databackupCtl);