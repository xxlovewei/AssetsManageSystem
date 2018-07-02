function dictSaveCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, id, $http, $rootScope) {
	$log.warn("window in:" + id);
	$scope.item = {};
	$scope.typeOpt = [ {
		id : "system",
		name : "系统"
	}, {
		id : "biz",
		name : "业务"
	} ]
	$scope.typeSel = $scope.typeOpt[0];
	$scope.statusOpt = [ {
		id : "Y",
		name : "有效"
	}, {
		id : "N",
		name : "无效"
	} ]
	$scope.statusSel = $scope.statusOpt[0];
	if (angular.isDefined(id)) {
		// 加载数据
		$http.post($rootScope.project + "/api/dict/queryByDictId.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data
				// STATUS
				if ($scope.item.status == "Y") {
					$scope.statusSel = $scope.statusOpt[0];
				} else if ($scope.item.status == "N") {
					$scope.statusSel = $scope.statusOpt[1];
				}
				// DICT_LEVEL
				if ($scope.item.dict_level == "system") {
					$scope.typeSel = $scope.typeOpt[0];
				} else if ($scope.item.dict_level == "biz") {
					$scope.typeSel = $scope.typeOpt[1];
				}
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

	$scope.sure = function() {
		$scope.item.status = $scope.statusSel.id;
		$scope.item.dict_level = $scope.typeSel.id;
		$http.post($rootScope.project + "/api/dict/saveDict.do", $scope.item).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			} else {
				notify({
					message : res.message
				});
			}
		})
	};

}

//
// me.set("dict_id", ps.getString("DICT_ID"));
// me.set("dict_item_id", UuidUtil.getUUID());
// me.setIf("name", ps.getString("NAME"));
// me.setIf("sort", ps.getString("SORT"));
// me.setIf("mark", ps.getString("MARK"));

function dictItemSaveCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, data, $http, $rootScope) {
	$log.warn("window in:" + data);
	$scope.item = {};
	$scope.item.dict_id = data.dict_id
	if (angular.isDefined(data.dict_item_id)) {
		// 加载数据
		$http.post($rootScope.project + "/api/dict/queryDictItemById.do", {
			id : data.dict_item_id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data;
				$scope.item.dict_id = data.dict_id;
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

	$scope.sure = function() {
		$http.post($rootScope.project + "/api/dict/saveDictItem.do", $scope.item).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			} else {
				notify({
					message : res.message
				});
			}
		})
	};

}

function sysDictSettingCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('rowCallback', rowCallback).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withOption("select", {
				style : 'single'
			}).withLanguage(DTLang);

	$scope.dtInstance = {}

	function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
		// Unbind first in order to avoid any duplicate handler
		// (see https://github.com/l-lin/angular-datatables/issues/87)
		$('td', nRow).unbind('click');
		$('td', nRow).bind('click', function() {
			$scope.$apply(function() {
				someClickHandler(aData);
			});
		});
		return nRow;
	}
	function someClickHandler(data) {
		flushSubtab(data.dict_id);
	}

	function renderMType(data, type, full) {
		if (data == "system") {
			return "系统";
		} else if (data == "biz") {
			return "业务";
		} else {
			return data;
		}
	}

	function renderMStatus(data, type, full) {
		if (data == "Y") {
			return "有效";
		} else if (data == "N") {
			return "无效";
		} else {
			return data;
		}
	}

	$scope.dtColumns = [ DTColumnBuilder.newColumn('name').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('dict_level').withTitle('类型').withOption('sDefaultContent', '').renderWith(renderMType),
			DTColumnBuilder.newColumn('status').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderMStatus) ]

	function flush() {
		var ps = {};
		$http.post($rootScope.project + "/api/dict/queryDict.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
				$scope.dtItemOptions.aaData = [];
			} else {
				notify({
					message : res.message
				});
			}
		})
	}
	flush();

	function save(id) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/dict/modal_dictSave.html',
			controller : dictSaveCtl,
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
	$scope.add = function() {
		save()
	}
	$scope.del = function() {

		var id = getSelectId();
		if (!angular.isDefined(id)) {
			notify({
				message : "请选择一行"
			});
			return;
		}

		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/dict/deleteDict.do", {
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
	$scope.update = function() {
		var id = getSelectId();
		if (angular.isDefined(id)) {
			save(id)
		} else {
			notify({
				message : "请选择一行"
			});
		}

	}

	function getSelectId() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		// 没有选择,或选择多行都返回错误
		if (data.length == 0 || data.length > 1) {
			return;
		} else {
			return $scope.dtOptions.aaData[data[0]].dict_id;
		}
	}

	/** ********************子表******************* */

	$scope.dtItemOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtItemInstance = {}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"row_update('" + full.dict_item_id + "')\" class=\"btn-white btn btn-xs\">更新</button>   ";
		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.dict_item_id + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}

	$scope.dtItemColumns = [ DTColumnBuilder.newColumn('name').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('sort').withTitle('排序').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('dict_id').withTitle('操作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	function flushSubtab(id) {

		var ps = {
			id : id
		};

		// id不存在,则尝试从select中获取
		if (!angular.isDefined(id)) {
			ps.id = getSelectId();
		}
		// 如果还是不存在则报错
		if (!angular.isDefined(ps.id)) {
			notify({
				message : "ID不存在"
			});
			return;
		}

		$http.post($rootScope.project + "/api/dict/queryDictItem.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtItemOptions.aaData = res.data;
			} else {
				notify({
					message : res.message
				});
			}
		})

	}

	$scope.itemquery = function() {
		flushSubtab();
	}

	$scope.row_update = function(id) {

		var ps = {};
		ps.dict_item_id = id;
		ps.dict_id = getSelectId();
		if (!angular.isDefined(ps.dict_id)) {
			notify({
				message : "ID不存在"
			});
			return;
		}

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/dict/modal_dictItemSave.html',
			controller : dictItemSaveCtl,
			size : 'md',
			resolve : { // 调用控制器与modal控制器中传递值
				data : function() {
					return ps;
				}
			}
		});
		modalInstance.result.then(function(result) {
			$log.log("result", result);
			if (result == "OK") {
				flushSubtab();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});
	}
	$scope.row_dtl = function(id) {
		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/dict/deleteDictItem.do", {
				id : id
			}).success(function(res) {
				if (res.success) {
					flushSubtab();
				}
				notify({
					message : res.message
				});
			})
		});

	}
};

app.register.controller('sysDictSettingCtl', sysDictSettingCtl);