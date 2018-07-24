function roleSaveCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, id, $http, $rootScope) {

	$scope.statusOpt = [ {
		id : "Y",
		name : "有效"
	}, {
		id : "N",
		name : "无效"
	} ]

	$scope.statusSel = $scope.statusOpt[0];
	$scope.item = {};
	if (angular.isDefined(id)) {
		// 修改
		$http.post($rootScope.project + "/api/sysRoleInfo/selectById.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data
				if ($scope.item.isAction == "Y") {
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
	} else {
		// 新增
	}

	$scope.sure = function() {

		$scope.item.isAction = $scope.statusSel.id;

		$http.post($rootScope.project + "/api/sysRoleInfo/insertOrUpdate.do", $scope.item).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
				notify({
					message : res.message
				});
			} else {
				notify({
					message : res.message
				});
			}
		})
	}
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}

function sysRoleSettingCtl( DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.meta ={
			tools : [  {
				id : "1",
				label : "新增",
				type : "btn",
				template:' <button ng-click="save()" class="btn btn-sm btn-primary" type="submit">新增</button>'
	 
			} ]
		}
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption('responsive',false).withOption('createdRow', function(row) {
		// Recompiling so we can bind Angular,directive to the
		$compile(angular.element(row).contents())($scope);
	});
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.roleId + "')\" class=\"btn-white btn btn-xs\">编辑</button> ";
		// acthtml = acthtml + " <button ng-click=\"row_detail()\"
		// class=\"btn-white btn btn-xs\">详细</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.roleId + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "无效";
		if (full.isAction == "Y") {
			res = "有效";
		}
		return res;
	}

	$scope.dtColumns = [ DTColumnBuilder.newColumn('roleName').withTitle('名称').withOption('sDefaultContent', '').withOption('width', '80'),
			DTColumnBuilder.newColumn('remark').withTitle('备注').withOption('sDefaultContent', '').withOption('width', '180'),
			DTColumnBuilder.newColumn('isAction').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('roleId').withTitle('操作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/sysRoleInfo/selectList.do", ps).success(function(res) {
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

	$scope.row_detail = function(id) {

	}
	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除功能?'
		}).then(function() {
			$http.post($rootScope.project + "/api/sysRoleInfo/deleteById.do", {
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

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/role/modal_role_save.html',
			controller : roleSaveCtl,
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

};

app.register.controller('sysRoleSettingCtl', sysRoleSettingCtl);