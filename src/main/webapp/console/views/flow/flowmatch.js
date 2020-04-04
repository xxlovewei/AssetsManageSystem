function modalFormListSelCtl($timeout, $localStorage, notify, $log, $uibModal,
		$window, $uibModalInstance, $scope, $http, $rootScope,
		DTOptionsBuilder, DTColumnBuilder, $compile, data) {
	console.log(data)
	$scope.typeOpt = data.formType;
	$scope.typeSel = "";
	if ($scope.typeOpt.length > 0) {
		$scope.typeSel = $scope.typeOpt[0];
	}

	// 分类
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(100)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", true).withOption('scrollY', '200px')
			.withOption('scrollX', true).withOption('bAutoWidth', true)
			.withOption('scrollCollapse', true).withOption('paging', true)
			.withOption('bStateSave', true).withOption('bProcessing', false)
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
			})

	$scope.dtInstance = {}

	function renderAction(data, type, full) {

		var html = "";
		return html;

	}

	$scope.selectCheckBoxAll = function(selected) {

		if (selected) {
			$scope.dtInstance.DataTable.rows().select();
		} else {
			$scope.dtInstance.DataTable.rows().deselect();
		}
	}

	var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';

	$scope.dtColumns = [
			DTColumnBuilder.newColumn(null).withTitle("").withClass(
					'select-checkbox checkbox_center').renderWith(function() {
				return ""
			}),
			DTColumnBuilder.newColumn('id').withTitle('编号').withOption(
					'sDefaultContent', '').withOption("width", '30'),

			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', '').withOption("width", '30')

	]

	function flush() {
		var ps = {};
		ps.owner = $scope.typeSel.id;
		$http.post(
				$rootScope.project + "/api/form/sysForm/Ext/selectByOwner.do",
				ps).success(function(res) {
			$scope.dtOptions.aaData = res.data;

		})
	}
	$scope.query = function() {
		flush()
	}
	flush();

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

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

			return $scope.dtOptions.aaData[data[0]];
		}
	}

	$scope.review = function() {
		var obj = getSelectRow();

		if (angular.isDefined(obj)) {
			$window
					.open($rootScope.project
							+ "/console/views/system/form/formdesign.html?id="
							+ obj.id)
		}

	}

	$scope.sure = function() {
		var obj = getSelectRow();
		if (angular.isDefined(obj)) {
			$uibModalInstance.close(obj);
		}

	}
}
function modalFlowListSelCtl($timeout, $localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, $http, $rootScope, DTOptionsBuilder,
		DTColumnBuilder, $compile) {

	// 分类
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
			})

	$scope.dtInstance = {}

	function renderName(data, type, full) {

		var html = full.model;
		return html;

	}

	function renderJg(data, type, full) {

		var html = full.rackstr + "-" + full.frame;
		return html;

	}
	$scope.selectCheckBoxAll = function(selected) {

		if (selected) {
			$scope.dtInstance.DataTable.rows().select();
		} else {
			$scope.dtInstance.DataTable.rows().deselect();
		}
	}

	var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';

	$scope.dtColumns = [
			DTColumnBuilder.newColumn(null).withTitle("").withClass(
					'select-checkbox checkbox_center').renderWith(function() {
				return ""
			}),
			DTColumnBuilder.newColumn('id').withTitle('编号').withOption(
					'sDefaultContent', '').withOption("width", '30'),
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', '').withOption("width", '30'),
			DTColumnBuilder.newColumn('key').withTitle('关键字').withOption(
					'sDefaultContent', '').withOption("width", '30'),
			DTColumnBuilder.newColumn('version').withTitle('版本').withOption(
					'sDefaultContent', '').withOption("width", '30'),
			DTColumnBuilder.newColumn('createDate').withTitle('创建时间')
					.withOption('sDefaultContent', '')
					.withOption('width', '30') ]

	function flush() {
		var ps = {}
		ps.pageIndex=1;
		ps.pageSize=100;
		$http.post($rootScope.project + "uflo/central/loadProcess", ps)
				.success(function(res) {
					$scope.dtOptions.aaData = res.result;

				})
	}
	$scope.query = function() {
		flush()
	}
	flush();

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

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

			return $scope.dtOptions.aaData[data[0]];
		}
	}

	$scope.sure = function() {
		var id = getSelectRow();
		if (angular.isDefined(id)) {
			$uibModalInstance.close(id);
		}

	}
}

