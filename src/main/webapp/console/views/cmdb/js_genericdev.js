function genericdevCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
		$log, notify, $scope, $http, $rootScope, $uibModal, $window, $state) {
	var gclass_id = $state.router.globals.current.data.classid;
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(100)
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

	$scope.dtInstance = {}
	$scope.selectCheckBoxAll = function(selected) {
		if (selected) {
			$scope.dtInstance.DataTable.rows().select();
		} else {
			$scope.dtInstance.DataTable.rows().deselect();
		}
	}

	var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
	$scope.dtColumns = [];
	$scope.dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
	'select-checkbox checkbox_center').renderWith(function() {
		return ""
	}));
	$scope.dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('编号').withOption(
			'sDefaultContent', '').withOption("width", '30'));
	// 输入判断
	if (angular.isDefined($state.router.globals.current.data.input_type)) {
		$scope.dtColumns.push(DTColumnBuilder.newColumn('typestr').withTitle('小类').withOption(
				 'sDefaultContent', '').withOption("width", '30'));
	}
	$scope.dtColumns.push(DTColumnBuilder.newColumn('brandstr').withTitle('品牌').withOption(
			 'sDefaultContent', '').withOption('width', '30'));			 
	$scope.dtColumns.push( DTColumnBuilder.newColumn('name').withTitle('型号').withOption(
			 'sDefaultContent', '').withOption('width', '50')
			 .renderWith(renderName));		
	$scope.dtColumns.push( DTColumnBuilder.newColumn('locstr').withTitle('位置').withOption(
			 'sDefaultContent', '').withOption('width', '30'));		
	$scope.dtColumns.push( DTColumnBuilder.newColumn('recyclestr').withTitle('资产状态').withOption(
			 'sDefaultContent', '').withOption('width', '30'));		
	$scope.dtColumns.push( DTColumnBuilder.newColumn('wbstr').withTitle('维保状态').withOption(
			 'sDefaultContent', '').withOption('width', '30').renderWith(renderWb));		
	$scope.dtColumns.push( DTColumnBuilder.newColumn('envstr').withTitle('运行环境').withOption(
			 'sDefaultContent', '').withOption('width', '30'));	
	$scope.dtColumns.push(DTColumnBuilder.newColumn('riskstr').withTitle('风险等级').withOption(
			 'sDefaultContent', '').withOption('width', '30'));	
	$scope.dtColumns.push(  DTColumnBuilder.newColumn('uuid').withTitle('机柜').withOption(
			 'sDefaultContent', '').renderWith(renderJg));	
	$scope.dtColumns.push(  DTColumnBuilder.newColumn('sn').withTitle('序列号').withOption(
			 'sDefaultContent', ''));	
	$scope.dtColumns.push( DTColumnBuilder.newColumn('buy_timestr').withTitle('采购时间')
			 .withOption('sDefaultContent', ''));	
	$scope.dtColumns.push(  DTColumnBuilder.newColumn('wbout_datestr').withTitle('脱保时间')
			 .withOption('sDefaultContent', ''));	
	$scope.dtColumns.push(   DTColumnBuilder.newColumn('wb_autostr').withTitle('脱保计算')
			 .withOption('sDefaultContent', ''));	
	$scope.dtColumns.push(  DTColumnBuilder.newColumn('confdesc').withTitle('配置描述').withOption(
			 'sDefaultContent', ''));	
	$scope.dtColumns.push(DTColumnBuilder.newColumn('changestate').withTitle('复核状态')
			 .withOption('sDefaultContent', '').renderWith(renderReview));	
				
				
 

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
					template : ' <button ng-click="filedown()" class="btn btn-sm btn-primary" type="submit">导出</button>'
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
	var dicts = "devbrand,devrisk,devenv,devrecycle,devwb,devdc,devrack";
	
	// 判断输入框
	if (angular.isDefined($state.router.globals.current.data.input_type)) {
		dicts = dicts + "," + $state.router.globals.current.data.input_type;
	}
	$http
			.post($rootScope.project + "/api/base/queryDictFast.do", {
				dicts : dicts,
				parts : "Y",
				partusers : "Y"
			})
			.success(
					function(res) {
						if (res.success) {
							gdicts = res.data;
							// 判断输入框
							if (angular
									.isDefined($state.router.globals.current.data.input_type)) {
								gdicts.stype = gdicts[$state.router.globals.current.data.input_type];
							} else {
								gdicts.stype = [];
							}
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

							var items = [ ]; 
							items.push({
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
							});
							
							if (angular
									.isDefined($state.router.globals.current.data.input_type)) {
								items.push({
									type : "select",
									disabled : "true",
									label : "资产类型",
									need : false,
									disable_search : "true",
									dataOpt : "typeOpt",
									dataSel : "typeSel"
								});
							}
							items.push( {
								type : "select",
								disabled : "false",
								label : "资产品牌",
								need : false,
								disable_search : "true",
								dataOpt : "pinpOpt",
								dataSel : "pinpSel"
							});
							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : true,
								maxlength : "50",
								placeholder : "请输型号",
								label : "资产型号",
								need : true,
								name : 'model',
								ng_model : "model"
							});
							items.push({
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
							});						
							items.push( {
								type : "select",
								disabled : "false",
								label : "风险等级",
								need : false,
								disable_search : "true",
								dataOpt : "riskOpt",
								dataSel : "riskSel"
							});
							
							items.push( {
								type : "select",
								disabled : "false",
								label : "脱保计算",
								need : false,
								disable_search : "true",
								dataOpt : "tbOpt",
								dataSel : "tbSel"
							});
							items.push( {
								type : "datetime",
								disabled : "false",
								label : "脱保时间",
								need : false,
								ng_model : "wboutdate"
							});
							items.push( {
								type : "select",
								disabled : "false",
								label : "维保状态",
								false : true,
								disable_search : "true",
								dataOpt : "wbOpt",
								dataSel : "wbSel"
							});
							
							items.push( {
								type : "select",
								disabled : "false",
								label : "设备状态",
								need : false,
								disable_search : "true",
								dataOpt : "statusOpt",
								dataSel : "statusSel"
							});
							items.push( {
								type : "select",
								disabled : "false",
								label : "设备位置",
								need : false,
								disable_search : "true",
								dataOpt : "locOpt",
								dataSel : "locSel"
							});
							items.push({
								type : "select",
								disabled : "false",
								label : "机柜号",
								need : false,
								disable_search : "true",
								dataOpt : "jgOpt",
								dataSel : "jgSel"
							});
							items.push({
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
							});
							items.push({
								type : "datetime",
								disabled : "false",
								label : "采购时间",
								false : true,
								ng_model : "buytime"
							});
							items.push({
								type : "input",
								disabled : "false",
								sub_type : "number",
								required : false,
								maxlength : "30",
								placeholder : "请输入采购价格",
								label : "采购价格",
								need : false,
								name : 'buy_price',
								ng_model : "buy_price"
							});
							items.push({
								type : "select",
								disabled : "false",
								label : "使用部门",
								need : false,
								disable_search : "false",
								dataOpt : "partOpt",
								dataSel : "partSel"
							});
							items.push({
								type : "select",
								disabled : "false",
								label : "使用人",
								need : false,
								disable_search : "false",
								dataOpt : "usedunameOpt",
								dataSel : "usedunameSel"
							});
							items.push( {
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
							});
							items.push({
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
							});
						 

							var bt = moment().subtract(1, "days");
							var tbtime = moment();

							if (angular.isDefined(res.data)
									&& angular.isDefined(res.data.data)
									&& angular
											.isDefined(res.data.data.buy_timestr)) {
								bt = moment(res.data.data.buy_timestr);
							}
							if (angular.isDefined(res.data)
									&& angular.isDefined(res.data.data)
									&& angular
											.isDefined(res.data.data.wbout_datestr)) {
								tbtime = moment(res.data.data.wbout_datestr);
							}

							meta = {
								class_id : gclass_id,
								footer_hide : false,
								title : "资产-"+$state.router.globals.current.data.pageTitle,
								item : {},
								buytime : bt,
								wboutdate : tbtime,
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
								tbOpt : [ {
									id : "1",
									name : "自动计算"
								}, {
									id : "0",
									name : "手工"
								} ],
								tbSel : "",
								envSel : "",
								jgOpt : [],
								jgSel : "",
								riskOpt : [],
								riskSel : "",
								items : items,
								sure : function(modalInstance, modal_meta) {
									// 判断输入框
									if (angular
											.isDefined($state.router.globals.current.data.input_type)) {
										modal_meta.meta.item.type = modal_meta.meta.typeSel.dict_item_id;
									}
									modal_meta.meta.item.part_id = modal_meta.meta.partSel.partid;
									modal_meta.meta.item.used_userid = modal_meta.meta.usedunameSel.user_id;
									modal_meta.meta.item.class_id = gclass_id;
									modal_meta.meta.item.env = modal_meta.meta.envSel.dict_item_id;
									modal_meta.meta.item.recycle = modal_meta.meta.statusSel.dict_item_id;
									modal_meta.meta.item.brand = modal_meta.meta.pinpSel.dict_item_id;
									modal_meta.meta.item.wb = modal_meta.meta.wbSel.dict_item_id;
									modal_meta.meta.item.loc = modal_meta.meta.locSel.dict_item_id;
									modal_meta.meta.item.risk = modal_meta.meta.riskSel.dict_item_id;
									modal_meta.meta.item.rack = modal_meta.meta.jgSel.dict_item_id;
									modal_meta.meta.item.buy_time_f = modal_meta.meta.buytime
											.format('YYYY-MM-DD');

									modal_meta.meta.item.wbout_date_f = modal_meta.meta.wboutdate
											.format('YYYY-MM-DD');
									modal_meta.meta.item.wb_auto = modal_meta.meta.tbSel.id;
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

app.register.controller('genericdevCtl', genericdevCtl);