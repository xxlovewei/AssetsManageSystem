

function processConfCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
                           $confirm, $log, notify, $scope, $http, $rootScope, $uibModal,
                           $stateParams) {
    $scope.crud = {
        "update" : false,
        "insert" : false,
        "select" : false,
        "remove" : false,
        "priv" : false,
        "cpwd" : false
    };



    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 600)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withFixedColumns({
            leftColumns : 0,
            rightColumns : 0
        }).withOption('bStateSave', true).withOption('bProcessing', false)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', false).withOption('createdRow', function(row) {
            $compile(angular.element(row).contents())($scope);
        }).withOption(
            'headerCallback',
            function(header) {
                if ((!angular.isDefined($scope.headerCompiled))
                    || $scope.headerCompiled) {
                    $scope.headerCompiled = true;
                    $compile(angular.element(header).contents())
                    ($scope);
                }
            }).withOption("select", {
            style : 'multi',
            selector : 'td:first-child'
        });

    $scope.dtInstance = {}


    $scope.selectCheckBoxAll = function(selected) {
        if (selected) {
            $scope.dtInstance.DataTable.rows().select();
        } else {
            $scope.dtInstance.DataTable.rows().deselect();
        }
    }

    function renderType(data, type, full) {
        if (data == "system") {
            return "系统";
        } else if (data == "user") {
            return "用户";
        } else {
            return data;
        }
    }
    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';

    $scope.dtColumns = [
        DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function() {
            return ""
        }),
        DTColumnBuilder.newColumn('id').withTitle('流程ID').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('name').withTitle('流程名称').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('code').withTitle('编码').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('type').withTitle('类型').withOption(
            'sDefaultContent', '').renderWith(renderType),
        DTColumnBuilder.newColumn('processdefid').withTitle('配置').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', '')
       ]

    function flush() {
        $http.post($rootScope.project + "/api/flow/sysProcessSetting/selectList.do", {})
            .success(function(res) {
                if (res.success) {
                    $scope.dtOptions.aaData = res.data;
                } else {
                    notify({
                        message : res.message
                    });
                }
            })
    }


    function getSelectRow() {
        var data = $scope.dtInstance.DataTable.rows({
            selected : true
        })[0];
        if (data.length == 0) {
            notify({
                message : "请至少选择一项"
            });
            return;
        } else if (data.length > 1) {
            notify({
                message : "请最多选择一项"
            });
            return;
        } else {
            return $scope.dtOptions.aaData[data[0]];
        }
    }
    $scope.remove = function() {
        var item = getSelectRow();
        if (angular.isDefined(item) && angular.isDefined(item.id)) {
            $confirm({
                text : '是否删除选中列?'
            }).then(
                function() {
                    $http.post(
                        $rootScope.project
                        + "/api/flow/sysProcessSetting/deleteById.do", {
                           id:item.id
                        }).success(function(res) {
                        if (res.success) {
                            flush();
                        }
                        notify({
                            message : res.message
                        });
                    });
                });

        }


    }


    $scope.query = function() {
        flush();
    }
    function save(id){

        var ps={};
        ps.id=id;
        var modalInstance = $uibModal.open({
            backdrop : true,
            templateUrl : 'views/flow/modal_flowlist.html',
            controller : modalFlowListSelCtl,
            size : 'lg',
            resolve : {

            }
        });

        modalInstance.result.then(function(result) {
            if (result == "OK") {
                flush();
            }
        }, function(reason) {

            $log.log("reason", reason)
        });
    }
    $scope.add = function() {
        save();
    }
    $scope.edit = function() {
        save(id);
    }

    flush();
};

app.register.controller('processConfCtl', processConfCtl);