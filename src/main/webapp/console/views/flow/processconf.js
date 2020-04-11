function modalFlowSelectCtl($timeout, $localStorage, notify, $log, $uibModal,
                              $uibModalInstance, $scope, $http, $rootScope, DTOptionsBuilder,
                              DTColumnBuilder, $compile) {
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withDOM('frtlip').withPaginationType('simple').withDisplayLength(
            50).withOption("ordering", false).withOption("responsive",
            false).withOption("searching", false).withOption('scrollY',
            300).withOption('scrollX', true).withOption(
            'bAutoWidth', true).withOption('scrollCollapse', true)
        .withOption('paging', false).withFixedColumns({
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

    function stateChange(iColumn, bVisible) {

    }
    $scope.dtInstance = {}
    $scope.selectCheckBoxAll = function(selected) {
        if (selected) {
            $scope.dtInstance.DataTable.rows().select();

        } else {
            $scope.dtInstance.DataTable.rows().deselect();

        }
    }

    function renderStatus(data, type, full) {
        var res = "";
        if (data == "normal") {
            res = "正常";
        } else if (data == "stop") {
            res = "停用";
        } else {
            res = data;
        }
        return res;
    }
    function renderType(data, type, full) {
        var res = "";
        if (data == "form") {
            res = "表单模式";
        } else if (data == "withoutform") {
            res = "无表单模式";
        } else {
            res = data;
        }
        return res;
    }
    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns = [];
    $scope.dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml)
        .withClass('select-checkbox checkbox_center').renderWith(
            function() {
                return ""
            }));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('id').withTitle('ID')
        .withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('name').withTitle('名称')
        .withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('type').withTitle('类型')
        .withOption('sDefaultContent', '').renderWith(renderType)),
        $scope.dtColumns.push(DTColumnBuilder.newColumn('status').withTitle('状态')
            .withOption('sDefaultContent', '').renderWith(renderStatus)),
        $scope.dtColumns.push(DTColumnBuilder.newColumn('ptplkey')
            .withTitle('流程模版').withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('form').withTitle('表单')
        .withOption('sDefaultContent', ''));
    $scope.dtColumns.push(DTColumnBuilder.newColumn('mark').withTitle('备注')
        .withOption('sDefaultContent', ''));

    $scope.catRootOpt = [];
    $scope.catRootSel = "";
    $scope.item = {};
    var ps = {};
    ps.ids = angular.toJson([ 5 ]);
    $http
        .post($rootScope.project + "/api/ctCategoryRoot/ext/selectList.do",
            ps).success(function(res) {
        if (res.success) {
            $scope.catRootOpt = res.data;
            if ($scope.catRootOpt.length > 0) {
                $scope.catRootSel = $scope.catRootOpt[0];
                flushTree($scope.catRootSel.id)
            }
        } else {
            notify({
                message : res.message
            });
        }
    });
    // 树配置
    $scope.treeConfig = {
        core : {
            multiple : false,
            animation : true,
            error : function(error) {
                $log.error('treeCtrl: error from js tree - '
                    + angular.toJson(error));
            },
            check_callback : true,
            worker : true
        },
        loading : "加载中……",
        ui : {
            theme_name : "classic" // 设置皮肤样式
        },
        rules : {
            type_attr : "rel", // 设置节点类型
            valid_children : "root" // 只有root节点才能作为顶级结点
        },
        callback : {
            onopen : function(node, tree_obj) {
                return true;
            }
        },
        types : {
            "default" : {
                icon : 'glyphicon glyphicon-th'
            },
            root : {
                icon : 'glyphicon glyphicon-home'
            },
            "node" : {
                "icon" : "glyphicon glyphicon-tag"
            },
            "category" : {
                "icon" : "glyphicon glyphicon-equalizer"
            }
        },
        version : 1,
        plugins : [ 'themes', 'types', 'contextmenu', 'changed' ],
        contextmenu : {
            items : {}
        }
    }

    $scope.addNewNode = function() {
        $scope.treeData.push({
            id : (newId++).toString(),
            parent : $scope.newNode.parent,
            text : $scope.newNode.text
        });
    };

    $scope.modelChanges = function(t) {
        return true;
    }



    $scope.curSelNode = "";
    $scope.readyCB = function() {

        $scope.tree = $scope.treeInstance.jstree(true)
        // 展开所有节点
        $scope.tree.open_all();
        // 响应节点变化
        $scope.treeInstance.on("changed.jstree", function(e, data) {

            if (data.action == "select_node") {
                // 加载数据
                var snodes = $scope.tree.get_selected();
                if (snodes.length == 1) {
                    var node = snodes[0];
                    $scope.curSelNode = node;
                    flush();
                }
            }
        });
    }
    $scope.cancel = function() {
        $uibModalInstance.dismiss('cancel');
    };
    function flushTree(id) {
        $http
            .post(
                $rootScope.project
                + "/api/ctCategroy/queryCategoryTreeList.do", {
                    root : id
                }).success(function(res) {
            if (res.success) {
                $scope.ignoreChanges = true;
                $scope.treeData = angular.copy(res.data);
                $scope.treeConfig.version++;
            } else {
                notify({
                    message : res.message
                });
            }
        });

    }
    $scope.query = function() {
        flushTree($scope.catRootSel.id);
    }

    function flush() {
        $http.post(
            $rootScope.project
            + "/api/flow/sysProcessDef/ext/selectList.do", {
                owner : $scope.curSelNode
            }).success(function(res) {
            if (res.success) {
                $scope.dtOptions.aaData = res.data;
            } else {
                notify({
                    message : res.message
                });
            }
        });

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

    $scope.sure = function() {
        var item=getSelectRow();
        if(angular.isDefined(item) && angular.isDefined(item.id)){
            $uibModalInstance.close(item);
        }
    };




}