function modalflowmatchsaveCtl($timeout, $localStorage, notify, $log,
		$uibModal, $uibModalInstance, $scope, meta, $http, $rootScope, $compile) {

	$scope.data = {};

	$scope.statusOpt = [ {
		id : "normal",
		name : "正常"
	}, {
		id : "stop",
		name : "停用"
	} ];
	$scope.statusSel = $scope.statusOpt[0];

	$scope.catOpt = [ {
		id : "form",
		name : "表单模式"
	}, {
		id : "withoutform",
		name : "无表单"
	} ];
	$scope.catSel = $scope.catOpt[0];

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	var formtype = [];
	$http.post($rootScope.project + "/api/ctCategory/Ext/selectListByRoot.do",
			{
				root : "6"
			}).success(function(res) {
		if (res.success) {
			formtype = res.data;
		} else {
			notify({
				message : res.message
			});
		}
	});

	if (angular.isDefined(meta.id)) {

		$http.post(
				$rootScope.project + "/api/flow/sysProcessDef/selectById.do", {
					id : meta.id
				}).success(function(res) {
			if (res.success) {
				$scope.data = res.data

				if ($scope.data.status == "normal") {
					$scope.statusSel = $scope.statusOpt[0];
				}
				if ($scope.data.status == "stop") {
					$scope.statusSel = $scope.statusOpt[1];
				}
				
				if ($scope.data.type == "form") {
					$scope.catSel = $scope.catOpt[0];
				}
				if ($scope.data.type == "withoutform") {
					$scope.catSel = $scope.catOpt[1];
				}
				
				
				
			 
				
			} else {
				notify({
					message : res.message
				});
			}
		});

	} else {
		$scope.data.cid = meta.cid;
	}
	$scope.selectprocess = function() {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/flow/modal_flowlist.html',
			controller : modalFlowListSelCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值

			}
		});

		modalInstance.result.then(function(result) {
			$log.log("result", result);
			$scope.data.ptplname = result.name;
			$scope.data.ptplkey = result.key;
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}
	$scope.selectform = function() {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/flow/modal_formSelect.html',
			controller : modalFormListSelCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				data : {
					formType : formtype
				}
			}
		});

		modalInstance.result.then(function(result) {
			$log.log("result", result);
			$scope.data.form = result.id;

		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}

	$scope.sure = function() {
		if (!angular.isDefined($scope.data.ptplkey)) {
			alert("请选择流程");
			return;
		}
		$scope.data.status = $scope.statusSel.id;
		$scope.data.type = $scope.catSel.id;
		$scope.data.owner = meta.node;
		$http.post(
				$rootScope.project
						+ "/api/flow/sysProcessDef/insertOrUpdate.do",
				$scope.data).success(function(res) {
			if (res.success) {
				$uibModalInstance.close('OK');
			} else {
				notify({
					message : res.message
				});
			}
		});
	}

}

