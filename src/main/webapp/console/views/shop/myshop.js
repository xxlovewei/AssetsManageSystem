  

function myshopListCtl( DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.meta ={
			tools : [  ]
		} 
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption('createdRow', function(row) {
		// Recompiling so we can bind Angular,directive to the
		$compile(angular.element(row).contents())($scope);
	});
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

		acthtml = acthtml + " <button ng-click=\"save('" + full.shop_id + "')\" class=\"btn-white btn btn-xs\">更新</button>  ";

		 
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
		$http.post($rootScope.project + "/api/shop/queryMyShop.do", {}).success(function(res) {
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
	 

};

app.register.controller('myshopListCtl', myshopListCtl);