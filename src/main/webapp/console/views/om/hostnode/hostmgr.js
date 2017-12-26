function nodeHostTermCtl(notify, $log, $uibModal, $uibModalInstance, $scope,
		id, $http, $rootScope) {

	$scope.cmd = "";
	$scope.result = "";
	$scope.ok = function() {
		if (angular.isDefined(id)) {
			var ps = {};
			ps.id = id;
			ps.cmd = $scope.cmd;
			$http.post($rootScope.project + "/api/node/executeHostNodeCommand.do",
					ps).success(function(res) {
				if (res.success) {
					$scope.result = res.data;
				} else {
					$scope.result = res.message;
				}
			})

		} else {
			notify({
				message : "主机未选择"
			});
		}
	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}

function nodeHostSaveCtl(notify, $log, $uibModal, $uibModalInstance, $scope,
		id, $http, $rootScope) {

	$log.warn("window in:" + id);
	$scope.item = {};

	$scope.hosttypeOpt = [ {
		id : "window",
		name : "Window"
	}, {
		id : "aix",
		name : "AIX"
	}, {
		id : "linux",
		name : "Linux"
	} ]
	$scope.hosttypeSel = $scope.hosttypeOpt[0];

	$scope.validOpt = [ {
		id : "Y",
		name : "正常"
	}, {
		id : "N",
		name : "暂停"
	} ]
	$scope.validSel = $scope.validOpt[0];

	// 当前只支持ssh登录
	$scope.logintypeOpt = [ {
		id : "ssh",
		name : "SSH登录"
	} ]

	$scope.logintypeSel = $scope.logintypeOpt[0];

	if (angular.isDefined(id)) {
		// 加载数据
		$http.post($rootScope.project + "/api/node/queryNodeById.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data
				if (res.data.isvalid == "Y") {
					$scope.validSel = $scope.validOpt[0];
				} else {
					$scope.validSel = $scope.validOpt[1];
				}

				if (res.data.smalltype == "aix") {
					$scope.hosttypeSel = $scope.hosttypeOpt[1];
				} else if (res.data.smalltype == "linux") {
					$scope.hosttypeSel = $scope.hosttypeOpt[2];
				} else if (res.data.smalltype == "window") {
					$scope.hosttypeSel = $scope.hosttypeOpt[0];
				}

			} else {
				notify({
					message : res.message
				});
			}
		})
	}

	$scope.sure = function() {

		$scope.item.isvalid = $scope.validSel.id;
		$scope.item.type = "host";
		$scope.item.smalltype = $scope.hosttypeSel.id;
		$scope.item.logintype = $scope.logintypeSel.id;
		$http.post($rootScope.project + "/api/node/saveNode.do", $scope.item)
				.success(function(res) {
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

function nodeHostMgrCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

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
		acthtml = acthtml + " <button ng-click=\"toTerm('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">终端</button> ";
		acthtml = acthtml + " <button ng-click=\"modify('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">修改</button>  ";
		acthtml = acthtml + " <button ng-click=\"remove('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button>  </div> ";
		return acthtml;
	}

	function renderStatus(data, type, full) {
		var value = data
		if (data == 'Y') {
			value = '正常'
		} else if (data == 'N') {
			value = '暂停'
		}
		return value;
	}

	function renderRunStatus(data, type, full) {

		var value = data
		if (data == 'Y') {
			value = '正常'
		} else if (data == 'N') {
			value = '暂停'
		}
		return value;
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('smalltype').withTitle('类型').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('ip').withTitle('IP').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('isvalid').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('isrunning').withTitle('运行状态')
					.withOption('sDefaultContent', '').renderWith(
							renderRunStatus),
			DTColumnBuilder.newColumn('cdate').withTitle('创建时间').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('username').withTitle('用户名').withOption(
					'sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('logtype').withTitle('登录类型').withOption(
					'sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('operator').withTitle('操作人员').withOption(
					'sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('seq').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]
	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/node/queryNodeHost.do", ps)
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

	$scope.remove = function(id) {
		if (angular.isDefined(id)) {
			// 删除
			$confirm({
				text : '是否删除?'
			}).then(function() {
				$http.post($rootScope.project + "/api/node/deleteNode.do", {
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

		}
	}

	$scope.modify = function(id) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/om/hostnode/modal_hostSave.html',
			controller : nodeHostSaveCtl,
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
	$scope.toTerm = function(id) {
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/om/hostnode/modal_toTerm.html',
			controller : nodeHostTermCtl,
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
				// flush();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}
};

app.register.controller('nodeHostMgrCtl', nodeHostMgrCtl);