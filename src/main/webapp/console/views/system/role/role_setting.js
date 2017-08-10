function roleSaveCtl($localStorage, notify, $log, $uibModal, $uibModalInstance,
		$scope, id, $http, $rootScope) {

	$scope.statusOpt = [{
				ID : "Y",
				NAME : "有效"
			}, {
				ID : "N",
				NAME : "无效"
			}]

	$scope.statusSel = $scope.statusOpt[0];
	$scope.item = {};
	if (angular.isDefined(id)) {
		// 修改
		$http.post($rootScope.project + "/api/role/roleQueryById.do", {
					ROLE_ID : id
				}).success(function(res) {
					if (res.success) {
						$scope.item = res.data
						if ($scope.item.IS_ACTION == "Y") {
							$scope.statusSel = $scope.statusOpt[0];
						} else {
							$scope.statusSel = $scope.statusOpt[1];
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

		$scope.item.IS_ACTION = $scope.statusSel.ID;
	 
		$http.post($rootScope.project + "/api/role/roleSave.do", $scope.item)
				.success(function(res) {
							if (res.success) {
								$uibModalInstance.close("OK");
								notify({
											message : res.message
										});
							} else {
								notify({
											message : res.message
										});
							}
						})
	}
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}

function sysRoleSettingCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise()
			.withPaginationType('full_numbers').withDisplayLength(25)
			.withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false)
			.withOption('bStateSave', true).withOption('bProcessing', true)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('bAutoWidth', false)
			.withOption('aaData', $scope.tabdata).withOption('createdRow',
					function(row) {
						// Recompiling so we can bind Angular,directive to the
						$compile(angular.element(row).contents())($scope);
					}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.ROLE_ID
				+ "')\" class=\"btn-white btn btn-xs\">编辑</button> ";
		// acthtml = acthtml + " <button ng-click=\"row_detail()\"
		// class=\"btn-white btn btn-xs\">详细</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.ROLE_ID
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "无效";
		if (full.IS_ACTION == "Y") {
			res = "有效";
		}
		return res;
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('ROLE_NAME').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('MARK').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('IS_ACTION').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('ROLE_ID').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction)]

	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/role/roleQuery.do", ps).success(
				function(res) {
					if (res.success) {
						$scope.dtOptions.aaData = res.data;
					}
				})
	}
	flush();

	$scope.row_detail = function(id) {

	}
	$scope.row_del = function(id) {
		$confirm({
					text : '是否删除功能?'
				}).then(function() {
					$http.post($rootScope.project + "/api/role/roleDelete.do", {
								ID : id
							}).success(function(res) {
								if (res.success) {
									flush();
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
		var modalInstance = $uibModal.open({
					backdrop : true,
					templateUrl : 'views/system/role/modal_role_save.html',
					controller : roleSaveCtl,
					size : 'md',
					resolve : { // 调用控制器与modal控制器中传递值
						id : function() {
							return id;
						}
					}
				});

		modalInstance.result.then(function(result) {
					$log.log("result", result);
					if (result == "OK") {

						flush();
					}
				}, function(reason) {
					// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
					$log.log("reason", reason)
				});
	}

};

app.register.controller('sysRoleSettingCtl', sysRoleSettingCtl);