function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function metricTemplAddFormCtl($localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, id, $http, $rootScope) {

	// $log.warn("window in:", userIds);

	$scope.item = {};

	if (angular.isDefined(id)) {
		$http.post($rootScope.project + "/api/mn/queryMetricGroupById.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data;
			} else {
				notify({
					message : res.message
				});
			}

		})

	}
	$scope.sure = function() {
		$scope.item.status = 'Y';
		$http.post($rootScope.project + "/api/mn/saveMetricGroup.do", $scope.item)
				.success(function(res) {
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

function metricTemplCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
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
		acthtml = acthtml + " <button ng-click=\"save('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "未知";
		if (full.status == "Y") {
			res = "显示";
		} else {
			res = "隐藏";
		}
		return res;
	}

	$scope.dtColumns = [

			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('status').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {

		$http.post($rootScope.project + "/api/mn/queryMetricGroup.do", {}).success(
				function(res) {
					if (res.success) {
						$scope.dtOptions.aaData = res.data;
					} else {
						notify({
							message : res.message
						});
					}
				})
	}
	flush();

	$scope.row_dtl = function(id) {

	}

	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除？'
		}).then(function() {
			$http.post($rootScope.project + "/api/mn/delMetricGroup.do", {
				id : id
			}).success(function(res) {
				if (res.success) {
					flush();
				} else {
					notify({
						message : res.message
					});
				}
			})
		});

	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/om/metric/modal_templ_save.html',
			controller : metricTemplAddFormCtl,
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

app.register.controller('metricTemplCtl', metricTemplCtl);