function wxappsavectl(notify, $log, $uibModal, $uibModalInstance, $scope, id,
		$http, $rootScope) {

	console.log("window in:" + id);

	$scope.item = {};
	if (angular.isDefined(id)) {
		$http.post($rootScope.project + "/api/wx/queryWxAppById.do", {
					id : id
				}).success(function(res) {
					if (res.success) {
						$scope.item = res.data;
					} else {

					}
				})

	}

	$scope.sure = function() {
		$http.post($rootScope.project + "/api/wx/saveWxApp.do", $scope.item)
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

function wxappCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
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
		acthtml = acthtml + " <button ng-click=\"modify('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"syncToWx('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">同步到微信</button>  ";
		acthtml = acthtml
				+ " <button ng-click=\"syncFromWx('"
				+ full.id
				+ "')\" class=\"btn-white btn btn-xs\">从微信同步</button>  </div>  ";
		return acthtml;
	}

	$scope.dtColumns = [

			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('app_id').withTitle('AppId').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('menu').withTitle('菜单').withOption(
					'sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction)]

	function flush() {
		var ps = {}

		$http.post($rootScope.project + "/api/wx/queryWxApps.do", ps).success(
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

	$scope.flush = function() {
		flush();
	}

	$scope.modify = function(id) {

		var modalInstance = $uibModal.open({
					backdrop : true,
					templateUrl : 'views/wx/modal_saveapp.html',
					controller : wxappsavectl,
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

	$scope.syncToWx = function(id) {

		$http.post($rootScope.project + "/api/wx/createMenuToWx.do", {
					id : id
				}).success(function(res) {
					notify({
								message : res.message
							});
				})
	}

	$scope.syncFromWx = function(id) {

		$http.post($rootScope.project + "/api/wx/sysncMenu.do", {
					id : id
				}).success(function(res) {
					notify({
								message : res.message
							});
				})
	}

};

app.register.controller('wxappCtl', wxappCtl);