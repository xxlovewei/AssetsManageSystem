function userRoleAdjustFormCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, userIds, $http, $rootScope) {

	$log.warn("window in:", userIds);
	$scope.userRoles = []
	$http.post($rootScope.project + "/api/role/roleQueryFormatKV.do", {}).success(function(res) {
		if (res.success) {
			$scope.userRoles = res.data;
			// 如果只有一个用户,则加载他的权限信息
			console.log("user cnt" + angular.fromJson(userIds).length);
			if (angular.fromJson(userIds).length == 1) {
				$http.post($rootScope.project + "/api/user/queryRole.do", {
					user_id : angular.fromJson(userIds)[0]
				}).success(function(res) {
					if (res.success) {
						$scope.roleSel = res.data;
					}
				})
			}

		}
	})

	$scope.roleSel = [];
	$scope.sure = function() {

		$log.warn(angular.toJson($scope.roleSel))
		if ($scope.roleSel.length == 0) {
			notify({
				message : "请至少选择一项角色"
			});
			return;
		}
		var ps = {};
		ps.user_ids = userIds;
		ps.roles = angular.toJson($scope.roleSel);
		$http.post($rootScope.project + "/api/user/userRoleChange.do", ps).success(function(res) {
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
		id : "Y",
		name : "锁定"
	}, {
		id : "N",
		name : "正常"
	} ]

	$scope.lockedSel = $scope.lockedOpt[0];

	if (!angular.isDefined(id)) {
		notify({
			message : "不存在ID"
		});
		$uibModalInstance.dismiss('cancel');
	} else {

		$http.post($rootScope.project + "/api/user/userQueryById.do", {
			user_id : id
		}).success(function(res) {
			$log.warn(res);
			if (res.success) {
				$scope.item = res.data;
	
				if (angular.isDefined(res.data.locked)) {
					if (res.data.locked == "Y") {
						$scope.lockedSel = $scope.lockedOpt[0];
					} else if (res.data.locked == "N") {
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
		$scope.item.locked = $scope.lockedSel.id;
		$http.post($rootScope.project + "/api/user/userSave.do", $scope.item).success(function(res) {
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
 

function sysUserSettingCtl( DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.userGroupOpt = [];
	$scope.userGroupSel = "";
	$http.post($rootScope.project + "/api/sysUserGroup/selectList.do", {}).success(function(res) {
		if (res.success) {

			$scope.userGroupOpt = prepend(res.data, {
				groupId : "ALL",
				name : "全部"
			});
			$scope.userGroupSel = $scope.userGroupOpt[0];
		} else {
			notify({
				message : res.message
			});
		}
	});

	$scope.URL = $rootScope.project + "/api/user/userQueryByGroup.do";	
	$scope.dtOptions = DTOptionsBuilder.fromSource($scope.URL).withDataProp(
	'data').withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", true).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', true).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withOption("select", {
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
	$scope.reloadData = reloadData;
 
	
	function reloadData() {
		var resetPaging = true;
		$scope.dtInstance.reloadData(callback, resetPaging);
	}
	var tabdata=[];
	function callback(json) {
		tabdata=json.data;
		console.log("#####",json);

	}
	
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.user_id + "')\" class=\"btn-white btn btn-xs\">详细</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "正常";
		if (full.locked == "Y") {
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
	}), DTColumnBuilder.newColumn('empl_id').withTitle('员工编号').withOption('sDefaultContent', ''),
	DTColumnBuilder.newColumn('user_name').withTitle('登录名').withOption('sDefaultContent', ''),
	DTColumnBuilder.newColumn('name').withTitle('姓名').withOption('sDefaultContent', ''),
	DTColumnBuilder.newColumn('tel').withTitle('手机号').withOption('sDefaultContent', ''),
	DTColumnBuilder.newColumn('user_type').withTitle('用户类型').withOption('sDefaultContent', '').renderWith(renderType),
			DTColumnBuilder.newColumn('user_id').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderStatus) ]

	console.log($scope.dtColumns);
	function flush() {
		
		if ($scope.userGroupSel.group_id != "ALL") {
			$scope.URL = $rootScope.project
					+ "/api/user/userQueryByGroup.do?group_id=" + $scope.userGroupSel.group_id;
		} else {
			$scope.URL = $rootScope.project + "/api/user/userQueryByGroup.do";
		}
		$scope.dtOptions.ajax = $scope.URL;
		reloadData();
 
	}
 

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

		var d=$scope.dtInstance.DataTable.context[0].json.data;
		// 批量删除
		var userids = [];
		for (var i = 0; i < data.length; i++) {
			// alert($scope.dtOptions.aaData[data[i]].USER_NO)
			userids.push(d[data[i]].user_id);
		}
		console.log(angular.toJson(userids))
		$confirm({
			text : '是否删除选中的用户?'
		}).then(function() {
			$http.post($rootScope.project + "/api/user/userDelete.do", {
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
		console.log(data);

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
		
 
		var d=$scope.dtInstance.DataTable.context[0].json.data;
		$log.warn(d[data[0]].user_id);

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/user/modal_user_save.html',
			controller : userSaveFormCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				id : function() {
					return d[data[0]].user_id;
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
		var d=$scope.dtInstance.DataTable.context[0].json.data;
		var userids = [];
		for (var i = 0; i < data.length; i++) {
			// alert($scope.dtOptions.aaData[data[i]].user_no)
			userids.push(d[data[i]].user_id);
		}

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/user/modal_user_role_save.html',
			controller : userRoleAdjustFormCtl,
			size : 'lg',
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

app.register.controller('sysUserSettingCtl', sysUserSettingCtl);