function resTranferOrderCtl($timeout, $localStorage, notify, $log, $uibModal,
                            $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                            DTColumnBuilder, $compile) {


    var dicts = "devdc"
    $scope.locOpt=[];
    $scope.locSel="";

    $scope.incompOpt=[];
    $scope.incompSel={};
    $scope.inpartOpt=[];
    $scope.inpartSel={};

    $scope.inuserOpt=[];
    $scope.inuserSel={};

    $scope.date = {
        receivedate: moment()
    }

    $rootScope.flowpagetype = meta.flowpagetype;
    $rootScope.flowbusid = meta.busid;
    $rootScope.flowtaskid = meta.taskid;
    $scope.item={};
    $scope.catOpt=[{id:"inside",name:"离职"},{id:"outside",name:"调拨"},{id:"other",name:"其他"}];
    $scope.catSel= $scope.catOpt[0];
    //save,select,
    if(angular.isUndefined(meta.pagetype)){
        meta.pagetype="select";
    }
    $scope.pagetype=meta.pagetype;



    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.dtOptions =
        DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
            .withPaginationType('full_numbers').withDisplayLength(100)
            .withOption("ordering", false).withOption("responsive", false)
            .withOption("searching", true).withOption('scrollY', 600)
            .withOption('scrollX', true).withOption('bAutoWidth', true)
            .withOption('scrollCollapse', true).withOption('paging', false)
            .withOption('bStateSave', true).withOption('bProcessing', false)
            .withOption('bFilter', false).withOption('bInfo', false)
            .withOption('serverSide', false).withOption('createdRow', function (row) {
            $compile(angular.element(row).contents())($scope);
        })
    $scope.dtColumns = [];
    $scope.dtColumns = zcBaseColsCreate(DTColumnBuilder, 'withoutselect');
    $scope.dtOptions.aaData = [];



    $http
        .post($rootScope.project + "/api/zc/queryDictFast.do",
            {
                dicts: dicts,
                parts: "Y",
                partusers: "Y",
                comp: "Y",
                belongcomp: "Y",
                comppart: "Y",
                uid: "resTranfer"
            }).success(function (res) {
        if (res.success) {
            $scope.incompOpt=res.data.comp
            if($scope.incompOpt.length>0){
                $scope.incompSel=$scope.incompOpt[0]
            }
            $scope.inpartOpt=res.data.parts
            if($scope.inpartOpt.length>0){
                $scope.inpartSel=$scope.inpartOpt[0]
            }

            $scope.locOpt=res.data.devdc
            if($scope.locOpt.length>0){
                $scope.locSel=$scope.locOpt[0]
            }
            $scope.inuserOpt=res.data.partusers;
            $scope.inuserSel="";


            if(angular.isDefined(meta.busid)){
                $http.post($rootScope.project + "/api/zc/resTranfer/ext/selectByBusid.do",
                    {busid:meta.busid}).success(function (res) {
                    if (res.success) {
                        $scope.item=res.data;

                        if(angular.isDefined(res.data.transfercatid)){
                            for(var i=0;i<$scope.catOpt.length;i++){
                                if($scope.catOpt[i].id==res.data.transfercatid){
                                    $scope.catSel=$scope.catOpt[i] ;
                                    break;
                                }
                            }

                        }

                        $scope.date.receivedate=  moment(res.data.receivedatestr);
                        $scope.dtOptions.aaData = res.data.items;

                        if (angular.isDefined(res.data.tloc)) {
                            for (var i = 0; i < $scope.locOpt.length; i++) {
                                if ($scope.locOpt[i].id == res.data.tloc) {
                                    $scope.locSel = $scope.locOpt[i];
                                    break;
                                }
                            }
                        }


                        if (angular.isDefined(res.data.tusedcompid)) {
                            for (var i = 0; i < $scope.incompOpt.length; i++) {
                                if ($scope.incompOpt[i].id == res.data.tusedcompid) {
                                    $scope.incompSel = $scope.incompOpt[i];
                                    break;
                                }
                            }
                        }


                        if (angular.isDefined(res.data.tusedpartid)) {
                            for (var i = 0; i < $scope.inpartOpt.length; i++) {
                                if ($scope.inpartOpt[i].partid == res.data.tusedpartid) {
                                    $scope.inpartSel = $scope.inpartOpt[i];
                                    break;
                                }
                            }
                        }


                        if (angular.isDefined(res.data.receiveruserid)) {
                            for (var i = 0; i < $scope.inuserOpt.length; i++) {
                                if ($scope.inuserOpt[i].user_id == res.data.receiveruserid) {
                                    $scope.inuserSel = $scope.inuserOpt[i];
                                    break;
                                }
                            }
                        }



                    }
                })
            }

        } else {
            notify({
                message: res.message
            });
        }
    })

    function close() {
        $uibModalInstance.close('OK');
    }
    $scope.windowclose = function () {
        $uibModalInstance.close('OK');
    }
    $scope.sure = function () {
        if ($scope.dtOptions.aaData.length == 0) {
            notify({
                message: "请选择资产"
            });
            return;
        }


        if(angular.isUndefined($scope.inuserSel.user_id)){
            notify({
                message: "请选择接收人"
            });
            return;

        }
        $scope.item.transfercatid=$scope.catSel.id;
        $scope.item.transfercatname=$scope.catSel.name;

        $scope.item.tusedcompid=$scope.incompSel.id;
        $scope.item.tusedcompname=$scope.incompSel.name

        $scope.item.receiveruserid= $scope.inuserSel.user_id
        $scope.item.receiverusername= $scope.inuserSel.name

        $scope.item.tloc=$scope.locSel.dict_item_id;
        $scope.item.tlocname=$scope.locSel.name;
        $scope.item.receivedate = $scope.date.receivedate.format('YYYY-MM-DD');
        $scope.item.tusedpartid=$scope.inpartSel.partid;
        $scope.item.tusedpartname=$scope.inpartSel.name;
        $scope.item.items = angular.toJson($scope.dtOptions.aaData);
        $http.post($rootScope.project + "/api/zc/resTranfer/ext/save.do",
            $scope.item).success(function (res) {
            if (res.success) {
                $uibModalInstance.close('OK');
            }
            notify({
                message: res.message
            });
        })


    }

    $scope.selectzc = function () {
        console.log(1111)
        var mdata = {};
        mdata.id = "";
        mdata.type = "many";
        mdata.datarange = "MYZY";


        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_common_zclist.html',
            controller: modal_common_ZcListCtl,
            size: 'blg',
            resolve: {
                data: function () {
                    return mdata
                }
            }
        });
        modalInstance.result.then(function (result) {
            $scope.dtOptions.aaData = result;
        }, function (reason) {
            $log.log("reason", reason)
        });
    }

}

