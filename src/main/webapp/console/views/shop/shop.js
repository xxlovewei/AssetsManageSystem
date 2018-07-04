 
function shopSaveCtl($log, $http, $rootScope, $scope, $uibModalInstance, data, notify) {
	$scope.item = {};

	$scope.statusOpt = [ {
		id : "stop",
		name : "停业"
	}, {
		id : "normal",
		name : "正常"
	} ];
	$scope.statusSel = $scope.statusOpt[0];

	if (angular.isDefined(data.shop_id)) {
		$http.post($rootScope.project + "/api/shop/queryShopById.do", {
			shop_id : data.shop_id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data;
				if (res.data.status == "stop") {
					$scope.statusSel = $scope.statusOpt[0];
				} else if (res.data.status == "normal") {
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

		$scope.item.status = $scope.statusSel.id;
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

function shopListCtl( DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.meta ={
			tools : [  {
				id : "1",
				label : "新增",
				type : "btn",
				template:' <button ng-click="save()" class="btn btn-sm btn-primary" type="submit">新增</button>'
	 
			} ]
		}

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			});
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

		acthtml = acthtml + " <button ng-click=\"save('" + full.shop_id + "')\" class=\"btn-white btn btn-xs\">更新</button>  ";

		acthtml = acthtml + " <button ng-click=\"row_delete('" + full.shop_id + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
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

	DTColumnBuilder.newColumn('shop_name').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('status').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('brand_id').withTitle('操作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		$http.post($rootScope.project + "/api/shop/queryShop.do", {}).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}else{
				notify({
					message : res.message
				});
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
		ps.shop_id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/shop/modal_shop_save.html',
			controller : shopSaveCtl,
			size : 'lg',
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