function importEmployeeCtl($log, $uibModalInstance, notify, $scope, $http,
                           $rootScope, $uibModal, $window, $timeout, meta) {
    $scope.downTpl = function () {
        $window.open($rootScope.project + "/api/hrm/downloadEmployeeImportTpl.do");
    }
    $scope.dzconfig = {
        url: 'fileupload.do',
        maxFilesize: 10000,
        paramName: "file",
        maxThumbnailFilesize: 2,
        // 一个请求上传多个文件
        uploadMultiple: true,
        // 当多文件上传,需要设置parallelUploads>=maxFiles
        parallelUploads: 1,
        maxFiles: 1,
        dictDefaultMessage: "点击上传需要上传的文件",
        acceptedFiles: ".xlsx,.xls",
        // 添加上传取消和删除预览图片的链接，默认不添加
        addRemoveLinks: true,
        // 关闭自动上传功能，默认会true会自动上传
        // 也就是添加一张图片向服务器发送一次请求
        autoProcessQueue: false,
        init: function () {
            $scope.myDropzone = this; // closure
        }
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.ok = function () {
        $scope.okbtnstatus = true;
        var id = getUuid();
        if ($scope.myDropzone.files.length > 0) {
            $scope.myDropzone.options.url = $rootScope.project
                + '/api/file/fileupload.do?uuid=' + id
                + '&bus=file&interval=10000&bus=file';
            $scope.myDropzone.uploadFile($scope.myDropzone.files[0])
        } else {
            notify({
                message: "请选择文件"
            });
            $scope.okbtnstatus = false;
            return;
        }
        $timeout(function () {
            $http.post(
                $rootScope.project
                + "/api/hrm/employeeBatchAdd.do", {
                    id: id
                }).success(function (res) {
                $scope.okbtnstatus = false;
                if (res.success) {
                    $scope.myDropzone.removeAllFiles(true);
                    $uibModalInstance.close('OK');
                } else {
                }
                notify({
                    message: res.message
                });
            })
        }, 3000);
    }
}

function orgEmpSavePartCtl($rootScope, $scope, $timeout, $log) {
    $scope.partOpt = []
    $scope.partSel = []
    $scope.$watch('partSel', function () {
        $log.info('partSel change');
        $rootScope.sys_partSel = $scope.partSel;
    }, true);
    // 获取列表
    $scope.$watch(function () {
        return $rootScope.sys_partOpt;
    }, function () {
        // $log.info("wath sys_partOpt change.", $rootScope.sys_partOpt);
        if (angular.isDefined($rootScope.sys_partOpt)) {
            $scope.partOpt = $rootScope.sys_partOpt;
            if ($scope.partOpt.length > 0) {
                $scope.partSel.push($scope.partOpt[0]);
            }
        }
    }, true);
    $scope.$watch(function () {
        return $rootScope.sys_partSelItem;
    }, function () {
        var parts = $rootScope.sys_partSelItem;
        if (angular.isDefined(parts)) {
            // $log.info("wath sys_partSelItem change.",
            // $scope.partOpt.length,$rootScope.sys_partSelItem, parts.length);
            if (parts.length == 0) {
            } else {
                var partsSel = [];
                $timeout(function () {
                    for (var i = 0; i < parts.length; i++) {
                        for (var j = 0; j < $scope.partOpt.length; j++) {
                            if ($scope.partOpt[j].node_id == parts[i].node_id) {
                                $log.info("match");
                                partsSel.push($scope.partOpt[j]);
                                break;
                            }
                        }
                    }
                    if (partsSel.length > 0) {
                        $scope.partSel = partsSel;
                    }
                }, 300);
            }
        }
    }, true);
}

function orgEmpSaveCtl($timeout, $localStorage, notify, $log, $uibModal,
                       $uibModalInstance, $scope, id, $http, $rootScope, partOpt, $timeout) {
    $scope.data = {};
    $timeout(function () {
        var d = angular.copy(partOpt)
        d.splice(0, 1);
        $rootScope.sys_partOpt = d;
    }, 800);
    $rootScope.sys_partSel;
    if (angular.isDefined(id)) {
        // 加载数据
        $http.post($rootScope.project + "/api/hrm/employeeQueryById.do", {
            empl_id: id
        }).success(function (res) {
            if (res.success) {
                $scope.data = res.data;
                $scope.data.OLD_PARTS = res.data.PARTS;
                $timeout(function () {
                    $rootScope.sys_partSelItem = res.data.PARTS
                }, 810);
            } else {
                notify({
                    message: res.message
                });
            }
        })
    } else {
        $rootScope.sys_partSelItem = [];
    }
    $timeout(function () {
        var modal = document.getElementsByClassName('modal-body');
        for (var i = 0; i < modal.length; i++) {
            var adom = modal[i].getElementsByClassName('chosen-container');
            for (var j = 0; j < adom.length; j++) {
                adom[i].style.width = "100%";
            }
        }
    }, 200);
    $scope.sure = function () {
        // 跨越controller获取数据数据
        if (!angular.isDefined($rootScope.sys_partSel)) {
            notify({
                message: "发生系统错误"
            });
            return;
        }
        if ($rootScope.sys_partSel.length == 0) {
            notify({
                message: "至少选择一个组织"
            });
            return;
        }
        if ($rootScope.sys_partSel.length > 3) {
            notify({
                message: "最多只可选三个组织"
            });
            return;
        }
        // 检查姓名
        if (!angular.isDefined($scope.data.name)) {
            notify({
                message: "请输入姓名"
            });
            return;
        }
        $scope.data.nodes = angular.toJson($rootScope.sys_partSel);
        var cmd = "";
        if (angular.isDefined($scope.data.empl_id)) {
            cmd = "/api/hrm/employeeUpdate.do"
            $scope.data.old_nodes = angular.toJson($scope.data.old_parts)
        } else {
            cmd = "/api/hrm/employeeAdd.do";
        }
        $http.post($rootScope.project + cmd, $scope.data).success(
            function (res) {
                notify({
                    message: res.message
                });
                if (res.success) {
                    $uibModalInstance.close("OK");
                }
            })
    }
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}

function orgEmpAdjustCtl($stateParams, DTOptionsBuilder, DTColumnBuilder,
                         $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
    $scope.data = {
        name: ""
    };
    $scope.partOpt = [];
    $scope.partSel = "";
    $scope.crud = {
        "update": false,
        "insert": false,
        "remove": false
    }
    var pbtns = $rootScope.curMemuBtns;
    privCrudCompute($scope.crud, pbtns);
    $http.post($rootScope.project + "/api/hrm/orgQueryLevelList.do", {})
        .success(function (res) {
            if (res.success) {
                var d = res.data;
                d.splice(0, 0, {
                    "routename": "全部",
                    node_id: "-1",
                    levels: 0
                })
                $scope.partOpt = d;
                $scope.partSel = $scope.partOpt[0];
            } else {
                notify({
                    message: res.message
                });
            }
        })
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType(
        'full_numbers').withDisplayLength(25).withOption("ordering", false)
        .withOption("responsive", false).withOption("searching", false)
        .withOption("paging", false).withOption('bStateSave', true)
        .withOption('bProcessing', true).withOption('bFilter', false)
        .withOption('bInfo', false).withOption('serverSide', false)
        .withOption('bAutoWidth', false).withOption('createdRow', function (row) {
            // Recompiling so we can bind Angular,directive to the
            $compile(angular.element(row).contents())($scope);
        });
    $scope.dtInstance = {}

    function renderAction(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";
        if ($scope.crud.update) {
            acthtml = acthtml + " <button ng-click=\"save('" + full.empl_id
                + "')\" class=\"btn-white btn btn-xs\">更新</button> ";
        }
        if ($scope.crud.remove) {
            acthtml = acthtml + " <button ng-click=\"row_del('" + full.empl_id
                + "')\" class=\"btn-white btn btn-xs\">删除</button>  ";
        }
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    function renderStatus(data, type, full) {
        var res = "无效";
        if (full.is_action == "Y") {
            res = "有效";
        }
        return res;
    }

    $scope.dtColumns = [
        DTColumnBuilder.newColumn('empl_id').withTitle('员工编号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('name').withTitle('姓名').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tel').withTitle('手机号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('route_name').withTitle('所属组织').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('role_id').withTitle('操作').withOption(
            'sDefaultContent', '').renderWith(renderAction)]

    function flush() {
        $scope.data.node_id = $scope.partSel.node_id;
        $http.post($rootScope.project + "/api/hrm/employeeQueryList.do",
            $scope.data).success(function (res) {
            if (res.success) {
                $scope.dtOptions.aaData = res.data;
            } else {
                notify({
                    message: res.message
                });
            }
        })
    }

    $scope.row_detail = function (id) {
    }
    $scope.row_del = function (id) {
        var data = $scope.dtInstance.DataTable.rows({
            selected: true
        })[0];
        $confirm({
            text: '是否删除功能?'
        }).then(function () {
            $http.post($rootScope.project + "/api/hrm/employeeDelete.do", {
                empl_id: id
            }).success(function (res) {
                if (res.success) {
                    flush();
                }
                notify({
                    message: res.message
                });
            })
        });
    }
    $scope.query = function () {
        flush();
    }
    $scope.save = function (id) {
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/org/modal_employee_save.html',
            controller: orgEmpSaveCtl,
            size: 'lg',
            resolve: {
                id: function () {
                    return id;
                },
                partOpt: function () {
                    return $scope.partOpt;
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
    $scope.batchimport = function () {
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/org/modal_importEmployee.html',
            controller: importEmployeeCtl,
            size: 'blg',
            resolve: {
                meta: function () {
                    return ""
                }
            }
        });
        modalInstance.result.then(function (result) {
            flush();
        }, function (reason) {
        });
    }
};
app.register.controller('orgEmpAdjustCtl', orgEmpAdjustCtl);
app.register.controller('orgEmpSavePartCtl', orgEmpSavePartCtl);
