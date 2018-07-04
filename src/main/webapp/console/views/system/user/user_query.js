 

function sysUserQueryCtl( DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
  

	
	
	
	
	$scope.meta = {
			tools : [ {
				id : "1",
				label : "用户组",
				type : "select",
				disablesearch:true,
				dataOpt :[],
				dataSel : ""
			}, {
				id : "1",
				label : "查询",
				type : "btn",
				template:' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">查询</button>'
	 
			} ]
		}
 
	$http.post($rootScope.project + "/api/user/queryGroup.do", {}).success(
			function(res) {
				if (res.success) {
					 $scope.meta.tools[0].dataOpt= prepend(res.data, {
						group_id : "ALL",
						name : "全部"
					});
					 $scope.meta.tools[0].dataSel= $scope.meta.tools[0].dataOpt[0];
				} else {
					notify({
						message : res.message
					});
				}
			});
	$scope.URL = $rootScope.project + "/api/user/userQueryByGroup.do";
	$scope.dtOptions = DTOptionsBuilder.fromSource($scope.URL).withDataProp(
			'data').withPaginationType('full_numbers')
			.withOption('serverSide', true)
			.withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			});
	
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
			DTColumnBuilder.newColumn('user_id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]
	

	function flush() {
		if ($scope.meta.tools[0].dataSel.group_id != "ALL") {
			$scope.URL = $rootScope.project
					+ "/api/user/userQueryByGroup.do?group_id=" +$scope.meta.tools[0].dataSel.group_id;
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