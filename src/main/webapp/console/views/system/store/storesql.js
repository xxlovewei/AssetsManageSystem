function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function sysStoreSqlCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.STORE_ID + "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.STORE_ID + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		if (data == "Y") {
			return "正常";
		} else if (data == "N") {
			return "停用";
		} else {
			return data;
		}
	}
	function renderACL(data, type, full) {
		if (data == "public") {
			return "公共";
		} else if (data == "user") {
			return "用户";
		} else if (data == "system") {
			return "系统";
		} else {
			return data;
		}
	}

	function renderRT(data, type, full) {
		if (data == "action") {
			return "行为";
		} else if (data == "array") {
			return "数组";
		} else if (data == "object") {
			return "对象";
		} else {
			return data;
		}
	}
	$scope.dtColumns = [

	DTColumnBuilder.newColumn('STORE_ID').withTitle('ID').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('NAME').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('ACL').withTitle('访问类型').withOption('sDefaultContent', '').renderWith(renderACL),
			DTColumnBuilder.newColumn('RETURN_TYPE').withTitle('返回类型').withOption('sDefaultContent', '').renderWith(renderRT),
			DTColumnBuilder.newColumn('IS_USED').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('SQL').withTitle('SQL文本').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('USER_ID').withTitle('动作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/store/queryStoreSql.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})
	}
	flush();
	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/store/deleteStoreSql.do", {
				store_id : id
			}).success(function(res) {
				flush();
			})
		});

	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {
		alert("待开发");
	}

};

app.register.controller('sysStoreSqlCtl', sysStoreSqlCtl);