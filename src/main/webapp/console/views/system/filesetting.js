function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function sysfileConfCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
 
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType(
			'full_numbers').withDisplayLength(25).withOption("ordering", false)
			.withOption("responsive", true).withOption("searching", false)
			.withOption("paging", false).withOption('bStateSave', true)
			.withOption('bProcessing', true).withOption('bFilter', false)
			.withOption('bInfo', false).withOption('serverSide', false)
			.withOption('bAutoWidth', false).withOption('aaData',
					$scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.ID
				+ "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.ID
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
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

	$scope.dtColumns = [

			DTColumnBuilder.newColumn('ID').withTitle('ID').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('NAME').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('PATH').withTitle('路径').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('IS_USED').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('USER_ID').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}

		$http.post($rootScope.project + "/api/file/fileConfQuery.do", ps).success(
				function(res) {
					if (res.success) {
						$scope.dtOptions.aaData = res.data;
					}
				})
	}
	flush();

	$scope.row_del = function(id) {
		alert("待开发");

	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {
		alert("待开发");
	}

};

app.register.controller('sysfileConfCtl', sysfileConfCtl);