function resPurchaseOrderCtl($timeout, $localStorage, notify, $log, $uibModal,
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

    $scope.dzconfig = {
        url: 'fileupload.do',
        maxFilesize: 10000,
        paramName: "file",
        maxThumbnailFilesize: 1,
        // 一个请求上传多个文件
        uploadMultiple: true,
        // 当多文件上传,需要设置parallelUploads>=maxFiles
        parallelUploads: 1,
        maxFiles: 1,
        dictDefaultMessage: "点击上传需要上传的文件",
        acceptedFiles: "image/jpeg,image/png,image/gif,.xls,.zip,.rar,.doc,.pdf,.docx,.txt,.xlsx",
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

    if(angular.isDefined(meta.busid)){
        $http.post($rootScope.project + "/api/zc/resPurchase/ext/selectById.do",
            {busid:meta.busid}).success(function (res) {
            if (res.success) {
                $scope.item=res.data;
                if($scope.item.plan=="inside"){
                    $scope.planSel=$scope.planOpt[0];
                }else if($scope.item.plan=="outside"){
                    $scope.planSel=$scope.planOpt[1];
                }
                if (angular.isDefined($scope.item.files) && $scope.item.files.length > 0) {
                    setTimeout(function () {
                        var mockFile = {
                            size: 0,
                            name: "附件",
                            // 需要显示给用户的图片名
                            uuid: $scope.item.files,
                            href: $rootScope.project + "/api/file/filedown.do?id=" + $scope.item.files,
                            url: $rootScope.project + "/api/file/filedown.do?id=" + $scope.item.files,
                            status: "success",
                            accepted: true
                        };
                        $scope.myDropzone.emit("addedfile", mockFile);
                        $scope.myDropzone.files.push(mockFile); // file must be added
                        // manually
                        $scope.myDropzone.createThumbnailFromUrl(mockFile, $rootScope.project + "/api/file/filedown.do?id=" + $scope.item.files);
                        $scope.myDropzone.emit("complete", mockFile);
                    }, 500)
                }



            }

        })
    }

    function close() {
        $uibModalInstance.close('OK');
    }
    $scope.windowclose = function () {
        $uibModalInstance.close('OK');
    }
    $scope.sure = function () {
        var id = getUuid();
        if ($scope.myDropzone.files.length > 0) {
            if (typeof ($scope.myDropzone.files[0].uuid) == "undefined") {
                // 需要上传
                $scope.myDropzone.options.url = $rootScope.project
                    + '/api/file/fileupload.do?uuid=' + id
                    + '&bus=file&interval=10000&bus=file';
                $scope.myDropzone.uploadFile($scope.myDropzone.files[0])
            } else {
                id = $scope.myDropzone.files[0].uuid;
            }
            $scope.item.files = id;
        } else {
            $scope.item.files = "";
        }

        $scope.item.plan=$scope.planSel.id;
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