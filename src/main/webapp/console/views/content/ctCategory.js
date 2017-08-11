function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

 

function ctCateSettingCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.catRootOpt = [];
	$scope.catRootSel = "";
	$scope.item = {};
	$http.post($rootScope.project + "/api/ctCategroy/queryRootCategory.do", {}).success(function(res) {
		if (res.success) {
			$scope.catRootOpt = res.data;
			if ($scope.catRootOpt.length > 0) {
				$scope.catRootSel = $scope.catRootOpt[0];
				flushTree($scope.catRootSel.ID)
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
				$log.error('treeCtrl: error from js tree - ' + angular.toJson(error));
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
			items : {
				"createPoint" : {
					"separator_before" : false,
					"separator_after" : false,
					"label" : function() {
						return "新建节点";
					},
					"_disabled" : function(data) {
						var inst = $scope.tree;
						var obj = inst.get_node(data.reference);
						// 只有在层级节点可以添加
						if (obj.type == "root" || obj.type == "node") {
							return false
						}
						return true;
					},
					"action" : function(data) {
						// first before after last
						var inst = $scope.tree;
						var obj = inst.get_node(data.reference);
						console.log("cur:obj", obj);

						$http.post($rootScope.project + "/api/ctCategroy/addCategory.do", {
							OLD_NODE_TYPE : obj.type,
							NAME : "新节点",
							OLD_ID : obj.id
						}).success(function(res) {

							if (res.success) {
								inst.create_node(obj, {
									id : res.data.ID,
									text : "新节点",
									parent : res.data.PARENT_ID,
									type : "node"
								}, "last", function(new_node) {
									console.log("new_node is:", new_node);
								});
							} else {
								notify({
									message : res.message
								});

							}

						})
					}
				},
				"DeleteItem" : {
					"separator_before" : false,
					"separator_after" : false,
					"label" : "删除节点",
					"_disabled" : function(data) {
						var inst = $scope.tree;
						var obj = inst.get_node(data.reference);
						$log.warn(obj);
						$log.warn(obj.type);
						if (obj.type == "root") {
							return true
						}
						return false;
					},
					"action" : function(data) {
						$log.info("删除节点");
						var inst = $scope.tree;
						var obj = inst.get_node(data.reference);
						$http.post($rootScope.project + "/api/ctCategroy/deleteCategory.do", {
							id : obj.id
						}).success(function(res) {
							if (res.success) {
								inst.delete_node(obj);
							}
							notify({
								message : res.message
							});
						})
					}

				}

			}
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
		console.log($scope.treeData);
		console.log($scope.tree.get_selected());

	}
	$scope.readyCB = function() {
		console.log('readyCB');
		$scope.tree = $scope.treeInstance.jstree(true)
		// 展开所有节点
		$scope.tree.open_all();
		// 响应节点变化
		$scope.treeInstance.on("changed.jstree", function(e, data) {
			console.log(data);
			if (data.action == "select_node") {
				// 加载数据
				var snodes = $scope.tree.get_selected();
				if (snodes.length == 1) {
					var node = snodes[0];
					console.log("select node:", node);
					$http.post($rootScope.project + "/api/ctCategroy/queryCategoryById.do", {
						id : node
					}).success(function(res) {
						if (res.success) {
							$scope.item = res.data;
						} else {
							notify({
								message : res.message
							});
						}
					});
				}

			}

		});

	}
	$scope.cc = function() {
		console.log('cc')

	}

	$scope.createCB = function(e, item) {
		console.log('createCB');
	};

	function flushTree(id) {

		$http.post($rootScope.project + "/api/ctCategroy/queryCategoryTreeList.do", {
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

	$scope.saveItem = function() {

		$http.post($rootScope.project + "/api/ctCategroy/updateCategory.do", $scope.item).success(function(res) {
			if (res.success) {
				var inst = $scope.tree;
				inst.rename_node($scope.item.ID, $scope.item.NAME)
			}
			notify({
				message : res.message
			});

		});
	}
	$scope.query = function() {
		flushTree($scope.catRootSel.ID);
	}

};

app.register.controller('ctCateSettingCtl', ctCateSettingCtl);