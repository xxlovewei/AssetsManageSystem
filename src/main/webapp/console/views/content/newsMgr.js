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
	$http.post($rootScope.project + "/user/queryGroup.do", {}).success(function(res) {
		if (res.success) {
			$scope.userGroupOpt = prepend(res.data, {
				GROUP_ID : "ALL",
				NAME : "全部"
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

	$scope.URL = $rootScope.project + "/api/news/queryNewsByDatatable.do?noContent=Y";
	$scope.dtOptions = DTOptionsBuilder.fromSource($scope.URL).withDataProp('data').withPaginationType('simple_numbers').withDisplayLength(10).withOption("ordering", false)
			.withOption("responsive", true).withOption("searching", false).withOption("paging", true).withOption('bStateSave', true).withOption('bProcessing', false).withOption(
					'bFilter', false).withOption('bInfo', false).withOption('serverSide', true).withOption('bAutoWidth', false).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtInstance = {}
	$scope.reloadData = reloadData;
	function reloadData() {
		var resetPaging = false;
		$scope.dtInstance.reloadData(callback, resetPaging);
	}
	function callback(json) {
		console.log(json);
	}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.ID + "')\" class=\"btn-white btn btn-xs\">详细</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.ID + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
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
		html = html + "<img style='height:50px;width:50px;' src=" + $rootScope.project + "file/imagedown.do?id=" + full.MPIC + "  />"
		return html;
	}

	$scope.dtColumns = [

	DTColumnBuilder.newColumn('ID').withTitle('主图').withOption('sDefaultContent', '').renderWith(renderMImage),
			DTColumnBuilder.newColumn('TITLE').withTitle('标题').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('CREATETIME').withTitle('创建时间').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('HITS').withTitle('点击量').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('URLTYPE').withTitle('链接类型').withOption('sDefaultContent', '').renderWith(renderType),
			DTColumnBuilder.newColumn('USER_ID').withTitle('动作').withOption('sDefaultContent', '').renderWith(renderAction) ]

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
		flush();
	}
	$scope.save = function(id) {

	}

};

app.register.controller('ctNewsMgrCtl', ctNewsMgrCtl);