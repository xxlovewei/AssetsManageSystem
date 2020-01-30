function dbinstanceSaveCtl($timeout, $localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, meta, $http, $rootScope) {

	$scope.item = {};
	// 
	// if (angular.isDefined(id)) {
	// // 加载数据
	// $http.post($rootScope.project + "/api/sysDict/selectById.do", {
	// id : id
	// }).success(function(res) {
	// if (res.success) {
	// $scope.item = res.data
	// // STATUS
	// if ($scope.item.status == "Y") {
	// $scope.statusSel = $scope.statusOpt[0];
	// } else if ($scope.item.status == "N") {
	// $scope.statusSel = $scope.statusOpt[1];
	// }
	// // DICT_LEVEL
	// if ($scope.item.dictLevel == "system") {
	// $scope.typeSel = $scope.typeOpt[0];
	// } else if ($scope.item.dictLevel == "biz") {
	// $scope.typeSel = $scope.typeOpt[1];
	// }
	// } else {
	// notify({
	// message : res.message
	// });
	// }
	// })
	// }

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	$timeout(function() {

		var modal = document.getElementsByClassName('modal-body');
		for (var i = 0; i < modal.length; i++) {
			var adom = modal[i].getElementsByClassName('chosen-container');

			for (var j = 0; j < adom.length; j++) {
				adom[i].style.width = "100%";
			}
		}
	}, 200);

	$scope.sure = function() {

	};

}

function opsdbbackupCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
		$log, notify, $scope, $http, $rootScope, $uibModal, $stateParams,
		$window) {

	var pbtns = $rootScope.curMemuBtns;

	var meta = {
		tablehide : false,
		toolsbtn : [
				{
					id : "btn",
					label : "",
					type : "btn",
					show : true,
					template : ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">搜索</button>'
				},
				{
					id : "btn2",
					label : "",
					type : "btn",
					show : false,
					priv : "insert",
					template : ' <button ng-click="save(0)" class="btn btn-sm btn-primary" type="submit">新增</button>'
				},
				{
					id : "btn2",
					label : "",
					type : "btn",
					show : false,
					priv : "update",
					template : ' <button ng-click="save(1)" class="btn btn-sm btn-primary" type="submit">更新</button>'
				},

				{
					id : "btn2",
					label : "",
					type : "btn",
					show : false,
					priv : "remove",
					template : ' <button ng-click="del()" class="btn btn-sm btn-primary" type="submit">删除</button>'
				},
				{
					id : "btn3",
					label : "",
					type : "btn",
					show : false,
					priv : "exportfile",
					template : ' <button ng-click="filedown()" class="btn btn-sm btn-primary" type="submit">全部导出(Excel)</button>'
				},
				{
					id : "btn4",
					label : "",
					type : "btn",
					show : false,
					priv : "importfile",
					template : ' <button ng-click="importfile()" class="btn btn-sm btn-primary" type="submit">导入数据</button>'
				} ],
		tools : [ {
			id : "input",
			show : true,
			label : "内容",
			placeholder : "输入型号、编号、序列号",
			type : "input",
			ct : ""
		} ]
	};

	$scope.meta = meta;
	privNormalCompute($scope.meta.toolsbtn, pbtns);

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(100)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", false).withOption('scrollY', '600px')
			.withOption('scrollX', true).withOption('bAutoWidth', true)
			.withOption('scrollCollapse', true).withOption('paging', false)
			.withFixedColumns({
				leftColumns : 0,
				rightColumns : 0
			}).withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('rowCallback',
					rowCallback).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withOption("select", {
				style : 'single'
			});

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
	var gid = ""
	function someClickHandler(data) {
		gid = data.id;
		flushSubtab(data.id);
	}
	$scope.dtColumns = [ DTColumnBuilder.newColumn('dbname').withTitle('系统')
			.withOption('sDefaultContent', '') ]

	function flush() {
		var ps = {};
		$http.post(
				$rootScope.project
						+ "/api/ops/opsNode/Ext/selectDBListSimple.do", ps)
				.success(function(res) {
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
	$scope.add = function() {
		// save()
	}

	// $scope.update = function() {
	// var id = getSelectId();
	// if (angular.isDefined(id)) {
	//
	// } else {
	// notify({
	// message : "请选择一行"
	// });
	// }
	//
	// }

	function getSelectId() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		// 没有选择,或选择多行都返回错误
		if (data.length == 0 || data.length > 1) {
			return;
		} else {
			return $scope.dtOptions.aaData[data[0]];
		}
	}

	/** ********************子表******************* */

	$scope.dtItemOptions = DTOptionsBuilder.fromFnPromise()
			.withDataProp('data').withPaginationType('full_numbers')
			.withDisplayLength(100).withOption("ordering", false).withOption(
					"responsive", false).withOption("searching", false)
			.withOption('scrollY', '600px').withOption('scrollX', true)
			.withOption('bAutoWidth', true).withOption('scrollCollapse', true)
			.withOption('paging', false).withFixedColumns({
				leftColumns : 0,
				rightColumns : 0
			}).withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('createdRow',
					function(row) {
						// Recompiling so we can bind Angular,directive to the
						$compile(angular.element(row).contents())($scope);
					});
	$scope.dtItemInstance = {}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		console.log($scope.crud.item_update);
		if ($scope.crud.item_update) {
			acthtml = acthtml + " <button ng-click=\"row_update('"
					+ full.dictItemId
					+ "')\" class=\"btn-white btn btn-xs\">更新</button>   ";
		}
		if ($scope.crud.item_remove) {
			acthtml = acthtml + " <button ng-click=\"row_dtl('"
					+ full.dictItemId
					+ "')\" class=\"btn-white btn btn-xs\">删除</button> ";
		}
		acthtml = acthtml + "</div>"
		return acthtml;
	}

	$scope.dtItemColumns = [
			DTColumnBuilder.newColumn('xtname').withTitle('系统').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('ip').withTitle('IP').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('sysdbdtlstr').withTitle('数据库')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('dbinstance').withTitle('数据库名称')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('dbbkstatusstr').withTitle('当前状况')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('dbbktypestr').withTitle('备份类型')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('dbbkmethodstr').withTitle('备份方式')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('dbbkarchtypestr').withTitle('日志模式')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('bkstrategy').withTitle('备份策略')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('bkkeep').withTitle('保留策略').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', '') ]
	function flushSubtab(id) {
		console.log(id);
		var ps = {
			nodeid : id
		};
		// id不存在,则尝试从select中获取
		if (!angular.isDefined(id)) {
			var node = getSelectId();
			ps.nodeid = node.id;
		}
		// 如果还是不存在则报错
		if (!angular.isDefined(ps.nodeid)) {
			notify({
				message : "ID不存在"
			});
			return;
		}

		$http.post($rootScope.project + "/api/ops/opsNode/Ext/selectDBList.do",
				ps).success(function(res) {
			if (res.success) {
				$scope.dtItemOptions.aaData = res.data;
			} else {
				notify({
					message : res.message
				});
			}
		})

	}
	$scope.query = function() {
		$http.post($rootScope.project + "/api/ops/opsNode/Ext/selectDBList.do",
				{}).success(function(res) {
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

	$scope.save = function(type) {

		var ps = {};
		if (type == "1") {
			// 更新
			// ps.id=

		} else {
			var node = getSelectId();
			if (!angular.isDefined(node)) {
				notify({
					message : "请先选择系统"
				});
				return;
			}

			console.log(node);
			ps.nodeid = node.id;
		}
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/ops/modal_dbinstanceSave.html',
			controller : dbinstanceSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				meta : function() {
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
			$http.post($rootScope.project + "/api/sysDictItem/deleteById.do", {
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

	$scope.filedown = function() {
		var ps = {}

		$window.open($rootScope.project
				+ "/api/ops/opsNode/Ext/selectDBListExport.do");
	}

};

app.register.controller('opsdbbackupCtl', opsdbbackupCtl);