function modalzclySaveCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal,
		$uibModalInstance) {

	var acttype = 'LY';
	$scope.data = {};
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(100)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", true).withOption('scrollY', '200px')
			.withOption('scrollX', true).withOption('bAutoWidth', true)
			.withOption('scrollCollapse', true).withOption('paging', true)
			.withFixedColumns({
				leftColumns : 0,
				rightColumns : 0
			}).withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('aaData',
					$scope.tabdata).withOption('createdRow', function(row) {
				$compile(angular.element(row).contents())($scope);
			});
	$scope.dtColumns = [

			DTColumnBuilder.newColumn('uuid').withTitle('编号').withOption(
					'sDefaultContent', '').withOption("width", '30'),
			DTColumnBuilder.newColumn('classname').withTitle('类型').withOption(
					'sDefaultContent', '').withOption("width", '30'),
			DTColumnBuilder.newColumn('brandstr').withTitle('品牌').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('name').withTitle('型号').withOption(
					'sDefaultContent', '').withOption('width', '50')
					.renderWith(renderName),
			DTColumnBuilder.newColumn('locstr').withTitle('位置').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('part_name').withTitle('部门').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('used_username').withTitle('使用人')
					.withOption('sDefaultContent', '')
					.withOption('width', '30'),
			DTColumnBuilder.newColumn('recyclestr').withTitle('资产状态')
					.withOption('sDefaultContent', '')
					.withOption('width', '30'),
			DTColumnBuilder.newColumn('confdesc').withTitle('配置描述').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('sn').withTitle('序列号').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('buy_timestr').withTitle('采购时间')
					.withOption('sDefaultContent', '') ]

	$scope.dtOptions.aaData = [];
	$scope.selectzc = function() {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_devfault_zclist.html',
			controller : modal_faultZcListCtl,
			size : 'blg',
			resolve : { // 调用控制器与modal控制器中传递值
				id : function() {
					return ""
				},
				type : function() {
					return "many"
				}
			}
		});

		modalInstance.result.then(function(result) {
			console.log(result);
			$scope.dtOptions.aaData = result;
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}

	$scope.sure = function() {

		$scope.data.type = acttype;

		if ($scope.dtOptions.aaData.length == 0) {
			alert("请先选择资产");
			return 

			

						

			

									

			

						

			

												

			

						

			

									

			

						

			

		}
		$scope.data.items = angular.toJson($scope.dtOptions.aaData);
		$http.post($rootScope.project + "/api/cmdb/resActionExt/insert.do",
				$scope.data).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
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

function zclyghCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log,
		notify, $scope, $http, $rootScope, $uibModal, $window, $state) {

	var pbtns = $rootScope.curMemuBtns;

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withDOM('frtlip').withPaginationType('full_numbers')
			.withDisplayLength(100).withOption("ordering", false).withOption(
					"responsive", false).withOption("searching", false)
			.withOption('scrollY', '600px').withOption('scrollX', true)
			.withOption('bAutoWidth', true).withOption('scrollCollapse', true)
			.withOption('paging', true).withFixedColumns({
				leftColumns : 0,
				rightColumns : 0
			}).withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('aaData',
					$scope.tabdata).withOption('createdRow', function(row) {
				$compile(angular.element(row).contents())($scope);
			}).withOption(
					'headerCallback',
					function(header) {
						if ((!angular.isDefined($scope.headerCompiled))
								|| $scope.headerCompiled) {
							$scope.headerCompiled = true;
							$compile(angular.element(header).contents())
									($scope);
						}
					}).withOption("select", {
				style : 'multi',
				selector : 'td:first-child'
			}).withButtons([ {
				extend : 'colvis',
				text : '显示隐藏列',
				fnLabel : function(dt, idx, title) {
					console.log(dt, idx, title);
					return (idx + 1) + ': ' + title;
				}
			}, {
				extend : 'csv',
				text : 'Excel(当前页)',
				exportOptions : {
					columns : ':visible',
					trim : true,
					modifier : {
						page : 'current'
					}
				}
			}, {
				extend : 'print',
				text : '打印(当前页)',
				exportOptions : {
					columns : ':visible',
					stripHtml : false,
					columns : ':visible',
					modifier : {
						page : 'current'
					}
				}
			} ]);

	function stateChange(iColumn, bVisible) {
		console.log('The column', iColumn, ' has changed its status to',
				bVisible);
	}
	$scope.dtInstance = {}
	$scope.selectCheckBoxAll = function(selected) {
		if (selected) {
			$scope.dtInstance.DataTable.rows().select();
			console.log($scope.dtInstance.DataTable)
			console.log($scope.dtInstance);
		} else {
			$scope.dtInstance.DataTable.rows().deselect();
			console.log($scope.dtInstance.DataTable)
			console.log($scope.dtInstance);
		}
	}

	function renderStatus(data, type, full) {
		var html = "未知"
		if (angular.isDefined(data)) {
			if (data == "submitforapproval") {
				return "待送审"
			} else if (data = "inreview") {
				return "审批中"
			} else if (data = "approvalsuccess") {
				return "审批通过"
			} else if (data = "approvalfailed") {
				return "审批不通过"
			}
		}
		return html;
	}

	var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
	$scope.dtColumns = [];
	$scope.dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml)
			.withClass('select-checkbox checkbox_center').renderWith(
					function() {
						return ""
					}));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('编号')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('spstatus').withTitle('状态')
			.withOption('sDefaultContent', '').renderWith(renderStatus));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('actusername').withTitle(
			'领用人').withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('operusername').withTitle(
			'操作人').withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('backtimestr').withTitle(
			'退回时间').withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('ct').withTitle('原因')
			.withOption('sDefaultContent', ''));
	// $scope.dtColumns.push(DTColumnBuilder.newColumn('mark').withTitle('备注')
	// .withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('total').withTitle('总数量')
			.withOption('sDefaultContent', ''));

	$scope.query = function() {
		flush();
	}

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
					template : ' <button ng-click="save(0)" class="btn btn-sm btn-primary" type="submit">领用</button>'
				},
				// {
				// id : "btn2",
				// label : "",
				// type : "btn",
				// show : false,
				// priv : "update",
				// template : ' <button ng-click="save(1)" class="btn btn-sm
				// btn-primary" type="submit">更新</button>'
				// },
				{
					id : "btn2",
					label : "",
					type : "btn",
					show : true,
					template : ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详情</button>'
				},
				{
					id : "btn2",
					label : "",
					type : "btn",
					show : false,
					priv : "remove",
					template : ' <button ng-click="del()" class="btn btn-sm btn-primary" type="submit">删除</button>'
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

	function flush() {
		var ps = {}

		ps.search = $scope.meta.tools[0].ct;
		ps.type = 'LY';
		$http.post($rootScope.project + "/api/cmdb/resActionExt/selectList.do",
				ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			} else {
				notify({
					message : res.message
				});
			}
		})

	}

	function getSelectRows() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		if (data.length == 0) {
			notify({
				message : "请至少选择一项"
			});
			return;
		} else if (data.length > 1000) {
			notify({
				message : "不允许超过1000个"
			});
			return;
		} else {
			var res = [];
			console.log("sel:", data);
			for (var i = 0; i < data.length; i++) {
				res.push($scope.dtOptions.aaData[data[i]].id)
			}
			return angular.toJson(res);
		}
	}

	$scope.detail = function() {
		var id = "";
		var selrow = getSelectRow();
		if (angular.isDefined(selrow)) {
			id = selrow.id;
		} else {
			return;
		}
		var ps = {};
		ps.id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_dtl.html',
			controller : modalcmdbdtlCtl,
			size : 'blg',
			resolve : { // 调用控制器与modal控制器中传递值
				meta : function() {
					return ps;
				}
			}
		});

		modalInstance.result.then(function(result) {
			$log.log("result", result);
			if (result == "OK") {
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}

	function getSelectRow() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		if (data.length == 0) {
			notify({
				message : "请至少选择一项"
			});
			return;
		} else if (data.length > 1) {
			notify({
				message : "请最多选择一项"
			});
			return;
		} else {
			console.log("sel:", data);
			return $scope.dtOptions.aaData[data[0]];
		}
	}

	$scope.del = function() {

		var selrows = getSelectRow();
		if (angular.isDefined(selrows)) {
			$confirm({
				text : '是否删除?'
			})
					.then(
							function() {
								$http
										.post(
												$rootScope.project
														+ "/api/cmdb/resActionExt/removeById.do",
												{
													id : selrows.id
												}).success(function(res) {
											if (res.success) {
												flush();
											} else {
												notify({
													message : res.message
												});
											}
										});
							});
		}
	}

	// //////////////////////////save/////////////////////
	$scope.save = function(id) {
		var ps = {};
		ps.id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_zcly.html',
			controller : modalzclySaveCtl,
			size : 'blg',
			resolve : { // 调用控制器与modal控制器中传递值
				meta : function() {
					return ps;
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

app.register.controller('zclyghCtl', zclyghCtl);