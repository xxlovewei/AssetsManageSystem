function sysParamSaveCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, id, $http, $rootScope) {

	$log.warn("window in:" + id);
	$scope.item = {};
	$scope.typeOpt = [ {
		id : "system",
		name : "系统"
	}, {
		id : "user",
		name : "用户"
	} ]
	$scope.typeSel = $scope.typeOpt[0];
	if (angular.isDefined(id)) {
		// 加载数据
		$http.post($rootScope.project + "/api/params/queryParamsById.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data
				// DICT_LEVEL
				if ($scope.item.dict_level == "system") {
					$scope.typeSel = $scope.typeOpt[0];
				} else if ($scope.item.dict_level == "user") {
					$scope.typeSel = $scope.typeOpt[1];
				}
			} else {
				notify({
					message : res.message
				});
			}
		})
	}

	$scope.sure = function() {

		$scope.item.type = $scope.typeSel.id;
		$http.post($rootScope.project + "/api/params/saveParams.do", $scope.item).success(function(res) {
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

function sysParamsCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withOption("select", {
				style : 'single'
			}).withLanguage(DTLang);
	$scope.dtInstance = {}

	function renderStatus(data, type, full) {

		var value = data
		if (data == 'system') {
			value = '系统'
		} else if (data == 'user') {
			value = '用户'
		}
		return value;
	}

	$scope.dtColumns = [ DTColumnBuilder.newColumn('name').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('value').withTitle('编码').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('type').withTitle('类型').withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption('sDefaultContent', '') ]

	function flush() {
		var ps = {}

		$http.post($rootScope.project + "/api/params/queryParams.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})
	}
	function getSelectId() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		// 没有选择,或选择多行都返回错误
		if (data.length == 0 || data.length > 1) {
			return;
		} else {
			return $scope.dtOptions.aaData[data[0]].id;
		}
	}

	flush();

	function save(id) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/params/modal_paramsSave.html',
			controller : sysParamSaveCtl,
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

	$scope.flush = function() {
		flush();
	}

	$scope.add = function() {
		save();
	}

	$scope.modify = function() {
		var id = getSelectId();
		if (angular.isDefined(id)) {
			save(id);
		} else {
			notify({
				message : "请选择一行"
			});
		}

	}

	$scope.del = function() {
		var id = getSelectId();
		if (angular.isDefined(id)) {
			//删除
			$confirm({
				text : '是否删除?'
			}).then(function() {
				$http.post($rootScope.project + "/api/params/deleteParams.do", {
					id : id
				}).success(function(res) {
					if (res.success) {
						flush();
					}
					notify({
						message : res.message
					});
				})
			});

		} else {
			notify({
				message : "请选择一行"
			});
		}

	}

};

app.register.controller('sysParamsCtl', sysParamsCtl);