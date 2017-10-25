function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function shopSaveCtl($log, $http, $rootScope, $scope, $uibModalInstance, data, notify) {
	$scope.item = {};

	$scope.statusOpt = [ {
		ID : "stop",
		NAME : "停业"
	}, {
		ID : "normal",
		NAME : "正常"
	} ];
	$scope.statusSel = $scope.statusOpt[0];

	if (angular.isDefined(data.SHOP_ID)) {
		$http.post($rootScope.project + "/api/shop/queryShopById.do", {
			shop_id : data.SHOP_ID
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data;
				if (res.data.STATUS == "stop") {
					$scope.statusSel = $scope.statusOpt[0];
				} else if (res.data.STATUS == "normal") {
					$scope.statusSel = $scope.statusOpt[1];
				}
			} else {
				notify({
					message : res.message
				});
			}
		})
	}

	$scope.sure = function() {

		$scope.item.STATUS = $scope.statusSel.ID;
		$http.post($rootScope.project + "/api/shop/saveShop.do", $scope.item).success(function(res) {
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

function shopListCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

		acthtml = acthtml + " <button ng-click=\"save('" + full.SHOP_ID + "')\" class=\"btn-white btn btn-xs\">更新</button>  ";

		acthtml = acthtml + " <button ng-click=\"row_delete('" + full.SHOP_ID + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;

	}

	function renderStatus(data, type, full) {

		if (data == "stop") {
			return "停业"
		} else if (data == "normal") {
			return "正常";
		} else {
			return data;
		}

	}
	$scope.dtColumns = [

	DTColumnBuilder.newColumn('SHOP_NAME').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('STATUS').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('MARK').withTitle('备注').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('BRAND_ID').withTitle('动作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		$http.post($rootScope.project + "/api/shop/queryShop.do", {}).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})
	}
	flush();

	$scope.row_delete = function(id) {
		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/shop/deleteShop.do", {
				shop_id : id
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
		ps.SHOP_ID = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/shop/modal_shop_save.html',
			controller : shopSaveCtl,
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

app.register.controller('shopListCtl', shopListCtl);