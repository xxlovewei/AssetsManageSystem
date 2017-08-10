function orgEmpSavePartCtl($rootScope, $scope, $timeout, $log) {

	$scope.partOpt = []
	$scope.partSel = []

	$scope.$watch('partSel', function() {
		$log.info('partSel change');
		$rootScope.sys_partSel = $scope.partSel;
	}, true);

	// 获取列表
	$scope.$watch(function() {
		return $rootScope.sys_partOpt;
	}, function() {
		//$log.info("wath sys_partOpt change.", $rootScope.sys_partOpt);
		if (angular.isDefined($rootScope.sys_partOpt)) {
			$scope.partOpt = $rootScope.sys_partOpt;
			if ($scope.partOpt.length > 0) {
				$scope.partSel.push($scope.partOpt[0]);
			}
		}
	}, true);

	$scope.$watch(function() {
		return $rootScope.sys_partSelItem;
	}, function() {
		var parts = $rootScope.sys_partSelItem;
		if (angular.isDefined(parts)) {
			//$log.info("wath sys_partSelItem change.", $scope.partOpt.length,$rootScope.sys_partSelItem, parts.length);
			if (parts.length == 0) {

			} else {
				var partsSel = [];
				$timeout(function() {
					for (var i = 0; i < parts.length; i++) {
						for (var j = 0; j < $scope.partOpt.length; j++) {
							if ($scope.partOpt[j].NODE_ID == parts[i].NODE_ID) {
								$log.info("match");
								partsSel.push($scope.partOpt[j]);
								break;
							}
						}
					}
					if (partsSel.length > 0) {
						$scope.partSel = partsSel;
					}
				}, 100);
			}

		}
	}, true);

}
function orgEmpSaveCtl($localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, id, $http, $rootScope, partOpt, $timeout) {

	$scope.data = {};
	$timeout(function() {
		var d = angular.copy(partOpt)
		d.splice(0, 1);
		$rootScope.sys_partOpt = d;
	}, 800);

	$rootScope.sys_partSel;

	if (angular.isDefined(id)) {
		// 加载数据
		$http.post($rootScope.project + "/hrm/employeeQueryById.do", {
			empl_id : id
		}).success(function(res) {
			if (res.success) {
			
				$scope.data = res.data;
				$scope.data.OLD_PARTS = res.data.PARTS;
				$timeout(function() {
					$rootScope.sys_partSelItem = res.data.PARTS
				}, 810);

			} else {
				notify({
					message : res.message
				});
			}

		})
	} else {
		$rootScope.sys_partSelItem = [];
	}
	$scope.sure = function() {



		// 跨越controller获取数据数据
		if (!angular.isDefined($rootScope.sys_partSel)) {
			notify({
				message : "发生系统错误"
			});
			return;
		}
		if ($rootScope.sys_partSel.length == 0) {
			notify({
				message : "至少选择一个组织"
			});
			return;

		}
		if ($rootScope.sys_partSel.length > 3) {
			notify({
				message : "最多只可选三个组织"
			});
			return;

		}
		// 检查姓名
		if (!angular.isDefined($scope.data.NAME)) {
			notify({
				message : "请输入姓名"
			});
			return;
		}
		$scope.data.NODES = angular.toJson($rootScope.sys_partSel);

		var cmd = "";
		if (angular.isDefined($scope.data.EMPL_ID)) {
			cmd = "/hrm/employeeUpdate.do"
			$scope.data.OLD_NODES = angular.toJson($scope.data.OLD_PARTS)
		} else {
			cmd = "/hrm/employeeAdd.do";
		}
	
		$http.post($rootScope.project + cmd, $scope.data).success(
				function(res) {
					notify({
						message : res.message
					});
					if (res.success) {
						$uibModalInstance.close("OK");
					}
				})

	}
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}

function orgEmpAdjustCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.data = {
		NAME : ""
	};
	$scope.partOpt = [];
	$scope.partSel = "";
	$http.post($rootScope.project + "/hrm/orgQueryLevelList.do", {}).success(
			function(res) {
				if (res.success) {
					var d = res.data;
					d.splice(0, 0, {
						"ROUTENAME" : "全部",
						NODE_ID : "-1",
						LEVELS : 0
					})
					$scope.partOpt = d;
					$scope.partSel = $scope.partOpt[0];
				}
			})

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
		acthtml = acthtml + " <button ng-click=\"save('" + full.EMPL_ID
				+ "')\" class=\"btn-white btn btn-xs\">编辑</button> ";
		// acthtml = acthtml + " <button ng-click=\"row_detail()\"
		// class=\"btn-white btn btn-xs\">详细</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.EMPL_ID
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
			DTColumnBuilder.newColumn('EMPL_ID').withTitle('员工编号').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('NAME').withTitle('姓名').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('NODE_NAME').withTitle('所属').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('ROLE_ID').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) 
					]

	function flush() {

		$scope.data.NODE_ID = $scope.partSel.NODE_ID;
		$http.post($rootScope.project + "/hrm/employeeQueryList.do",
				$scope.data).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})
	}

	$scope.row_detail = function(id) {

	}
	$scope.row_del = function(id) {
		
		
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		console.log("select data", data.length);
		
		$confirm({
			text : '是否删除功能?'
		}).then(function() {
			$http.post($rootScope.project + "/hrm/employeeDelete.do", {
				EMPL_ID : id
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
			templateUrl : 'views/org/modal_employee_save.html',
			controller : orgEmpSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				id : function() {
					return id;
				},
				partOpt : function() {
					return $scope.partOpt;
				}
			}
		});

		modalInstance.result.then(function(result) {
			 
			if (result == "OK") {
				flush();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});
	}

};

app.register.controller('orgEmpAdjustCtl', orgEmpAdjustCtl);
app.register.controller('orgEmpSavePartCtl', orgEmpSavePartCtl);
