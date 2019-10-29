var gclass_id = "firewall";
function firewallCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
		$log, notify, $scope, $http, $rootScope, $uibModal, $window) {
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(50)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", true).withOption('scrollY', '600px')
			.withOption('scrollX', true).withOption('bAutoWidth', true)
			.withOption('scrollCollapse', true).withOption('paging', true)
			.withFixedColumns({
				leftColumns : 0,
				rightColumns : 0
			}).withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('aaData',
					$scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withOption(
					'headerCallback',
					function(header) {
						if ((!angular.isDefined($scope.headerCompiled))
								|| $scope.headerCompiled) {
							// Use this headerCompiled field to only compile
							// header once
							$scope.headerCompiled = true;
							$compile(angular.element(header).contents())
									($scope);
						}
					}).withOption("select", {
				style : 'multi',
				selector : 'td:first-child'
			});

	$scope.dtInstance = {}

	function renderName(data, type, full) {

		var html = full.model;
		return html;

	}

	function renderJg(data, type, full) {
		var html = full.rackstr + "-" + full.frame;
		return html;
	}

	function renderReview(data, type, full) {
		if (data == "reviewed") {
			return "已复核"
		} else {
			return "未复核"
		}
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
			DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
					'select-checkbox checkbox_center').renderWith(function() {
				return ""
			}),
			DTColumnBuilder.newColumn('uuid').withTitle('编号').withOption(
					'sDefaultContent', '').withOption("width", '30'),
			DTColumnBuilder.newColumn('name').withTitle('型号').withOption(
					'sDefaultContent', '').withOption('width', '50')
					.renderWith(renderName),
			DTColumnBuilder.newColumn('brandstr').withTitle('品牌').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('locstr').withTitle('位置').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('recyclestr').withTitle('状态').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('wbstr').withTitle('维保').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('envstr').withTitle('运行环境').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('riskstr').withTitle('风险等级').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('confdesc').withTitle('配置描述').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('uuid').withTitle('机柜').withOption(
					'sDefaultContent', '').renderWith(renderJg),
			// DTColumnBuilder.newColumn('typestr').withTitle('小类').withOption(
			// 'sDefaultContent', ''),
			DTColumnBuilder.newColumn('sn').withTitle('序列号').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('buy_timestr').withTitle('采购时间')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('changestate').withTitle('复核状态')
					.withOption('sDefaultContent', '').renderWith(renderReview),
			// DTColumnBuilder.newColumn('create_username').withTitle('录入人')
			// .withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('update_username').withTitle('更新人')
					.withOption('sDefaultContent', '')
	// ,
	// DTColumnBuilder.newColumn('review_username').withTitle('复核人')
	// .withOption('sDefaultContent', '')
	]

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
					template : ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">搜索</button>'
				},
				{
					id : "btn2",
					label : "",
					type : "btn",
					template : ' <button ng-click="save(0)" class="btn btn-sm btn-primary" type="submit">新增</button>'
				},
				{
					id : "btn2",
					label : "",
					type : "btn",
					template : ' <button ng-click="save(1)" class="btn btn-sm btn-primary" type="submit">修改</button>'
				},

				{
					id : "btn2",
					label : "",
					type : "btn",
					template : ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详情</button>'
				},
				{
					id : "btn2",
					label : "",
					type : "btn",
					template : ' <button ng-click="del()" class="btn btn-sm btn-primary" type="submit">删除</button>'
				},
				{
					id : "btn3",
					label : "",
					type : "btn",
					template : ' <button ng-click="filedown()" class="btn btn-sm btn-primary" type="submit">下载</button>'
				} ],
		tools : [ {
			id : "select",
			label : "位置",
			type : "select",
			disablesearch : true,
			dataOpt : [],
			dataSel : ""
		}, {
			id : "select",
			label : "环境",
			type : "select",
			disablesearch : true,
			dataOpt : [],
			dataSel : ""
		}, {
			id : "select",
			label : "维保",
			type : "select",
			disablesearch : true,
			dataOpt : [],
			dataSel : ""
		}, {
			id : "select",
			label : "状态",
			type : "select",
			disablesearch : true,
			dataOpt : [],
			dataSel : ""
		}, {
			id : "input",
			label : "内容",
			placeholder : "输入型号、编号、序列号",
			type : "input",
			ct : ""
		} ]
	}
	$scope.meta = meta;

	function flush() {
		var ps = {}
		ps.id = gclass_id;
		ps.loc = $scope.meta.tools[0].dataSel.dict_item_id;
		ps.env = $scope.meta.tools[1].dataSel.dict_item_id;
		ps.wb = $scope.meta.tools[2].dataSel.dict_item_id;
		ps.recycle = $scope.meta.tools[3].dataSel.dict_item_id;
		ps.search = $scope.meta.tools[4].ct;
		$http.post($rootScope.project + "/api/base/queryResAllByClass.do", ps)
				.success(function(res) {
					if (res.success) {
						$scope.dtOptions.aaData = res.data;
					} else {
						notify({
							message : res.message
						});
					}
				})
	}

	$scope.filedown = function() {
		var ps = {}
		ps.id = gclass_id;
		ps.loc = $scope.meta.tools[0].dataSel.dict_item_id;
		ps.env = $scope.meta.tools[1].dataSel.dict_item_id;
		ps.wb = $scope.meta.tools[2].dataSel.dict_item_id;
		ps.recycle = $scope.meta.tools[3].dataSel.dict_item_id;
		ps.search = $scope.meta.tools[4].ct;
		$window.open($rootScope.project
				+ "/api/base/res/exportServerData.do?id=" + ps.id + "&loc="
				+ ps.loc + "&env=" + ps.env + "&wb=" + ps.wb + "&recycle="
				+ ps.recycle + "&search=" + ps.search);
	}
	var gdicts = {};
	$http
			.post(
					$rootScope.project + "/api/base/queryDictFast.do",
					{
						dicts : "devbrand,devrisk,devenv,devrecycle,devwb,devdc,devservertype,devrack",
						parts : "Y",
						partusers : "Y"
					}).success(function(res) {
				if (res.success) {
					gdicts = res.data;

					// 填充行数据
					var tenv = [];
					angular.copy(gdicts.devenv, tenv);

					var twb = [];
					angular.copy(gdicts.devwb, twb);

					var tloc = [];
					angular.copy(gdicts.devdc, tloc);

					var trecycle = [];
					angular.copy(gdicts.devrecycle, trecycle);

					var parts = [];
					angular.copy(gdicts.parts, parts);

					var partusers = [];
					angular.copy(gdicts.partusers, partusers);

					gdicts.parts.unshift({
						partid : "none",
						name : "未设置"
					});

					gdicts.partusers.unshift({
						user_id : "none",
						name : "未设置"
					});

					$scope.meta.tools[0].dataOpt = tloc;
					$scope.meta.tools[0].dataSel = tloc[0];

					tloc.unshift({
						dict_item_id : "all",
						name : "全部"
					});
					$scope.meta.tools[0].dataOpt = tloc;
					$scope.meta.tools[0].dataSel = tloc[0];

					tenv.unshift({
						dict_item_id : "all",
						name : "全部"
					});
					$scope.meta.tools[1].dataOpt = tenv;
					$scope.meta.tools[1].dataSel = tenv[0];

					twb.unshift({
						dict_item_id : "all",
						name : "全部"
					});
					$scope.meta.tools[2].dataOpt = twb;
					$scope.meta.tools[2].dataSel = twb[0];

					trecycle.unshift({
						dict_item_id : "all",
						name : "全部"
					});
					$scope.meta.tools[3].dataOpt = trecycle;
					$scope.meta.tools[3].dataSel = trecycle[0];

					flush();
				} else {
					notify({
						message : res.message
					});
				}
			})

	$scope.del = function() {

		var selrow = getSelectRow();
		if (angular.isDefined(selrow)) {
			var id = selrow.id;
			$confirm({
				text : '是否删除?'
			}).then(
					function() {
						$http.post(
								$rootScope.project
										+ "/api/base/res/deleteById.do", {
									id : id
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

	function loadOpt(modal_meta, gdicts) {
		// 品牌
		var item = modal_meta.meta.item;
		console.log("loadOpt", item)
		modal_meta.meta.pinpOpt = gdicts.devbrand;
		if (modal_meta.meta.pinpOpt.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.brand)) {
				for (var i = 0; i < gdicts.devbrand.length; i++) {
					console.log(modal_meta.meta.pinpOpt[i].dict_item_id + "--"
							+ item.brand)
					if (modal_meta.meta.pinpOpt[i].dict_item_id == item.brand) {
						modal_meta.meta.pinpSel = modal_meta.meta.pinpOpt[i];
					}
				}
			} else {
				if (gdicts.devbrand.length > 0) {
					modal_meta.meta.pinpSel = modal_meta.meta.pinpOpt[0];
				}
			}
		}

		// 部门
		modal_meta.meta.partOpt = gdicts.parts;
		if (gdicts.parts.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.part_id)) {
				for (var i = 0; i < gdicts.parts.length; i++) {
					if (gdicts.parts[i].partid == item.part_id) {
						modal_meta.meta.partSel = gdicts.parts[i];
					}
				}
			} else {
				if (gdicts.parts.length > 0) {
					modal_meta.meta.partSel = gdicts.parts[0];
				}
			}
		}

		// 使用人
		modal_meta.meta.usedunameOpt = gdicts.partusers;
		if (gdicts.partusers.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.used_userid)) {
				for (var i = 0; i < gdicts.partusers.length; i++) {
					if (gdicts.partusers[i].user_id == item.used_userid) {
						modal_meta.meta.usedunameSel = gdicts.partusers[i];
					}
				}
			} else {
				if (gdicts.partusers.length > 0) {
					modal_meta.meta.usedunameSel = gdicts.partusers[0];
				}
			}
		}

		// 等级
		modal_meta.meta.riskOpt = gdicts.devrisk;
		if (gdicts.devrisk.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.risk)) {
				for (var i = 0; i < gdicts.devrisk.length; i++) {
					if (gdicts.devrisk[i].dict_item_id == item.risk) {
						modal_meta.meta.riskSel = gdicts.devrisk[i];
					}
				}
			} else {
				if (gdicts.devrisk.length > 0) {
					modal_meta.meta.riskSel = gdicts.devrisk[0];
				}
			}
		}

		// 环境
		modal_meta.meta.envOpt = gdicts.devenv;
		if (gdicts.devenv.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.env)) {
				for (var i = 0; i < gdicts.devenv.length; i++) {
					if (gdicts.devenv[i].dict_item_id == item.env) {
						modal_meta.meta.envSel = gdicts.devenv[i];
					}
				}
			} else {
				if (gdicts.devenv.length > 0) {
					modal_meta.meta.envSel = gdicts.devenv[0];

				}
			}
		}

		// 状态
		modal_meta.meta.statusOpt = gdicts.devrecycle;
		if (gdicts.devrecycle.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.recycle)) {
				for (var i = 0; i < gdicts.devrecycle.length; i++) {
					if (gdicts.devrecycle[i].dict_item_id == item.recycle) {
						modal_meta.meta.statusSel = gdicts.devrecycle[i];
					}
				}
			} else {
				if (gdicts.devrecycle.length > 0) {
					modal_meta.meta.statusSel = gdicts.devrecycle[0];
				}
			}
		}

		// 维保
		modal_meta.meta.wbOpt = gdicts.devwb;
		if (gdicts.devwb.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.wb)) {
				for (var i = 0; i < gdicts.devwb.length; i++) {
					if (gdicts.devwb[i].dict_item_id == item.wb) {
						modal_meta.meta.wbSel = gdicts.devwb[i];
					}
				}
			} else {
				if (gdicts.devwb.length > 0) {
					modal_meta.meta.wbSel = gdicts.devwb[0];
				}
			}
		}

		// 位置
		modal_meta.meta.locOpt = gdicts.devdc;
		if (gdicts.devdc.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.loc)) {
				for (var i = 0; i < gdicts.devdc.length; i++) {
					if (gdicts.devdc[i].dict_item_id == item.loc) {
						modal_meta.meta.locSel = gdicts.devdc[i];
					}
				}
			} else {
				if (gdicts.devdc.length > 0) {
					modal_meta.meta.locSel = gdicts.devdc[0];
				}
			}
		}

		// 类型
		modal_meta.meta.typeOpt = gdicts.devservertype;
		if (gdicts.devservertype.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.type)) {
				for (var i = 0; i < gdicts.devservertype.length; i++) {
					if (gdicts.devservertype[i].dict_item_id == item.type) {
						modal_meta.meta.typeSel = gdicts.devservertype[i];
					}
				}
			} else {
				if (gdicts.devservertype.length > 0) {
					modal_meta.meta.typeSel = gdicts.devservertype[0];
				}
			}
		}

		// 机柜
		modal_meta.meta.jgOpt = gdicts.devrack;
		if (gdicts.devrack.length > 0) {
			if (angular.isDefined(item) && angular.isDefined(item.rack)) {
				for (var i = 0; i < gdicts.devrack.length; i++) {
					if (gdicts.devrack[i].dict_item_id == item.rack) {
						modal_meta.meta.jgSel = gdicts.devrack[i];
					}
				}
			} else {
				if (gdicts.devrack.length > 0) {
					modal_meta.meta.jgSel = gdicts.devrack[0];
				}
			}
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

	// //////////////////////////save/////////////////////
	$scope.save = function(type) {
		var id;
		if (type == 1) {
			var selrow = getSelectRow();
			if (angular.isDefined(selrow)) {
				id = selrow.id;
			} else {
				return;
			}
		}
		$http
				.post($rootScope.project + "/api/base/queryResAllById.do", {
					id : id,
					classId : gclass_id
				})
				.success(
						function(res) {
							console.log(res.data);
							if (!res.success) {
								notify({
									message : res.message
								});
								return;
							}
							var meta = {};

							var items = [ {
								type : "input",
								disabled : "true",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "",
								label : "资产编号",
								need : false,
								name : 'uuid',
								ng_model : "uuid"
							}, 
							
//							{
//								type : "select",
//								disabled : "false",
//								label : "类型",
//								need : true,
//								disable_search : "true",
//								dataOpt : "typeOpt",
//								dataSel : "typeSel"
//							},
							// {
							// type : "input",
							// disabled : "false",
							// sub_type : "text",
							// required : true,
							// maxlength : "50",
							// placeholder : "请输入名称",
							// label : "名称",
							// need : true,
							// name : 'name',
							// ng_model : "name"
							// },
							{
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : true,
								maxlength : "50",
								placeholder : "请输型号",
								label : "型号",
								need : true,
								name : 'model',
								ng_model : "model"
							}, {
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : true,
								maxlength : "50",
								placeholder : "请输入序列号",
								label : "序列号",
								need : true,
								name : 'sn',
								ng_model : "sn"
							}, {
								type : "datetime",
								disabled : "false",
								label : "采购时间",
								need : true,
								ng_model : "buytime"
							}, {
								type : "select",
								disabled : "false",
								label : "维保",
								need : true,
								disable_search : "true",
								dataOpt : "wbOpt",
								dataSel : "wbSel"
							}, {
								type : "select",
								disabled : "false",
								label : "风险等级",
								need : false,
								disable_search : "true",
								dataOpt : "riskOpt",
								dataSel : "riskSel"
							}, {
								type : "select",
								disabled : "false",
								label : "设备状态",
								need : true,
								disable_search : "true",
								dataOpt : "statusOpt",
								dataSel : "statusSel"
							}, {
								type : "select",
								disabled : "false",
								label : "运行环境",
								need : true,
								disable_search : "true",
								dataOpt : "envOpt",
								dataSel : "envSel"
							}, {
								type : "select",
								disabled : "false",
								label : "品牌",
								need : false,
								disable_search : "true",
								dataOpt : "pinpOpt",
								dataSel : "pinpSel"
							}, {
								type : "select",
								disabled : "false",
								label : "设备位置",
								need : false,
								disable_search : "true",
								dataOpt : "locOpt",
								dataSel : "locSel"
							}, {
								type : "select",
								disabled : "false",
								label : "机柜号",
								need : false,
								disable_search : "true",
								dataOpt : "jgOpt",
								dataSel : "jgSel"
							}, {
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入",
								label : "机架",
								need : false,
								name : 'frame',
								ng_model : "frame"
							}, {
								type : "input",
								disabled : "false",
								sub_type : "number",
								required : false,
								maxlength : "30",
								placeholder : "请输入采购价",
								label : "采购价",
								need : false,
								name : 'buy_price',
								ng_model : "buy_price"
							}, {
								type : "select",
								disabled : "false",
								label : "使用部门",
								need : false,
								disable_search : "false",
								dataOpt : "partOpt",
								dataSel : "partSel"
							}, {
								type : "select",
								disabled : "false",
								label : "使用人",
								need : false,
								disable_search : "false",
								dataOpt : "usedunameOpt",
								dataSel : "usedunameSel"
							}, {
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "配置描述",
								label : "请输入配置描述",
								need : false,
								name : 'confdesc',
								ng_model : "confdesc"
							}, {
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入备注",
								label : "备注",
								need : false,
								name : 'mark',
								ng_model : "mark"
							}

							];

							var bt = moment().subtract(1, "days");

							meta = {
								class_id : gclass_id,
								footer_hide : false,
								title : "基础设施",
								item : {},
								buytime : bt,
								statusOpt : [],
								statusSel : "",
								pinpOpt : [],
								pinpSel : "",
								headuserOpt : [],
								headuserSel : "",
								partOpt : [],
								partSel : "",
								usedunameOpt : [],
								usedunameSel : "",
								locOpt : [],
								locSel : "",
								wbOpt : [],
								wbSel : "",
								envOpt : [],
								envSel : "",
								jgOpt : [],
								jgSel : "",
								riskOpt : [],
								riskSel : "",
								items : items,
								sure : function(modalInstance, modal_meta) {
									// 返回接口
									console.log('sure', modal_meta.meta)
									modal_meta.meta.item.part_id = modal_meta.meta.partSel.partid;
									modal_meta.meta.item.used_userid = modal_meta.meta.usedunameSel.user_id;
									modal_meta.meta.item.class_id = gclass_id;
									modal_meta.meta.item.env = modal_meta.meta.envSel.dict_item_id;
									modal_meta.meta.item.recycle = modal_meta.meta.statusSel.dict_item_id;
									modal_meta.meta.item.brand = modal_meta.meta.pinpSel.dict_item_id;
									modal_meta.meta.item.wb = modal_meta.meta.wbSel.dict_item_id;
									modal_meta.meta.item.loc = modal_meta.meta.locSel.dict_item_id;
									modal_meta.meta.item.risk = modal_meta.meta.riskSel.dict_item_id;
									modal_meta.meta.item.type = modal_meta.meta.typeSel.dict_item_id;
									modal_meta.meta.item.rack = modal_meta.meta.jgSel.dict_item_id;
									modal_meta.meta.item.buy_time = modal_meta.meta.buytime
											.format('YYYY-MM-DD');
									console.log('sure set', modal_meta.meta)

									// 动态参数
									if (angular.isDefined(modal_meta.meta.attr)
											&& modal_meta.meta.attr.length > 0) {
										for (var j = 0; j < modal_meta.meta.attr.length; j++) {
											var code = modal_meta.meta.attr[j].attr_code;
											modal_meta.meta.attr[j].attr_value = modal_meta.meta.item[modal_meta.meta.attr[j].attr_code];
										}
									}
									modal_meta.meta.item.attrvals = angular
											.toJson(modal_meta.meta.attr);
									console.log("par:", modal_meta.meta.item);
									$http
											.post(
													$rootScope.project
															+ "/api/base/addResCustom.do",
													modal_meta.meta.item)
											.success(function(res) {
												if (res.success) {
													modalInstance.close("OK");
												} else {
													notify({
														message : res.message
													});
												}
											});

								},
								init : function(modal_meta) {
									var tt = {};
									angular.copy(gdicts, tt)
									loadOpt(modal_meta, tt);

								}
							}

							//
							if (angular.isDefined(res.data.data)
									&& angular.isDefined(res.data.data.id)) {
								meta.item = res.data.data;
								// 填充其他数据
							}

							// 补充属性数据
							if (angular.isDefined(res.data.attr)
									&& res.data.attr.length > 0) {
								meta.attr = res.data.attr;
								for (var i = 0; i < res.data.attr.length; i++) {
									if (i == 0) {
										var e = {
											type : "dashed"
										}
										items.push(e);
									}

									var attr_type = res.data.attr[i].attr_type;
									if (attr_type == "string") {
										var e = {
											type : "input",
											disabled : "false",
											sub_type : "text",
											required : true,
											maxlength : "100",
											placeholder : "请输入内容",
											label : res.data.attr[i].attr_name,
											need : false,
											name : res.data.attr[i].attr_code,
											ng_model : res.data.attr[i].attr_code,
										}
										items.push(e);
									} else if (attr_type == "number") {
										var e = {
											type : "input",
											disabled : "false",
											sub_type : "number",
											required : true,
											maxlength : "20",
											placeholder : "请输入内容",
											label : res.data.attr[i].attr_name,
											need : false,
											name : res.data.attr[i].attr_code,
											ng_model : res.data.attr[i].attr_code,
										}
										items.push(e);
									}

								}

							}

							console.log(meta);
							// 打开静态框
							var modalInstance = $uibModal
									.open({
										backdrop : true,
										templateUrl : 'views/Template/modal_simpleForm.html',
										controller : modal_simpleFormCtl,
										size : 'lg',
										resolve : { // 调用控制器与modal控制器中传递值
											meta : function() {
												return meta;
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

						})

	}

};

app.register.controller('firewallCtl', firewallCtl);