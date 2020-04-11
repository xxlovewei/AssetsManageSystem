function modalzcActionSPCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal, meta,
		$uibModalInstance, $window, $stateParams,$timeout) {
		console.log(meta);
		var url = $rootScope.project + "uflo/diagram?processKey=" + meta.pk;
		$scope.url = url;
		let vm;
		$timeout(function(){
			var jd=decodeURI(meta.flowform.formct);
			let jsonData  =angular.fromJson(jd);
			console.log(jsonData);
			vm = new Vue({
				el: '#app',
				data: {
					jsonData
				},
				mounted () {
					this.init()
				},
				methods: {
					init(){
						console.log(this);
						console.log(this.jsonData);
					},
					handleSubmit(p) {
						// 通过表单提交按钮触发，获取promise对象
						p().then(res => {
							// 获取数据成功
							alert(JSON.stringify(res))
						})
							.catch(err => {
								console.log(err, '校验失败')
							})
					},
					getData() {
						// 通过函数获取数据
						this.$refs.kfb.getData().then(res => {
							// 获取数据成功
							alert(JSON.stringify(res))
						})
							.catch(err => {
								console.log(err, '校验失败')
							})
					}
				}
			})
		},1000)


	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
	$scope.sure = function() {
						vm.$refs.kfb.getData().then(res => {
							// 获取数据成功
							meta.jsonvalue=JSON.stringify(res);
							meta.ifsp=1;
							$http.post($rootScope.project + "/api/zc/flow/startProcess.do",
									meta).success(function(res) {
									if (res.success) {
										$uibModalInstance.close("OK");
									} else {
										notify({
											message : res.message
										});
									}
								})

						})
							.catch(err => {
								console.log(err, '校验失败')
							})

		//
	}



}
function modalzcActionSaveCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal,$timeout,
		$uibModalInstance, $state, meta) {
	console.log(meta);
	$scope.actmsg =meta.actmsg;
	$scope.data = {};
	$scope.dtOptions=DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
		.withPaginationType('full_numbers').withDisplayLength(100)
		.withOption("ordering", false).withOption("responsive", false)
		.withOption("searching", true).withOption('scrollY', 600)
		.withOption('scrollX', true).withOption('bAutoWidth', true)
		.withOption('scrollCollapse', true).withOption('paging', false)
		.withFixedColumns({
			leftColumns : 0,
			rightColumns : 0
		}).withOption('bStateSave', true).withOption('bProcessing', false)
		.withOption('bFilter', false).withOption('bInfo', false)
		.withOption('serverSide', false).withOption('createdRow', function(row) {
		$compile(angular.element(row).contents())($scope);
	})

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
			resolve : {
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

			$log.log("reason", reason)
		});
	}
	$scope.sure = function() {
		$scope.data.bustype = meta.flowtype;
		if ($scope.dtOptions.aaData.length == 0) {
			alert("请先选择资产");
			return;
		}
		$scope.data.items = angular.toJson($scope.dtOptions.aaData);
		$http.post($rootScope.project + "/api/zc/insertBill.do",
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
	var flowtype = $state.router.globals.current.data.actiontype;
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withDOM('frtlip').withPaginationType('full_numbers')
			.withDisplayLength(100).withOption("ordering", false).withOption(
					"responsive", false).withOption("searching", true)
			.withOption('scrollY', 600).withOption('scrollX', true)
			.withOption('bAutoWidth', true).withOption('scrollCollapse', true)
			.withOption('paging', true).withFixedColumns({
				leftColumns : 0,
				rightColumns : 0
			}).withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('createdRow', function(row) {
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
	$scope.dtColumns.push(DTColumnBuilder.newColumn('busid').withTitle('编号')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('pstatus').withTitle(
			'状态').withOption('sDefaultContent', '').renderWith(renderZCSPStatus));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('ptitle').withTitle('标题')
			.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('pstartusername').withTitle('流程发起人')
		.withOption('sDefaultContent', ''));
	// if (acttype == "LY") {
	// 	$scope.dtColumns.push(DTColumnBuilder.newColumn('df1').withTitle('领用人')
	// 			.withOption('sDefaultContent', ''));
	// } else if (acttype == "JY") {
	// 	$scope.dtColumns.push(DTColumnBuilder.newColumn('df1').withTitle('借用人')
	// 			.withOption('sDefaultContent', ''));
	// } else if (acttype == "ZY") {
	// 	$scope.dtColumns.push(DTColumnBuilder.newColumn('df1').withTitle('转移人')
	// 			.withOption('sDefaultContent', ''));
	// }
	// $scope.dtColumns.push(DTColumnBuilder.newColumn('df2').withTitle('退回时间')
	// 		.withOption('sDefaultContent', ''));
	//
	// $scope.dtColumns.push(DTColumnBuilder.newColumn('dtotal').withTitle('总数量')
	// 		.withOption('sDefaultContent', ''));
	// $scope.dtColumns.push(DTColumnBuilder.newColumn('df10').withTitle('操作人')
	// 		.withOption('sDefaultContent', ''));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('createTime').withTitle(
			'创建时间').withOption('sDefaultContent', ''));

	// $scope.dtColumns.push(DTColumnBuilder.newColumn('id').withTitle('审批流程')
	// .withOption('sDefaultContent', '').renderWith(renderSpReview));
	$scope.query = function() {
		flush();
	}

	var actbtn = "操作";
	if (flowtype == "LY") {
		actbtn = "领用";
	} else if (flowtype == "JY") {
		actbtn = "借用";
	} else if (flowtype == "ZY") {
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
		ps.bustype = flowtype;
		$http.post($rootScope.project + "/api/zc/selectListBills.do",
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
		ps.flowtype = flowtype;


		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_zcActionDtl.html',
			controller : modalzcActionDtlCtl,
			size : 'blg',
			resolve : {
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

			if (result == "OK") {
			}
		}, function(reason) {

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
		ps.flowtype = flowtype;
		if (ps.flowtype == "LY") {
			ps.actmsg = "领用人";
		} else if (ps.flowtype == "JY") {
			ps.actmsg = "借用人";
		} else if (ps.flowtype == "ZY") {
			ps.actmsg = "转移人";
		}
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_zcAction.html',
			controller : modalzcActionSaveCtl,
			size : 'blg',
			resolve : {
				meta : function() {
					return ps;
				}
			}
		});
		modalInstance.result.then(function(result) {

			if (result == "OK") {
				flush();
			}
		}, function(reason) {

			$log.log("reason", reason)
		});



	}

	$scope.ss = function() {
		var item = getSelectRow();
		if (angular.isDefined(item)) {
			item.flowtype = flowtype;
			if( item.pstatus!="submitforapproval"){
				notify({
					message : "该状态不允许送审"
				});
				return;
			}
			if (item.flowtype == "LY") {
				item.flowcode="process_zcly";
			} else if (item.flowtype == "JY") {
				item.flowcode="process_zcjy";
			} else if (item.flowtype == "ZY") {
				item.flowcode="process_zczy";
			}
			$http.post($rootScope.project + "/api/flow/sysProcessSetting/ext/selectByCode.do",
				{code:item.flowcode}).success(function(res) {
				if(!res.success){
					notify({
						message : "未定义流程"
					});
					return ;
				}
				item.flowform=res.data;
				if(!angular.isDefined(res.data.processdefid)){
					notify({
						message : "未定义流程"
					});
				}
				item.processdefid=res.data.processdefid;
				item.pk=res.data.processKey;
				var modalInstance = $uibModal.open({
					backdrop : true,
					templateUrl : 'views/cmdb/modal_zcActionSP.html',
					controller : modalzcActionSPCtl,
					size : 'blg',
					resolve : {
						meta : function() {
							return item;
						}
					}
				});
				modalInstance.result.then(function(result) {

					if (result == "OK") {
						flush();
					}
				}, function(reason) {

					$log.log("reason", reason)
				});
			});
		}
	}
	$scope.back = function() {
	}

};

app.register.controller('zcactionCtl', zcactionCtl);