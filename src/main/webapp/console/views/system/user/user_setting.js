function userRoleAdjustFormCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, userIds, $http, $rootScope) {

	$log.warn("window in,", userIds);
	$scope.userRoles = []
	$http.post($rootScope.project + "/role/roleQuery.do", {}).success(function(res) {
		if (res.success) {
			$scope.userRoles = res.data
		}
	})

	$scope.roleSel = [];
	$scope.sure = function() {

		$log.warn(angular.toJson($scope.roleSel))
		if ($scope.roleSel.length == 0) {
			notify({
				message : "请至少选择一项角色"
			});
			return

						

		}
		var ps = {};
		ps.USER_IDS = userIds;
		ps.ROLES = angular.toJson($scope.roleSel);
		$http.post($rootScope.project + "/user/userRoleChange.do", ps).success(function(res) {
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
function userSaveFormCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, id, $http, $rootScope) {

	$scope.item = {}
	$scope.lockedOpt = [ {
		ID : "Y",
		NAME : "锁定"
	}, {
		ID : "N",
		NAME : "正常"
	} ]

	$scope.lockedSel = $scope.lockedOpt[0];

	if (!angular.isDefined(id)) {
		notify({
			message : "不存在ID"
		});
		$uibModalInstance.dismiss('cancel');
	} else {

		$http.post($rootScope.project + "/user/userQueryById.do", {
			user_id : id
		}).success(function(res) {
			$log.warn(res);
			if (res.success) {
				$scope.item = res.data;
				if (angular.isDefined(res.data.LOCKED)) {
					if (res.data.LOCKED == "Y") {
						$scope.lockedSel = $scope.lockedOpt[0];
					} else if (res.data.LOCKED == "N") {
						$scope.lockedSel = $scope.lockedOpt[1];
					}
				}

			} else {
				notify({
					message : res.message
				});
			}
		})

	}
	$scope.sure = function() {
		$scope.item.USER_ID = $scope.item.USER_NO;
		$scope.item.LOCKED = $scope.lockedSel.ID;
		$http.post($rootScope.project + "/user/userSave.do", $scope.item).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			} else {

			}
			notify({
				message : res.message
			});
		});

	}
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}

function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function sysUserSettingCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.userGroupOpt = [];
	$scope.userGroupSel = "";
	$http.post($rootScope.project + "/user/queryGroup.do", {}).success(function(res) {
		if (res.success) {

			$scope.userGroupOpt = prepend(res.data, {
				GROUP_ID : "ALL",
				NAME : "全部"
			});
			$scope.userGroupSel = $scope.userGroupOpt[0];
		} else {
			notify({
				message : res.message
			});
		}
	});

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang).withOption("select", {
				style : 'multi',
				selector : 'td:first-child'
			}).withButtons([ {
				text : '全选',
				key : '1',
				action : function(e, dt, node, config) {
					dt.rows().select();
				}
			}, {
				text : '全不选',
				key : '1',
				action : function(e, dt, node, config) {

					dt.rows().deselect();
				}
			} ]);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.USER_ID + "')\" class=\"btn-white btn btn-xs\">详细</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "正常";
		if (full.LOCKED == "Y") {
			res = "锁定";
		}
		return res;
	}
 
	function renderType(data, type, full) {
		if (data == "sys") {
			return "系统";
		} else if (data == "empl") {
			return "员工";
		} else {
			return data;
		}
	}
	$scope.dtColumns = [ DTColumnBuilder.newColumn(null).withTitle('').withClass('select-checkbox').renderWith(function() {
		return '';
	}), DTColumnBuilder.newColumn('EMPL_ID').withTitle('员工编号').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('USER_NAME').withTitle('登录名').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('NAME').withTitle('姓名').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('TEL').withTitle('手机号').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('TYPE').withTitle('用户类型').withOption('sDefaultContent', '').renderWith(renderType),
			DTColumnBuilder.newColumn('USER_ID').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderStatus) ]

	console.log($scope.dtColumns);
	function flush() {
		var ps = {}
		if ($scope.userGroupSel.GROUP_ID != "ALL") {
			ps.group_id = $scope.userGroupSel.GROUP_ID;
		}

		$http.post($rootScope.project + "/user/userQueryByGroup.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;

			}
		})
	}
	flush();

	$scope.row_dtl = function(id) {
		notify({
			message : "待开发"
		});
	}

	$scope.del = function() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];

		if (data.length == 0) {
			notify({
				message : "请至少选择一个用户"
			});
			return;
		}

		// 批量删除
		var userids = [];
		for (var i = 0; i < data.length; i++) {
			// alert($scope.dtOptions.aaData[data[i]].USER_NO)
			userids.push($scope.dtOptions.aaData[data[i]].USER_NO);
		}
		angular.toJson(userids)
		$confirm({
			text : '是否删除选中的用户?'
		}).then(function() {
			$http.post($rootScope.project + "/user/userDelete.do", {
				user_ids : angular.toJson(userids)
			}).success(function(res) {
				flush();
				notify({
					message : res.message
				});
			});
		});

	}

	$scope.update = function() {

		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];

		if (data.length == 0) {
			notify({
				message : "请至少选择一个用户"
			});
			return;
		}
		if (data.length > 1) {
			notify({
				message : "只能选择一个用户"
			});
			return;
		}

		$log.warn($scope.dtOptions.aaData[data[0]].USER_ID);

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/user/modal_user_save.html',
			controller : userSaveFormCtl,
			size : 'md',
			resolve : { // 调用控制器与modal控制器中传递值
				id : function() {
					return $scope.dtOptions.aaData[data[0]].USER_NO;
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

		console.log("select data", data.length);

	}

	$scope.updateRole = function() {

		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];

		if (data.length == 0) {
			notify({
				message : "请至少选择一个用户"
			});
			return;
		}

		var userids = [];
		for (var i = 0; i < data.length; i++) {
			// alert($scope.dtOptions.aaData[data[i]].USER_NO)
			userids.push($scope.dtOptions.aaData[data[i]].USER_NO);
		}

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/user/modal_user_role_save.html',
			controller : userRoleAdjustFormCtl,
			size : 'md',
			resolve : { // 调用控制器与modal控制器中传递值
				userIds : function() {
					return angular.toJson(userids);
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

	$scope.detail = function() {
		notify({
			message : "待开发"
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
};

app.register.controller('sysUserSettingCtl', sysUserSettingCtl);