//angular.isArray()	如果引用的是数组返回 true
//angular.isDate()	如果引用的是日期返回 true
//angular.isDefined()	如果引用的已定义返回 true
//angular.isElement()	如果引用的是 DOM 元素返回 true
//angular.isFunction()	如果引用的是函数返回 true
//angular.isNumber()	如果引用的是数字返回 true
//angular.isObject()	如果引用的是对象返回 true
//angular.isString()	如果引用的是字符串返回 true
//angular.isUndefined()	如果引用的未定义返回 true
//angular.equals()

function getTree(data, primaryIdName, parentIdName) {
	if (!data || data.length == 0 || !primaryIdName || !parentIdName)
		return [];

	var tree = [], rootIds = [], item = data[0], primaryKey = item[primaryIdName], treeObjs = {}, parentId, parent, len = data.length, i = 0;

	while (i < len) {
		item = data[i++];

		primaryKey = item[primaryIdName];
		treeObjs[primaryKey] = item;
		parentId = item[parentIdName];
		console.log(parentId);
		if (parentId) {
			parent = treeObjs[parentId];

			if (parent.children) {
				parent.children.push(item);
			} else {
				parent.children = [ item ];
			}
		} else {
			rootIds.push(primaryKey);
		}
	}

	for (var i = 0; i < rootIds.length; i++) {
		tree.push(treeObjs[rootIds[i]]);
	}
	;

	return tree;
}

function menuModuleCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, data, $http, $rootScope) {
	$scope.item = {}
	$log.log("window in:", data);
	$scope.modulesOpt = []
	$scope.modulesSel = ""
	$scope.item = data;
	$scope.nameaction = true;

	$scope.sure = function() {

	};
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function menuAclCtl($timeout, DTLang, DTOptionsBuilder, DTColumnBuilder, notify, $log, $uibModal, $uibModalInstance, $scope, data, $http, $rootScope, $compile) {
	$scope.item = {}
	$log.log("window in:", data);
	var mid = data.NODE_ID;
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(10).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", true).withOption("paging", true).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withOption('initComplete', function(settings, conf) {
				if (angular.isDefined($scope.dtInstance.DataTable)) {
					$timeout(function() {
						for (var i = 0; i < $scope.dtOptions.aaData.length; i++) {
							if ($scope.dtOptions.aaData[i].SELECTED == "Y") {
								$scope.dtInstance.DataTable.row(':eq(' + i + ')').select();
							} else {
								break;
							}
						}
					}, 80)
				}
			}).withLanguage(DTLang).withOption("select", {
				style : 'multi',
				selector : 'td:first-child'
			});

	$scope.dtInstance = {}

	$scope.dtColumns = [ DTColumnBuilder.newColumn(null).withTitle('').withClass('select-checkbox').renderWith(function() {
		return '';
	}), DTColumnBuilder.newColumn('CTACLTEXT').withTitle('ACL类型').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('URL').withTitle('URL').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('MARK').withTitle('备注').withOption('sDefaultContent', '') ]
	// $scope.dtInstance.DataTable.rows('.even').select();
	function flush() {
		var ps = {};
		ps.module_id = mid
		$http.post($rootScope.project + "/api/module/queryModuleItemMap.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})

	}
	flush();
	$scope.sure = function() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		var urls = [];
		for (var j = 0; j < data.length; j++) {
			urls.push($scope.dtOptions.aaData[data[j]])
		}

		var ps = {};
		ps.items = angular.toJson(urls);
		ps.module_id = mid;
		$http.post($rootScope.project + "/api/module/updateModuleItemMap.do", ps).success(function(res) {
			if (res.success) {
				$uibModalInstance.dismiss('cancel');
			}
			notify({
				message : res.message
			});
		})

	};
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function menuModifyCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, data, $http, $rootScope) {
	$log.log("window in:", data);
	$scope.item = {};
	$scope.item = angular.copy(data)

	$scope.topMenuOpt = [];
	$scope.topMenuSel = "";
	$scope.actionOpt = [ {
		ID : "Y",
		NAME : "有效"
	}, {
		ID : "N",
		NAME : "无效"
	} ]
	$scope.actionSel = $scope.actionOpt[0];

	$scope.showOpt = [ {
		ID : "Y",
		NAME : "有效"
	}, {
		ID : "N",
		NAME : "无效"
	} ];
	$scope.showSel = $scope.showOpt[0]

	$scope.nodeOpt = [ {
		ID : "dir",
		NAME : "目录"
	}, {
		ID : "menu",
		NAME : "菜单"
	} ];
	$scope.nodeSel = $scope.nodeOpt[0];

	if ($scope.item.ACTIONTYPE == "ADD") {
		$scope.item.NODE_NAME = "";
		$scope.item.MARK = ""
		$scope.item.KEY = ""
		$scope.item.LOGO = ""
		$scope.item.SORT = ""
		$scope.item.MODULE_ID = "";
		$scope.item.MENU_LEVEL = "";
		$scope.item.OLD_NODE_ID = $scope.item.NODE_ID;
		$scope.item.OLD_ROUTE = $scope.item.ROUTE;
	} else if ($scope.item.ACTIONTYPE == "EDIT") {
		if ($scope.item.TYPE == "menu") {
			$scope.nodeSel = $scope.nodeOpt[1];
		} else if ($scope.item.TYPE == "dir") {
			$scope.nodeSel = $scope.nodeOpt[0];
		}

		if ($scope.item.IS_ACTION == "Y") {
			$scope.actionSel = $scope.actionOpt[0];
		} else if ($scope.item.IS_ACTION == "N") {
			$scope.actionSel = $scope.actionOpt[1];
		}

		if ($scope.item.IS_G_SHOW == "Y") {
			$scope.showSel = $scope.showOpt[0]
		} else if ($scope.item.IS_G_SHOW == "N") {
			$scope.showSel = $scope.showOpt[1]
		}
	} else if ($scope.item.ACTIONTYPE == "ADDMASTER") {
		$scope.item.NODE_NAME = "";
		$scope.item.MARK = ""
		$scope.item.KEY = ""
		$scope.item.LOGO = ""
		$scope.item.SORT = ""
		$scope.item.MODULE_ID = "";
		$scope.item.MENU_LEVEL = "";
		$http.post($rootScope.project + "/api/menu/treeTop.do", {}).success(function(res) {
			if (res.success) {
				$scope.topMenuOpt = res.data;
				if (res.data.length > 0) {
					$scope.topMenuSel = res.data[0];
				}
			}
		})
	}

	$scope.sure = function() {

		var ps = $scope.item;
		ps.IS_ACTION = $scope.actionSel.ID;
		ps.IS_G_SHOW = $scope.showSel.ID;
		ps.TYPE = $scope.nodeSel.ID;
		if (angular.isDefined($scope.topMenuSel.MENU_ID)) {
			ps.MENU_ID = $scope.topMenuSel.MENU_ID;
		}

		if ($scope.item.ACTIONTYPE == "EDIT") {
			$log.log("修改")
			$http.post($rootScope.project + "/api/menu/updateNode.do", ps).success(function(res) {
				if (res.success) {
					$localStorage.remove("dt_sys_menu");
					notify({
						message : res.message
					});
					$uibModalInstance.close("OK");
				} else {
					notify({
						message : res.message
					});
				}
			})
		} else if ($scope.item.ACTIONTYPE == "ADD" || $scope.item.ACTIONTYPE == "ADDMASTER") {
			$log.log("新增")
			$http.post($rootScope.project + "/api/menu/addNode.do", ps).success(function(res) {
				if (res.success) {
					$localStorage.remove("dt_sys_menu");
					notify({
						message : res.message
					});
					$uibModalInstance.close("OK");
				} else {
					notify({
						message : res.message
					});
				}
			})

		}

	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function sysmenuCtl($confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.topMenuOpt = []
	$scope.topMenuSel = "";

	$scope.tree_expand_level = 2;
	$http.post($rootScope.project + "/api/menu/treeTop.do", {}).success(function(res) {
		if (res.success) {
			$scope.topMenuOpt = res.data;
			if (res.data.length > 0) {
				$scope.topMenuSel = res.data[0];
				flush();
			}
		}
	})

	var acthtml = " <div class=\"btn-group\"> ";
	acthtml = acthtml + " <button ng-click=\"cellTemplateScope.edit(row.branch)\" class=\"btn-white btn btn-xs\">编辑</button> ";
	acthtml = acthtml + " <button ng-click=\"cellTemplateScope.add(row.branch)\" class=\"btn-white btn btn-xs\">新增</button> ";
	acthtml = acthtml + " <button ng-click=\"cellTemplateScope.del(row.branch)\" class=\"btn-white btn btn-xs\">删除</button> ";
	acthtml = acthtml + " <button ng-click=\"cellTemplateScope.acl(row.branch)\" class=\"btn-white  btn btn-xs\">权限</button> </div> ";

	var tree;
	var myTreeData = [];
	$scope.my_tree = tree = {};
	$scope.expanding_property = {
		field : "NODE_NAME",
		displayName : "名称",
		sortable : true,
		filterable : true,
		cellTemplate : "<i>{{row.branch[expandingProperty.field]}}</i>"
	};

	$scope.col_defs = [ {
		field : "KEY",
		sortable : false,
		displayName : "程序键",
		sortingType : "string"
	}, {
		field : "TYPETEXT",
		sortable : false,
		displayName : "类型",
		sortingType : "string"
	}, {
		field : "ACL_CNT",
		sortable : false,
		displayName : "ACL规则数",
		sortingType : "string"
	}, {
		field : "NODE_ID",
		displayName : "动作",
		cellTemplate : acthtml,
		cellTemplateScope : {
			setmodule : function(data) { // this works too:
				// 展示不实现
				var ps = data;
				var modalInstance = $uibModal.open({
					backdrop : true,
					templateUrl : 'views/system/menu/modal_menu_module.html',
					controller : menuModuleCtl,
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
						// flush();
					}
				}, function(reason) {
					// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
					$log.log("reason", reason)
				});
			},
			add : function(data) { // this works too: $scope.someMethod;
				var ps = data;
				ps.ACTIONTYPE = "ADD";
				var modalInstance = $uibModal.open({
					backdrop : true,
					templateUrl : 'views/system/menu/modal_menu_save.html',
					controller : menuModifyCtl,
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
						flush();
					}
				}, function(reason) {
					// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
					$log.log("reason", reason)
				});
			},
			del : function(data) { // this works too: $scope.someMethod;
				$log.log('del', data);
				var ps = {};
				ps.NODE_ID = data.NODE_ID;
				ps.MENU_ID = data.MENU_ID;
				$confirm({
					text : '是否删除本节点?'
				}).then(function() {
					$http.post($rootScope.project + "/api/menu/deleteNode.do", ps).success(function(res) {
						notify({
							message : res.message
						});
						if (res.success) {
							flush();
							// /删除本节点,修改删除
						}
					})
				});
			},
			acl : function(data) { // this works too: $scope.someMethod;
				$log.log('acl', data);
				var ps = {};
				ps.NODE_ID = data.NODE_ID;
				ps.MENU_ID = data.MENU_ID;

				if (data.TYPE != "menu") {
					notify({
						message : "请选择菜单"
					});
					return;
				}
				var modalInstance = $uibModal.open({
					backdrop : true,
					templateUrl : 'views/system/menu/modal_menu_acl.html',
					controller : menuAclCtl,
					size : 'lg',
					resolve : { // 调用控制器与modal控制器中传递值
						data : function() {
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

			},
			edit : function(data) { // this works too: $scope.someMethod;
				var ps = data;
				ps.ACTIONTYPE = "EDIT";
				var modalInstance = $uibModal.open({
					backdrop : true,
					templateUrl : 'views/system/menu/modal_menu_save.html',
					controller : menuModifyCtl,
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
						flush();
					}
				}, function(reason) {
					// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
					$log.log("reason", reason)
				});

			}
		}
	} ];
	$scope.tree_data = [];
	var rawTreeData = [];
	var myTreeData = [];
	function flush() {
		$http.post($rootScope.project + "/api/menu/treeDataDirect.do", {
			ID : $scope.topMenuSel.MENU_ID
		}).success(function(res) {
			if (res.success) {
				rawTreeData = res.data
				myTreeData = getTree(rawTreeData, 'NODE_ID', 'PARENT_ID');
				$scope.tree_data = myTreeData;
				setTimeout(function() {
					$log.log($scope.my_tree)
					// ???为什么无效
					$scope.my_tree.expand_all();
				}, 8500)

			}
		})
	}

	$scope.fff = function() {
		$log.log($scope.my_tree);
		$scope.tree_expand_level = 3;
		$scope.my_tree.expand_all();
	}
	$scope.query = function() {
		flush();
	}

	$scope.addMasterNode = function() {
		var ps = {};
		ps.ACTIONTYPE = "ADDMASTER";
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/menu/modal_menu_save.html',
			controller : menuModifyCtl,
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
				flush();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});
	}

	$scope.my_tree_handler = function(branch) {
		console.log('you clicked on', branch)
	}

};

app.register.controller('sysmenuCtl', sysmenuCtl);