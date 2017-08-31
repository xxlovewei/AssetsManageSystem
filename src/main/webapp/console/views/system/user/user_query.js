function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function sysUserQueryCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

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

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.USER_ID + "')\" class=\"btn-white btn btn-xs\">详细</button> </div> ";
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
		if(data=="sys"){
			return "系统";
		}else if(data=="empl"){
			return "员工";
		}else{
			return data;
		}
	}
	
	
	$scope.dtColumns = [

	DTColumnBuilder.newColumn('EMPL_ID').withTitle('员工编号').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('USER_NAME').withTitle('登录名').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('NAME').withTitle('姓名').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('TEL').withTitle('手机号').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('USER_TYPE').withTitle('用户类型').withOption('sDefaultContent', '').renderWith(renderType),
			DTColumnBuilder.newColumn('USER_ID').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('USER_ID').withTitle('动作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		if ($scope.userGroupSel.GROUP_ID != "ALL") {
			ps.group_id = $scope.userGroupSel.GROUP_ID;
		}

		$http.post($rootScope.project + "/api/user/userQueryByGroup.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})
	}
	flush();

	$scope.row_dtl = function(id) {

	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {

	}

};

app.register.controller('sysUserQueryCtl', sysUserQueryCtl);