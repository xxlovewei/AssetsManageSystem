function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function ctNewsMgrCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.userGroupOpt = [];
	$scope.userGroupSel = "";
	$http.post($rootScope.project + "/api/user/queryGroup.do", {}).success(function(res) {
		if (res.success) {
			$scope.userGroupOpt = prepend(res.data, {
				group_id : "ALL",
				name : "全部"
			});
			$scope.userGroupSel = $scope.userGroupOpt[0];
		} else {
			notify({
				message : res.message
			});
		}
	});

	var dd = [ {
		"id" : 860,
		"firstName" : "Superman",
		"lastName" : "Yoda"
	}, {
		"id" : 870,
		"firstName" : "Foo",
		"lastName" : "Whateveryournameis"
	}, {
		"id" : 590,
		"firstName" : "Toto",
		"lastName" : "Titi"
	}, {
		"id" : 803,
		"firstName" : "Luke",
		"lastName" : "Kyle"
	} ]

	$scope.URL = $rootScope.project + "/api/news/queryNewsByPage.do?noContent=Y";
	$scope.dtOptions = DTOptionsBuilder.fromSource($scope.URL).withDataProp('aadata').withPaginationType('full_numbers').withDisplayLength(10).withOption("ordering", false)
			.withOption("responsive", true).withOption("searching", false).withOption("paging", true).withOption('bStateSave', true).withOption('bProcessing', false).withOption(
					'bFilter', false).withOption('bInfo', false).withOption('serverSide', true).withOption('bAutoWidth', false).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
 		 
	 
	$scope.dtInstance = {}
	$scope.reloadData = reloadData;
	var tabdata=[]
	function reloadData() {
		var resetPaging = false;
		$scope.dtInstance.reloadData(callback, resetPaging);
	}
	function callback(json) {
		tabdata=json.data;
	 
	}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

//		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.id + "')\" class=\"btn-white btn btn-xs\">详细</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.id + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "正常";
		if (full.LOCKED == "Y") {
			res = "锁定";
		}
		return res;
	}

	function renderType(data, type, full) {
		if (data == "outer") {
			return "外链";
		} else if (data == "local") {
			return "内置";
		} else {
			return data;
		}
	}

	function renderMImage(data, type, full) {
		var html = ""
		html = html + "<img style='height:50px;width:50px;' src=" + $rootScope.project + "/api/file/imagedown.do?id=" + full.mpic + "  />"
		return html;
	}

	$scope.dtColumns = [

	DTColumnBuilder.newColumn('id').withTitle('主图').withOption('sDefaultContent', '').renderWith(renderMImage),
			DTColumnBuilder.newColumn('title').withTitle('标题').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('createtime').withTitle('创建时间').withOption('sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('hits').withTitle('点击量').withOption('sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('urltype').withTitle('链接类型').withOption('sDefaultContent', '').renderWith(renderType),
			DTColumnBuilder.newColumn('user_id').withTitle('动作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/news/deleteNews.do", {
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
		alert("未实现");
	}
	$scope.save = function(id) {

	}

};

app.register.controller('ctNewsMgrCtl', ctNewsMgrCtl);