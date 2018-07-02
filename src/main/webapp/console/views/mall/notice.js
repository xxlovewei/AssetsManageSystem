function mallNoticeItemSaveCtl($localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, id, $http, $rootScope) {
	$log.info("window in:" + id)
	$scope.usedOpt = [ {
		id : "N",
		name : "隐藏"
	}, {
		id : "Y",
		name : "显示"
	} ];

	$scope.usedSel = $scope.usedOpt[0];

	$scope._simpleConfig = {
		// 这里可以选择自己需要的工具按钮名称,此处仅选择如下五个,'simpleupload', 去除了,原因是上传后为改变状态，未解决
		toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|',
				'bold', 'italic', 'underline', 'fontborder', 'strikethrough',
				'superscript', 'subscript', 'removeformat', 'formatmatch',
				'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor',
				'backcolor', 'insertorderedlist', 'insertunorderedlist',
				'selectall', 'cleardoc', '|', 'rowspacingtop',
				'rowspacingbottom', 'lineheight', '|', 'customstyle',
				'paragraph', 'fontfamily', 'fontsize', '|',
				'directionalityltr', 'directionalityrtl', 'indent', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
				'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft',
				'imageright', 'imagecenter', '|', 'insertimage', 'emotion',
				'scrawl', 'insertvideo', 'music', 'attachment', 'map', ,
				'insertframe', 'insertcode', 'webapp', 'pagebreak', 'template',
				'background', '|', 'horizontal', 'date', 'time', 'spechars',
				'snapscreen', 'wordimage', '|', 'inserttable', 'deletetable',
				'insertparagraphbeforetable', 'insertrow', 'deleterow',
				'insertcol', 'deletecol', 'mergecells', 'mergeright',
				'mergedown', 'splittocells', 'splittorows', 'splittocols',
				'charts', '|', 'print', 'preview', 'searchreplace', 'drafts',
				'help' ] ],
		// focus时自动清空初始化时的内容
		autoClearinitialContent : true,
		// 关闭字数统计
		wordCount : false,
		// 关闭elementPath
		elementPathEnabled : false
	};
	$scope.content = '';

	$scope.item = {};
	if (angular.isDefined(id)) {
		// 修改
		$http.post($rootScope.project + "/api/notice/queryNoticeById.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data
				if ($scope.item.is_show == "Y") {
					$scope.usedSel = $scope.usedOpt[1];
				} else {
					$scope.usedSel = $scope.usedOpt[0];
				}
				if(angular.isDefined(res.data.ct)){
					$scope.content=res.data.ct;	
				}				
			} else {
				notify({
					message : res.message
				});
			}
		})
	} else {
		// 新增
	}

	$scope.sure = function() {

		$scope.item.type = "mall";
		$scope.item.is_show = $scope.usedSel.id;
		$scope.item.ct=$scope.content;
		$http.post($rootScope.project + "/api/notice/saveNotice.do",
				$scope.item).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			}
			notify({
				message : res.message
			});
		})
	}
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}

function mallNoticeCtl(  DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.meta ={
			tools : [  {
				id : "1",
				name : "新增",
				type : "btn",
				template:' <button ng-click="save()" class="btn btn-sm btn-primary" type="submit">新增</button>'
	 
			} ]
		}
		
 
	$scope.URL = $rootScope.project + "/api/mallnotice/queryNotice.do";
	$scope.dtOptions = DTOptionsBuilder.fromSource($scope.URL).withDataProp('data').withPaginationType('full_numbers').withDisplayLength(10).withOption("ordering", false)
	.withOption("responsive", true).withOption("searching", false).withOption("paging", true).withOption('bStateSave', true).withOption('bProcessing', false).withOption(
			'bFilter', false).withOption('bInfo', false).withOption('serverSide', true).withOption('bAutoWidth', false).withOption('createdRow', function(row) {
		// Recompiling so we can bind Angular,directive to the
		$compile(angular.element(row).contents())($scope);
	});

	$scope.dtInstance = {}
	$scope.reloadData = reloadData;
	function reloadData() {
		var resetPaging = false;
		$scope.dtInstance.reloadData(callback, resetPaging);
	}

	function callback(json) {
		 
	}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">编辑</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "隐藏";
		if (full.is_show== "Y") {
			res = "显示";
		}
		return res;
	}
	 

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('title').withTitle('标题').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('rdate').withTitle('修改时间').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_show').withTitle('是否显示').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	$scope.row_detail = function(id) {

	}
	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除功能?'
		}).then(function() {
			$http.post($rootScope.project + "/api/notice/delNotice.do", {
				id : id
			}).success(function(res) {
				if (res.success) {
					reloadData();
				}
				notify({
					message : res.message
				});
			})
		});
	}

	$scope.query = function() {
		reloadData();
	}
	$scope.save = function(id) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/mall/modal_noticesave.html',
			controller : mallNoticeItemSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				id : function() {
					return id;
				}
			}
		});

		modalInstance.result.then(function(result) {
			$log.log("result", result);
			if (result == "OK") {
				reloadData();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});
	}

};

app.register.controller('mallNoticeCtl', mallNoticeCtl);