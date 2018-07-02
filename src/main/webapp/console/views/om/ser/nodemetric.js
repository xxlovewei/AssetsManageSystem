 

function sernodemetricCtl($localStorage, notify, $log, $uibModal, data,
		$uibModalInstance, $scope, $http, $rootScope) {

	// $log.warn("window in:", userIds);
	$scope.item = data
	console.log($scope.item);
	$scope.showOpt = [{
				id : "Y",
				name : "是"
			}, {
				id : "N",
				name : "否"
			}];
	$scope.showSel = $scope.showOpt[0];
	if (angular.isDefined(data.is_show) && data.is_show == "N") {
		$scope.showSel = $scope.showOpt[1];
	}
	$scope.sure = function() {
		$scope.item.is_show = $scope.showSel.id;
		$http.post($rootScope.project + "/api/mn/saveServiceNodeMetric.do",
				$scope.item).success(function(res) {
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

function mnservicenodemetricCtl( DTOptionsBuilder, DTColumnBuilder,
		$compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.serviceOpt = [];
	$scope.serviceSel = "";

	$scope.nodeOpt = [];
	$scope.nodeSel = "";

	$http.post($rootScope.project + "/api/mn/queryServics.do", {}).success(
			function(res) {
				if (res.success) {
					$scope.serviceOpt = res.data;
					if (res.data.length > 0) {
						$scope.serviceSel = res.data[0];
					}
				} else {
					notify({
								message : res.message
							});
				}
			})

	// 检测变化
	var watch2 = $scope.$watch('serviceSel',
			function(oldValue, newValue, scope) {
				console.log(oldValue, newValue, scope);
				if (angular.isDefined($scope.serviceSel.id)) {
					$scope.nodeOpt = [];
					$scope.nodeSel = "";
					$scope.dtOptions.aaData = [];
					$http.post(
							$rootScope.project
									+ "/api/mn/queryMnServiceNodes.do", {
								id : $scope.serviceSel.id
							}).success(function(res) {
								if (res.success) {
									$scope.nodeOpt = res.data;
									if (res.data.length > 0) {
										$scope.nodeSel = res.data[0];
										flush();
									}
								} else {
									notify({
												message : res.message
											});
								}
							})
				}
			});

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
					});
	$scope.dtInstance = {}

	// (ser_id,node_id,metric_id,di,is_show)
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		// acthtml = acthtml + " <button ng-click=\"save('" + full.id
		// + "')\" class=\"btn-white btn btn-xs\">更新</button> ";
		acthtml = acthtml + " <button ng-click=\"row_update('"
				+ full.service_id + "','" + full.node_id + "','" + full.id
				+ "','" + full.di + "','" + full.is_show + "','" + full.mtype
				+ "','"+full.node_v_a_v+"')\" class=\"btn-white btn btn-xs\">更新</button> </div> ";
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

 
	function warnAction(data, type, full) {
		var res = "未知";
		if (full.is_warn == "Y") {
			res = "是";
		} else {
			res = "否";
		}
		return res;
	}
	function warnctAction(data, type, full) {
		var v_a = full.v_a;
		var v_a_m = full.v_a_m;
		var v_a_v = full.node_v_a_v;

		var html = v_a + v_a_m + v_a_v;

		return html;
	}

	$scope.dtColumns = [

			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('showtype').withTitle('显示类型').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_show').withTitle('是否显示').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			// DTColumnBuilder.newColumn('showstatus').withTitle('状态').withOption(
			// 'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('di').withTitle('数据间隔').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('cols').withTitle('字段').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mtype').withTitle('来源类型').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_warn').withTitle('是否告警').withOption(
					'sDefaultContent', '').renderWith(warnAction),
			DTColumnBuilder.newColumn('id').withTitle('告警内容').withOption(
					'sDefaultContent', '').renderWith(warnctAction),
			DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction)]

	function flush() {

		$http.post($rootScope.project + "/api/mn/queryServiceNodeMetric.do", {
					service_id : $scope.serviceSel.id,
					node_id : $scope.nodeSel.id
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

	$scope.query = function() {
		flush();
	}
	$scope.row_update = function(ser_id, node_id, metric_id, di, is_show, mtype,v_a_v) {

		var data = {};
		data.service_id = ser_id;
		data.metric_id = metric_id;
		data.node_id = node_id;
		data.di = di;
		data.is_show = is_show;
		data.mtype = mtype;
		data.v_a_v = v_a_v;
		var modalInstance = $uibModal.open({
					backdrop : true,
					templateUrl : 'views/om/ser/modal_sernodemetric.html',
					controller : sernodemetricCtl,
					size : 'lg',
					resolve : { // 调用控制器与modal控制器中传递值
						data : function() {
							return data;
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

app.register.controller('mnservicenodemetricCtl', mnservicenodemetricCtl);