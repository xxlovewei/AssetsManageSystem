
function modalzcallocationCtl($timeout, $localStorage, notify, $log, $uibModal,
                          $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                          DTColumnBuilder, $compile) {
    //type:detail,sure,add

    $scope.ctl={};
    $scope.ctl.inuserSel=false;
    $scope.ctl.outcompSel=false;
    $scope.ctl.incompSel=false;
    $scope.ctl.inpartSel=false;
    $scope.ctl.locSel=false;
    $scope.ctl.tolocdtl=false;
    $scope.ctl.mark=false;
    $scope.ctl.chosenzcbtn=false;
    $scope.ctl.surebtn=false;
    if(meta.actiontype=="detail"){
        $scope.ctl.inuserSel=true;
        $scope.ctl.outcompSel=true;
        $scope.ctl.incompSel=true;
        $scope.ctl.inpartSel=true;
        $scope.ctl.locSel=true;
        $scope.ctl.tolocdtl=true;
        $scope.ctl.mark=true;
        $scope.ctl.chosenzcbtn=true;
        $scope.ctl.surebtn=true;
    }else if(meta.actiontype=="sure"){
        $scope.ctl.inuserSel=true;
        $scope.ctl.outcompSel=true;
        $scope.ctl.incompSel=true;
        $scope.ctl.inpartSel=true;
        $scope.ctl.locSel=true;
        $scope.ctl.tolocdtl=true;
        $scope.ctl.mark=true;
        $scope.ctl.chosenzcbtn=true;
        $scope.ctl.surebtn=true;
    }else if(meta.actiontype=="add"){
    }


    $scope.outcompOpt=[];
    $scope.outcompSel="";
    $scope.incompOpt=[];
    $scope.incompSel="";
    $scope.inuserOpt=[];
    $scope.inuserSel="";

    $scope.locOpt=[];
    $scope.locSel="";
    $scope.inpartOpt=[];
    $scope.inpartSel="";


    $scope.outcompOpt=meta.gdict.comp;
    if(meta.gdict.length>0){
        $scope.outcompSel=$scope.outcompOpt[0];
    }
    $scope.inuserOpt=meta.gdict.partusers;
    $scope.incompOpt=meta.gdict.comp;
    $scope.locOpt=meta.gdict.devdc;
    if(meta.gdict.devdc.length>0){
        $scope.locSel=$scope.locOpt[0];
    }


    $scope.$watch('incompSel',function(newValue,oldValue){
        if(angular.isDefined($scope.incompSel.id)){
            $scope.inpartOpt=meta.gdict.comppart[$scope.incompSel.id];
            if( $scope.inpartOpt.length>0){
                $scope.inpartSel= $scope.inpartOpt[0];
            }
        }

    });

    $scope.dtOptions=DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 600)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', false)
        .withFixedColumns({
            leftColumns : 0,
            rightColumns : 0
        }).withOption('bStateSave', true).withOption('bProcessing', false)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', false).withOption('createdRow', function(row) {
            $compile(angular.element(row).contents())($scope);
        })


    $scope.dtColumns = [];
    $scope.dtColumns=zcBaseColsCreate(DTColumnBuilder,'withoutselect');

    $scope.dtOptions.aaData = [];


    $scope.data = {};
    $scope.data.mark = "";
    $scope.data.reason = "";

    $scope.selectzc = function() {
        var mdata={};
        mdata.id="";
        mdata.type="many";
        mdata.datarange="repair";
        if(angular.isUndefined($scope.outcompSel.id)){
            notify({
                message :"请选择调出公司"
            });
            return ;
        }

        mdata.belongcomp=$scope.outcompSel.id;

    //    mdata.belong_company_id=$scope.outcompSel.id;
        var modalInstance = $uibModal.open({
            backdrop : true,
            templateUrl : 'views/cmdb/modal_common_zclist.html',
            controller : modal_common_ZcListCtl,
            size : 'blg',
            resolve : {
                data:function(){
                    return mdata
                }
            }
        });
        modalInstance.result.then(function(result) {
            $scope.dtOptions.aaData=result;
        }, function(reason) {

            $log.log("reason", reason)
        });

    }

    if(angular.isDefined(meta.id)){
        //获取数据
        $http.post($rootScope.project + "/api/zc/resAllocate/ext/selectById.do",
            {id:meta.id}).success(function(res) {
            if (res.success) {
                $scope.dtOptions.aaData=res.data.items;
                $scope.data=res.data;

                if(angular.isDefined(res.data.frombelongcompid)){
                    for(var i=0;i<$scope.outcompOpt.length;i++){
                        if($scope.outcompOpt[i].id==res.data.frombelongcompid){
                            $scope.outcompSel=$scope.outcompOpt[i];
                            break;
                        }
                    }
                }


                if(angular.isDefined(res.data.tobelongcompid)){
                    for(var i=0;i<$scope.incompOpt.length;i++){
                        if($scope.incompOpt[i].id==res.data.tobelongcompid){
                            $scope.incompSel=$scope.incompOpt[i];
                            break;
                        }
                    }
                }

                if(angular.isDefined(res.data.tousedpartid)){
                    for(var i=0;i<$scope.inpartOpt.length;i++){
                        if($scope.inpartOpt[i].id==res.data.tousedpartid){
                            $scope.inpartSel=$scope.inpartOpt[i];
                            break;
                        }
                    }
                }

                if(angular.isDefined(res.data.toloc)){
                    for(var i=0;i<$scope.locOpt.length;i++){
                        if($scope.locOpt[i].id==res.data.toloc){
                            $scope.locSel=$scope.locOpt[i];
                            break;
                        }
                    }
                }

                if(angular.isDefined(res.data.allocateuserid)){
                    var e={};
                    e.user_id=$scope.data.allocateuserid;
                    e.name=$scope.data.allocateusername;
                    var arr=[];
                    arr.push(e);
                    $scope.inuserOpt=arr;
                    $scope.inuserSel= $scope.inuserOpt[0];
                }
            }else{
                notify({
                    message : res.message
                });
            }

        })

    }

    $scope.cancel = function() {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.sure = function() {
        if($scope.dtOptions.aaData.length==0){
            notify({
                message :"请选择资产"
            });
            return;
        }
        if(angular.isUndefined($scope.inuserSel.user_id)){
            notify({
                message :"请选择调度人"
            });
            return
        }

        if(angular.isUndefined($scope.outcompSel.id)){
            notify({
                message :"请选择调出公司"
            });
            return
        }

        if(angular.isUndefined($scope.incompSel.id)){
            notify({
                message :"请选择调入公司"
            });
            return
        }


        if(angular.isUndefined($scope.inpartSel.partid)){
            notify({
                message :"请选择调入部门"
            });
            return
        }

        if(angular.isUndefined($scope.locSel.dict_id)){
            notify({
                message :"请选择调入区域"
            });
            return
        }

        $scope.data.items=angular.toJson($scope.dtOptions.aaData);
        $scope.data.allocateuserid= $scope.inuserSel.user_id;
        $scope.data.allocateusername = $scope.inuserSel.name;

        $scope.data.frombelongcompid=$scope.outcompSel.id;
        $scope.data.frombelongcompname=$scope.outcompSel.name;

        $scope.data.tobelongcompid=$scope.incompSel.id;
        $scope.data.tobelongcompname=$scope.incompSel.name;

        $scope.data.tousedcompid=$scope.incompSel.id;
        $scope.data.tousedcompname=$scope.incompSel.name;

        $scope.data.tousedpartid=$scope.inpartSel.partid;
        $scope.data.tousedpartname=$scope.inpartSel.name;

        $scope.data.toloc=$scope.locSel.dict_item_id;
        $scope.data.tolocname=$scope.locSel.name;


        $http.post($rootScope.project + "/api/zc/resAllocate/ext/insertOrUpdate.do",
            $scope.data).success(function(res) {
            if (res.success) {
                $uibModalInstance.close('OK');
            }
            notify({
                message : res.message
            });
        })

    }
}

function zcallocationCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
                            $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

    var gdict={};
    var dicts = "devdc"
    $http
        .post($rootScope.project + "/api/zc/queryDictFast.do",
            {
                dicts : dicts,
                parts : "Y",
                partusers : "Y",
                comp :"Y",
                belongcomp:"Y",
                comppart:"Y",
                uid:"allocation"
            }).success(function(res) {
        if (res.success) {
            gdict=res.data;
        } else {
            notify({
                message : res.message
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
        })

    $scope.dtInstance = {}

    $scope.selectCheckBoxAll = function(selected) {
        if (selected) {
            $scope.dtInstance.DataTable.rows().select();
        } else {
            $scope.dtInstance.DataTable.rows().deselect();
        }
    }
    function renderStatus(data, type, full) {
        if(data=="doing"){
            return "未完成"
        }else if(data=="finish"){
            return "已完成"
        }else if(data=="cancel"){
            return "已取消"
        }else{
            return data;
        }
    }

    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns = [
        DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function() {
            return ""
        }),
        DTColumnBuilder.newColumn('uuid').withTitle('单据号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('status').withTitle('办理状态').withOption(
            'sDefaultContent', '').renderWith(renderStatus),
        DTColumnBuilder.newColumn('frombelongcompname').withTitle('调出公司').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('allocateusername').withTitle('调入管理员').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tobelongcompname').withTitle('调入公司').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tousedpartname').withTitle('调入部门').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tolocname').withTitle('调入区域').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tolocdtl').withTitle('存放位置').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('acttime').withTitle('调入日期').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('createTime').withTitle('创建时间').withOption(
            'sDefaultContent', '')]

    $scope.query = function() {
        flush();

    }

    var meta = {
        tablehide : false,
        tools : [
            {
                id : "input",
                label : "内容",
                placeholder : "输入型号、编号、序列号",
                type : "input",
                show : true,
                ct : ""
            },
            {
                id : "btn",
                label : "",
                type : "btn",
                show : true,
                priv:"select",
                template : ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">搜索</button>'
            },

            {
                id : "btn3",
                label : "",
                type : "btn",
                show : false,
                priv:"insert",
                template : ' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">申请调拨</button>'
            },
            {
                id : "btn4",
                label : "",
                type : "btn",
                show : false,
                priv:"act1",
                template : ' <button ng-click="finish()" class="btn btn-sm btn-primary" type="submit">确认调拨</button>'
            },
            {
                id : "btn4",
                label : "",
                type : "btn",
                show : false,
                priv:"remove",
                template : ' <button ng-click="cancel()" class="btn btn-sm btn-primary" type="submit">取消调拨</button>'
            },
            {
                id : "btn5",
                label : "",
                type : "btn",
                show : false,
                priv:"detail",
                template : ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详细</button>'
            }]
    }
    $scope.meta = meta;

    privNormalCompute($scope.meta.tools, $rootScope.curMemuBtns);
    function flush() {
        var ps = {};
        ps.search = $scope.meta.tools[0].ct;
        $http
            .post($rootScope.project + "/api/zc/resAllocate/ext/selectList.do",
                ps).success(function(res) {
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

    function action(actiontype,id) {
        var meta={};
        meta.actiontype=actiontype;
        meta.id=id;
        meta.gdict=gdict;
        var modalInstance = $uibModal.open({
            backdrop : true,
            templateUrl : 'views/cmdb/modal_zcallocation.html',
            controller : modalzcallocationCtl,
            size : 'lg',
            resolve : {
                meta : function() {
                    return meta;
                }
            }
        });
        modalInstance.result.then(function(result) {
            flush();
        }, function(reason) {
            $log.log("reason", reason)
        });

    }

    $scope.cancel=function(){
        var selrow = getSelectRow();
        if (angular.isDefined(selrow)&&angular.isDefined(selrow.id)) {
            $confirm({
                text : '确定要将所选单据取消?'
            }).then(
                function() {
                    $http.post(
                        $rootScope.project
                        + "/api/zc/resAllocate/ext/cancelAllocationById.do", {
                            id :selrow.id
                        }).success(function(res) {
                        if (res.success) {
                            flush();
                        } else {
                            notify({
                                message : res.message
                            });
                        }
                    });
                });
        }
    }

    $scope.finish=function(){
        var selrow = getSelectRow();
        if (angular.isDefined(selrow)&&angular.isDefined(selrow.id)) {
            $confirm({
                text : '确定要将所选单据状态修改为已完成?'
            }).then(
                function() {
                    $http.post(
                        $rootScope.project
                        + "/api/zc/resAllocate/ext/sureAllocationById.do", {
                            id :selrow.id
                        }).success(function(res) {
                        if (res.success) {
                            flush();
                        } else {
                            notify({
                                message : res.message
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


    $scope.detail = function() {var selrow = getSelectRow();
        if (angular.isDefined(selrow)&&angular.isDefined(selrow.id)) {
            action('detail',selrow.id);
        } else {
            return;
        }

    }


    $scope.add=function(){
        action('add');
    }


    flush();

};

app.register.controller('zcallocationCtl', zcallocationCtl);
