 

function prodrootfCatSaveCtl($uibModalInstance, $compile, $confirm, $log,
		notify, $scope, $http, $rootScope, $uibModal, data) {

	$log.info("window in", data);

	$scope.is_code_show=true;
	$scope.item = {};
	$scope.item.mark = "";
	$scope.item.code = "";
	$scope.item.text = ""

	$scope.statusOpt = [ {
		id : "Y",
		name : "使用中"
	}, {
		id : "N",
		name : "停用"
	} ]
	$scope.statusSel = $scope.statusOpt[0];

	// 如果已经存在ID,则查询数据
	if (angular.isDefined(data.id)) {
		$scope.is_code_show=false;
		$http.post($rootScope.project + "/api/categoryF/rootCatQueryById.do", {
			id : data.id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data;
				if ($scope.item.is_used == "Y") {
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

		$scope.item.is_used = $scope.statusSel.id;

		var cmd = "";
		if (angular.isDefined(data.id)) {
			cmd = "/api/categoryF/rootCatUpdate.do";
			$scope.item.ID = data.id;
		} else {
			cmd = "/api/categoryF/rootCatAdd.do";
		}

		//
		$scope.item.is_used = $scope.statusSel.id;
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

function prodrootfCatCtl( DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
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

		acthtml = acthtml + " <button ng-click=\"save('" + full.id
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

			DTColumnBuilder.newColumn('code').withTitle('编码').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('text').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_used').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
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
			size : 'lg',
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