function sysFlowMatchCtl($window, $stateParams, DTOptionsBuilder,
		DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http,
		$rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withDOM('frtlip').withPaginationType('simple').withDisplayLength(
					50).withOption("ordering", false).withOption("responsive",
					false).withOption("searching", false).withOption('scrollY',
					'300px').withOption('scrollX', true).withOption(
					'bAutoWidth', true).withOption('scrollCollapse', true)
			.withOption('paging', false).withFixedColumns({
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
			});

	function stateChange(iColumn, bVisible) {

	}
	$scope.dtInstance = {}
	$scope.selectCheckBoxAll = function(selected) {
		if (selected) {
			$scope.dtInstance.DataTable.rows().select();

		} else {
			$scope.dtInstance.DataTable.rows().deselect();

		}
	}

	function renderStatus(data, type, full) {
		var res = "";
		if (data == "normal") {
			res = "正常";
		} else if (data == "stop") {
			res = "停用";
		} else {
			res = data;
		}
		return res;
	}
	function renderType(data, type, full) {
		var res = "";
		if (data == "form") {
			res = "表单模式";
		} else if (data == "withoutform") {
			res = "无表单模式";
		} else {
			res = data;
		}
		return res;
	}
	var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
	$scope.dtColumns = [];
	$scope.dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml)
			.withClass('select-checkbox checkbox_center').renderWith(
					function() {
						return ""
					}));

	$scope.dtColumns.push(DTColumnBuilder.newColumn('name').withTitle('名称')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('type').withTitle('类型')
			.withOption('sDefaultContent', '').renderWith(renderType)),
	$scope.dtColumns.push(DTColumnBuilder.newColumn('status').withTitle('状态')
			.withOption('sDefaultContent', '').renderWith(renderStatus)),
			$scope.dtColumns.push(DTColumnBuilder.newColumn('ptplkey')
					.withTitle('流程模版').withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('form').withTitle('表单')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('mark').withTitle('备注')
			.withOption('sDefaultContent', ''));

	$scope.catRootOpt = [];
	$scope.catRootSel = "";
	$scope.item = {};
	var ps = {};
	ps.ids = angular.toJson([ 5 ]);
	$http
			.post($rootScope.project + "/api/ctCategoryRoot/Ext/selectList.do",
					ps).success(function(res) {
				if (res.success) {
					$scope.catRootOpt = res.data;
					if ($scope.catRootOpt.length > 0) {
						$scope.catRootSel = $scope.catRootOpt[0];
						flushTree($scope.catRootSel.id)
					}
				} else {
					notify({
						message : res.message
					});
				}
			});
	// 树配置
	$scope.treeConfig = {
		core : {
			multiple : false,
			animation : true,
			error : function(error) {
				$log.error('treeCtrl: error from js tree - '
						+ angular.toJson(error));
			},
			check_callback : true,
			worker : true
		},
		loading : "加载中……",
		ui : {
			theme_name : "classic" // 设置皮肤样式
		},
		rules : {
			type_attr : "rel", // 设置节点类型
			valid_children : "root" // 只有root节点才能作为顶级结点
		},
		callback : {
			onopen : function(node, tree_obj) {
				return true;
			}
		},
		types : {
			"default" : {
				icon : 'glyphicon glyphicon-th'
			},
			root : {
				icon : 'glyphicon glyphicon-home'
			},
			"node" : {
				"icon" : "glyphicon glyphicon-tag"
			},
			"category" : {
				"icon" : "glyphicon glyphicon-equalizer"
			}
		},
		version : 1,
		plugins : [ 'themes', 'types', 'contextmenu', 'changed' ],
		contextmenu : {
			items : {}
		}
	}

	$scope.addNewNode = function() {
		$scope.treeData.push({
			id : (newId++).toString(),
			parent : $scope.newNode.parent,
			text : $scope.newNode.text
		});
	};

	$scope.modelChanges = function(t) {
		return true;
	}

	$scope.test = function() {
		$log.info("测试");

	}

	$scope.curSelNode = "";
	$scope.readyCB = function() {

		$scope.tree = $scope.treeInstance.jstree(true)
		// 展开所有节点
		$scope.tree.open_all();
		// 响应节点变化
		$scope.treeInstance.on("changed.jstree", function(e, data) {

			if (data.action == "select_node") {
				// 加载数据
				var snodes = $scope.tree.get_selected();
				if (snodes.length == 1) {
					var node = snodes[0];
					$scope.curSelNode = node;

					flush();
				}
			}
		});
	}
	$scope.cc = function() {

	}
	$scope.createCB = function(e, item) {

	};
	function flushTree(id) {
		$http
				.post(
						$rootScope.project
								+ "/api/ctCategroy/queryCategoryTreeList.do", {
							root : id
						}).success(function(res) {
					if (res.success) {
						$scope.ignoreChanges = true;
						$scope.treeData = angular.copy(res.data);
						$scope.treeConfig.version++;
					} else {
						notify({
							message : res.message
						});
					}
				});

	}
	$scope.query = function() {
		flushTree($scope.catRootSel.id);
	}

	$scope.rowupdate = function() {
		var selrow = getSelectRow();

		if (angular.isDefined(selrow)) {
			rowsave(selrow.id);
		}

	}

	$scope.rowinsert = function() {
		rowsave();
	}
	function rowsave(id) {

		var p = {};
		if ($scope.curSelNode == "") {
			notify({
				message : "请先选择分类"
			});
			return;
		}
		p.node = $scope.curSelNode;
		p.id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/flow/modal_flowmatchsave.html',
			controller : modalflowmatchsaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				meta : function() {
					return p;
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

	function flush() {
		$http.post(
				$rootScope.project
						+ "/api/flow/sysProcessDef/Ext/selectList.do", {
					owner : $scope.curSelNode
				}).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			} else {
				notify({
					message : res.message
				});
			}
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

			return $scope.dtOptions.aaData[data[0]];
		}
	}

	$scope.formreview = function() {
		var selrow = getSelectRow();
		if (angular.isDefined(selrow)) {

			if (angular.isDefined(selrow.form)) {
				$window.open($rootScope.project
						+ "/console/views/system/form/formdesign.html?id="
						+ selrow.form);
			} else {
				notify({
					message : "请先设置表单"
				});
			}

		}
	}
	$scope.preview = function() {
		var selrow = getSelectRow();
		if (angular.isDefined(selrow)) {
			var ps = {};
			ps.pk = selrow.ptplkey;
			var modalInstance = $uibModal.open({
				backdrop : true,
				templateUrl : 'views/flow/modal_reviewProcess.html',
				controller : modalreviewProcessCtl,
				size : 'lg',
				resolve : { // 调用控制器与modal控制器中传递值
					meta : function() {
						return ps;
					}
				}
			});

			modalInstance.result.then(function(result) {
			}, function(reason) {
				// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
				$log.log("reason", reason)
			});

		}
	}

	$scope.rowdel = function() {
		var selrow = getSelectRow();
		if (angular.isDefined(selrow)) {
			$confirm({
				text : '是否删除?'
			})
					.then(
							function() {
								$http
										.post(
												$rootScope.project
														+ "/api/flow/sysProcessDef/deleteById.do",
												{
													id : selrow.id
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

};

app.register.controller('sysFlowMatchCtl', sysFlowMatchCtl);