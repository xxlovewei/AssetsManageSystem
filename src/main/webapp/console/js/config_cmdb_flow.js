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