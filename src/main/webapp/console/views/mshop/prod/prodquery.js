function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function saveprodqueryCtl($log, $http, $rootScope, $scope, $uibModalInstance,
		$timeout, data, notify) {
	$scope.item = {};
	$scope.item.is_used = "Y";
	$scope.item.name = "";

	$scope.dlOpt = data.dlOpt;
	$scope.dlSel = "";
	if (data.dlOpt.length > 0) {
		$scope.dlSel = data.dlOpt[0];
	}

	$scope.xlOpt = [];
	$scope.xlSel = "";

	var watch2 = $scope
			.$watch(
					'dlSel',
					function(oldValue, newValue, scope) {
						if (angular.isDefined($scope.dlSel.class_id)) {
							console.log($scope.dlSel.class_id);
							console.log($scope.dlSel.name);
							var ps = {};
							ps.module = $scope.dlSel.class_id;
							$http
									.post(
											$rootScope.project
													+ "/api/prod/queryXl.do",
											ps)
									.success(
											function(xlres) {
												if (xlres.success) {
													$scope.xlOpt = xlres.data;
													if (xlres.data.length > 0) {
														// 如果存在小类
														$scope.xlSel = xlres.data[0];
														if (angular
																.isDefined($scope.item.xl)) {
															for (var i = 0; i < $scope.xlOpt.length; i++) {
																if ($scope.xlOpt[i].class_id == $scope.item.xl) {
																	$scope.xlSel = $scope.xlOpt[i];
																	break;
																}
															}
														}

													}
												}
											})
						}
					});

	if (angular.isDefined(data.id)) {
		$http.post($rootScope.project + "/api/mshop/queryProdById.do", {
			id : data.id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data;
				var a = $timeout(function() {
					for (var i = 0; i < $scope.dlOpt.length; i++) {
						if ($scope.dlOpt[i].class_id == res.data.dl) {
							$scope.dlSel = $scope.dlOpt[i];
							break;
						}
					}
				}, 800);

			} else {
				notify({
					message : res.message
				});
			}
		})
	}

	$scope.sure = function() {

		if (angular.isDefined($scope.dlSel) && angular.isDefined($scope.xlSel)
				&& angular.isDefined($scope.dlSel.class_id)
				&& angular.isDefined($scope.xlSel.class_id)) {
			$scope.item.dl = $scope.dlSel.class_id;
			$scope.item.xl = $scope.xlSel.class_id;
			$http.post($rootScope.project + "/api/mshop/saveProd.do",
					$scope.item).success(function(res) {
				if (res.success) {
					$uibModalInstance.close("OK");
				}
				notify({
					message : res.message
				});
			})
		} else {
			alert("选择类型");
		}

	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function prodqueryCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.first = true;
	$scope.dlOpt = [];
	$scope.dlSel = "";
	$scope.xlOpt = [];
	$scope.xlSel = "";
	$http.post($rootScope.project + "/api/prod/queryDl.do", {}).success(
			function(res) {
				if (res.success) {
					$scope.dlOpt = res.data;
					if (res.data.length > 0) {
						$scope.dlSel = res.data[0];
					}
				}
			})

	var watch2 = $scope.$watch('dlSel', function(oldValue, newValue, scope) {
		if (angular.isDefined($scope.dlSel.class_id)) {
			console.log($scope.dlSel.class_id);
			console.log($scope.dlSel.name);
			var ps = {};
			ps.module = $scope.dlSel.class_id;
			$http.post($rootScope.project + "/api/prod/queryXl.do", ps)
					.success(function(xlres) {
						if (xlres.success) {
							$scope.xlOpt = xlres.data;
							if (xlres.data.length > 0) {
								$scope.xlSel = xlres.data[0];
								if ($scope.first) {
									flush();
									$scope.first = false;
								}
							}
						}
					})
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

		acthtml = acthtml + " <button ng-click=\"row_delete('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;

	}

	function renderStatus(data, type, full) {

		if (data == "Y") {
			return "使用中"
		} else {
			return "暂停";
		}

	}
	$scope.dtColumns = [

			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('price').withTitle('价格').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {};
		if (angular.isDefined($scope.dlSel.class_id)
				&& angular.isDefined($scope.xlSel.class_id)) {
			ps.dl = $scope.dlSel.class_id;
			ps.xl = $scope.xlSel.class_id;
		} else {
			$scope.dtOptions.aaData = [];
		}
		$http.post($rootScope.project + "/api/mshop/queryProdByXl.do", ps)
				.success(function(res) {
					if (res.success) {
						$scope.dtOptions.aaData = res.data;
					}
				})
	}

	$scope.row_delete = function(id) {
		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/mshop/delProd.do", {
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

	$scope.row_dtl = function(id) {

	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {
		var ps = {}
		ps.id = id;
		ps.dlOpt = $scope.dlOpt;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/mshop/prod/modal_prodsave.html',
			controller : saveprodqueryCtl,
			size : 'md',
			resolve : { // 调用控制器与modal控制器中传递值
				data : function() {
					return ps
				}
			}
		});

		modalInstance.result.then(function(result) {
			flush();
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}

};

app.register.controller('prodqueryCtl', prodqueryCtl);