function GetDateNowId()
{
    var vNow = new Date();
    var sNow="";
    sNow += String(vNow.getFullYear());
    sNow += String(vNow.getMonth() + 1);
    sNow += String(vNow.getDate());
    sNow += String(vNow.getHours());
    sNow += String(vNow.getMinutes());
    sNow += String(vNow.getSeconds());
    sNow += String(vNow.getMilliseconds());
   return sNow;
}


function zcinventorySaveCtl($timeout, $localStorage, notify, $log, $uibModal,
                          $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                          DTColumnBuilder, $compile) {


    $scope.item={};
    $scope.adminuserOpt=meta.dict.partusers;
    $scope.adminuserSel="";
    if( $scope.adminuserOpt.length>0){
        $scope.adminuserSel=$scope.adminuserOpt[0];
    }

    $scope.pduserOpt=meta.dict.partusers;
    $scope.pduserSel=[];

    $scope.belongcompOpt=meta.dict.belongcomp;
    $scope.belongcompSel=[];

    $scope.compOpt=meta.dict.comp;
    $scope.compSel=[]

    $scope.comppartOpt=meta.dict.parts;
    $scope.comppartSel=[];



    $scope.zcCatOpt=meta.dict.zccatused;
    $scope.zcCatSel=[];

    $scope.zcAreaOpt=meta.dict.devdc
    $scope.zcAreaSel=[];



    $scope.cancel = function() {
        $uibModalInstance.dismiss('cancel');
    };

    $scope.sure = function() {
        if(angular.isDefined($scope.adminuserSel)&&angular.isDefined($scope.adminuserSel.user_id)){
            $scope.item.adminuserid=$scope.adminuserSel.user_id;
            $scope.item.adminusername=$scope.adminuserSel.name;
        }else{
            notify({
                message :"请选择盘点负责人"
            });
            return;
        }
        //分配盘点人员
        if(angular.isDefined($scope.pduserSel)&&$scope.pduserSel.length>0){
            var tstr="";
            for(var i=0;i<$scope.pduserSel.length;i++){
                tstr=tstr+$scope.pduserSel[i].name+" ";
            }
            $scope.item.pduserdata=angular.toJson($scope.pduserSel);
            $scope.item.pduserlist=tstr;
        }else{
            notify({
                message :"请选择分配盘点人员"
            });
            return;
        }


        if(angular.isDefined($scope.zcAreaSel)&&$scope.zcAreaSel.length>0){
            var tstr1="";
            var tstr2="";
            for(var i=0;i<$scope.zcAreaSel.length;i++){
                tstr1=tstr1+$scope.zcAreaSel[i].dict_item_id+" ";
                tstr2=tstr2+$scope.zcAreaSel[i].name+" ";
            }
            $scope.item.areadata=angular.toJson($scope.zcAreaSel);
            $scope.item.area=tstr1;
            $scope.item.areaname=tstr2;
        }

       if(angular.isDefined($scope.belongcompSel)&& angular.isDefined($scope.belongcompSel.id)){
           $scope.item.belongcomp=$scope.belongcompSel.id;
           $scope.item.belongcompname=$scope.belongcompSel.name;
       }

        if(angular.isDefined($scope.compSel)&& angular.isDefined($scope.compSel.id)){
            $scope.item.usedcomp=$scope.compSel.id;
            $scope.item.usedcompname=$scope.compSel.name;
        }

        if(angular.isDefined($scope.comppartSel)&&$scope.comppartSel.length>0){
            var tstr1="";
            var tstr2="";
            for(var i=0;i<$scope.comppartSel.length;i++){
                tstr1=tstr1+$scope.comppartSel[i].partid+" ";
                tstr2=tstr2+$scope.comppartSel[i].name+" ";
            }
            $scope.item.usedpartdata=angular.toJson($scope.comppartSel);
            $scope.item.usedpart=tstr1;
            $scope.item.usedpartname=tstr2;
        }

        if(angular.isDefined($scope.zcCatSel)&&$scope.zcCatSel.length>0){
            var tstr1="";
            var tstr2="";
            for(var i=0;i<$scope.zcCatSel.length;i++){
                tstr1=tstr1+$scope.zcCatSel[i].id+" ";
                tstr2=tstr2+$scope.zcCatSel[i].name+" ";
            }
            $scope.item.rescatdata=angular.toJson($scope.zcCatSel);
            $scope.item.rescat=tstr1;
            $scope.item.rescatname=tstr2;
        }
        if(angular.isUndefined($scope.item.status)){
            $scope.item.status="start";
        }

        if(angular.isUndefined($scope.item.syncstatus)){
            $scope.item.syncstatus="0";
        }

        if(angular.isUndefined($scope.item.batchid)){
            $scope.item.batchid= GetDateNowId();
        }


        $scope.item.manualinventory=1;
        $scope.item.allusersinventory=0;

        $http.post($rootScope.project + "/api/zc/resInventory/ext/insertOrUpdate.do",
            $scope.item).success(function(res) {
            if (res.success) {
                $uibModalInstance.close('ok');
            }
            notify({
                message : res.message
            });
        })
    }
}

function zcPdCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
                            $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

    var gdict={};
    var dicts="devdc";
    $http
        .post($rootScope.project + "/api/zc/queryDictFast.do", {
            dicts : dicts,
            parts : "Y",
            partusers : "Y",
            comp :"Y",
            belongcomp:"Y",
            zccatused:"Y",
            uid:"zcinventory"
        })
        .success(
            function(res) {
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


    function renderAction(data, type, full) {
        //分配，删除，详情
        if(data=="wait"){
            return "维修中"
        }else if(data=="finish"){
            return "已完成"
        }else{
            return data;
        }
    }

    function renderStatus(data, type, full) {
        if(data=="wait"){
            return "等待开始"
        }else if(data=="start"){
            return "进行中"
        }else if(data=="cancel"){
            return "取消"
        }else if(data=="finsih "){
            return "完成"
        }else{
            return data;
        }
    }

    function renderDownload(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";
            acthtml = acthtml + " <button ng-click=\"download('"
                + full.id
                + "')\" class=\"btn-white btn btn-xs\">下载</button>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    function renderAction(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";

        var tstr="";
        if(full.status=='start' || full.status=='wait'){
        }else{
            tstr=" disabled=\"disabled\"";
        }
        acthtml = acthtml + " <button  "+tstr+" ng-click=\"assignusers('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">分配用户</button>   ";


        var tstr2="";
        if(full.status=='finish' && full.syncstatus!='1'){
        }else{
            tstr2=" disabled=\"disabled\"";
        }
        acthtml = acthtml + " <button "+ tstr2+"ng-click=\"syncdata('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">同步数据</button>   ";


        var tstr3="";
        if(full.status=='start' ||full.status=='wait'){
        }else{
            tstr3=" disabled=\"disabled\"";
        }
        acthtml = acthtml + " <button "+ tstr3+"ng-click=\"cancel('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">取消</button>   ";


        acthtml = acthtml + " <button ng-click=\"detail('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">详情</button>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }
    function renderDataStatus(data, type, full) {
        if(data=="1"){
            return "已同步"
        }else if(data=="0"){
            return "未同步"
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
        DTColumnBuilder.newColumn('batchid').withTitle('批次号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('adminusername').withTitle('负责人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('status').withTitle('盘点状态').withOption(
            'sDefaultContent', '').renderWith(renderStatus),
        DTColumnBuilder.newColumn('syncstatus').withTitle('数据同步').withOption(
            'sDefaultContent', '').renderWith(renderDataStatus),
        DTColumnBuilder.newColumn('belongcompname').withTitle('所属公司').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('usedcompname').withTitle('使用公司').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('createTime').withTitle('创建时间').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('id').withTitle('盘点资产').withOption(
            'sDefaultContent', '').renderWith(renderDownload),
        DTColumnBuilder.newColumn('id').withTitle('动作').withOption(
            'sDefaultContent', '').withOption(
            'width', '200px').renderWith(renderAction)

    ]

    $scope.query = function() {
        flush();

    }

    var meta = {
        tablehide : false,
        tools : [
            {
                id : "input",
                label : "内容",
                placeholder : "",
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
                show : true,
                priv:"insert",
                template : ' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">新建</button>'
            },
         	{
                id : "btn2",
                label : "",
                type : "btn",
                show : true,
                priv:"remove",
                template : ' <button ng-click="del()" class="btn btn-sm btn-primary" type="submit">删除</button>'
            }]
    }
    $scope.meta = meta;


    privNormalCompute($scope.meta.tools, $rootScope.curMemuBtns);
    function flush() {
        var ps = {};
        ps.search = $scope.meta.tools[0].ct;
        $http
            .post($rootScope.project + "/api/zc/resInventory/ext/selectList.do",
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

    function action(id) {
        var meta={};
        meta.id=id;
        meta.dict=gdict;
        var modalInstance = $uibModal.open({
            backdrop : true,
            templateUrl : 'views/cmdb/modal_zcinventory.html',
            controller : zcinventorySaveCtl,
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

    $scope.finish=function(){

    }
    $scope.del = function() {

        var selrow = getSelectRow();
        if (angular.isDefined(selrow)&&angular.isDefined(selrow.id)) {
            $confirm({
                text : '是否删除?'
            }).then(
                function() {
                    $http.post(
                        $rootScope.project
                        + "/api/zc/resRepair/ext/deleteById.do", {
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
        } else {
            return;
        }


    }

    $scope.assignusers=function(){
        alert('开发中')
    }


    $scope.download=function(){
alert('开发中')
    }

    $scope.cancel=function(){
        alert('开发中')
    }





    $scope.detail = function() {var selrow = getSelectRow();
        alert('开发中')
    }



    $scope.add=function(){
        action();
    }
    flush();

};

app.register.controller('zcPdCtl', zcPdCtl);
