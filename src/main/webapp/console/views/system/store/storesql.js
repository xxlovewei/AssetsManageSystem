function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function sysStoreSqlSaveCtl(notify, $log, $uibModal, $uibModalInstance, $scope, id, $http, $rootScope, $timeout) {
	$scope.usedOpt = [ {
		id : "Y",
		name : "正常"
	}, {
		id : "N",
		name : "停用"
	} ];
	$scope.usedSel = $scope.usedOpt[0];

	$scope.aclOpt = [ {
		id : "public",
		name : "公共"
	}, {
		id : "user",
		name : "用户"
	}, {
		id : "system",
		name : "系统"
	} ];
	$scope.aclSel = $scope.aclOpt[0];

	$scope.returnOpt = [ {
		id : "array",
		name : "数组"
	}, {
		id : "object",
		name : "对象"
	}, {
		id : "action",
		name : "行为"
	} ];
	$scope.returnSel = $scope.returnOpt[0];

	if (angular.isDefined(id)) {
		// 加载数据
		$http.post($rootScope.project + "/api//store/queryStoreSqlById.do", {
			store_id : id
		}).success(function(res) {

			if (res.success) {
				$scope.item = res.data;

				if ($scope.item.IS_USED == "Y") {
					$scope.usedSel = $scope.usedOpt[0];
				} else if ($scope.item.IS_USED == "N") {
					$scope.usedSel = $scope.usedOpt[1];
				}

				if (res.data.ACL == "public") {
					$scope.aclSel = $scope.aclOpt[0];
				} else if (res.data.ACL == "user") {
					$scope.aclSel = $scope.aclOpt[1];
				} else if (res.data.ACL == "system") {
					$scope.aclSel = $scope.aclOpt[2];
				}

				if (res.data.RETURN_TYPE == "array") {
					$scope.returnSel = $scope.returnOpt[0];
				} else if (res.data.RETURN_TYPE == "object") {
					$scope.returnSel = $scope.returnOpt[1];
				} else if (res.data.RETURN_TYPE == "action") {
					$scope.returnSel = $scope.returnOpt[2];
				}

			} else {
				notify({
					message : res.message
				});
			}

		})
	}

	$scope.sure = function() {
		$scope.item.IS_USED = $scope.usedSel.id;
		$scope.item.ACL = $scope.aclSel.id;
		$scope.item.RETURN_TYPE = $scope.returnSel.id;
		$http.post($rootScope.project + "/api/store/saveStoreSql.do", $scope.item).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			} else {
				notify({
					message : res.message
				});
			}
		})
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}
function sysStoreSqlCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.STORE_ID + "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.STORE_ID + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		if (data == "Y") {
			return "正常";
		} else if (data == "N") {
			return "停用";
		} else {
			return data;
		}
	}
	function renderACL(data, type, full) {
		if (data == "public") {
			return "公共";
		} else if (data == "user") {
			return "用户";
		} else if (data == "system") {
			return "系统";
		} else {
			return data;
		}
	}

	function renderRT(data, type, full) {
		if (data == "action") {
			return "行为";
		} else if (data == "array") {
			return "数组";
		} else if (data == "object") {
			return "对象";
		} else {
			return data;
		}
	}
	$scope.dtColumns = [

	DTColumnBuilder.newColumn('STORE_ID').withTitle('ID').withOption('sDefaultContent', ''), DTColumnBuilder.newColumn('NAME').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('ACL').withTitle('访问类型').withOption('sDefaultContent', '').renderWith(renderACL),
			DTColumnBuilder.newColumn('RETURN_TYPE').withTitle('返回类型').withOption('sDefaultContent', '').renderWith(renderRT),
			DTColumnBuilder.newColumn('IS_USED').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('SQL').withTitle('SQL文本').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('USER_ID').withTitle('动作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/store/queryStoreSql.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})
	}
	flush();
	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/store/deleteStoreSql.do", {
				store_id : id
			}).success(function(res) {
				flush();
			})
		});

	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/store/modal_storesqlSave.html',
			controller : sysStoreSqlSaveCtl,
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

app.register.controller('sysStoreSqlCtl', sysStoreSqlCtl);