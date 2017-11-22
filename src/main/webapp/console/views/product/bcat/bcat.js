//重命名
function prodCatBItemRenameCtl($log, $http, $rootScope, $scope,
		$uibModalInstance, id, notify) {
	$scope.item = {};
	$scope.item.name = "";

	$scope.sure = function() {

		var ps = {};
		ps.id = id;
		ps.text = $scope.item.name;
		$http.post($rootScope.project + "/api/categoryB/rename.do", ps)
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
// 属性选项
function prodCatAttrValueSaveCtl($log, $http, $rootScope, $scope, $compile,
		DTLang, DTOptionsBuilder, DTColumnBuilder, $uibModalInstance, id,
		notify) {

	$scope.data = [];

	function flush() {

		$http.post($rootScope.project + "/api/categoryB/catAttrValueQuery.do",
				{
					attr_id : id
				}).success(function(res) {
			if (res.success) {
				$scope.data = res.data
			} else {
				notify({
					message : res.message
				})
			}

		})
	}
	flush()

	$scope.add = function() {

		$http.post($rootScope.project + "/api/categoryB/catAttrValueAdd.do", {
			attr_id : id
		}).success(function(res) {
			if (res.success) {
				flush()
			} else {
				notify({
					message : res.message
				})
			}

		})
	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(item) {
		$http.post($rootScope.project + "/api/categoryB/catAttrValueUpdate.do",
				item).success(function(res) {

			notify({
				message : res.message
			});
		})
	}

	$scope.del = function(item) {

		$http.post($rootScope.project + "/api/categoryB/catAttrValueDel.do",
				item).success(function(res) {

			if (res.success) {
				flush();
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
// 属性修改
function prodCatBUpdateAttrCtl($log, $http, $rootScope, $scope,
		$uibModalInstance, id, notify) {
	console.log("in", id);
	$scope.item = {};

	$scope.typeopt = [ {
		id : "base",
		name : "基本属性"
	}, {
		id : "sale",
		name : "销售属性"
	}, {
		id : "desc",
		name : "描述属性"
	} ]

	$scope.typeSel = $scope.typeOpt[0];

	$scope.aliasOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.aliasSel = $scope.aliasOpt[0];

	$scope.searchOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.searchSel = $scope.searchOpt[1];

	$scope.mustOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.mustSel = $scope.mustOpt[1];

	$scope.usedOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.usedSel = $scope.usedOpt[0];

	$scope.multiOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.multiSel = $scope.multiOpt[0];

	$http.post($rootScope.project + "/api/categoryB/catAttrQueryById.do", {
		id : id
	}).success(function(res) {
		if (res.success) {
			console.log(res);
			$scope.item.name = res.data.name;
			$scope.item.od = res.data.od;
			$scope.item.id = res.data.id;
			// 是否可别名
			if (res.data.can_alias == "Y") {
				$scope.aliasSel = $scope.aliasOpt[0];
			} else {
				$scope.aliasSel = $scope.aliasOpt[1];
			}

			// 是否搜索
			if (res.data.is_search == "Y") {
				$scope.searchSel = $scope.searchOpt[0];
			} else {
				$scope.searchSel = $scope.searchOpt[1];
			}

			// 是否必须
			if (res.data.is_need == "Y") {
				$scope.mustSel = $scope.mustOpt[0];
			} else {
				$scope.mustSel = $scope.mustOpt[1];
			}

			// 是否使用中
			if (res.data.is_used == "Y") {
				$scope.usedSel = $scope.usedOpt[0];
			} else {
				$scope.usedSel = $scope.usedOpt[1];
			}

		} else {
			notify({
				message : res.message
			})
		}

	})
	$scope.sure = function() {

		var ps = {};
		ps.id = $scope.item.id;
		ps.is_need = $scope.mustSel.id;
		ps.can_alias = $scope.aliasSel.id;
		ps.name = $scope.item.name
		ps.attr_id = id;
		ps.od = $scope.item.od
		// ps.ATTR_TYPE = $scope.typeSel.ID
		// ps.IS_MULTI = $scope.multiSel.ID
		ps.is_used = $scope.usedSel.id;
		// ps.VALUE_TYPE = "string";
		ps.is_search = $scope.searchSel.id;

		$http.post($rootScope.project + "/api/categoryB/catAttrUpdate.do", ps)
				.success(function(res) {
					if (res.success) {
						$uibModalInstance.close("OK");
					} else {

					}
					notify({
						message : res.message
					})

				})

	}

	$scope.cancel = function() {

		$uibModalInstance.dismiss('cancel');
	};

}

// 新增属性弹出框
function prodCatBAddAttrCtl($log, $http, $rootScope, $scope, $uibModalInstance,
		id, notify) {
	console.log("in", id);
	$scope.item = {};
	$scope.item.name = "";
	$scope.item.od = 2;

	$scope.typeOpt = [ {
		id : "base",
		name : "基本属性"
	}, {
		id : "sale",
		name : "销售属性"
	} ]

	$scope.typeSel = $scope.typeOpt[0];

	$scope.aliasOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.aliasSel = $scope.aliasOpt[0];

	$scope.searchOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.searchSel = $scope.searchOpt[1];

	$scope.mustOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.mustSel = $scope.mustOpt[1];

	$scope.usedOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.usedSel = $scope.usedOpt[0];

	$scope.isenumOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.isenumSel = $scope.isenumOpt[0];

	$scope.isinputOpt = [ {
		id : "Y",
		name : "是"
	}, {
		id : "N",
		name : "否"
	} ]
	$scope.isinputSel = $scope.isinputOpt[1];

	$scope.inputtypeOpt = [ {
		id : "input",
		name : "输入框"
	}, {
		id : "select-single",
		name : "单选框"
	}, {
		id : "select-multi",
		name : "多选框"
	} ]

	$scope.inputtypeSel = $scope.inputtypeOpt[0]

	$scope.sure = function() {

		if (!angular.isDefined(id)) {
			alert("品类不存在");
			return;
		}

		if ($scope.typeSel.id == "base"
				&& $scope.inputtypeSel.id == "select-multi") {
			notify({
				message : "基本属性的多选暂不支持"
			})
			return

		}

		var ps = {};
		ps.is_need = $scope.mustSel.id;
		ps.can_alias = $scope.aliasSel.id;
		ps.name = $scope.item.name
		ps.cat_id = id;
		ps.od = $scope.item.od
		ps.attr_type = $scope.typeSel.id
		ps.is_enum = $scope.isenumSel.id
		ps.is_used = $scope.usedSel.id;
		ps.is_search = $scope.searchSel.id;
		ps.is_input = $scope.isinputSel.id;
		ps.input_type = $scope.inputtypeSel.id;
		$http.post($rootScope.project + "/api/categoryB/catAttrAdd.do", ps)
				.success(function(res) {
					if (res.success) {
						$uibModalInstance.close("OK");
					} 
					notify({
						message : res.message
					})

				})

	}

	$scope.cancel = function() {

		$uibModalInstance.dismiss('cancel');
	};

}

function prodCatBCtl($compile, DTLang, DTOptionsBuilder, DTColumnBuilder,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

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

	function renderInput(data, type, full) {
		if (data == "input") {
			return "输入框"
		} else if (data == "select-single") {
			return "单选框"
		} else if (data == "select-multi") {
			return "多选框"
		} else {
			return data;
		}

	}

	function renderEn(data, type, full) {
		if (data == "Y") {
			return "是"
		} else {
			return "否"
		}

	}

	function renderStatus(data, type, full) {
		if (data == "Y") {
			return "使用中"
		} else {
			return "停用"
		}

	}

	function renderMust(data, type, full) {
		if (data == "Y") {
			return "必填"
		} else {
			return "选填"
		}

	}
	function renderAction(data, type, full) {

		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"attrEdit('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">编辑</button> ";

		if (full.INPUT_TYPE != "input") {
			acthtml = acthtml + " <button ng-click=\"attrvalueEdit('"
					+ full.ATTR_ID
					+ "')\" class=\"btn-white btn btn-xs\">选项值</button> ";
		}

		acthtml = acthtml + " <button ng-click=\"attrDel('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";

		return acthtml;
	}

	$scope.attrDel = function(id) {
		$confirm({
			text : '是否删除该属性?'
		}).then(function() {
			$http.post($rootScope.project + "/api/categoryB/catAttrDel.do", {
				id : id
			}).success(function(res) {
				notify({
					message : res.message
				});
				if (res.success) {
					var snodes = $scope.tree.get_selected();
					var node = snodes[0];
					flushAttrTable(node);

				}

			})
		});
	}

	$scope.attrEdit = function(id) {

		var snodes = $scope.tree.get_selected();
		var node = snodes[0];

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/product/bcat/modal_cat_attr_update.html',
			controller : prodCatBUpdateAttrCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				id : function() {
					return id
				}
			}
		});

		modalInstance.result.then(function(result) {
			if (result == "OK") {
				flushAttrTable(node);
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});
	}

	$scope.attrvalueEdit = function(id) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/product/bcat/modal_cat_attrvalue_save.html',
			controller : prodCatAttrValueSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				id : function() {
					return id
				}
			}
		});

		modalInstance.result.then(function(result) {
			if (result == "OK") {
				// flushAttrTable(node);
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('input_type').withTitle('输入类型')
					.withOption('sDefaultContent', '').renderWith(renderInput),
			DTColumnBuilder.newColumn('od').withTitle('排序').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_used').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('is_need').withTitle('必填').withOption(
					'sDefaultContent', '').renderWith(renderMust),
			DTColumnBuilder.newColumn('attr_type_name').withTitle('属性类型')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('attr_id').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction),

	]

	function flushAttrTable(node) {

		$http.post($rootScope.project + "/api/categoryB/catAttrQuery.do", {
			cat_id : node
		}).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			} else {
				notify({
					message : res.message
				})
			}

		})

	}

	$scope.treeData = [];
	$scope.ignoreChanges = false;
	$scope.newNode = {};

	function flush() {
		var ps = {};

		$http.post($rootScope.project + "/api/categoryB/queryTreeList.do", ps)
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
	flush();
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
								$rootScope.project + "/api/categoryB/add.do", {
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
								$rootScope.project + "/api/categoryB/add.do", {
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
						$http
								.post(
										$rootScope.project
												+ "/api/categoryB/delete.do", {
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
									templateUrl : 'views/product/bcat/modal_bcat_rename.html',
									controller : prodCatBItemRenameCtl,
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

					flushAttrTable(node);

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

	$scope.addattr = function() {

		var snodes = $scope.tree.get_selected();
		if (snodes.length == 1) {
			var node = snodes[0];
			console.log("select node:", node);

			var modalInstance = $uibModal.open({
				backdrop : true,
				templateUrl : 'views/product/bcat/modal_cat_attr_add.html',
				controller : prodCatBAddAttrCtl,
				size : 'lg',
				resolve : { // 调用控制器与modal控制器中传递值
					id : function() {
						return node
					}
				}
			});

			modalInstance.result.then(function(result) {
				if (result == "OK") {
					flushAttrTable(node);
				}
			}, function(reason) {
				// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
				$log.log("reason", reason)
			});

		} else {

			notify({
				message : "请选择品类"
			});
		}

	}

};

app.register.controller('prodCatBCtl', prodCatBCtl);