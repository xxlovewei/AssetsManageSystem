function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function saveproddlCtl($log, $http, $rootScope, $scope, $uibModalInstance,
		data, notify) {
	$scope.item = {};
	$scope.item.is_used = "Y";
	$scope.item.name = "";

	if (angular.isDefined(data.class_id)) {
		$http.post($rootScope.project + "/api/prod/queryDlById.do", {
			id : data.class_id
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

		$http.post($rootScope.project + "/api/prod/saveDl.do", $scope.item)
				.success(function(res) {
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

function proddlCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

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

		acthtml = acthtml + " <button ng-click=\"save('" + full.class_id
				+ "')\" class=\"btn-white btn btn-xs\">更新</button>  ";

		acthtml = acthtml + " <button ng-click=\"row_delete('" + full.class_id
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
			DTColumnBuilder.newColumn('od').withTitle('顺序').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('class_id').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {

		$http.post($rootScope.project + "/api/prod/queryDl.do", {}).success(
				function(res) {
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
			$http.post($rootScope.project + "/api/prod/delDl.do", {
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
		ps.class_id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/mshop/prod/modal_savedl.html',
			controller : saveproddlCtl,
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

app.register.controller('proddlCtl', proddlCtl);