function chosenProcessCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal, meta,
		$uibModalInstance, $window, $stateParams) {

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
	$scope.dtColumns.push(DTColumnBuilder.newColumn('ptplkey').withTitle('模版名称')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('type').withTitle('类型')
			.withOption('sDefaultContent', '').renderWith(renderType));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('status').withTitle('状态')
			.withOption('sDefaultContent', '').renderWith(renderStatus));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('mark').withTitle('备注')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('form').withTitle('表单')
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

	flushTree(5);
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

	$scope.sure = function() {
		var selrow = getSelectRow();

		if (angular.isDefined(selrow)) {
	 
			meta.processkey = selrow.ptplkey;
			if (!angular.isDefined(selrow.ptplkey)) {
				notify({
					message : "未设置模版"
				});
				return "";
			}
			meta.spmethod = "1";
			$http.post(
					$rootScope.project + "/api/cmdb/flow/zc/startProcess.do",
					meta).success(function(res) {
				if (res.success) {
					$uibModalInstance.close("OK");
				} else {
					notify({
						message : res.message
					});
				}
			})
		}

	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	function flush() {
		$http.post(
				$rootScope.project
						+ "/api/flow/sysProcessDef/Ext/selectList.do", {
					owner : $scope.curSelNode
				}).success(function(res) {
			if (res.success) {
				var r=[];
				for(var i=0;i<res.data.length;i++){
					
					if(angular.isDefined(res.data[i].type)&&angular.isDefined(res.data[i].status)   ){
						if(res.data[i].status=="normal"){
							r.push(res.data[i]);
						}
					}
				}
				$scope.dtOptions.aaData =r;
			} else {
				notify({
					message : res.message
				});
			}
		});

	}

}
function modalzclySaveCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal,
		$uibModalInstance, $state, meta) {

	$scope.actmsg = "操作";
	if (meta.acttype == "LY") {
		$scope.actmsg = "领用人";
	} else if (meta.acttype == "JY") {
		$scope.actmsg = "借用人";
	} else if (meta.acttype == "ZY") {
		$scope.actmsg = "转移人";
	}

	$scope.data = {};
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
		 
			$scope.dtOptions.aaData = result;
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}

	$scope.sure = function() {

		$scope.data.ptype = meta.acttype;

		if ($scope.dtOptions.aaData.length == 0) {
			alert("请先选择资产");
			return;

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

function zcactionCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
		$log, notify, $scope, $http, $rootScope, $uibModal, $window, $state) {

	var pbtns = $rootScope.curMemuBtns;
	var acttype = $state.router.globals.current.data.actiontype;
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withDOM('frtlip').withPaginationType('full_numbers')
			.withDisplayLength(100).withOption("ordering", false).withOption(
					"responsive", false).withOption("searching", true)
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
		var html = data;

		if (angular.isDefined(data)) {
			if (data == "submitforapproval") {
				html = "<span style='color:#33FFFF; font-weight:bold'>待送审</span>";
			} else if (data == "inreview") {
				html = "<span style='color:#00F; font-weight:bold'>审批中</span>";
			} else if (data == "success") {
				html = "<span style='color:green; font-weight:bold'>审批成功</span>";
			} else if (data == "failed") {

				html = "<span style='color:red;font-weight:bold'>审批失败</span>";
			} else if (data == "cancel") {
				html = "<span style='color:red;font-weight:bold'>审批取消</span>"
			} else if (data == "rollback") {
				html = "<span style='color:red;font-weight:bold'>审批退回</span>";
			} else {
				html = data;
			}
		}

		return html;
	}

	function renderSpReview(data, type, full) {
		var html = "";
		if (angular.isDefined(full.spmethod) && full.spmethod == "1"
				&& full.spstatus != "submitforapproval") {
			html = "1212";
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
	$scope.dtColumns.push(DTColumnBuilder.newColumn('duuid').withTitle('编号')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('pstatusdtl').withTitle(
			'状态').withOption('sDefaultContent', '').renderWith(renderStatus));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('dtitle').withTitle('标题')
			.withOption('sDefaultContent', ''));
	if (acttype == "LY") {
		$scope.dtColumns.push(DTColumnBuilder.newColumn('df1').withTitle('领用人')
				.withOption('sDefaultContent', ''));
	} else if (acttype == "JY") {
		$scope.dtColumns.push(DTColumnBuilder.newColumn('df1').withTitle('借用人')
				.withOption('sDefaultContent', ''));
	} else if (acttype == "ZY") {
		$scope.dtColumns.push(DTColumnBuilder.newColumn('df1').withTitle('转移人')
				.withOption('sDefaultContent', ''));
	}
	$scope.dtColumns.push(DTColumnBuilder.newColumn('df2').withTitle('退回时间')
			.withOption('sDefaultContent', ''));

	$scope.dtColumns.push(DTColumnBuilder.newColumn('dtotal').withTitle('总数量')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('df10').withTitle('操作人')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('createTime').withTitle(
			'创建时间').withOption('sDefaultContent', ''));

	// $scope.dtColumns.push(DTColumnBuilder.newColumn('id').withTitle('审批流程')
	// .withOption('sDefaultContent', '').renderWith(renderSpReview));
	$scope.query = function() {
		flush();
	}

	var actbtn = "操作";
	if (acttype == "LY") {
		actbtn = "领用";
	} else if (acttype == "JY") {
		actbtn = "借用";
	} else if (acttype == "ZY") {
		actbtn = "转移";
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
					template : ' <button ng-click="save(0)" class="btn btn-sm btn-primary" type="submit">'
							+ actbtn + '</button>'
				},
				{
					id : "btn2",
					label : "",
					type : "btn",
					show : false,
					priv : "act1",
					template : ' <button ng-click="ss()" class="btn btn-sm  btn-primary" type="submit">送审</button>'
				},
//				{
//					id : "btn2",
//					label : "",
//					type : "btn",
//					show : false,
//					priv : "act2",
//					template : ' <button ng-click="back()" class="btn btn-sm  btn-primary" type="submit">归还</button>'
//				},
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
			id : "2",
			label : "开始时间",
			type : "datetime",
			time : moment().subtract(60, "days"),
			show : true,
		}, {
			id : "2",
			label : "结束时间",
			type : "datetime",
			time : moment().add(1, "days"),
			show : true,
		}, {
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

		if ($scope.meta.tools[1].time - $scope.meta.tools[0].time >= 0) {
		} else {
			notify({
				message : "请选择正确的时间范围"
			});
			return;
		}
		ps.sdate = $scope.meta.tools[0].time.format('YYYY-MM-DD');
		ps.edate = $scope.meta.tools[1].time.format('YYYY-MM-DD');
		ps.search = $scope.meta.tools[0].ct;
		ps.type = acttype;
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
	flush();

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
		ps.acttype = acttype;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_zcActionDtl.html',
			controller : modalzcActionDtlCtl,
			size : 'blg',
			resolve : { // 调用控制器与modal控制器中传递值
				meta : function() {
					return ps;
				},
				pagetype : function() {
					return "query";
				},
				task : function() {
					return "";
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
		ps.acttype = acttype;
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

	$scope.ss = function() {

		var item = getSelectRow();
		if (angular.isDefined(item)) {
			item.tplid = acttype;
			var modalInstance = $uibModal.open({
				backdrop : true,
				templateUrl : 'views/flow/modal_flowselect.html',
				controller : chosenProcessCtl,
				size : 'blg',
				resolve : { // 调用控制器与modal控制器中传递值
					meta : function() {
						return item;
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

	$scope.back = function() {

	}

};

app.register.controller('zcactionCtl', zcactionCtl);