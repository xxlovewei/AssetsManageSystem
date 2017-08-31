function hrmEmployeeSaveCtl($log, $http, $rootScope, $scope, $uibModalInstance,
		data, notify) {
	console.log("window in:", data);
	$scope.item = data;

	$scope.sure = function() {
		var cmd = "";
		if (angular.isDefined($scope.item.EMPLOYEE_ID)) {
			cmd = "/api/hrm/employeeUpdate.do";
		} else {
			cmd = "/api/hrm/employeeAdd.do";
		}

		 
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

function hrmOrgEmployeeCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.topMenuOpt = []
	$scope.topMenuSel = "";

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType(
			'full_numbers').withDisplayLength(25).withOption("ordering", false)
			.withOption("responsive", true).withOption("searching", false)
			.withOption("paging", false).withOption('bStateSave', true)
			.withOption('bProcessing', true).withOption('bFilter', false)
			.withOption('bInfo', false).withOption('serverSide', false)
			.withOption('bAutoWidth', false).withOption('aaData',
					$scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.ROLE_ID
				+ "')\" class=\"btn-white btn btn-xs\">编辑</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.ROLE_ID
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "无效";
		if (full.IS_ACTION == "Y") {
			res = "有效";
		}
		return res;
	}

	$scope.dtColumns = [
			
			DTColumnBuilder.newColumn('EMPL_ID').withTitle('员工编号').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('NAME').withTitle('姓名').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('MARK').withTitle('备注').withOption(
					'sDefaultContent', '') //,
	//			DTColumnBuilder.newColumn('ID').withTitle('动作').withOption(
	//					'sDefaultContent', '').renderWith(renderAction) 

	]

	var org_id = "";
	$http.post($rootScope.project + "/api/hrm/orgQuery.do", {}).success(
			function(res) {
				if (res.success) {
					$scope.topMenuOpt = res.data;
					if (res.data.length > 0) {
						$scope.topMenuSel = res.data[0];
						// 加载组织信息
						flush();

					}
				}
			})

	$scope.treeData = [];
	$scope.ignoreChanges = false;
	$scope.newNode = {};

	function flush() {
		var ps = {};
		ps.org_id = $scope.topMenuSel.ORG_ID;
		org_id = ps.org_id;
		$http.post($rootScope.project + "/api/hrm/orgNodeTreeQuery.do", ps)
				.success(function(res) {
					if (res.success) {
					 
						$scope.ignoreChanges = true;
						$scope.treeData = angular.copy(res.data);
						$scope.treeConfig.version++;
					} else {
						notify({
							message : res.message
						});
					}
				})

	}

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
			"file" : {
				"icon" : "fa fa-file icon-state-warning icon-lg"
			}
		},
		version : 1,
		plugins : [ 'themes', 'types', 'changed' ]

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

	function flushEmployee() {
		var node = "";
		var snodes = $scope.tree.get_selected();
		if (snodes.length == 1) {
			var node = snodes[0];
			console.log("select node:", node);

		}
		$http.post($rootScope.project + "/api/hrm/queryEmplByOrg.do", {
			node_id : node
		}).success(function(res) {
			if (res.success) {
			 
				$scope.dtOptions.aaData = res.data;
			} else {
				notify({
					message : res.message
				});
			}
		})

	}
	$scope.test = function() {
		$log.info("测试");
		console.log($scope.treeData);
		console.log($scope.tree.get_selected());

	}
	$scope.readyCB = function() {
		
		$scope.tree = $scope.treeInstance.jstree(true)
		// 展开所有节点
		$scope.tree.open_all();
		// 响应节点变化
		$scope.treeInstance.on("changed.jstree", function(e, data) {
			console.log(data);
			if (data.action == "select_node") {
				// 加载数据
				flushEmployee();

			}

		});

	}
	$scope.cc = function() {
		 

	}

	$scope.createCB = function(e, item) {
		 
	};

	$scope.save = function(id) {
	 
		var ps = {};
		var snodes = $scope.tree.get_selected();
		if (snodes.length == 1) {
			ps.NODE_ID = snodes[0];
		} else {
			notify({
				message : "请先选择节点"
			});
			return;
		}

		if (id != "add") {
			ps.EMPLOYEE_ID = id;
		}
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/org/modal_employee_save.html',
			controller : hrmEmployeeSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				data : function() {
					return ps;
				}
			}
		});

		modalInstance.result.then(function(result) {
		 
			if (result == "OK") {
				flushEmployee();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});
	}

};

app.register.controller('hrmOrgEmployeeCtl', hrmOrgEmployeeCtl);
