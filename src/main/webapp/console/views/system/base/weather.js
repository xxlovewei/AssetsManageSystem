function prodCtl($uibModal, $log, $rootScope, $scope, DTLang, DTOptionsBuilder,
		DTColumnBuilder, $compile, $http) {

	$scope.dtldzconfig = {
		url : 'fileupload.do',
		maxFilesize : 10000,
		paramName : "file",
		maxThumbnailFilesize : 4,
		// 一个请求上传多个文件
		uploadMultiple : true,
		// 当多文件上传,需要设置parallelUploads>=maxFiles
		parallelUploads : 4,
		maxFiles : 4,
		dictDefaultMessage : "点击上传图片",
		acceptedFiles : "image/jpeg,image/png,image/gif",
		// 添加上传取消和删除预览图片的链接，默认不添加
		addRemoveLinks : true,
		// 关闭自动上传功能，默认会true会自动上传
		// 也就是添加一张图片向服务器发送一次请求
		autoProcessQueue : false,
		init : function() {
			$scope.myDropzone = this; // closure
		}
	};

	$scope.ok = function() {
		console.log(getUuid());
		id=getUuid();
		$scope.myDropzone.options.url = $rootScope.project
				+ 'file/fileupload.do?bus=prodimgs&uuid=' + id
				+ '&type=image&interval=10000';
		console.log($scope.myDropzone.uploadFile($scope.myDropzone.files[0]));
	}

}

app.register.controller('prodCtl', prodCtl);