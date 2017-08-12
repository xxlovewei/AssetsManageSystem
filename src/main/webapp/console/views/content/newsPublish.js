function ctNewsPublishCtl($compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.accessOpt = [ {
		ID : "local",
		NAME : "本地"
	}, {
		ID : "outer",
		NAME : "外链"
	} ]
	$scope.accessSel = $scope.accessOpt[0];
	$scope._simpleConfig = {
		// 这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
		toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat',
				'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
				'rowspacingtop', 'rowspacingbottom', 'lineheight', '|', 'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|', 'directionalityltr', 'directionalityrtl',
				'indent', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|', 'link', 'unlink', 'anchor', '|',
				'imagenone', 'imageleft', 'imageright', 'imagecenter', '|', 'simpleupload', 'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map',
				'gmap', 'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template', 'background', '|', 'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage',
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
		
		$scope.item.CONTENT=$scope.content;
		$scope.item.URLTYPE=$scope.accessSel.ID;
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