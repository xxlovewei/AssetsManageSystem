function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

//重命名
function prodCatFItemRenameCtl($log, $http, $rootScope, $scope,
		$uibModalInstance, id, notify) {
	$scope.item = {};
	$scope.item.name = "";

	$scope.sure = function() {

		var ps = {};
		ps.id = id;
		ps.text = $scope.item.name;
		$http.post($rootScope.project + "/api/categoryF/rename.do", ps)
				.success(function(res) {
					if (res.success) {
						$uibModalInstance.close(ps.text);
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

function prodfCatCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.catRootOpt = [];
	$scope.catRootSel = "";
	$http.post($rootScope.project + "/api/categoryF/rootCatQuery.do", {
	}).success(function(res) {
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
	//树配置
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
			items : {
				"createCat" : {
					"separator_before" : false,
					"separator_after" : false,
					"label" : function() {
						return "新建品类";
					},
					"_disabled" : function(data) {
						var inst = $scope.tree;
						var obj = inst.get_node(data.reference);
						// 只有在层级节点可以添加

						if (obj.type == "root" || obj.type == "category") {
							return true;
						}
						if (obj.type == "node") {
							return false;
						}
						return true;
					},
					"action" : function(data) {
						// first before after last
						var inst = $scope.tree;
						var obj = inst.get_node(data.reference);
						console.log("cur:obj", obj);
						$http.post(
								$rootScope.project + "/api/categoryF/add.do", {
									action : "cat",
									text : "新品类",
									id : obj.id
								}).success(function(res) {
							if (res.success) {

								inst.create_node(obj, {
									id : res.data.id,
									text : "新品类",
									parent : obj.id,
									type : res.data.type
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
				"createPoint" : {
					"separator_before" : false,
					"separator_after" : false,
					"label" : function() {
						return "新建层级";
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
						$http.post(
								$rootScope.project + "/api/categoryF/add.do", {
									action : "node",
									text : "新节点",
									id : obj.id
								}).success(function(res) {

							$log.warn(res);
							if (res.success) {
								$log.warn({
									id : res.data.id,
									text : "新节点",
									parent : obj.id
								});
								inst.create_node(obj, {

									id : res.data.id,
									text : "新节点",
									parent : obj.id,
									type : res.data.type
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
//						$http
//								.post(
//										$rootScope.project
//												+ "/api/categoryF/delete.do", {
//											id : obj.id
//										}).success(function(res) {
//									if (res.success) {
//										inst.delete_node(obj);
//									}
//									notify({
//										message : res.message
//									});
//								})
					}

				},
				"RenameItem" : {
					"separator_before" : false,
					"separator_after" : false,
					"label" : "重命名",
					"_disabled" : function(data) {
						var inst = $scope.tree;
						var obj = inst.get_node(data.reference);
						if (obj.type == "root") {
							return true
						}
						return false;
					},
					"action" : function(data) {
						var inst = $scope.tree;
						var obj = inst.get_node(data.reference);

						var modalInstance = $uibModal
								.open({
									backdrop : true,
									templateUrl : 'views/product/fcat/modal_fcat_rename.html',
									controller : prodCatFItemRenameCtl,
									size : 'md',
									resolve : { // 调用控制器与modal控制器中传递值
										id : function() {
											return obj.id
										}
									}
								});

						modalInstance.result.then(function(result) {
							$log.warn("result:", result);
							inst.rename_node(obj.id, result)
						}, function(reason) {
							// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
							$log.log("reason", reason)
						});

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

					//flushAttrTable(node);

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

	function flushTree(id){
		
		$http.post($rootScope.project + "/api/categoryF/queryTreeList.do", {root_id:id
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
//		
//		
//		$scope.ignoreChanges = true;
//		$scope.treeData = angular.copy(data);
//		$scope.treeConfig.version++;
	}
	$scope.addattr = function() {

//		var snodes = $scope.tree.get_selected();
//		if (snodes.length == 1) {
//			var node = snodes[0];
//			console.log("select node:", node);
//
//			var modalInstance = $uibModal.open({
//				backdrop : true,
//				templateUrl : 'views/product/cat/modal_cat_attr_add.html',
//				controller : prodCatBAddAttrCtl,
//				size : 'lg',
//				resolve : { // 调用控制器与modal控制器中传递值
//					id : function() {
//						return node
//					}
//				}
//			});
//
//			modalInstance.result.then(function(result) {
//				if (result == "OK") {
//					flushAttrTable(node);
//				}
//			}, function(reason) {
//				// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
//				$log.log("reason", reason)
//			});
//
//		} else {
//
//			notify({
//				message : "请选择品类"
//			});
//		}

	}
	
	

	//右边表格
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

		acthtml = acthtml + " <button ng-click=\"save('" + full.ID
				+ "')\" class=\"btn-white btn btn-xs\">更新</button>  ";

		acthtml = acthtml + " <button ng-click=\"row_delete('" + full.ID
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;

	}

	function renderStatus(data, type, full) {

		if (data == "Y") {
			return "使用中"
		} else {
			return "暂停";
		}

	}
	$scope.dtColumns = [

			DTColumnBuilder.newColumn('text').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_used').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('id').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]
 

	$scope.row_dtl = function(id) {

	}

	$scope.query = function() {
		flushTree($scope.catRootSel.id);
	}
	$scope.save = function(id) {

	}

};

app.register.controller('prodfCatCtl', prodfCatCtl);