function ctCompProfileCtl($compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtldzconfig = {
		url : 'fileupload.do',
		maxFilesize : 10000,
		paramName : "file",
		maxThumbnailFilesize : 1,
		// 一个请求上传多个文件
		uploadMultiple : true,
		// 当多文件上传,需要设置parallelUploads>=maxFiles
		parallelUploads : 1,
		maxFiles : 1,
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
	$scope.item = {}
	$http.post($rootScope.project + "/api/company/queryCompany.do", $scope.item).success(function(res) {
		if (res.success) {
			// 加载数据
			$scope.item = res.data;
			 $scope.content=$scope.item.CONTENT;
			// 处理图片
			setTimeout(function() {
				// 设置图片
				var mockFile = {
					name : "主图",
					uuid : $scope.item.MPIC,
					href : $rootScope.project + "/api/file/imagedown.do?id=" + $scope.item.MPIC,
					url : $rootScope.project + "/api/file/imagedown.do?id=" + $scope.item.MPIC,
					status : "success",
					accepted : true,
					type : 'image/png'
				};
				$scope.myDropzone.emit("addedfile", mockFile);
				$scope.myDropzone.files.push(mockFile);
				// manually
				$scope.myDropzone.createThumbnailFromUrl(mockFile, $rootScope.project + "/api/file/imagedown.do?id=" + $scope.item.MPIC);
				$scope.myDropzone.emit("complete", mockFile);
			}, 600);

		}

	});

	$scope._simpleConfig = {
		// 这里可以选择自己需要的工具按钮名称,此处仅选择如下五个,'simpleupload', 去除了,原因是上传后为改变状态，未解决
		toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat',
				'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
				'rowspacingtop', 'rowspacingbottom', 'lineheight', '|', 'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|', 'directionalityltr', 'directionalityrtl',
				'indent', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|', 'link', 'unlink', 'anchor', '|',
				'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',  'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map',
				, 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|', 'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage',
				'|', 'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown',
				'splittocells', 'splittorows', 'splittocols', 'charts', '|', 'print', 'preview', 'searchreplace', 'drafts', 'help' ] ],
		// focus时自动清空初始化时的内容
		autoClearinitialContent : true,
		// 关闭字数统计
		wordCount : false,
		// 关闭elementPath
		elementPathEnabled : false
	};
	$scope.content = '';
	$scope.save = function() {
		console.log($scope._simpleConfig);
		// 处理图片
		if ($scope.myDropzone.files.length == 0) {
			notify({
				message : "请选择新闻主图"
			});
			return;
		}
		var picid = getUuid();
		$scope.myDropzone.options.url = $rootScope.project + '/api/file/fileupload.do?bus=news&uuid=' + picid + '&type=image&interval=10000';
		if (angular.isDefined($scope.myDropzone.files[0].uuid)) {
			// 已经上传
			picid = $scope.myDropzone.files[0].uuid;
		} else {
			$scope.myDropzone.uploadFile($scope.myDropzone.files[0])
		}

		$scope.item.MPIC = picid;
		$scope.item.CONTENT = $scope.content;
		$http.post($rootScope.project + "/api/company/updateCompany.do", $scope.item).success(function(res) {
			if (res.success) {
			} else {
			}
			notify({
				message : res.message
			});
		});
	}

};

app.register.controller('ctCompProfileCtl', ctCompProfileCtl);