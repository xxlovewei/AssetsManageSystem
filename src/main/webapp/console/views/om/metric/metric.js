function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function metricAddFormCtl($localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, id, $http, $rootScope) {

	$scope.chartdataOpt = [ {
		id : "direct",
		name : "直接"
	}, {
		id : "indata",
		name : "数据中"
	} ];
	$scope.chartdataSel = $scope.chartdataOpt[0];
	$scope.item = {};

	if (angular.isDefined(id)) {
		$http.post($rootScope.project + "/api/mn/queryMetricById.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data;
				if (res.data.chartdatatype == "direct") {
					$scope.chartdataSel = $scope.chartdataOpt[0];
				} else {
					$scope.chartdataSel = $scope.chartdataOpt[1];
				}

			} else {
				notify({
					message : res.message
				});
			}

		})

	}
	$scope.sure = function() {
		$scope.item.status = 'Y';
		$scope.item.ds = "tab";
		$scope.item.showtype = "chart";
		$scope.item.chartdatatype = $scope.chartdataSel.id;

		$http.post($rootScope.project + "/api/mn/saveMetric.do", $scope.item)
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

function metricCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
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
			DTColumnBuilder.newColumn('cols').withTitle('字段').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {

		$http.post($rootScope.project + "/api/mn/queryMetric.do", {}).success(
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
			$http.post($rootScope.project + "/api/mn/delMetric.do", {
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
			templateUrl : 'views/om/metric/modal_metric_save.html',
			controller : metricAddFormCtl,
			size : 'lg',
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

app.register.controller('metricCtl', metricCtl);