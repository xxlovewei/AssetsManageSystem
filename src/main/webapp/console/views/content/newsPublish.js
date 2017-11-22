function ctNewsPublishCtl($compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.accessOpt = [ {
		ID : "local",
		NAME : "内置"
	}, {
		ID : "outer",
		NAME : "外链"
	} ]

	$scope.mpicOpt = [ {
		"id" : "left",
		"name" : "左侧"
	}, {
		"id" : "right",
		"name" : "右侧"
	}, {
		"id" : "top",
		"name" : "上部"
	},{
		"id" : "bottom",
		"name" : "底部"
	} ,{
		"id" : "center",
		"name" : "居中"
	}];
	$scope.mpicSel=$scope.mpicOpt[0];
	$scope.accessSel = $scope.accessOpt[0];

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
	
	$scope._simpleConfig = {
		// 这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
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
		//处理图片
		if ($scope.myDropzone.files.length == 0) {
			notify({
				message : "请选择新闻主图"
			});
			return;
		}
		var picid = getUuid();
		$scope.myDropzone.options.url = $rootScope.project
				+ '/api/file/fileupload.do?bus=news&uuid=' + picid
				+ '&type=image&interval=10000';
		if(angular.isDefined($scope.myDropzone.files[0].uuid )){
			//已经上传
			picid=$scope.myDropzone.files[0].uuid;
		}else{
			$scope.myDropzone.uploadFile($scope.myDropzone.files[0])
		}
		 
		$scope.item.mpic=picid;
		$scope.item.mpic_loc=$scope.mpicSel.id;
		$scope.item.content = $scope.content;
		$scope.item.urltype = $scope.accessSel.id;
		$http.post($rootScope.project + "/api/news/publishNews.do", $scope.item).success(function(res) {
			if (res.success) {

			} else {

			}
			notify({
				message : res.message
			});
		});
	}

};

app.register.controller('ctNewsPublishCtl', ctNewsPublishCtl);