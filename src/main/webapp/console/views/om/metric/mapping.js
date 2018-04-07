function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}
 

function metricTemplAddmetricCtl($timeout, DTLang, DTOptionsBuilder, DTColumnBuilder,
		notify, $log, $uibModal, $uibModalInstance, $scope, id, $http,
		$rootScope, $compile) {
	$scope.item = {}
	$log.log("window in:", id);

	$scope.dtOptions = DTOptionsBuilder
			.fromFnPromise()
			.withPaginationType('full_numbers')
			.withDisplayLength(10)
			.withOption("ordering", false)
			.withOption("responsive", true)
			.withOption("searching", true)
			.withOption("paging", true)
			.withOption('bStateSave', true)
			.withOption('bProcessing', true)
			.withOption('bFilter', false)
			.withOption('bInfo', false)
			.withOption('serverSide', false)
			.withOption('bAutoWidth', false)
			.withOption('aaData', $scope.tabdata)
			.withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			})
			.withOption(
					'initComplete',
					function(settings, conf) {
						if (angular.isDefined($scope.dtInstance.DataTable)) {
							$timeout(
									function() {
										for (var i = 0; i < $scope.dtOptions.aaData.length; i++) {
											if ($scope.dtOptions.aaData[i].selected == "Y") {

												$scope.dtInstance.DataTable
														.row(':eq(' + i + ')')
														.select();
											} else {
												continue;
											}
										}
									}, 80)
						}
					}).withLanguage(DTLang).withOption("select", {
				style : 'multi',
				selector : 'td:first-child'
			});

	$scope.dtInstance = {}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn(null).withTitle('').withClass(
					'select-checkbox').renderWith(function() {
				return '';
			}),
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('ip').withTitle('IP').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('type').withTitle('类型').withOption(
					'sDefaultContent', '') ]
	// $scope.dtInstance.DataTable.rows('.even').select();
	function flush() {

		$http.post($rootScope.project + "/api/mn/mnServiceNeedAddNodes.do", {
			id : id
		}).success(function(res) {
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
	$scope.sure = function() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		var urls = [];
		for (var j = 0; j < data.length; j++) {
			urls.push($scope.dtOptions.aaData[data[j]].id)
		}
		var ps = {};
		ps.node_ids = angular.toJson(urls);
		ps.id = id;
		$http.post($rootScope.project + "/api/mn/mnServiceAddNodes.do", ps)
				.success(function(res) {
					if (res.success) {
						$uibModalInstance.close("OK");
					}
					notify({
						message : res.message
					});
				})

	};
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function metricmappingCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.templOpt = [];
	$scope.templSel = "";

	$http.post($rootScope.project + "/api/mn/queryMetricGroup.do", {}).success(
			function(res) {
				if (res.success) {
					$scope.templOpt = res.data;
					if (res.data.length > 0) {
						$scope.templSel = res.data[0];
						flush();
					}
				} else {
					notify({
						message : res.message
					});
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
		// acthtml = acthtml + " <button ng-click=\"save('" + full.id
		// + "')\" class=\"btn-white btn btn-xs\">更新</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.service_id
				+ "','" + full.id
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
			DTColumnBuilder.newColumn('ip').withTitle('IP').withOption(
					'sDefaultContent', ''),
			// DTColumnBuilder.newColumn('showstatus').withTitle('状态').withOption(
			// 'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('type').withTitle('类型').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {

		$http.post($rootScope.project + "/api/mn/queryMnServiceNodes.do", {
			id : $scope.templSel.id
		}).success(function(res) {
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

	$scope.row_del = function(service_id, node_id) {
		$confirm({
			text : '是否删除？'
		}).then(function() {
			$http.post($rootScope.project + "/api/mn/mnServiceDelNode.do", {
				id : service_id,
				node_id : node_id
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
	$scope.add = function() {

		var id = $scope.templSel.id;
		if (!angular.isDefined(id)) {
			alert("没有Serviec_Id");
			return;
		}
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/om/metric/modal_metricmapping.html',
			controller : metricTemplAddmetricCtl,
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
	$scope.save = function(id) {

		 
	}

};

app.register.controller('metricmappingCtl', metricmappingCtl);