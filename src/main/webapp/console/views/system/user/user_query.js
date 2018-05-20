function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function sysUserQueryCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.userGroupOpt = [];
	$scope.userGroupSel = "";
	$http.post($rootScope.project + "/api/user/queryGroup.do", {}).success(
			function(res) {
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
	$scope.URL = $rootScope.project + "/api/user/userQueryByGroup.do";
	$scope.dtOptions = DTOptionsBuilder.fromSource($scope.URL).withDataProp(
			'data').withPaginationType('full_numbers').withDisplayLength(10)
			.withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", true)
			.withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', true).withOption('bAutoWidth', false)
			.withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	
	$scope.reloadData = reloadData;
	$scope.dtInstance = {}
	
	function reloadData() {
		var resetPaging = false;
		$scope.dtInstance.reloadData(callback, resetPaging);
	}
	function callback(json) {
		console.log(json);

	}
	 
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.user_id
				+ "')\" class=\"btn-white btn btn-xs\">详细</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "正常";
		if (full.locked == "Y") {
			res = "锁定";
		}
		return res;
	}

	function renderType(data, type, full) {
		if (data == "sys") {
			return "系统";
		} else if (data == "empl") {
			return "员工";
		} else {
			return data;
		}
	}

	$scope.dtColumns = [

			DTColumnBuilder.newColumn('empl_id').withTitle('员工编号').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('user_name').withTitle('登录名').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('name').withTitle('姓名').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('tel').withTitle('手机号').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('user_type').withTitle('用户类型')
					.withOption('sDefaultContent', '').renderWith(renderType),
			DTColumnBuilder.newColumn('user_id').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('user_id').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]
	

	function flush() {

		if ($scope.userGroupSel.group_id != "ALL") {
			$scope.URL = $rootScope.project
					+ "/api/user/userQueryByGroup.do?group_id=" + $scope.userGroupSel.group_id;
		} else {
			$scope.URL = $rootScope.project + "/api/user/userQueryByGroup.do";
		}
		$scope.dtOptions.ajax = $scope.URL;
		reloadData();

	}

	$scope.row_dtl = function(id) {

	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {

	}

};

app.register.controller('sysUserQueryCtl', sysUserQueryCtl);