function modalFlowConfSaveCtl($timeout, $localStorage, notify, $log, $uibModal,meta,
                             $uibModalInstance, $scope, $http, $rootScope, DTOptionsBuilder,
                             DTColumnBuilder, $compile) {

    $scope.typeOpt=[{id:"system",name:"系统"},{id:"user",name:"用户"}];
    $scope.typeSel= $scope.typeOpt[0];
    $scope.cancel = function() {
        $uibModalInstance.dismiss('cancel');
    };

    $scope.data={};
    if(angular.isDefined(meta.id)){

        $http.post($rootScope.project + "/api/flow/sysProcessSetting/selectById.do", {id:meta.id})
            .success(function(res) {
                if (res.success) {
                  $scope.data=res.data;
                    if(res.data.type=="system"){
                        $scope.typeSel= $scope.typeOpt[0];
                    }else{
                        $scope.typeSel= $scope.typeOpt[1];
                    }
                } else {
                    notify({
                        message : res.message
                    });
                }
            })
    }



    $scope.sure = function() {
        $scope.data.type=$scope.typeSel.id;
        $http.post($rootScope.project + "/api/flow/sysProcessSetting/insertOrUpdate.do",$scope.data)
            .success(function(res) {
                if (res.success) {
                    $uibModalInstance.close("OK");
                }
                notify({
                    message : res.message
                });
            })

    };

    $scope.selectprocess=function(){


        var modalInstance = $uibModal.open({
            backdrop : true,
            templateUrl : 'views/flow/modal_flowselect.html',
            controller : modalFlowSelectCtl,
            size : 'lg'
        });
        modalInstance.result.then(function(result) {
            $scope.data.processdefid=result.id
        }, function(reason) {
            $log.log("reason", reason)
        });

    }


}
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
        DTColumnBuilder.newColumn('processdefid').withTitle('流程配置编码').withOption(
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
            templateUrl : 'views/flow/modal_flowconfsave.html',
            controller : modalFlowConfSaveCtl,
            size : 'lg',
            resolve : {
                meta : function() {
                    return ps;
                }
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
        var item = getSelectRow();
        if (angular.isDefined(item) && angular.isDefined(item.id)) {
            save(item.id);
        }
    }
    flush();
};

app.register.controller('processConfCtl', processConfCtl);