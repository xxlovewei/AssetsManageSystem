function touchChartCtl(notify, $log, $uibModal, $uibModalInstance, $scope, url,
		$http, $rootScope) {
	$log.info(url);
	$scope.url = url;
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function touchWebSaveCtl(notify, $log, $uibModal, $uibModalInstance, $scope,
		node, $http, $rootScope) {

	$log.info("window in:", node);
	$scope.item = {};
	if (angular.isDefined(node)) {
		$http.post($rootScope.project + "/api/mn/om/queryUrlMetricById.do", {
			node : node
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
		$http.post($rootScope.project + "/api/mn/om/saveUrlMetric.do",
				$scope.item).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			} else {
				notify({
					message : res.message
				});
			}
		})

	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function touchWebCtl($window, DTLang, DTOptionsBuilder, DTColumnBuilder,
		$compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal,
		$window) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType(
			'full_numbers').withDisplayLength(25).withOption("ordering", false)
			.withOption("responsive", true).withOption("searching", true)
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
		// acthtml = acthtml + " <button ng-click=\"toTerm('" + full.id
		// + "')\" class=\"btn-white btn btn-xs\">终端</button> ";
		// acthtml = acthtml + " <button ng-click=\"toSftp('" + full.id
		// + "')\" class=\"btn-white btn btn-xs\">Sftp</button> ";
		// acthtml = acthtml + " <button ng-click=\"addapp('" + full.id
		// + "')\" class=\"btn-white btn btn-xs\">添加应用</button> ";
		acthtml = acthtml + " <button ng-click=\"chart('" + full.node
				+ "','resp')\" class=\"btn-white btn btn-xs\">响应时间</button>  ";
		acthtml = acthtml + " <button ng-click=\"chart('" + full.node
				+ "','status')\" class=\"btn-white btn btn-xs\">响应码</button>  ";
		acthtml = acthtml + " <button ng-click=\"modify('" + full.node
				+ "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"remove('" + full.node
				+ "')\" class=\"btn-white btn btn-xs\">删除</button>  </div> ";
		return acthtml;
	}

	function renderStatus(data, type, full) {
		var value = data
		if (data == '1') {
			value = '启用'
		} else if (data == '0') {
			value = '停用'
		}
		return value;
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('url').withTitle('地址').withOption(
					'sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('is_running').withTitle('运行状态')
					.withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('threshold').withTitle('阀值').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('interval_time').withTitle('间隔')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('interval_time').withTitle('时间内最大次数')
					.withOption('maxwarn', ''),
			DTColumnBuilder.newColumn('resp_time').withTitle('响应时间')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('resp_status').withTitle('响应码')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('inserttime').withTitle('最近时间')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]
	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/mn/om/queryUrlMetricData.do", ps)
				.success(function(res) {
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

	$scope.chart = function(node, type) {

		var url = $rootScope.project + "/mn/weburl.html?node=" + node
				+ "&type=" + type + "&time=1/2";
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/om/touch/modal_chart.html',
			controller : touchChartCtl,
			size : 'blg',
			resolve : { // 调用控制器与modal控制器中传递值
				url : function() {
					return url;
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

	$scope.remove = function(id) {

		if (angular.isDefined(id)) {
			// 删除
			$confirm({
				text : '是否删除?'
			}).then(
					function() {
						$http.post(
								$rootScope.project
										+ "/api/mn/om/deleteUrlMetric.do", {
									node : id
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
	}

	$scope.modify = function(node) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/om/touch/modal_save.html',
			controller : touchWebSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				node : function() {
					return node;
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

app.register.controller('touchWebCtl', touchWebCtl);