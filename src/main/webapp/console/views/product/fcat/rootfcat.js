function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function prodrootfCatSaveCtl($uibModalInstance, $compile, $confirm, $log,
		notify, $scope, $http, $rootScope, $uibModal, data) {

	$log.info("window in", data);

	$scope.is_code_show=true;
	$scope.item = {};
	$scope.item.MARK = "";
	$scope.item.CODE = "";
	$scope.item.TEXT = ""

	$scope.statusOpt = [ {
		ID : "Y",
		NAME : "使用中"
	}, {
		ID : "N",
		NAME : "停用"
	} ]
	$scope.statusSel = $scope.statusOpt[0];

	// 如果已经存在ID,则查询数据
	if (angular.isDefined(data.ID)) {
		$scope.is_code_show=false;
		$http.post($rootScope.project + "/api/categoryF/rootCatQueryById.do", {
			id : data.ID
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data;
				if ($scope.item.IS_USED == "Y") {
					$scope.statusSel = $scope.statusOpt[0];
				} else {
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

		$scope.item.IS_USED = $scope.statusSel.ID;

		var cmd = "";
		if (angular.isDefined(data.ID)) {
			cmd = "/api/categoryF/rootCatUpdate.do";
			$scope.item.ID = data.ID;
		} else {
			cmd = "/api/categoryF/rootCatAdd.do";
		}

		//
		$scope.item.IS_USED = $scope.statusSel.ID;
		$http.post($rootScope.project + cmd, $scope.item).success(
				function(res) {
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

function prodrootfCatCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
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

		acthtml = acthtml + " <button ng-click=\"save('" + full.ID
				+ "')\" class=\"btn-white btn btn-xs\">更新</button>  ";

		// acthtml = acthtml + " <button ng-click=\"row_delete('" + full.ID
		// + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
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

			DTColumnBuilder.newColumn('CODE').withTitle('编码').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('TEXT').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('MARK').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('IS_USED').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('ID').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {

		$http.post($rootScope.project + "/api/categoryF/rootCatQuery.do", {})
				.success(function(res) {
					if (res.success) {
						$scope.dtOptions.aaData = res.data;
					}
				})
	}
	flush();

	$scope.row_dtl = function(id) {

	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {

		var ps = {};
		ps.ID = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/product/fcat/modal_rootfcat_save.html',
			controller : prodrootfCatSaveCtl,
			size : 'md',
			resolve : { // 调用控制器与modal控制器中传递值
				data : function() {
					return ps
				}
			}
		});

		modalInstance.result.then(function(result) {

			if (result == "OK") {
				flush();
			}

		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}

};

app.register.controller('prodrootfCatCtl', prodrootfCatCtl);